package demos.rxtxDemo;

import gnu.io.CommPortIdentifier;

import java.util.Enumeration;

public class Lanceur {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(" System.getProperty(\"os.name\") : "+System.getProperty("os.name"));
		System.out.println(" System.getProperty(\"os.arch\") : "+System.getProperty("os.arch"));

		//
		// Platform specific port name, here= a Unix name
		//
		// NOTE: On at least one Unix JavaComm implementation JavaComm 
//		       enumerates the ports as "COM1" ... "COMx", too, and not
//		       by their Unix device names "/dev/tty...". 
//		       Yet another good reason to not hard-code the wanted
//		       port, but instead make it user configurable.
		//
		String wantedPortName = "COM7";
		 
		//
		// Get an enumeration of all ports known to JavaComm
		//
		@SuppressWarnings("rawtypes")
		Enumeration portIdentifiers = CommPortIdentifier.getPortIdentifiers();
		//
		// Check each port identifier if 
		//   (a) it indicates a serial (not a parallel) port, and
		//   (b) matches the desired name.
		//
		CommPortIdentifier portId = null;  // will be set if port found
		while (portIdentifiers.hasMoreElements())
		{
		    CommPortIdentifier pid = (CommPortIdentifier) portIdentifiers.nextElement();
		    if(pid.getPortType() == CommPortIdentifier.PORT_SERIAL )//&&   pid.getName().equals(wantedPortName)) 
		    {
		    	System.out.println(" Port : "+ pid.getName());
		    	if( pid.getName().equals(wantedPortName) ){
			        portId = pid;
			        break;
		    	}
		    }
		}
		if(portId == null)
		{
		    System.err.println("Could not find serial port " + wantedPortName);
		    System.exit(1);
		}else
			System.out.println("Trouv����������!!!!!!!!!!!!!");
		//
		// Use port identifier for acquiring the port
		//
	}

}