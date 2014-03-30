package com.ap.put.sr.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

import com.ap.put.sr.chatpanel.NewGameView;
import com.ap.put.sr.common.NewGameModel;
import com.ap.put.sr.game.GameComponent;
import com.ap.put.sr.serverpanel.ServerPanelView;
import com.ap.put.sr.timing.ProgressBarModel;
import com.ap.put.sr.timing.ProgressBarView;

public class ServerModel implements Runnable {

	public boolean FLobby;
	public boolean FServer;
	public int port;
	public Socket client;
	public ServerSocket server;
	public NewGameModel newGameModel;
	public ServerView view;

	public ServerModel(int port) {

		System.out.println("server zrobiony");
		this.port = port;
		this.server = null;
		this.client = null;

		newGameModel = null;

	}

	public void addServerView(ServerView newView) {
		view = newView;

	}

	public void setLobby(boolean lobbyNew) {
		FLobby = lobbyNew;
	}

	public void setServer(Boolean FServeNew) {
		FServer = FServeNew;
	}

	public void closeServer() {
		try {
			if (server != null)
				server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void startServer() {
		if (server == null) {
			try {
				server = new ServerSocket(port);
				setLobby(true);
				setServer(true);
			} catch (IOException e) {
				System.out.println("Could not listen on port");
				JOptionPane.showMessageDialog(null, "x", "err",
						JOptionPane.ERROR_MESSAGE);
				setLobby(false);
				setServer(false);
			}
		}
	}

	public void stopServer() {
		try {
			server.close();
			server = null;
			// client=null;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		startServer();

		System.out.println("server stoi!");
		view.setStatusText("Servet stoi");

		while (FLobby) {
			while (FServer) {
				startServer();

				if (client == null) {
					view.setStatusText("OCZEKIWANIE NA KLIENTA...");

					try {

						client = server.accept();

						setServer(false);
						setLobby(false);
						view.listener.setEnabled(false);

						// new model
						newGameModel = new NewGameModel(client, view);

						ProgressBarModel pbm = new ProgressBarModel(
								newGameModel);
						ProgressBarView pbv = new ProgressBarView(pbm);
						pbm.addView(pbv);

						newGameModel.addProgressBarModel(pbm);

						// new views

						GameComponent gameComponentView = new GameComponent(
								newGameModel, true);
						newGameModel.addNewGameComponentView(gameComponentView);

						NewGameView gameView = new NewGameView(newGameModel);
						newGameModel.addNewGameView(gameView);

						ServerPanelView spv = new ServerPanelView(newGameModel);
						
						//HistoryPanelView hv = new HistoryPanelView(newGameModel);

						//newGameModel.addHistoryView(hv);

						// add views to main frame

						view.addToLineEnd(gameView);

						view.addToPageStart(spv);
						view.addToPageEnd(pbv);

						view.addToCenter(gameComponentView);
						view.resize();
						// start game!
						newGameModel.start();

					} catch (IOException e) {
						System.out.print("k");
					}

				} else {
					view.setStatusText("Obs³uga klienta");
				}
				System.out.println("koniec petli FServer");
			}// while serverOn

			System.out.println("koniec petli FLobby");

		}// while serverRunning

		closeServer();
		System.out.println("koniec watku serwera");
	}// run
}
