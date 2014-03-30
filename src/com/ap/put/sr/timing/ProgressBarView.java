package com.ap.put.sr.timing;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.ap.put.sr.common.Config;

public class ProgressBarView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JProgressBar progressBar;
	public ProgressBarController controller;

	public ProgressBarView(ProgressBarModel model) {

		controller = new ProgressBarController(model, this);
		createUI();
	}

	public void createUI() {
		progressBar = new JProgressBar(0, Config.TURN_TIME);
		progressBar.setValue(0);
		progressBar.setString(Config.TURN_TIME + " sek.");
		progressBar.setStringPainted(true);

		setLayout(new GridLayout(0, 1));
		add(progressBar);
	}

}
