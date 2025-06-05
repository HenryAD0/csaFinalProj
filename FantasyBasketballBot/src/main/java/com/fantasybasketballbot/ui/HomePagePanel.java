package com.fantasybasketballbot.ui;

import javax.swing.*;
import java.awt.*;

public class HomePagePanel extends JPanel {

    private JButton optimizeRosterButton;
    private JButton myRosterButton;

    public HomePagePanel() {
        setLayout(new BorderLayout());

        // Title text
        JLabel titleLabel = new JLabel("Fantasy Basketball Bot", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 10));

        optimizeRosterButton = new JButton("Optimize Roster");
        myRosterButton = new JButton("My Roster");

        buttonPanel.add(optimizeRosterButton);
        buttonPanel.add(myRosterButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Add action listeners (placeholders for navigation)
        optimizeRosterButton.addActionListener(e -> navigateToRosterOptimization());
        myRosterButton.addActionListener(e -> navigateToRosterDisplay());
    }

    private void navigateToRosterOptimization() {
        // Logic to navigate to Roster Optimization Page
        System.out.println("Navigating to Roster Optimization Page...");
    }

    private void navigateToRosterDisplay() {
        // Logic to navigate to Roster Display Page
        System.out.println("Navigating to Roster Display Page...");
    }
}
