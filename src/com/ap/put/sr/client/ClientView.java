package com.ap.put.sr.client;

import com.ap.put.sr.common.Config;
import com.ap.put.sr.common.GameFrameView;

public class ClientView extends GameFrameView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClientView() {

		super();
		this.setTitle("Klient");
		createControls();
		setLocation(Config.SCREEN_WIDTH / 2, 0);
		this.setVisible(true);
	}

}
