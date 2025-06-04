package com.fantasybasketballbot;

import javax.swing.*;
import java.awt.*;
import com.fantasybasketballbot.ui.RosterOptimizerPanel;
import com.fantasybasketballbot.ui.FreeAgentsPanel;
import com.fantasybasketballbot.ui.DraftAnalyzerPanel;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Fantasy Basketball Bot");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLayout(new BorderLayout());

            // Create a tabbed pane to hold the panels
            JTabbedPane tabbedPane = new JTabbedPane();

            // Add custom panels to the tabbed pane
            tabbedPane.addTab("Roster Optimizer", new RosterOptimizerPanel());
            tabbedPane.addTab("Free Agents", new FreeAgentsPanel());
            tabbedPane.addTab("Draft Analyzer", new DraftAnalyzerPanel());

            // Add the tabbed pane to the frame
            frame.add(tabbedPane, BorderLayout.CENTER);

            frame.setVisible(true);
        });
    }
}