package com.fantasybasketballbot;

import javax.swing.*;
import java.awt.*;
import com.fantasybasketballbot.ui.HomePagePanel;
import com.fantasybasketballbot.ui.RosterOptimizationPanel;
import com.fantasybasketballbot.ui.RosterDisplayPanel;

public class Main {
    private static JFrame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create the main application frame
            frame = new JFrame("Fantasy Basketball Bot");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLayout(new BorderLayout());

            // Start with the Home Page
            showHomePage();

            frame.setVisible(true);
        });
    }

    // Method to display the Home Page
    public static void showHomePage() {
        frame.getContentPane().removeAll();
        frame.add(new HomePagePanel(), BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    // Method to display the Roster Optimization Page
    public static void showRosterOptimizationPage() {
        frame.getContentPane().removeAll();
        frame.add(new RosterOptimizationPanel(), BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    // Method to display the Roster Display Page
    public static void showRosterDisplayPage() {
        frame.getContentPane().removeAll();
        frame.add(new RosterDisplayPanel(), BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }
}