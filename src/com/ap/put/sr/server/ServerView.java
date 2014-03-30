package com.ap.put.sr.server;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ap.put.sr.common.GameFrameView;


public class ServerView extends GameFrameView{
	

	private static final long serialVersionUID = 1L;
	public ServerController controller;
	public JLabel statusLabel;
	public JButton close;
	public JButton listener;
	public JButton btnListenerStop;
	public static final String BUTTON_CLOSE_SERVER = "Zamknij pokój";
	public static final String BUTTON_LISTENER_START_SERVER = "WZNÓW dzia³anie serwer";
	public static final String BUTTON_LISTENER_STOP_SERVER = "WSTRZYMAJ dzia³anie serwera";
	public static final String STATUS = "Status: ";


	
	public ServerView(ServerModel sm){
		
		super();
		this.controller = new ServerController(sm, this); 		
		this.setTitle("Server");	
		createControls();
		setLocation(0, 0);
		
		this.setVisible(true);
	}
	
	
	
	public void createControls() {



		// serverPanel
		statusLabel = new JLabel(STATUS);
		close = new JButton(BUTTON_CLOSE_SERVER);
		close.addActionListener(controller);
		close.setEnabled(false);

		listener = new JButton(BUTTON_LISTENER_STOP_SERVER);
		listener.addActionListener(controller);

		// add
		pageStartPanel = new JPanel(new GridLayout(3, 1));
		pageStartPanel.add(statusLabel);
		pageStartPanel.add(listener);
		pageStartPanel.add(close);
		addToPageStart(pageStartPanel);
		
		
		pageEndPanel = new JPanel();
		//pageEndPanel.add(new JLabel("pageEndPanel"));
		addToPageEnd(pageEndPanel);
		

		centerPanel = new JPanel();
		//centerPanel.add(new JLabel("server"));
		addToCenter(centerPanel);

		lineEndPanel = new JPanel();
		//lineEndPanel.add(new JLabel("lien end"));
		addToLineEnd(lineEndPanel);


		
	}
	
	public void setStatusText(String txt) {
		// TODO Auto-generated method stub
		statusLabel.setText(STATUS + txt);
	}

}
