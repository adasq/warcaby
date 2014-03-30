package com.ap.put.sr.game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import com.ap.put.sr.common.NewGameModel;

public class MouseMoving implements MouseMotionListener {

	public NewGameModel model;
	public GameComponent viewGame;

	public MouseMoving(NewGameModel m, GameComponent v) {
		model = m;
		viewGame = v;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		// System.out.println("mouseDragged");

		if (viewGame.currentPawn != null) {
			viewGame.currentPawn.setDragPossition(e.getPoint());
			viewGame.repaint();
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		// System.out.println("mouseMoved");

	}

}
