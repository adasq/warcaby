package com.ap.put.sr.serverpanel;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.ap.put.sr.common.NewGameModel;



public class ServerPanelView extends JPanel {


	private static final long serialVersionUID = 1L;
	public JButton restartGame;
	public JButton changeColor;
	public ServerPanelController controller;
	
	public static final String BUTTON_RESTART_GAME="Nowa Gra";
	public static final String BUTTON_CHANGE_COLOR="Zmieñ kolor";
	
	public ServerPanelView(NewGameModel ngm){
		controller = new ServerPanelController(ngm, this);
		createGUI();
		
	}
	

	private void createGUI() {
		
		setLayout(new GridLayout(0, 2));

		changeColor=new JButton(BUTTON_CHANGE_COLOR);
		changeColor.addActionListener(controller);
		
		restartGame= new JButton(BUTTON_RESTART_GAME);
		restartGame.addActionListener(controller);
		
		add(restartGame);
		add(changeColor);
	
		
	}
	
	
}
