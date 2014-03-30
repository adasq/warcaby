package com.ap.put.sr.common;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Config {

	public static int SCREEN_WIDTH;
	public static int SCREEN_HEIGHT;
	
	public static final int TURN_TIME=50;
	
	
	public static final String MESSAGE_WIN=" Gratulacje! Wygra³eœ! :)";
	public static final String MESSAGE_LOSE=" Niestety, przegra³eœ. :(";

	public Config() {

	}

	public static void init() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension scrnsize = toolkit.getScreenSize();
		SCREEN_WIDTH = (int) scrnsize.getWidth();
		SCREEN_HEIGHT = (int) scrnsize.getHeight();

	}

}
