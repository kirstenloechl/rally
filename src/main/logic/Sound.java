package logic;

import java.io.*;
import javax.sound.sampled.*;

public class Sound {

	private Sound() {
		throw new IllegalStateException("Utility class");
	}

	public static void playSound(String audioFile) {
		
		try {
			File bounceFile = new File(audioFile);
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(bounceFile);
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioIn);
	        clip.start();
		} 
		catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
	         e.printStackTrace();
	    }
	}
}
