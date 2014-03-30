package com.ap.put.sr.mainwindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.JOptionPane;

import com.ap.put.sr.chatpanel.NewGameView;
import com.ap.put.sr.client.ClientView;
import com.ap.put.sr.common.NewGameModel;
import com.ap.put.sr.game.GameComponent;
import com.ap.put.sr.server.ServerModel;
import com.ap.put.sr.server.ServerView;
import com.ap.put.sr.timing.ProgressBarModel;
import com.ap.put.sr.timing.ProgressBarView;

public class MainWindowController implements ActionListener {

	public MainWindowModel model;
	public MainWindowView view;

	public MainWindowController(MainWindowModel m, MainWindowView v) {

		model = m;
		view = v;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		 
		
		String command = e.getActionCommand();

		if (command == MainWindowView.START_SERVER) {

			int port = view.getServerPort();
	
			if (model.isPortUsed(port)) {
				JOptionPane.showMessageDialog(null, "Port jest zajêty!", "B³¹d", JOptionPane.ERROR_MESSAGE, null);
				return;
			}
			 
			ServerModel sm = new ServerModel(port);
			ServerView sv = new ServerView(sm);
			sm.addServerView(sv);
			Thread t = new Thread(sm);
			t.start();

		} else if (command == MainWindowView.CONNECT_TO_SERVER) {
			
			String ip = view.getConnectIp();
			int port = view.getConnectPort();
			//System.out.println(ip);
			
			
			if (!model.isIpValid(ip)) {
				JOptionPane.showMessageDialog(null, "Adres IP niepoprawny!", "B³¹d", JOptionPane.ERROR_MESSAGE, null);
				return;
			}

			
	
			Socket socket=null;
			
			try {
				socket= new Socket(ip,port);
				ClientView cv = new ClientView();
				

				
				
				NewGameModel ngm = new NewGameModel(socket, cv);
				
				
				
				ProgressBarModel pbm = new ProgressBarModel(ngm);
				ProgressBarView pbv = new ProgressBarView(pbm);
				pbm.addView(pbv);
				
				ngm.addProgressBarModel(pbm);
				
				//HistoryPanelView hp = new HistoryPanelView(ngm);	
				//ngm.addHistoryView(hp);
				
				GameComponent gc = new GameComponent(ngm, false);
				ngm.addNewGameComponentView(gc);
				
				NewGameView ngv = new NewGameView(ngm);
				ngm.addNewGameView(ngv);
				
				cv.addToPageEnd(pbv);
				cv.addToLineEnd(ngv);
				cv.addToCenter(gc);
				cv.resize();
				ngm.start();
			} catch (Exception e2) {

				JOptionPane.showMessageDialog(null, "B³¹d podczas próby po³¹czenia.", "B³¹d", JOptionPane.ERROR_MESSAGE, null);
			}
			
			
		}

	}

}
