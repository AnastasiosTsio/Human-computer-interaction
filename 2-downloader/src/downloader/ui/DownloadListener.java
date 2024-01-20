package downloader.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DownloadListener implements ActionListener {
    Main main;

    public DownloadListener(Main main) {
        this.main = main;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("add")) {
            try {
                main.download(main.enteredLink.getText());

                main.repaint();

                main.enteredLink.setText("Enter a URL");
                ;
            } catch (Exception ex) {
                main.enteredLink.setText("Invalid URL");
                main.enteredLink.addFocusListener(new FocusManager("Invalid URL", main.enteredLink));
            }
            main.enteredLink.setFocusable(false);
            main.enteredLink.setFocusable(true);
        }
    }
}