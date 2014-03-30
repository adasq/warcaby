package com.ap.put.sr.common;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.ap.put.sr.game.GameComponent;

public class GameFrameView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JComponent centerPanel;
	public JPanel pageStartPanel;
	public JPanel pageEndPanel;
	public JPanel lineEndPanel;

	public GameFrameView() {
		// window
		this.setLayout(new BorderLayout());

		int componentSize = GameComponent.GAME_FIELDS_TOTAL_WIDTH
				+ (2 * GameComponent.GAME_BOARD_BORDER);
		this.setSize(componentSize + 150, componentSize + 80);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		// createControls();
	}

	public void createControls() {
		pageStartPanel = new JPanel();
		addToPageStart(pageStartPanel);

		pageEndPanel = new JPanel();
		addToPageEnd(pageEndPanel);

		centerPanel = new JPanel();
		addToCenter(centerPanel);

		lineEndPanel = new JPanel();
		addToLineEnd(lineEndPanel);

	}

	public void resize() {
		int componentSize = GameComponent.GAME_FIELDS_TOTAL_WIDTH
				+ (2 * GameComponent.GAME_BOARD_BORDER);	
		centerPanel.setSize(componentSize, componentSize);
		int h = componentSize + (int) pageStartPanel.getSize().getHeight()
				+ (int) pageEndPanel.getSize().getHeight();
		int w = componentSize + 150;
		this.setSize(w, h + 41);
	}

	public void addToLineEnd(JPanel p) {
		remove(lineEndPanel);
		lineEndPanel = p;
		add(lineEndPanel, BorderLayout.LINE_END);
		getContentPane().validate();

	}

	public void addToPageStart(JPanel p) {
		remove(pageStartPanel);
		pageStartPanel = p;
		add(pageStartPanel, BorderLayout.PAGE_START);
		getContentPane().validate();
		System.out.println("~~" + pageStartPanel.getSize().getHeight());
	}

	public void addToPageEnd(JPanel p) {
		remove(pageEndPanel);
		pageEndPanel = p;
		add(pageEndPanel, BorderLayout.PAGE_END);
		getContentPane().validate();

	}

	public void addToCenter(JComponent p) {

		remove(centerPanel);
		add(p, BorderLayout.CENTER);
		getContentPane().validate();

	}

}
