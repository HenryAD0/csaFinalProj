import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import java.util.*;

public class PlayerComparisonModel {
    private MultiLayerNetwork model;

    public PlayerComparisonModel() {
        int numInputs = 56;
        int outputNum = 1;

        MultiLayerConfiguration config = new NeuralNetConfiguration.Builder()
                .seed(123)
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
                .updater(new Adam(0.01))
                .list()
                .layer(new DenseLayer.Builder()
                        .nIn(numInputs)
                        .nOut(64)
                        .activation(Activation.RELU)
                        .dropOut(0.2)
                        .build())
                .layer(new DenseLayer.Builder()
                        .nOut(32)
                        .activation(Activation.RELU)
                        .dropOut(0.2)
                        .build())
                .layer(new OutputLayer.Builder(LossFunctions.LossFunction.XENT)
                        .activation(Activation.SIGMOID)
                        .nOut(outputNum)
                        .build())
                .build();

        model = new MultiLayerNetwork(config);
        model.init();
    }

    public void train(List<Player> players, int epochs) {
        Random rand = new Random();
        for (int epoch = 0; epoch < epochs; epoch++) {
            for (int i = 0; i < players.size(); i++) {
                Player p1 = players.get(rand.nextInt(players.size()));
                Player p2 = players.get(rand.nextInt(players.size()));

                if (p1 == p2 || p1.getGames().size() < 6 || p2.getGames().size() < 6) continue;

                INDArray features = createFeatureVector(p1, p2);
                double label = (p1.getGames().get(p1.getGames().size() - 1).getFantasyPoints() >
                        p2.getGames().get(p2.getGames().size() - 1).getFantasyPoints()) ? 1.0 : 0.0;

                INDArray labels = Nd4j.create(new double[]{label}, new int[]{1, 1});
                DataSet ds = new DataSet(features, labels);
                model.fit(ds);
            }
        }
    }

    public double predict(Player p1, Player p2) {
        INDArray input = createFeatureVector(p1, p2);
        return model.output(input).getDouble(0);
    }

    public ArrayList<Player> selectTopPlayers(ArrayList<Player> players, int topN) {
        ArrayList<Player> topPlayers = new ArrayList<>();
        ArrayList<Integer> winCounts = new ArrayList<>(Collections.nCopies(players.size(), 0));

        for (int i = 0; i < players.size(); i++) {
            for (int j = i + 1; j < players.size(); j++) {
                Player p1 = players.get(i);
                Player p2 = players.get(j);
                if (p1.getGames().size() < 6 || p2.getGames().size() < 6) continue;

                double prediction = predict(p1, p2);
                if (prediction > 0.5) {
                    winCounts.set(i, winCounts.get(i) + 1);
                } else {
                    winCounts.set(j, winCounts.get(j) + 1);
                }
            }
        }

        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            indices.add(i);
        }

        indices.sort((i1, i2) -> winCounts.get(i2) - winCounts.get(i1));

        for (int i = 0; i < topN && i < indices.size(); i++) {
            topPlayers.add(players.get(indices.get(i)));
        }

        return topPlayers;
    }

    private INDArray createFeatureVector(Player p1, Player p2) {
        double[] features = new double[56];
        double[] p1Stats = extractStats(p1);
        double[] p2Stats = extractStats(p2);
        System.arraycopy(p1Stats, 0, features, 0, 28);
        System.arraycopy(p2Stats, 0, features, 28, 28);
        return Nd4j.create(features, new int[]{1, 56});
    }

    private double[] extractStats(Player player) {
        List<StatsForGame> games = player.getGames();
        int size = games.size();
        double[] stats = new double[28];

        for (int i = size - 6; i < size - 1; i++) {
            StatsForGame g = games.get(i);
            stats[0] += g.getMinutes();
            stats[1] += g.getFieldGoalsMade();
            stats[2] += g.getFieldGoalsAttempted();
            stats[3] += g.getThreePointersMade();
            stats[4] += g.getThreePointersAttempted();
            stats[5] += g.getFreeThrowsMade();
            stats[6] += g.getFreeThrowsAttempted();
            stats[7] += g.getRebounds();
            stats[8] += g.getAssists();
            stats[9] += g.getSteals();
            stats[10] += g.getBlocks();
            stats[11] += g.getTurnovers();
            stats[12] += g.getPoints();
            stats[13] += g.getFantasyPoints();
        }
        for (int i = 0; i < 14; i++) stats[i] /= 5.0;

        stats[14] = player.getAverageMinutes();
        stats[15] = player.getAverageFieldGoalsMade();
        stats[16] = player.getAverageFieldGoalsAttempted();
        stats[17] = player.getAverageThreePointersMade();
        stats[18] = player.getAverageThreePointersAttempted();
        stats[19] = player.getAverageFreeThrowsMade();
        stats[20] = player.getAverageFreeThrowsAttempted();
        stats[21] = player.getAverageRebounds();
        stats[22] = player.getAverageAssists();
        stats[23] = player.getAverageSteals();
        stats[24] = player.getAverageBlocks();
        stats[25] = player.getAverageTurnovers();
        stats[26] = player.getAveragePoints();
        stats[27] = player.getAverageFantasyPoints();

        return stats;
    }

    public MultiLayerNetwork getModel() {
        return model;
    }
}
