package org.app.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import org.app.model.LocationSelector;
import org.app.model.Pair;

public class LocationSelectorGUI {

    public static void onButtonPressed(final int value) {
        System.out.println("Location ID: " + value);
    }

    public static void main(String[] args) {

        final LocationSelector city_selector = new LocationSelector();

        final JFrame frame = new JFrame("Scegli la Localita'");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        frame.setLayout(new BorderLayout());

        // Aggiungi il JLabel all'inizio
        final JLabel label = new JLabel("Cerca una citta' (in inglese):");
        frame.add(label, BorderLayout.NORTH);

        final JTextField textField = new JTextField(20);
        // Usa un JPanel per tenere sia il JLabel che la JTextField
        final JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.add(label);
        northPanel.add(textField);
        frame.add(northPanel, BorderLayout.NORTH);

        final JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        final JScrollPane scrollPane = new JScrollPane(resultPanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {
                final String inputText = textField.getText();
                if (inputText.trim().equals("") || inputText.length() < 2) {
                    resultPanel.removeAll();
                    // Refresh GUI
                    resultPanel.revalidate();
                    resultPanel.repaint();
                    return;
                }

                final List<Pair<String, Integer>> locations = city_selector.getPossibleLocations(inputText);

                resultPanel.removeAll();

                for (final Pair<String, Integer> location : locations) {
                    final String buttonText = location.getX();
                    final int LOCATION_ID = location.getY();

                    final JButton button = new JButton(buttonText);

                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(final ActionEvent e) {
                            onButtonPressed(LOCATION_ID);
                        }
                    });

                    resultPanel.add(button);
                }
    
                // Refresh GUI
                resultPanel.revalidate();
                resultPanel.repaint();
            }
        });

        frame.setVisible(true);
    }

}
