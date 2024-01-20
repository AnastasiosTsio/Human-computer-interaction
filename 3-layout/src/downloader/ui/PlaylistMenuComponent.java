package downloader.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.tree.DefaultMutableTreeNode;

public class PlaylistMenuComponent extends JPanel {

    // Main to debug
    // public static void main(String[] args) {
    // JFrame frame = new JFrame("Playlist");
    // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // frame.setSize(400, 100);
    // frame.add(new PlaylistMenuComponent());
    // frame.setVisible(true);


    // }
    public static String[] songsNames = {
            "Highway to Hell",
            "Girls Got Rhythm",
            "Walk All Over You",
            "Touch Too Much",
            "Beating Around the Bush",
            "Shot Down in Flames",
            "Get It Hot",
            "If You Want Blood"
    };

    static String[] songsNames2 = {
                "Hells Bells",
                "Shoot to Thrill",
                "What Do You Do for Money Honey",
                "Given the Dog a Bone",
                "Let Me Put My Love Into You",
                "Back in Black",
                "You Shook Me All Night Long",
                "Have a Drink on Me",
                "Shake a Leg",
                "Rock and Roll Ain't Noise Pollution"
        };

    public PlaylistMenuComponent() {
        super();
        setLayout(new BorderLayout());
        add(playlist(), BorderLayout.CENTER);
        add(optionsBar(), BorderLayout.SOUTH);

    }

    public DefaultMutableTreeNode initTemplatePlaylist() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Music Library");
        DefaultMutableTreeNode band1 = new DefaultMutableTreeNode("AC_DC");
        DefaultMutableTreeNode album1Band1 = new DefaultMutableTreeNode("Highway to Hell");
        DefaultMutableTreeNode album2Band1 = new DefaultMutableTreeNode("Back in Black");
        for (String songName : songsNames) {
            if (songName.equals(MusicPlayerComponent.currentSong))
                album1Band1.add(new DefaultMutableTreeNode(songName + " (playing)"));
            else
                album1Band1.add(new DefaultMutableTreeNode(songName));
        }
        band1.add(album1Band1);

        for (String songName : songsNames2) {
            album2Band1.add(new DefaultMutableTreeNode(songName));
        }
        band1.add(album2Band1);

        root.add(band1);

        return root;
    }

    private JPanel optionsBar() {
        JPanel optionBar = new JPanel(new BorderLayout());
        optionBar.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        JPanel buttonsOptions = new JPanel(new BorderLayout());
        add(optionBar, BorderLayout.SOUTH);
        optionBar.add(buttonsOptions, BorderLayout.WEST);
        buttonsOptions.setLayout(new BoxLayout(buttonsOptions, BoxLayout.X_AXIS));
        JButton add = new JButton("\u002B");
        JButton shuffle = new JButton("\u21C4");
        JButton loop = new JButton("\u21BA");
        JTextField search = new JTextField("\u2315");
        search.setPreferredSize(new Dimension(100, 20));

        JLabel numberOfSongs = new JLabel("15 elements");
        numberOfSongs.setHorizontalAlignment(JLabel.CENTER);

        buttonsOptions.add(add);
        buttonsOptions.add(shuffle);
        buttonsOptions.add(loop);

        optionBar.add(search, BorderLayout.EAST);
        optionBar.add(numberOfSongs, BorderLayout.CENTER);
        

        return optionBar;
    }

    private JPanel playlist() {
        JPanel playlist = new JPanel(new BorderLayout());
        JTree tree = new JTree(initTemplatePlaylist());

        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    // Double-click detected
                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                    if (selectedNode != null && selectedNode.getUserObject() instanceof String
                            && (Arrays.asList(songsNames).contains(selectedNode.getUserObject()) || Arrays.asList(songsNames2).contains(selectedNode.getUserObject()) )) {
                        // Assuming userObject is a String (song name)
                        String newSong = (String) selectedNode.getUserObject();
                        // Call the function to change the current song
                        MusicPlayerComponent.changeSong(newSong);
                    }
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(tree);
        playlist.add(scrollPane, BorderLayout.CENTER);
        return playlist;
    }

}
