package org.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

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

        final JTextField textField = new JTextField(20);
        frame.add(textField, BorderLayout.NORTH);

        final JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        final JScrollPane scrollPane = new JScrollPane(resultPanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {
                final String inputText = textField.getText();
                if (inputText.trim().equals("")) {
                    resultPanel.removeAll();
                    // Refresh GUI
                    resultPanel.revalidate();
                    resultPanel.repaint();
                    return;
                }

                final List<Pair<String, Integer>> result = city_selector.getPossibleLocations(inputText);

                resultPanel.removeAll();

                for (final Pair<String, Integer> pair : result) {
                    final String buttonText = pair.getX();
                    final int LOCATION_ID = pair.getY();

                    final JButton button = new JButton(buttonText);

                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
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
