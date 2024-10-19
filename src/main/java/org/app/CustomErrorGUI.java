package org.app;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class CustomErrorGUI {

    // Metodo per far partire una GUI di errore con messaggio personalizzato
    public static void showError(final String message, final String title) {
        final JOptionPane optionPane = new JOptionPane(message, JOptionPane.ERROR_MESSAGE);
        final JDialog dialog = optionPane.createDialog(title);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }

    // Metodo per far partire una GUI di avvertimento con messaggio personalizzato
    public static void showWarning(final String message, final String title) {
        final JOptionPane optionPane = new JOptionPane(message, JOptionPane.WARNING_MESSAGE);
        final JDialog dialog = optionPane.createDialog(title);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }

    // Metodo per far partire una GUI di informazione con messaggio personalizzato
    public static void showInfo(final String message, final String title) {
        final JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
        final JDialog dialog = optionPane.createDialog(title);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }

    // Metodo per far partire una GUI di conferma
    public static boolean showConfirm(final String message, final String title) {
        final int result = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }

}
