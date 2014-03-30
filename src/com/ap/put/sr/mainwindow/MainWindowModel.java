package com.ap.put.sr.mainwindow;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainWindowModel {

	private Object [] ports = {6666,6667,6668};
	private static final String IP_REG_EXP= "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	
	
	public MainWindowModel(){
		 
	}
	
 
	
	public Object[] getPortList(){
		return ports;	
	}
	
	public boolean isPortUsed(int p){
		ServerSocket s = null;
		boolean isUsed=false;
		try {
		    s = new ServerSocket(p);
		} catch (IOException e) {
			isUsed=true;
		} finally { 
		    // Clean up
		    if (s != null)
				try {
					s.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		   
		}
		 return isUsed;
	}
	
	
	
	public boolean isIpValid(String ip){
		Pattern pattern = Pattern.compile(IP_REG_EXP);
		Matcher matcher = pattern.matcher(ip);
		  return matcher.matches();	   
	}
	
	
	
	
}
