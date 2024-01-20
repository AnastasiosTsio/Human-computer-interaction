package downloader.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.*;
import javax.swing.*;

import downloader.fc.Downloader;

public class Download extends JPanel {
    Downloader downloader;
    JProgressBar progressBar;
    JTextArea text;
    static final String PLAY   = "▶";
    static final String PAUSE  = "❙❙";
    static final String REMOVE = "✖";

    public Download(String filename) {
        this(new Downloader(filename));
    }

    public Download(Downloader downloader) {
        super();
        this.downloader = downloader;
        componentInit();
    }

    private void componentInit() {
        
        JPanel left = new JPanel(new BorderLayout());
        JPanel right = new JPanel(new BorderLayout());
        JPanel generalPanel = new JPanel(new BorderLayout()); 
        add(generalPanel);
        generalPanel.add(left, BorderLayout.CENTER);
        generalPanel.add(right, BorderLayout.EAST);

        JButton cancelButton = new JButton(REMOVE);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelDownload();
            }
        });
    
        JButton pauseButton = new JButton(PAUSE);
        JSeparator separator = new JSeparator(JSeparator.VERTICAL);
        separator.setPreferredSize(new Dimension(4, 0));
        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (downloader.isDone()) return;

                if (!isPaused()) {
                    downloader.downloadLock.lock();
                    downloader.isPaused = true;
                } else {
                    downloader.downloadLock.unlock();
                    downloader.isPaused = false;
                }
                pauseButton.setText(isPaused() ? PLAY : PAUSE);
            }
        });

        right.add(pauseButton, BorderLayout.WEST);

        right.add(cancelButton, BorderLayout.EAST);

        right.add(separator, BorderLayout.CENTER);
        right.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        
        this.progressBar = new JProgressBar();
        
        text = new JTextArea(" " + downloader.toString());
        text.setEditable(false);
        text.setFont(new java.awt.Font("Arial", 0, 13));
      
        left.add(progressBar, BorderLayout.SOUTH);
        left.add(text, BorderLayout.NORTH);
        
        progressBar.setForeground(java.awt.Color.BLUE);

    }

    public void startDownload() {
        setLayout(new StackLayout());
        progressBar.setStringPainted(true);
        downloader.isPaused = false;
        this.setValue(0);
        downloader.execute();
        downloader.addPropertyChangeListener(
                new PropertyChangeListener() {
                    public void propertyChange(PropertyChangeEvent evt) {
                        setValue(downloader.getProgress());
                        if (downloader.isDone()) {
                            setString("Done.");
                            text.setText(" " + downloader.toString());
                        } else {
                            setString(downloader.getProgress() + "%");
                        }

                        progressBar.setForeground(new Color(0, (downloader.getProgress() * 255 / 100),
                                255 - (downloader.getProgress() * 255 / 100)));
                        System.out.flush();
                    }
                });
    }

    private void setValue(int value) {
        progressBar.setValue(value);
    }

    public Downloader getDownloader() {
        return downloader;
    }

    private void setString(String string) {
        progressBar.setString(string);
    }

    private void cancelDownload() {
        downloader.cancel(true);
        if (downloader.isPaused)
            downloader.downloadLock.unlock();
            
        setVisible(false);
    }

    private boolean isPaused() {
        return downloader.isPaused;
    }
}
