# Fantasy Basketball Bot

## Overview
The Fantasy Basketball Bot is a Java desktop application designed to assist fantasy basketball managers in optimizing their rosters, evaluating free agents, and making informed draft decisions through statistical analysis and basic machine learning techniques.

## Features
- **Home Page**:
  - A simple home page with title text.
  - Buttons to navigate to the "Roster Optimization" and "My Roster" pages.
- **Roster Optimization**:
  - Import players from hardcoded CSV files to create player objects.
  - Select up to 8 players for the current team and additional "roster interests."
  - Analyze the selected players using a placeholder machine learning model to find the optimal roster.
  - Save optimized rosters to a JSON file with user-defined names.
- **Roster Display**:
  - View saved rosters from the JSON file.
  - Buttons to add or optimize future interests, navigating back to the roster optimization page.
- **Machine Learning Integration**:
  - Placeholder for Deeplearning4j-based analysis of player data to optimize rosters.

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
│   │   │   │           ├── HomePagePanel.java
│   │   │   │           ├── RosterOptimizationPanel.java
│   │   │   │           └── RosterDisplayPanel.java
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
- Java 21 or higher
- Maven
- Deeplearning4j (for machine learning functionalities)
- Gson (for JSON file handling)

## Contributing
Contributions are welcome! Please submit a pull request or open an issue for any enhancements or bug fixes.

## License
This project is licensed under the MIT License. See the LICENSE file for details.