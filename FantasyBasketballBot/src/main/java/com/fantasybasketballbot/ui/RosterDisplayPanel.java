package com.fantasybasketballbot.ui;

import com.fantasybasketballbot.model.Team;
import com.fantasybasketballbot.util.JsonUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RosterDisplayPanel extends JPanel {

    private JTable rosterTable;
    private JButton addFutureInterestsButton;
    private JButton optimizeFutureInterestsButton;
    private JButton backButton;
    private ArrayList<Team> rosters;

    public RosterDisplayPanel() {
        setLayout(new BorderLayout());

        // Load rosters from JSON file
        rosters = JsonUtils.loadRosters();

        // Header
        JLabel headerLabel = new JLabel("My Rosters", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(headerLabel, BorderLayout.NORTH);

        // Roster table (placeholder for roster data)
        rosterTable = new JTable(new RosterTableModel(rosters)); // Custom table model
        add(new JScrollPane(rosterTable), BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 10));

        backButton = new JButton("Back");
        addFutureInterestsButton = new JButton("Add Future Interests");
        optimizeFutureInterestsButton = new JButton("Optimize Future Interests");

        buttonPanel.add(backButton);
        buttonPanel.add(addFutureInterestsButton);
        buttonPanel.add(optimizeFutureInterestsButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        backButton.addActionListener(e -> navigateToHomePage());
        addFutureInterestsButton.addActionListener(e -> addFutureInterests());
        optimizeFutureInterestsButton.addActionListener(e -> optimizeFutureInterests());
    }

    private void navigateToHomePage() {
        // Logic to navigate back to Home Page
        System.out.println("Navigating to Home Page...");
    }

    private void addFutureInterests() {
        // Logic to add future interests
        System.out.println("Adding future interests...");
    }

    private void optimizeFutureInterests() {
        // Logic to optimize future interests
        System.out.println("Optimizing future interests...");
    }
}
