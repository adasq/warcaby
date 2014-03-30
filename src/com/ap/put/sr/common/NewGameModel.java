package com.ap.put.sr.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.ap.put.sr.chatpanel.NewGameView;
import com.ap.put.sr.game.GameComponent;
import com.ap.put.sr.protocol.Protocol;
import com.ap.put.sr.timing.ProgressBarModel;

public class NewGameModel extends Thread {

	public NewGameView view;
	public GameComponent viewGame;
//	public HistoryPanelView viewHistory;
	public Socket client;
	public BufferedReader in;
	public PrintWriter out;
	public boolean threadLoop;
	public GameFrameView gamewindow;
	public ProgressBarModel progressBarModel;
	public boolean OPPONENT_READY;

	public NewGameModel(Socket client, GameFrameView gfv) {
		this.client = client;
		threadLoop = true;
		gamewindow = gfv;
		OPPONENT_READY=false;
	}
	
	public void addProgressBarModel(ProgressBarModel m){
		progressBarModel=m;
	}

	public void addNewGameView(NewGameView ngv) {
		view = ngv;
	}

	public void addNewGameComponentView(GameComponent ngv) {
		viewGame = ngv;
	}

//	public void addHistoryView(HistoryPanelView hv) {
//		viewHistory = hv;
//
//	}

	public void sendMessage(String msg) {
		out.println(msg);
		out.flush();

	}

	public void closeConnection() {
		try {
			out.close();
			in.close();
			client = null;
			threadLoop = false;
			gamewindow.dispose();

		} catch (IOException e1) {
			System.out.println("closeConnection() problem");
		}
	}

	public void run() {// ========================================================================================

		try {
			in = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
			out = new PrintWriter(client.getOutputStream(), true);
		} catch (IOException e) {
			System.out.println("Read failed1");
		}

		String line; 
		sendMessage("9sync");
		while (threadLoop) {
			try {
				
				line = in.readLine();
				Protocol.decodeMessage(line, this);
				//progressBarModel.restart();
				
				
				//viewHistory.appendHistoryText(line);

			} catch (IOException e) {
				System.out.println("problem z odczytem/wyslaniem komunikatu");

				closeConnection();
			}

		}

		System.out.println("watek klienta zakonczony");

	}// =====================================================================================================

}// class
