# VLC Media Player UI

This is a simple Java Swing-based UI for a VLC media player. The application features a resizable main window with a playlist toggle functionality.
## Authors
- KOHRS Noah
- TSIOMPANIDIS Anastasios

## Features

- **Resizable Window:** The main window can be resized by the user.

- **Playlist Toggle:** The application includes a playlist that can be toggled on and off. When the playlist is visible, the window adjusts its height accordingly.

## How to Use

1. **Run the Application:**
   - Execute the `MainVLC` class to start the VLC media player UI.

2. **Resizable Window:**
   - The main window is resizable by dragging the window borders.

3. **Playlist Toggle:**
   - Click the playlist toggle button to show/hide the playlist.
   - When the playlist is visible, the window adjusts its height to accommodate the playlist.

## Code Overview

The main functionality of the application is provided by the `MainVLC` class. Here are some key points:

- **Window Initialization:**
  - The main window is initialized with a preferred height.
  - The `componentInit` method sets up the UI components, including the main panel, song layout, and playlist.

- **Resizable Window Handling:**
  - The window is set to trigger a resize event when resized by the user.
  - The `componentResized` method adjusts the window size based on user interactions and playlist visibility.

- **Playlist Toggle:**
  - The `togglePlaylist` method toggles the visibility of the playlist and adjusts the window size accordingly.

- **Run the Application:**
  - The `main` method creates an instance of the `MainVLC` class and starts the Swing application.


<br>

---
**Note:** This application is a UI prototype and does not include actual media playback functionality. It serves as a demonstration of Java Swing UI concepts.
