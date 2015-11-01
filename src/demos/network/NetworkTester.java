package demos.network;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;

public class NetworkTester {

	public NetworkTester() {
		// TODO Auto-generated constructor stub
	}

	
	public static ArrayList<String> getIps(){
		ArrayList<String> ips = new ArrayList<String>();
	 
		try{
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
	 
	         while (interfaces.hasMoreElements()) {  // carte reseau trouvee
	            NetworkInterface interfaceN = (NetworkInterface)interfaces.nextElement(); 
	            Enumeration<InetAddress> ienum = interfaceN.getInetAddresses();
	            while (ienum.hasMoreElements()) {  // retourne l adresse IPv4 et IPv6
	                InetAddress ia = ienum.nextElement();
	                String adress = ia.getHostAddress().toString();
	 
//	                if( adress.length() < 16){          //On s'assure ainsi que l'adresse IP est bien IPv4
//	                    if(adress.startsWith("127")){  //Ce n'est pas l'adresse IP Local' 
//	                        System.out.println(ia.getHostAddress());
//	                    }
//	                    else if(adress.indexOf(":") > 0){
//	                        System.out.println(ia.getHostAddress()); // les ":" indique que c'est une IPv6"
//	                    }
//	                } 
	                
	                if( adress.startsWith("192") || adress.startsWith("10") ){
	                	System.out.println(adress);
	                	System.out.println( interfaceN.getDisplayName() );
	                }
	 
	                ips.add(adress);        
	            }
	        }
	    }
	    catch(Exception e){
	        System.out.println("pas de carte reseau");
	        e.printStackTrace();
	    }
	 
	    return ips;
	}
	
	
	public static void main(String[] args) {
		NetworkTester.getIps();
	}
}
