package com.ap.put.sr.server;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.ap.put.sr.common.GameFrameView;



public class ServerController implements ActionListener {

	
	private ServerModel model;
	private ServerView view;
	
	public ServerController(ServerModel sm, GameFrameView sv){
		
		model=sm;
		view=(ServerView)sv;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
 
		
		String command = e.getActionCommand();
		
		if(command == ServerView.BUTTON_LISTENER_STOP_SERVER){
		//stopujemy nasluch:
		model.setServer(false);
		model.stopServer();
		view.close.setEnabled(true);
		view.setStatusText("WSTRZYMANO PRACÊ SERWERA");
		view.listener.setText(ServerView.BUTTON_LISTENER_START_SERVER);
		
		
		
		}else if(command == ServerView.BUTTON_LISTENER_START_SERVER){
		//startujemy nasluch:
		model.setServer(true);
		view.close.setEnabled(false);
		view.listener.setText(ServerView.BUTTON_LISTENER_STOP_SERVER);
		view.setStatusText("WZNOWIONO PRACÊ SERWERA");
			
		}else if(command == ServerView.BUTTON_CLOSE_SERVER){
			
			model.setLobby(false);
			view.dispose();
			  
			     
			     
		}
		
		 
    
		
	}//action performd
}
