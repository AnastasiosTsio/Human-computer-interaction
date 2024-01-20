package downloader.ui;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import javax.swing.border.Border;

import java.awt.*;
import java.util.Random;

public class MusicPlayerComponent extends JPanel {
    static JSlider musicPlayer;
    JTextArea text;
    static String currentSong;
    static boolean hasChanged = false;
    // Main to debug
    
    // public static void main(String[] args) {
    //     JFrame frame = new JFrame("CurrentMusic");
    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     frame.setSize(400, 100);
    //     frame.add(new MusicPlayerComponent("filename"));
    //     frame.setVisible(true);
        
    // }

    public MusicPlayerComponent(String filename) {   
        super(new BorderLayout());
        //setBackground(Color.GREEN);
        //Set the margin 
        Border border = BorderFactory.createCompoundBorder(BorderFactory.createLoweredBevelBorder(), BorderFactory.createEmptyBorder(5,10,5,10));
        setBorder(border);
        MusicPlayerComponent.musicPlayer = new JSlider();
        //musicPlayer.setBackground(Color.GREEN);
        musicPlayer.setValue(10);
        add(musicPlayer, BorderLayout.SOUTH);
        JLabel valueLabel = new JLabel("00:00");
        

        currentSong = "Highway to Hell";
        JLabel cSong = new JLabel(currentSong);
        add(cSong, BorderLayout.WEST);
        add(valueLabel, BorderLayout.EAST);
        

        new Thread(){
            public void run(){
                playSong(valueLabel,musicPlayer,cSong);
            }
        }.start();
    }
    
    private void playSong(JLabel valueLabel, JSlider musicPlayer, JLabel cSong) {
        while (true) {
                if(hasChanged){
                    SwingUtilities.invokeLater(() -> cSong.setText(currentSong));
                    musicPlayer.setValue(0);
                    hasChanged = false;
                }
                if(musicPlayer.getValue() >= 99){
                    nextRandomSong();
                }
                else{
                   
                    SwingUtilities.invokeLater(() -> valueLabel.setText(convertSecondsToTimeString(musicPlayer.getValue())));
                    SwingUtilities.invokeLater(() -> musicPlayer.setValue((musicPlayer.getValue() + 1)%100));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    public static void changeSong(String filename) {
        currentSong = filename;
        hasChanged = true;
    }

    public static void nextRandomSong(){
        Random random = new Random();
        String[] selectedArray = (random.nextBoolean()) ? PlaylistMenuComponent.songsNames : PlaylistMenuComponent.songsNames2;
        int randomIndex = random.nextInt(selectedArray.length);
        currentSong = selectedArray[randomIndex];
        hasChanged = true;
    }

    public static void changeTime(int n){
        musicPlayer.setValue(musicPlayer.getValue()+n);
    }
    public static String convertSecondsToTimeString(int totalSeconds) {
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;
        if (hours > 0) {
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        }
        else {
            return String.format("%02d:%02d", minutes, seconds);
        }
    }
}
