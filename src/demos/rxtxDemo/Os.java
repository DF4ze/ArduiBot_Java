package demos.rxtxDemo;


public class Os{
	 
	private static String OS_NAME = System.getProperty("os.name").toLowerCase();
	private static String OS_ARCH = System.getProperty("os.arch").toLowerCase();
 
	private static int OS_NAME_ID;
	public static final int UNKNOW 	= 0;
	public static final int WINDOWS = 1;
	public static final int MAC 	= 2;
	public static final int LINUX 	= 3;
	public static final int SOLARIS = 4;
	
	
	private static int OS_ARCH_ID;
	public static final int x32 	= 1;
	public static final int x86 	= 1;
	public static final int x64 	= 2;
	

	private static int OS_FULL_ID;
	public static final int WINDOWS_X32 	= 1;
	public static final int WINDOWS_X86 	= 1;
	public static final int WINDOWS_X64 	= 2;
	public static final int MAC_X32 		= 3;
	public static final int MAC_X86 		= 3;
	public static final int MAC_X64 		= 4;
	public static final int LINUX_X32 		= 5;
	public static final int LINUX_X86 		= 5;
	public static final int LINUX_X64 		= 6;
	public static final int SOLARIS_X32 	= 7;
	public static final int SOLARIS_X86 	= 7;
	public static final int SOLARIS_X64 	= 8;
	
	static{
		if (isWindows()) {
			OS_NAME_ID = WINDOWS;
		} else if (isMac()) {
			OS_NAME_ID = MAC;
		} else if (isLinux()) {
			OS_NAME_ID = LINUX;
		} else if (isSolaris()) {
			OS_NAME_ID = SOLARIS;
		} else {
			OS_NAME_ID = UNKNOW;
		}		
		
		if( is32() ){
			OS_ARCH_ID = x32;
		}else if( is64() ){
			OS_ARCH_ID = x64;
		}else{
			OS_ARCH_ID = UNKNOW;
		}
			
		if( OS_NAME_ID == WINDOWS && OS_ARCH_ID == x32){
			OS_FULL_ID = WINDOWS_X32;
		}else if( OS_NAME_ID == WINDOWS && OS_ARCH_ID == x64){
			OS_FULL_ID = WINDOWS_X64;
		}else if( OS_NAME_ID == MAC && OS_ARCH_ID == x32){
			OS_FULL_ID = MAC_X32;
		}else if( OS_NAME_ID == MAC && OS_ARCH_ID == x64){
			OS_FULL_ID = MAC_X64;
		}else if( OS_NAME_ID == LINUX && OS_ARCH_ID == x32){
			OS_FULL_ID = LINUX_X32;
		}else if( OS_NAME_ID == LINUX && OS_ARCH_ID == x64){
			OS_FULL_ID = LINUX_X64;
		}else if( OS_NAME_ID == SOLARIS && OS_ARCH_ID == x32){
			OS_FULL_ID = SOLARIS_X32;
		}else if( OS_NAME_ID == SOLARIS && OS_ARCH_ID == x64){
			OS_FULL_ID = SOLARIS_X64;
		}else{
			OS_FULL_ID = UNKNOW;
		}

	}
	
	
	
	public static void main(String[] args) {
		System.out.println(OS_NAME+" "+OS_ARCH+" "+OS_NAME_ID+" "+OS_ARCH_ID);
		System.out.println("OS NAME : "+Os.getName());
		System.out.println("OS ID : "+Os.getNameID());
		System.out.println("ARCH NAME : "+Os.getArch());
		System.out.println("ARCH ID : "+Os.getArchID());
		System.out.println("FULL ID : "+Os.getFullOS());
	}
	
	
	
	public static boolean isWindows() {
		return (OS_NAME.indexOf("win") >= 0);
	}
	public static boolean isMac() {
		return (OS_NAME.indexOf("mac") >= 0);
	}
	public static boolean isLinux() {
		return (OS_NAME.indexOf("nix") >= 0 || OS_NAME.indexOf("nux") >= 0 || OS_NAME.indexOf("aix") > 0 );
	}
	public static boolean isSolaris() {
		return (OS_NAME.indexOf("sunos") >= 0);
	}
	
	public static boolean is32(){
		return (OS_ARCH.indexOf("86") >= 0 || OS_ARCH.indexOf("32") >= 0 );
	}
	public static boolean is64(){
		return (OS_ARCH.indexOf("64") >= 0);		
	}

	public static String getArch() {
		return OS_ARCH;
	}
	public static int getArchID() {
		return OS_ARCH_ID;
	}
	public static String getName() {
		return OS_NAME;
	}
	public static int getNameID() {
		return OS_NAME_ID;
	}
	
	public static int getFullOS() {
		return OS_FULL_ID;
	}
 
}