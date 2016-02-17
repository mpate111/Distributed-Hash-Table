//package peer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertyFile {
	
	public Properties prop = null;
	InputStream input = ReadPropertyFile.class.getClassLoader().getResourceAsStream("config.properties");
	
	public ReadPropertyFile() throws Exception{
		prop =new Properties();
		prop.load(input);
		
	}

	public String getServerPort(){
		return prop.getProperty("portNo1");	
	}
	
	public String getServerPort1(){
		return prop.getProperty("portNo2");	
	}
	
	public String getServerPort2(){
		return prop.getProperty("portNo3");	
	}
	
	public String getServerPort3(){
		return prop.getProperty("portNo4");	
	}
	
	public String getServerPort4(){
		return prop.getProperty("portNo5");	
	}
	
	public String getServerPort5(){
		return prop.getProperty("portNo6");	
	}
	
	public String getServerPort6(){
		return prop.getProperty("portNo7");	
	}
	
	public String getServerPort7(){
		return prop.getProperty("portNo8");	
	}
	
	public String getHost(){
		return prop.getProperty("host1");
	}
	
	public String getHost1(){
		return prop.getProperty("host2");
	}
	
	public String getHost2(){
		return prop.getProperty("host3");
	}
	
	public String getHost3(){
		return prop.getProperty("host4");
	}
	
	public String getHost4(){
		return prop.getProperty("host5");
	}
	
	public String getHost5(){
		return prop.getProperty("host6");
	}
	
	public String getHost6(){
		return prop.getProperty("host7");
	}
	
	public String getHost7(){
		return prop.getProperty("host8");
	}
	
	
}
