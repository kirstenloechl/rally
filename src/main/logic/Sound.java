package logic;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;


public class Sound {

	public static void playSound(String audioFile) {
		
		try {
			File bounceFile = new File(audioFile);
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(bounceFile);
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioIn);
	        clip.start();
		} 
		catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
	    } 
		catch (IOException e) {
	         e.printStackTrace();
	    } 
		catch (LineUnavailableException e) {
	         e.printStackTrace();
	    }
	}
}
