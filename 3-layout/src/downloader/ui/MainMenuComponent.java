package downloader.ui;

import java.awt.*;

import javax.swing.*;

public class MainMenuComponent extends JPanel {
    public static final int PADDING = 8 ; 
    public static final Dimension NORMAL_BUTTON_SIZE = new Dimension(45, 30);
    public static final Dimension PLAY_BUTTON_SIZE = new Dimension(65, 45);
    public static final Dimension SIDE_LONG_BUTTON_SIZE = new Dimension(60, 30);
    
    private JButton more ;

    public MainMenuComponent(MainVLC mainVLC) {
        super();
        this.setPreferredSize(new Dimension(700, MainVLC.PREFERED_HEIGHT));
        this.setLayout(new BorderLayout());
        this.add(leftPart(), BorderLayout.WEST);
        this.add(rightPart(mainVLC), BorderLayout.CENTER);
        this.setBorder(BorderFactory.createEmptyBorder(PADDING, 0, PADDING, PADDING));
    }

    private JPanel rightPart(MainVLC mainVLC) {
        JPanel right = new JPanel(new BorderLayout());
        MusicPlayerComponent top = new MusicPlayerComponent("Noix");
        right.add(top, BorderLayout.CENTER);
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        right.add(bottom, BorderLayout.SOUTH);
        JSlider volumeSlider = new JSlider();
        volumeSlider.setMaximumSize(new Dimension(150, 20));
        bottom.add(volumeSlider, BorderLayout.WEST);
        more = new JButton(SYMBOLS.DOWN_MENU);
        bottom.add(more, BorderLayout.EAST);
        more.addActionListener(e -> {
            mainVLC.togglePlaylist();
        });
        return right;
    }

    public JButton getMoreButton() {
        return more;
    }

    private JPanel leftPart() {
        JPanel left = new JPanel(new BorderLayout());
        left.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,0));

        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER, PADDING, 0));
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER, PADDING, 0));

        left.add(top, BorderLayout.NORTH);
        left.add(bottom, BorderLayout.SOUTH);

        JButton play = new JButton(SYMBOLS.PLAY);
        
        JButton back = new JButton(SYMBOLS.BACK);
        back.addActionListener(e -> {
            MusicPlayerComponent.changeTime(-5);;
        });

        JButton forward = new JButton(SYMBOLS.FORWARD);
        forward.addActionListener(e -> {
            MusicPlayerComponent.changeTime(5);
        });

        top.add(back);
        top.add(play);
        top.add(forward);

        JButton stop = new JButton(SYMBOLS.STOP);
        JButton hardBack = new JButton(SYMBOLS.HARDBACK);
        hardBack.addActionListener(e -> {
            MusicPlayerComponent.changeSong(MusicPlayerComponent.currentSong);
        });
        JButton hardForward = new JButton(SYMBOLS.HARDFORWARD);
        hardForward.addActionListener(e -> {
            MusicPlayerComponent.nextRandomSong();
        });
        bottom.add(hardBack, BorderLayout.WEST);
        bottom.add(stop, BorderLayout.CENTER);
        bottom.add(hardForward, BorderLayout.EAST);

        
        play.setPreferredSize(PLAY_BUTTON_SIZE);
        stop.setPreferredSize(NORMAL_BUTTON_SIZE);
        back.setPreferredSize(NORMAL_BUTTON_SIZE);
        forward.setPreferredSize(NORMAL_BUTTON_SIZE);
        hardBack.setPreferredSize(SIDE_LONG_BUTTON_SIZE);
        hardForward.setPreferredSize(SIDE_LONG_BUTTON_SIZE);


        return left;
    }

}
