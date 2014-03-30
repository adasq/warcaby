package com.ap.put.sr.chatpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.ap.put.sr.common.NewGameModel;
import com.ap.put.sr.protocol.Protocol;

public class NewGameController implements ActionListener, KeyListener {

	NewGameModel model;
	NewGameView view;

	NewGameController(NewGameModel m, NewGameView v) {
		model = m;
		view = v;
	}

	public void sendMessage() {
		
		
		String toSend = view.getContentToSend();
		toSend=toSend.trim().replaceAll("\\s+", " ");
		if(toSend.isEmpty())return;
		model.sendMessage(Protocol.createTextMessage(toSend));
		view.setContentToSend("");

		view.setChat(view.getChat() + "\r\n" + NewGameView.PLAYER + toSend);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();

		if (command == NewGameView.SEND_BUTTON_TEXT) {
			sendMessage();

		} else if (command == NewGameView.DISCONNECT_CLIENT_TEXT) {

			model.sendMessage(Protocol.createCloseMessage());
			model.closeConnection();
		} else if (command == NewGameView.READY_BUTTON_TEXT) {
			if(model.OPPONENT_READY){
				model.sendMessage(Protocol.createStartGameMessage());
				if(model.viewGame.PLAYER_BLACK){
					model.viewGame.changeTurn();
					model.progressBarModel.yourTurn();
									}
				model.view.updateTurnLabel();
				
			}else{
				model.sendMessage(Protocol.createReadyMessage());
			}
			view.ready.setEnabled(false);
			
		}

	

	}// action

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		// e.getSource()
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			sendMessage();
		}
	}

}
