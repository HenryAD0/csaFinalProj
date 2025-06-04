# Fantasy Basketball Bot

## Overview
The Fantasy Basketball Bot is a Java desktop application designed to assist fantasy basketball managers in optimizing their rosters, evaluating free agents, and making informed draft decisions through statistical analysis and basic machine learning techniques.

## Features
- **Real-time Roster Optimization**: Provides recommendations for roster changes based on current league data and player performance metrics.
- **Free Agent Ranking**: Scores and ranks available players using weighted statistics and recent performance trends.
- **Draft Analyzer**: Compares player projections with historical outcomes to identify potential "busts" and "sleepers" using a basic neural network.
- **Machine Learning Integration**: Utilizes Deeplearning4j to train a neural network on historical fantasy data to predict future fantasy points.

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
│   │   │   │       └── ui
│   │   │   │           ├── RosterOptimizerPanel.java
│   │   │   │           ├── FreeAgentsPanel.java
│   │   │   │           └── DraftAnalyzerPanel.java
│   │   └── resources
│   │       └── application.properties
├── pom.xml
└── README.md
```

## Setup Instructions
1. **Clone the Repository**: 
   ```bash
   git clone <repository-url>
   cd FantasyBasketballBot
   ```

2. **Build the Project**: 
   Use Maven to build the project:
   ```bash
   mvn clean install
   ```

3. **Run the Application**: 
   Execute the main class:
   ```bash
   mvn exec:java -Dexec.mainClass="com.fantasybasketballbot.Main"
   ```

## Dependencies
- Java 11 or higher
- Maven
- Deeplearning4j (for machine learning functionalities)

## Contributing
Contributions are welcome! Please submit a pull request or open an issue for any enhancements or bug fixes.

## License
This project is licensed under the MIT License. See the LICENSE file for details.