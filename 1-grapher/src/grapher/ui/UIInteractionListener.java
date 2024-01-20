package grapher.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import grapher.fc.FunctionFactory;

public class UIInteractionListener implements ActionListener, ListSelectionListener {
    Main main;

    public UIInteractionListener(Main main) {
        this.main = main;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        main.grapher.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand().strip();
        if (cmd.equals("+") || cmd.equals("Add...")) {
            addFunction();
        } else if (cmd.equals("-") || cmd.equals("Remove")) {
            removeFunction();
        } else if (cmd.equals("Modify")) {
            modifyFunction();
        }

    }

    private void removeFunction() {
        int index = main.grapher.visualList.getSelectedIndex();
        if (index != -1) {
            main.listFuncs.remove(index);
        }
    }

    private void modifyFunction() {
        if (main.grapher.visualList.isSelectionEmpty()) return;
        ColoredFunction coloredFunction = main.grapher.visualList.getSelectedValue();
        try {
            String modifiedFunction = JOptionPane.showInputDialog(
                UIInteractionListener.this.main,
                "Modify the function formula :",
                "Change a function",
                // Text input that contains old function formula
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                coloredFunction.function.toString()
            ).toString();
            coloredFunction.function = FunctionFactory.createFunction(modifiedFunction);
            main.grapher.repaint();
        } catch (Exception e) {
            unavaibleFunction();
        }
    }

    private void addFunction() {
        String newFunction = JOptionPane.showInputDialog(
            UIInteractionListener.this.main,
            "Enter the function formula :",
            "Adding a new function",
            JOptionPane.PLAIN_MESSAGE
        );
        try {
            main.addFunction(newFunction);
        } catch (Exception e) {
            unavaibleFunction();
        }
    }

    private void unavaibleFunction() {
        JOptionPane.showMessageDialog(
            UIInteractionListener.this.main,
            "The function you entered is not valid.",
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }

}
