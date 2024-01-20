package downloader.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

public class MainVLC extends JFrame {
    public static final int PREFERED_HEIGHT = 98;
    public static final int FULL_WIN_MIN_HEIGHT = 300;
    public static int HEADER_HEIGHT;
    private int softwareResizeReceiver;
    public boolean isPlaylistVisible = false;
    public PlaylistMenuComponent playlist;
    public MainMenuComponent mainMenu;

    public MainVLC() {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(700, PREFERED_HEIGHT));
        componentInit();
        setVisible(true);
        HEADER_HEIGHT = this.getHeight() - this.getContentPane().getHeight();
        this.setSize(new Dimension(700, HEADER_HEIGHT + PREFERED_HEIGHT));

        this.addComponentListener(
                new java.awt.event.ComponentAdapter() {
                    public void componentResized(java.awt.event.ComponentEvent evt) {
                        if (softwareResizeReceiver > 0) {
                            softwareResizeReceiver--;
                            return;
                        }
                        if (isPlaylistVisible) {
                            if (getSize().height < PREFERED_HEIGHT + HEADER_HEIGHT + 50) {
                                togglePlaylist();
                                setSize(new Dimension(getSize().width, PREFERED_HEIGHT + HEADER_HEIGHT));
                            }
                        } else {
                            Dimension size = new Dimension(getSize().width, PREFERED_HEIGHT + HEADER_HEIGHT);
                            setSize(size);
                        }

                    }
                });
    }

    public void componentInit() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        mainMenu = new MainMenuComponent(this);
        mainPanel.add(mainMenu, BorderLayout.NORTH);

        playlist = new PlaylistMenuComponent();
        mainPanel.add(playlist, BorderLayout.CENTER);
        playlist.setVisible(isPlaylistVisible);

        this.add(mainPanel);
    }

    public void callACodeResize() {
        softwareResizeReceiver++;
    }

    public void togglePlaylist() {
        isPlaylistVisible = !isPlaylistVisible;
        playlist.setVisible(isPlaylistVisible);
        mainMenu.getMoreButton().setText(isPlaylistVisible ? SYMBOLS.UP_MENU : SYMBOLS.DOWN_MENU);
        callACodeResize();
        if (isPlaylistVisible)
            setSize(new Dimension(getSize().width, FULL_WIN_MIN_HEIGHT + HEADER_HEIGHT));
        else
            setSize(new Dimension(getSize().width, PREFERED_HEIGHT + HEADER_HEIGHT));
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainVLC mainVLC = new MainVLC();
            }
        });

    }
}
