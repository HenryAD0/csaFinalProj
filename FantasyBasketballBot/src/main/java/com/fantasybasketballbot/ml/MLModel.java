package com.fantasybasketballbot.ml;


import com.fantasybasketballbot.model.Player;
import com.fantasybasketballbot.model.StatsForGame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class MLModel {
    private static final Logger log = LoggerFactory.getLogger(MLModel.class);
    private static final int NUM_INPUTS = 56;
    private static final int HIDDEN_SIZE = 32;
    private static final double LEARNING_RATE = 0.001;
   
    private double[][] weights1; // Input -> Hidden
    private double[] bias1;
    private double[][] weights2; // Hidden -> Output
    private double[] bias2;
    private final Random rand;


    public MLModel() {
        this.rand = new Random(123);
        initializeModel();
    }


    private void initializeModel() {
        // Initialize weights with Xavier initialization
        double w1Scale = Math.sqrt(2.0 / NUM_INPUTS);
        double w2Scale = Math.sqrt(2.0 / HIDDEN_SIZE);


        weights1 = new double[HIDDEN_SIZE][NUM_INPUTS];
        weights2 = new double[1][HIDDEN_SIZE];
        bias1 = new double[HIDDEN_SIZE];
        bias2 = new double[1];


        // Initialize weights randomly
        for (int i = 0; i < HIDDEN_SIZE; i++) {
            for (int j = 0; j < NUM_INPUTS; j++) {
                weights1[i][j] = (rand.nextDouble() * 2 - 1) * w1Scale;
            }
            bias1[i] = 0;
        }


        for (int i = 0; i < HIDDEN_SIZE; i++) {
            weights2[0][i] = (rand.nextDouble() * 2 - 1) * w2Scale;
        }
        bias2[0] = 0;


        log.info("Model initialized successfully");
    }


    private double sigmoid(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }


    private double[] sigmoid(double[] x) {
        double[] result = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            result[i] = sigmoid(x[i]);
        }
        return result;
    }


    private double[] relu(double[] x) {
        double[] result = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            result[i] = Math.max(0, x[i]);
        }
        return result;
    }


    private double[] reluDerivative(double[] x) {
        double[] result = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            result[i] = x[i] > 0 ? 1 : 0;
        }
        return result;
    }


    // Matrix multiplication: a[m][n] * b[n] = c[m]
    private double[] matrixVectorMultiply(double[][] a, double[] b) {
        double[] result = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = 0;
            for (int j = 0; j < b.length; j++) {
                result[i] += a[i][j] * b[j];
            }
        }
        return result;
    }


    // Vector addition: a[n] + b[n] = c[n]
    private double[] vectorAdd(double[] a, double[] b) {
        double[] result = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = a[i] + b[i];
        }
        return result;
    }


    // Outer product: a[m] * b[n] = c[m][n]
    private double[][] outerProduct(double[] a, double[] b) {
        double[][] result = new double[a.length][b.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                result[i][j] = a[i] * b[j];
            }
        }
        return result;
    }


    // Matrix subtraction: a[m][n] - b[m][n] = c[m][n]
    private double[][] matrixSubtract(double[][] a, double[][] b) {
        double[][] result = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                result[i][j] = a[i][j] - b[i][j];
            }
        }
        return result;
    }


    // Matrix scalar multiplication: a[m][n] * scalar = b[m][n]
    private double[][] matrixScalarMultiply(double[][] a, double scalar) {
        double[][] result = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                result[i][j] = a[i][j] * scalar;
            }
        }
        return result;
    }


    // Vector scalar multiplication: a[n] * scalar = b[n]
    private double[] vectorScalarMultiply(double[] a, double scalar) {
        double[] result = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = a[i] * scalar;
        }
        return result;
    }


    // Element-wise vector multiplication: a[n] * b[n] = c[n]
    private double[] vectorElementwiseMultiply(double[] a, double[] b) {
        double[] result = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = a[i] * b[i];
        }
        return result;
    }


    public void train(List<Player> players, int epochs) {
        if (players == null || players.isEmpty()) {
            throw new IllegalArgumentException("Players list cannot be null or empty");
        }


        // Split into training and validation sets (80-20 split)
        int splitPoint = (int) (players.size() * 0.8);
        List<Player> trainingPlayers = players.subList(0, splitPoint);
        List<Player> validationPlayers = players.subList(splitPoint, players.size());


        log.info("Starting training with {} epochs", epochs);
        double bestValidationLoss = Double.MAX_VALUE;
        int patienceCounter = 0;
        final int PATIENCE = 5;


        for (int epoch = 0; epoch < epochs; epoch++) {
            double epochLoss = trainEpoch(trainingPlayers);
            double validationLoss = validate(validationPlayers);
           
            log.info("Epoch {}: Training Loss = {}, Validation Loss = {}", epoch, epochLoss, validationLoss);


            // Early stopping logic
            if (validationLoss < bestValidationLoss) {
                bestValidationLoss = validationLoss;
                patienceCounter = 0;
                try {
                    saveModel("best_model.bin");
                } catch (IOException e) {
                    log.error("Failed to save best model", e);
                }
            } else {
                patienceCounter++;
                if (patienceCounter >= PATIENCE) {
                    log.info("Early stopping triggered at epoch {}", epoch);
                    break;
                }
            }
        }


        try {
            loadModel("best_model.bin");
        } catch (IOException e) {
            log.error("Failed to load best model", e);
        }
    }


    private double trainEpoch(List<Player> players) {
        double totalLoss = 0;
        int batchCount = 0;


        for (int i = 0; i < players.size(); i++) {
            for (int j = i + 1; j < players.size(); j++) {
                Player p1 = players.get(i);
                Player p2 = players.get(j);


                if (!isValidPlayerPair(p1, p2)) continue;


                double[] features = createFeatureVector(p1, p2);
                double label = determineLabel(p1, p2);


                // Forward pass
                double[] hidden = new double[HIDDEN_SIZE];
                // Input to hidden
                for (int h = 0; h < HIDDEN_SIZE; h++) {
                    double sum = 0;
                    for (int f = 0; f < NUM_INPUTS; f++) {
                        sum += weights1[h][f] * features[f];
                    }
                    hidden[h] = Math.max(0, sum + bias1[h]); // ReLU activation
                }


                // Hidden to output
                double outputSum = 0;
                for (int h = 0; h < HIDDEN_SIZE; h++) {
                    outputSum += weights2[0][h] * hidden[h];
                }
                double output = sigmoid(outputSum + bias2[0]);


                // Compute loss
                double loss = -label * Math.log(Math.max(output, 1e-15)) -
                             (1 - label) * Math.log(Math.max(1 - output, 1e-15));
                totalLoss += loss;


                // Backward pass
                double outputGrad = output - label;


                // Update hidden to output weights
                for (int h = 0; h < HIDDEN_SIZE; h++) {
                    weights2[0][h] -= LEARNING_RATE * outputGrad * hidden[h];
                }
                bias2[0] -= LEARNING_RATE * outputGrad;


                // Update input to hidden weights
                for (int h = 0; h < HIDDEN_SIZE; h++) {
                    double hiddenGrad = outputGrad * weights2[0][h] * (hidden[h] > 0 ? 1 : 0); // ReLU derivative
                    for (int f = 0; f < NUM_INPUTS; f++) {
                        weights1[h][f] -= LEARNING_RATE * hiddenGrad * features[f];
                    }
                    bias1[h] -= LEARNING_RATE * hiddenGrad;
                }


                batchCount++;
            }
        }


        return batchCount > 0 ? totalLoss / batchCount : 0.0;
    }


    private double validate(List<Player> players) {
        double totalLoss = 0;
        int count = 0;


        for (int i = 0; i < players.size(); i++) {
            for (int j = i + 1; j < players.size(); j++) {
                Player p1 = players.get(i);
                Player p2 = players.get(j);


                if (!isValidPlayerPair(p1, p2)) continue;


                double[] features = createFeatureVector(p1, p2);
                double label = determineLabel(p1, p2);
                double prediction = predict(p1, p2);


                double loss = -label * Math.log(prediction) - (1 - label) * Math.log(1 - prediction);
                totalLoss += loss;
                count++;
            }
        }


        return count > 0 ? totalLoss / count : 0.0;
    }


    private boolean isValidPlayerPair(Player p1, Player p2) {
        boolean valid = p1 != p2 &&
               p1.getGames() != null && p2.getGames() != null &&
               !p1.getGames().isEmpty() && !p2.getGames().isEmpty();
        System.out.println("Validating player pair: " + p1.getPlayerName() + " vs " + p2.getPlayerName() + ": " + valid);
        if (!valid) {
            System.out.println("p1 games: " + (p1.getGames() != null ? p1.getGames().size() : "null"));
            System.out.println("p2 games: " + (p2.getGames() != null ? p2.getGames().size() : "null"));
        }
        return valid;
    }


    private double determineLabel(Player p1, Player p2) {
        double p1Points = p1.getGames().get(p1.getGames().size() - 1).getFpts();
        double p2Points = p2.getGames().get(p2.getGames().size() - 1).getFpts();
        return p1Points > p2Points ? 1.0 : 0.0;
    }


    public double predict(Player p1, Player p2) {
        System.out.println("Predicting between " + p1.getPlayerName() + " and " + p2.getPlayerName());
        if (!isValidPlayerPair(p1, p2)) {
            throw new IllegalArgumentException("Invalid player pair for prediction");
        }


        double[] features = createFeatureVector(p1, p2);
        double[] hidden = relu(vectorAdd(matrixVectorMultiply(weights1, features), bias1));
        return sigmoid(vectorAdd(matrixVectorMultiply(weights2, hidden), bias2)[0]);
    }


    public List<Player> selectTopPlayers(List<Player> players, int topN) {
        if (players == null || players.isEmpty()) {
            throw new IllegalArgumentException("Players list cannot be null or empty");
        }
        if (topN <= 0) {
            throw new IllegalArgumentException("topN must be positive");
        }


        Map<Player, Integer> winCounts = new HashMap<>();
        players.forEach(p -> winCounts.put(p, 0));


        for (int i = 0; i < players.size(); i++) {
            for (int j = i + 1; j < players.size(); j++) {
                Player p1 = players.get(i);
                Player p2 = players.get(j);


                if (!isValidPlayerPair(p1, p2)) continue;


                try {
                    double prediction = predict(p1, p2);
                    if (prediction > 0.5) {
                        winCounts.merge(p1, 1, Integer::sum);
                    } else {
                        winCounts.merge(p2, 1, Integer::sum);
                    }
                } catch (Exception e) {
                    log.error("Error predicting match between {} and {}", p1.getPlayerName(), p2.getPlayerName(), e);
                }
            }
        }


        return winCounts.entrySet().stream()
                .sorted(Map.Entry.<Player, Integer>comparingByValue().reversed())
                .limit(topN)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }


    private double[] createFeatureVector(Player p1, Player p2) {
        double[] features = new double[NUM_INPUTS];
        double[] p1Stats = extractStats(p1);
        double[] p2Stats = extractStats(p2);
        System.arraycopy(p1Stats, 0, features, 0, 28);
        System.arraycopy(p2Stats, 0, features, 28, 28);
        return features;
    }


    private double[] extractStats(Player player) {
        List<StatsForGame> games = player.getGames();
        System.out.println("Extracting stats for player: " + player.getPlayerName() + " with " + games.size() + " games");
        double[] stats = new double[28];
       
        // Calculate last 5 games average
        int startIndex = Math.max(0, games.size() - 5);
        System.out.println("Start index: " + startIndex + ", games.size(): " + games.size());
        for (int i = startIndex; i < games.size(); i++) {
            System.out.println("Processing game at index: " + i);
            StatsForGame g = games.get(i);
            updateStats(stats, g, 0);
        }
       
        // Normalize last 5 games stats
        int numGames = games.size() - startIndex;
        System.out.println("Normalizing by " + numGames + " games");
        for (int i = 0; i < 14; i++) {
            stats[i] /= numGames;
        }


        // Add season averages
        ArrayList<Double> seasonAverages = player.getSeasonAVG_2425();
        System.out.println("Season averages size: " + seasonAverages.size());
        if (seasonAverages.size() >= 14) {
            for (int i = 0; i < 14; i++) {
                stats[14 + i] = seasonAverages.get(i);
            }
        }
       
        return stats;
    }


    private void updateStats(double[] stats, StatsForGame g, int offset) {
        stats[offset] += g.getMinutes();
        stats[offset + 1] += g.getFgm();
        stats[offset + 2] += g.getFga();
        stats[offset + 3] += g.getThreePm();
        stats[offset + 4] += g.getThreePm();  // Using made threes as attempted since we don't have attempts
        stats[offset + 5] += g.getFtm();
        stats[offset + 6] += g.getFta();
        stats[offset + 7] += g.getReb();
        stats[offset + 8] += g.getAst();
        stats[offset + 9] += g.getStl();
        stats[offset + 10] += g.getBlk();
        stats[offset + 11] += g.getTo();
        stats[offset + 12] += g.getPts();
        stats[offset + 13] += g.getFpts();
    }


    public void saveModel(String filepath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath))) {
            oos.writeObject(weights1);
            oos.writeObject(bias1);
            oos.writeObject(weights2);
            oos.writeObject(bias2);
            log.info("Model saved to {}", filepath);
        }
    }


    public void loadModel(String filepath) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filepath))) {
            weights1 = (double[][]) ois.readObject();
            bias1 = (double[]) ois.readObject();
            weights2 = (double[][]) ois.readObject();
            bias2 = (double[]) ois.readObject();
            log.info("Model loaded from {}", filepath);
        } catch (ClassNotFoundException e) {
            throw new IOException("Error loading model: " + e.getMessage(), e);
        }
    }
}
