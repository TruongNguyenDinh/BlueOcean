/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package BlueOceanScene.Models;

/**
 *
 * @author truon
 */
public class Song {
    
    private final String title;
    private final String artist;
    private final String filepath;

    public Song(String title, String artist, String filepath) {
        this.title = title;
        this.artist = artist;
        this.filepath = filepath;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getFilepath() {
        return filepath;
    }

    public String getDisplayName() {
        return title + " - " + artist;
    }
    
}
