package com.ap.put.sr.common;

import java.awt.Toolkit;

import javax.swing.JFrame;

import com.ap.put.sr.mainwindow.MainWindowModel;
import com.ap.put.sr.mainwindow.MainWindowView;

public class Initialize {

	/**
	 * @param args
	 */
	private static final String WINDOW_TITLE="Gra sieciowa: Warcaby";
	private static final String WINDOW_ICON_PATH="/images/icon.gif";
	private static final int WINDOW_WIDTH=400;
	private static final int WINDOW_HEIGHT=300;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		MainWindowModel m = new MainWindowModel();
		MainWindowView v = new MainWindowView(m);
	 
		Config.init();
		
		int x =(Config.SCREEN_WIDTH/2)-(WINDOW_WIDTH/2);
		int y= (Config.SCREEN_HEIGHT/2)-(WINDOW_HEIGHT/2);
		
		JFrame frame = new JFrame(WINDOW_TITLE);
		frame.setBounds(x, y, WINDOW_WIDTH, WINDOW_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
 
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
			       Initialize.class.getResource(WINDOW_ICON_PATH)));

	//	frame.setAlwaysOnTop(true);	

		frame.add(v);
		frame.setVisible(true);	
	}
}
