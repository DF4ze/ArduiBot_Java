package modeles.dao.communication;

import modeles.DroneActions;

public class ArduiBotServer {
	private String name;
	private String ip;
	private int port;

	public ArduiBotServer( ) {
		this.setName(DroneActions.name);
		this.setIp(DroneActions.ip);
		this.setPort(DroneActions.port);
	}	
	
	public ArduiBotServer( String name, String ip, int port ) {
		this.setName(name);
		this.setIp(ip);
		this.setPort(port);
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

}