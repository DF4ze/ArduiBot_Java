package controleurs.audio;

import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import controleurs.Debug;
import modeles.dao.communication.beansFifos.FifoAudioInput;
import modeles.entites.MicrophoneModel;

public class StreamedMicPlayer extends Thread{

	public StreamedMicPlayer() {
		
	}

	public void run(){
		try {
			SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(new DataLine.Info(SourceDataLine.class, MicrophoneModel.audioFormat));

	        sourceDataLine.open(MicrophoneModel.audioFormat);
	        sourceDataLine.start();
	        
	        System.out.print("Buffering");
	        synchronized (FifoAudioInput.getList()) {
	        	
				while( FifoAudioInput.getList().size() < MicrophoneModel.MIN_BUFFERED ){
					try {
						FifoAudioInput.getList().wait(5000);
						System.out.print(".");
					} catch (InterruptedException e) {}
				}
			}
			System.out.println("");
	        
			
	        int cnt = 0;
	        try {
	        	while (MicrophoneModel.connected) {
	        		
	        		byte tempBuffer[] = new byte[MicrophoneModel.BUFF_SIZE];
	        		
	        		if( FifoAudioInput.getList().size() != 0 ){
	        			
		    	        cnt = FifoAudioInput.pop().read(tempBuffer, 0,tempBuffer.length);
			            if (cnt > 0) {
		                    // Write data to the internal buffer of
		                    // the data line where it will be
		                    // delivered to the speaker.
			            	//System.out.print("'");
			            	sourceDataLine.write(tempBuffer, 0, cnt);
		                    sourceDataLine.drain();
		                   // System.out.print("'");
		                }// end if
	        		
	        		}else{
	        			System.out.print("Buffering");
	        	        synchronized (FifoAudioInput.getList()) {
        	        		do{
	        					try {
	        						FifoAudioInput.getList().wait(5000);
	        						System.out.print(".");
	        					} catch (InterruptedException e) {}
	        				}while( FifoAudioInput.getList().size() < MicrophoneModel.MIN_BUFFERED );
	        	        	
	        			}
	        			System.out.println("");	        			
	        		}
	            }
	        } catch (IOException e) {
	        	MicrophoneModel.connected = false;
	            if( Debug.isEnable() )
	            	System.err.println("Erreur de lecture micro "+e.getMessage());
	        }
	        // Block and wait for internal buffer of the
	        // data line to empty.
	        sourceDataLine.drain();
	        sourceDataLine.close();		
		} catch (LineUnavailableException e1) {
			MicrophoneModel.connected = false;
            if( Debug.isEnable() )
            	System.err.println("Erreur de lecture micro "+e1.getMessage());
		}
	}
}
