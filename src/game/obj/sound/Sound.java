package game.obj.sound;

import javafx.scene.media.AudioClip;

public class Sound {

    private final AudioClip shoot;
    private final AudioClip hit;
    private final AudioClip destroy;

    public Sound() {
        this.shoot = new AudioClip(getClass().getResource("/game/obj/sound/shoot.wav").toExternalForm());
        this.hit = new AudioClip(getClass().getResource("/game/obj/sound/hit.wav").toExternalForm());
        this.destroy = new AudioClip(getClass().getResource("/game/obj/sound/destroy.wav").toExternalForm());
    }

    public void soundShoot() {
        play(shoot);
    }

    public void soundHit() {
        play(hit);
    }

    public void soundDestroy() {
        play(destroy);
    }

    private void play(AudioClip clip) {
        if (clip != null) {
            clip.play();
        }
    }
}