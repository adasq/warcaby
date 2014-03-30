package com.ap.put.sr.game;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Pawn {

	public BufferedImage pattern;
	public Field currentField;
	public Point2D currentPossition;
	public boolean isPawnBlack;
	public boolean isPawnDamka;
	public int id;

	public Pawn(Field currentField, int id) {
		this.isPawnDamka=false;
		this.id = id;
		this.currentField = currentField;
		this.currentField.pawn = this;
		currentPossition = new Point2D.Double(currentField.topleftpoint.getX(),
				currentField.topleftpoint.getY());

	}// constructor

	public void setDragPossition(Point2D p) {
		currentPossition = new Point2D.Double(p.getX()
				- GameComponent.GAME_FIELD_SIZE / 2, p.getY()
				- GameComponent.GAME_FIELD_SIZE / 2);
	}

	public void changeCurrentField(Field f) {
		currentField.pawn = null;
		currentField = f;
		currentField.pawn = this;
		currentPossition = f.topleftpoint;
	}// changeCurrentField

	@Override
	public String toString() {
		return "(" + this.currentField.column + "." + this.currentField.row
				+ ")";

	}
}
