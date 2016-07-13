package controller;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
//Code found and modified from http://noobtuts.com/java/play-sounds
public class Sounds {
    
    public static Clip backgroundMusic;
    public static boolean mute = false;
    
    public static synchronized void play(final String fileName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (!mute) {
                    try {
                        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(fileName));
                        if (fileName.equals("sounds/songLoop.wav") || fileName.equals("sounds/bossLoop.wav")) {
                            backgroundMusic = AudioSystem.getClip();
                            backgroundMusic.open(inputStream);
                            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
                            backgroundMusic.start();
                            Thread.sleep(14700);
                        } else {
                            Clip clip = AudioSystem.getClip();
                            clip.open(inputStream);
                            clip.start();
                        }
                    } catch (Exception ex) {
                        System.out.println("ERROR " + ex.getMessage());
                    }
                }
            }
        }).start();
    }
    
}
 