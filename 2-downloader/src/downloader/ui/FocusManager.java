package downloader.ui;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.Color;
import javax.swing.JTextField;

public class FocusManager implements FocusListener {
    String placeholder;
    JTextField enteredLink;
    
    public FocusManager(String placeholder, JTextField enteredLink) {
        this.placeholder = placeholder;
        this.enteredLink = enteredLink;
    }
    
    @Override
    public void focusGained(FocusEvent e) {
        if (enteredLink.getText().equals(placeholder)) {
            enteredLink.setText("");
            enteredLink.setForeground(Color.BLACK);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if ( enteredLink.getText().isEmpty()) {
             enteredLink.setText(placeholder);
             enteredLink.setForeground(Color.GRAY);
        }
    }
}

