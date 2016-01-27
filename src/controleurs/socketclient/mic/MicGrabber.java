package controleurs.socketclient.mic;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.sound.sampled.AudioInputStream;

import modeles.dao.communication.beansFifos.FifoAudioInput;
import modeles.entites.MicrophoneModel;

public class MicGrabber extends Thread{

	private Socket socket;
	
	public MicGrabber( Socket sock ) {
		socket = sock;
	}

	public void run(){
		System.out.println("En attente de reception audio");
    	;

        ObjectInputStream inObject = null;
		try {
			inObject = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e1) {
			System.err.println("Erreur lors de de la récupération du socket micro : "+e1.getMessage());
			return;
		}	
    	byte [] buf = new byte[ MicrophoneModel.BUFF_SIZE ];
        while( MicrophoneModel.connected ){
	        try {
				inObject.readFully(buf);
		        AudioInputStream audioInputStream = new AudioInputStream(new ByteArrayInputStream(buf),MicrophoneModel.audioFormat, buf.length / MicrophoneModel.audioFormat.getFrameSize());
		        
		        FifoAudioInput.add(audioInputStream);
		        
	        }catch( Exception e ){
	        	MicrophoneModel.connected = false;
	        	break;
	        }
		}
			
        try {
			inObject.close();
		} catch (IOException e) {}
	}
}
