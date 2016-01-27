package modeles.entites;

import javax.sound.sampled.AudioFormat;

public class MicrophoneModel {
	
	public static final int CHUNK_SIZE = 1024;
	public static final int BUFF_SIZE = CHUNK_SIZE*10;
	public static final AudioFormat audioFormat = new AudioFormat(8000.0f, 16, 1, true, true);
	public static final int MIN_BUFFERED = 0;
	public static boolean connected = false;
	
	private MicrophoneModel() {
		// TODO Auto-generated constructor stub
	}



}
