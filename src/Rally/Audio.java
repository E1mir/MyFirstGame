package Rally;
import javazoom.jl.decoder.JavaLayerException;
import  javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Audio implements Runnable{
    public void run() {
        try {
            Player audio = new Player(new FileInputStream("res/TheFatRat - Time Lapse.mp3"));
            audio.play();
        } catch (JavaLayerException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
