package downloader.ui;

import java.awt.ScrollPane;
import java.awt.Scrollbar;
import javax.swing.*;


public class Main extends JFrame {
    public String[] urls;
    JPanel downloadPanel;
    JTextField enteredLink;

    public Main(String title, String[] args) {
        super(title);

        // Container principal
        JPanel topLayerPanel = new JPanel(new java.awt.BorderLayout());
        add(topLayerPanel);

        // Pour ajouter une barre de défilement
        ScrollPane scrollPane = new ScrollPane();
        Scrollbar scrollBar = new Scrollbar();
        scrollPane.add(scrollBar);
        topLayerPanel.add(scrollPane, java.awt.BorderLayout.CENTER);

        // Panel principal étant scrollable
        JPanel mainPanel = new JPanel(new java.awt.BorderLayout()) ;
        scrollPane.add(mainPanel);

        // Panel contenant la liste des téléchargements
        downloadPanel = new JPanel(new StackLayout());
        mainPanel.add(downloadPanel);

        // Panel contenant la barre d'ajout de téléchargement
        JToolBar addDownloadBar = new JToolBar();
        topLayerPanel.add(addDownloadBar, java.awt.BorderLayout.SOUTH);
        addDownloadBar.setFloatable(false);
        enteredLink = new JTextField();
        enteredLink.setText("Enter a URL");
        enteredLink.addFocusListener(new FocusManager("Enter a URL", enteredLink));
        addDownloadBar.add(enteredLink);
        addDownloadBar.addSeparator();
        JButton addButton = new JButton("add");
        addButton.addActionListener(new DownloadListener(this));
        addDownloadBar.add(addButton);

        // Pour que le bouton "add" soit actionné si on appuie sur entrée.
        getRootPane().setDefaultButton(addButton);
        
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.urls = args;
        downloadAll();
    }

    public void downloadAll(){
        for (String url : urls) {
            download(url);
        }
    }
    public void download(String url){
        Download download = new Download(url);
        downloadPanel.add(download);
        download.startDownload();
        repaint();
    }

    public static void main(String[] args) {
        new Main("Downloader", args).setVisible(true);
    }
}
