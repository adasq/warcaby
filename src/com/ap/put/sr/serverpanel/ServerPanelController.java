package com.ap.put.sr.serverpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.ap.put.sr.common.NewGameModel;
import com.ap.put.sr.protocol.Protocol;


public class ServerPanelController implements ActionListener {//ladnie ladnie

	public NewGameModel model;
	public ServerPanelView view;
	
	public ServerPanelController(NewGameModel sm, ServerPanelView spv){ // przyzwoicie
		
		model=sm;
		view=spv; 
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String command = e.getActionCommand();
		
		if(command == ServerPanelView.BUTTON_RESTART_GAME){

			model.sendMessage(Protocol.createRestartMessage());
			model.OPPONENT_READY=false;
			model.view.ready.setEnabled(true);		
			model.progressBarModel.notReadyState();
			model.view.setDefaultTurnLabel();
			model.viewGame.restartGame();
			
			//model.progressBarModel.s
			
			
		}else if(command == ServerPanelView.BUTTON_CHANGE_COLOR){ //kurwa Ÿle!!!!!!!!!!!!
			model.sendMessage(Protocol.createColorMessage());// calkiem calkiem
			model.viewGame.PLAYER_BLACK=!model.viewGame.PLAYER_BLACK;// tu moze byc
			model.OPPONENT_READY=false;
			model.view.ready.setEnabled(true);
			model.progressBarModel.notReadyState();
			model.view.setDefaultTurnLabel();
			model.viewGame.restartGame();
			
		}
		
	}

	
	
	
	
	
}
