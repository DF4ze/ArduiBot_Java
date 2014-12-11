package modeles.entites;

public class SocketModel {

	private String name;
	private String ip;
	private int port;
	
	
	public SocketModel() {
	
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public int getPort() {
		return port;
	}


	public void setPort(int port) {
		this.port = port;
	}
	
	public String toString(){
		return name+" ("+ip+":"+port+")";
	}

}
