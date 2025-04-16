package game.obj.sound;

import javafx.scene.media.AudioClip;

public class Sound {

    private final AudioClip shoot;
    private final AudioClip hit;
    private final AudioClip destroy;
    private final AudioClip click; // Âm thanh click
    private final AudioClip backgroundMusic;
    public Sound() {
        this.shoot = loadSound("/game/obj/sound/shoot.wav");
        this.hit = loadSound("/game/obj/sound/hit.wav");
        this.destroy = loadSound("/game/obj/sound/destroy.wav");
        this.click = loadSound("/game/obj/sound/click.wav");
        this.backgroundMusic = loadSound("/game/obj/sound/backmusic.wav");
    }
    
    // Phương thức riêng để nạp âm thanh
    private AudioClip loadSound(String path) {
        try {
            return new AudioClip(getClass().getResource(path).toExternalForm());
        } catch (Exception e) {
            System.err.println("Không thể tải âm thanh: " + path);
            return null;
        }
    }
    public void playBackgroundMusic(){
        if (backgroundMusic != null) {
            backgroundMusic.setCycleCount(AudioClip.INDEFINITE); // Lặp lại nhạc nền
            backgroundMusic.setVolume(2);
            backgroundMusic.play();
        }
    }
    public void stopBackgroundMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
        }
    }
    // Phương thức phát âm thanh bắn
    public void soundShoot() {
        play(shoot);
    }

    // Phương thức phát âm thanh trúng đạn
    public void soundHit() {
        play(hit);
    }

    // Phương thức phát âm thanh phá hủy
    public void soundDestroy() {
        play(destroy);
    }

    // Phương thức phát âm thanh click
    public void soundClick() {
        play(click);
    }

    // Phương thức chung để phát âm thanh
    private void play(AudioClip clip) {
        if (clip != null) {
            clip.play();
        }
    }
}
