package br.com.desafioYaman.Yaman;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Helper {

	private Properties prop = new Properties();
    private InputStream input = null;
	public Helper() {
		
	}
	public String getValueConfigProperties(String configName){
	    try {
	        this.input = new FileInputStream("config.properties");
	        this.prop.load(input);
	        return prop.getProperty(configName);
	        
	    } catch (IOException ex) {
	        ex.printStackTrace();
	        return null;
	    } finally {
	        if (input != null) {
	            try {
	                input.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
}
