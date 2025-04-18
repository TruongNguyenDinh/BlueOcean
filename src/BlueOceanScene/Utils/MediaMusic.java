    package BlueOceanScene.Utils;

import BlueOceanScene.Models.Song;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaException;

/**
 * A class to manage background music in the application.
 * @author truon
 */
public class MediaMusic {
    private static double currentVolume = 0.8;
    private static MediaPlayer mediaPlayer;
    private static int currentIndex = 0;
    private static List<Song> playlist = new ArrayList<>();
    private static Label currentMusicLabel;
    public static void setLabel(Label label) {
        currentMusicLabel = label;
    }
    public static void playPlaylist(List<String> filePaths) {
        playlist.clear();
        if (filePaths == null || filePaths.isEmpty()) {
            System.err.println("Playlist is empty!");
            return;
        }
        for(String file:filePaths){
            if(file.contains("--")){
                String[] parts = file.split("--");
                String title = parts[0].trim();
                String artist = parts[1].replace(".mp3", "").trim();
                String filePath = new File(file).toURI().toString();
                playlist.add(new Song(title,artist,filePath));
            }
        }
//        callBackgroundMusic();
    }
    public static void callBackgroundMusic() {
        try {
            if (playlist == null || playlist.isEmpty()) return;
            // Kiểm tra nếu mediaPlayer đã tồn tại, dừng và giải phóng trước khi tạo mới
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
            if (currentMusicLabel != null) {
                currentMusicLabel.setText(getCurrentSongName());
            }

            // Đường dẫn file nhạc
//            String path = "file:/D:/Java/BlueOceanClient/BGMusic/bgmusic.mp3";
            Song currentSong = playlist.get(currentIndex);

            Media media = new Media(currentSong.getFilepath());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setVolume(currentVolume);
                mediaPlayer.setOnEndOfMedia(() -> {
                currentIndex = (currentIndex + 1) % playlist.size(); // Phát tiếp bài tiếp theo, vòng lặp
                callBackgroundMusic();
            });
           
            
            // Phát nhạc
            mediaPlayer.play();
        } catch (MediaException e) {
            System.err.println("Error loading music: " + e.getMessage());
        }
    }
    public static String getCurrentSongName() {
        if (playlist.isEmpty()) return "Không có bài nào.";
        Song song = playlist.get(currentIndex);
        return song.getDisplayName(); // "Tên - Tác giả"
    }
    public static void setVolume1(double volume){
        currentVolume = volume/100;
        if (mediaPlayer!=null){
            mediaPlayer.setVolume(currentVolume);
        }
        
    }

    public static void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public static void playMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }
}