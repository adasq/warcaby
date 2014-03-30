package com.ap.put.sr.protocol;

import javax.swing.JOptionPane;

import com.ap.put.sr.common.NewGameModel;
import com.ap.put.sr.game.PawnBlack;
import com.ap.put.sr.game.PawnWhite;

public class Protocol {

	public static final int ACTION_MOVE = 0;
	public static final int ACTION_RESTART_GAME = 1;
	public static final int ACTION_CHANGE_COLOR = 2;
	public static final int ACTION_SEND_MESSAGE = 3;
	public static final int ACTION_DISCONNECT = 4;
	public static final int ACTION_OPONNENT_READY = 5;
	public static final int ACTION_START_GAME = 6;
	public static final int ACTION_NO_MOVE = 7;
	public static final int ACTION_SET_DAMKA = 8;
	
	
	public static final String DELIMITER = ".";

	public Protocol() {

	}

	public static String createTextMessage(String txt) {
		StringBuilder s = new StringBuilder();
		s.append(ACTION_SEND_MESSAGE);
		s.append(txt);
		return s.toString();
	}

	public static String createMoveMessage(int id, int col, int row, boolean doubleKill) {

		StringBuilder s = new StringBuilder();
		s.append(ACTION_MOVE);
		s.append(id);
		s.append(DELIMITER);
		s.append(col);
		s.append(DELIMITER);
		s.append(row);
		s.append(DELIMITER);
		s.append(doubleKill?1:0);

		return s.toString();
	}
	
	public static String createDamkaMessage(int id){
		
		StringBuilder s = new StringBuilder();
		s.append(ACTION_SET_DAMKA);
		s.append(id);
		
		return s.toString();
	}
	
	public static String createNoActionMessage() {
		StringBuilder s = new StringBuilder();
		s.append(ACTION_NO_MOVE);
		return s.toString();
	}

	public static String createStartGameMessage() {
		StringBuilder s = new StringBuilder();
		s.append(ACTION_START_GAME);
		return s.toString();
	}

	public static String createReadyMessage() {
		StringBuilder s = new StringBuilder();
		s.append(ACTION_OPONNENT_READY);
		return s.toString();
	}

	public static String createRestartMessage() {
		StringBuilder s = new StringBuilder();
		s.append(ACTION_RESTART_GAME);
		return s.toString();
	}

	public static String createColorMessage() {
		StringBuilder s = new StringBuilder();
		s.append(ACTION_CHANGE_COLOR);
		return s.toString();
	}

	public static String createCloseMessage() {
		StringBuilder s = new StringBuilder();
		s.append(ACTION_DISCONNECT);
		return s.toString();

	}

	public static void decodeMessage(String msg, NewGameModel model) {

		int action = Integer.parseInt(msg.substring(0, 1));
		String content;
		String[] tab;
		switch (action) {

		case Protocol.ACTION_SET_DAMKA:
			
			content = msg.substring(1);
			tab = content.split("\\" + DELIMITER);
			int pawnId = Integer.parseInt(tab[0]);
			System.out.println("ID DAMKI:"+pawnId);
			
			if(model.viewGame.PLAYER_BLACK){
				//gramy czarnymi to w bialych szukamy
				for (PawnWhite w : model.viewGame.white) {
					if(w.id == pawnId){
						w.setAsDamka();
					}
				}
				
			}else{
				//gramy bialymi to w czarnych szukamy
				for (PawnBlack b : model.viewGame.black) {
					if(b.id == pawnId){
						b.setAsDamka();
					}
				}
			}
			
			
			break;
		
		
		case Protocol.ACTION_MOVE:
			content = msg.substring(1);
			tab = content.split("\\" + DELIMITER);
			int id = Integer.parseInt(tab[0]);
			int col = 7 - Integer.parseInt(tab[1]);
			int row = 7 - Integer.parseInt(tab[2]);
			boolean doubleKill= (Integer.parseInt(tab[3])==1)?true:false;
			
			System.out.println("Double Kill:"+ doubleKill);
			
			model.viewGame.controllerMouseTyping.oponentMove(id, col, row, doubleKill);
			model.viewGame.repaint();
			if(!doubleKill)model.progressBarModel.yourTurn();

			break;

		case Protocol.ACTION_RESTART_GAME:

			model.OPPONENT_READY = false;
			model.view.ready.setEnabled(true);
			model.progressBarModel.notReadyState();
			model.view.setDefaultTurnLabel();
			model.viewGame.restartGame();
			break;

		case Protocol.ACTION_CHANGE_COLOR:
			model.viewGame.PLAYER_BLACK = !model.viewGame.PLAYER_BLACK;
			model.OPPONENT_READY = false;
			model.view.ready.setEnabled(true);
			model.progressBarModel.notReadyState();
			model.view.setDefaultTurnLabel();
			model.viewGame.restartGame();
			break;

		case Protocol.ACTION_SEND_MESSAGE:
			content = msg.substring(1);
			model.view.appendTextToChat(content);
			break;
		case Protocol.ACTION_DISCONNECT:
			System.out.println("ACTION_DISCONNECT");
			JOptionPane.showMessageDialog(null, "Przeciwnik roz³¹czy³ siê.",
					"Info", JOptionPane.INFORMATION_MESSAGE);
			model.closeConnection();
			break;
		case Protocol.ACTION_OPONNENT_READY:
			model.OPPONENT_READY = true;

			break;
		case Protocol.ACTION_START_GAME:
			model.OPPONENT_READY = true;
			System.out.println("gramy!");

			if (model.viewGame.PLAYER_BLACK) {
				model.viewGame.changeTurn();
				model.progressBarModel.yourTurn();
			}
			model.view.updateTurnLabel();

			break;
		case Protocol.ACTION_NO_MOVE:
			model.viewGame.changeTurn();
			model.view.updateTurnLabel();			
			model.progressBarModel.yourTurn();
			break;

		default:
			System.out.println("default: "+msg);
			break;
		}
	}

}
