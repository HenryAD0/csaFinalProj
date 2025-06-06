# Fantasy Basketball Bot

## Overview
The Fantasy Basketball Bot is a Java-based command-line application designed to assist fantasy basketball managers in comparing players and predicting their performance in upcoming games using statistical analysis and basic machine learning techniques.

## Features
- **Hardcoded Player Data**:
  - The program uses 20 hardcoded player objects with sample game statistics as the training base for the machine learning model.
- **Machine Learning Integration**:
  - A basic machine learning model is implemented to predict which player will score more fantasy points in the next game based on historical data.
- **Player Comparison**:
  - After training the model, users can select two players from the list of all players and compare them to see which player is predicted to score more fantasy points in the next game.
- **Top Players**:
  - The program displays the top 5 players based on the model's predictions after training.

## Project Structure
```
FantasyBasketballBot
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com
│   │   │   │   └── fantasybasketballbot
│   │   │   │       ├── Main.java
│   │   │   │       ├── model
│   │   │   │       │   ├── Player.java
│   │   │   │       │   ├── StatsForGame.java
│   │   │   │       │   └── Team.java
│   │   │   │       ├── util
│   │   │   │       │   └── CSVReader.java
│   │   │   │       ├── ml
│   │   │   │       │   └── MLModel.java
│   │   └── resources
│   │       └── application.properties
├── pom.xml
└── README.md
```

## Dependencies
- Java 21 or higher
- Maven
- SLF4J (for logging)
- Gson (for JSON file handling)

## Notes on Project Completion
While the project successfully implements basic machine learning functionality and allows users to compare players, several goals were not achieved due to challenges with the machine learning model configuration and integration between classes. These include:
- **Java Swing UI**:
  - The initial plan was to create a fully functional Java Swing-based user interface to make the program more user-friendly. However, due to time constraints and integration issues, the program remains command-line-based, requiring users to type numbers to interact with the program.
- **Roster Optimization**:
  - The program was intended to include a feature for optimizing rosters using a drag-and-drop UI, allowing users to select from a list of potential player picks and their current roster to determine the optimal roster. This feature was not implemented.
- **Saving and Exporting Rosters**:
  - The ability to save and export roster lists locally for future use was planned but not completed.
- **Draft Pick Using ML Model**:
  - A draft pick feature using the machine learning model was planned but not implemented.

## Goals and Future Improvements
Despite the challenges, the project demonstrates the potential for integrating machine learning into fantasy basketball tools. Future improvements could include:
- Implementing a graphical user interface (GUI) using Java Swing or JavaFX.
- Enhancing the machine learning model with more advanced algorithms and frameworks.
- Expanding the dataset to include real-world player statistics for better predictions.
- Adding features like roster optimization, draft pick functionality, and saving/exporting rosters.