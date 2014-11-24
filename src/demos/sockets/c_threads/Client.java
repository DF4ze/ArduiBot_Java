package demos.sockets.c_threads;
import java.io.IOException;
import java.net.Socket;

public class Client {

	public static void main(String[] zero){
		
		Socket socket;
		try {
		socket = new Socket("localhost",2009);
		socket.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}