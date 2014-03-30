package com.ap.put.sr.game;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Field {

	public Point2D topleftpoint;
	public Rectangle2D square;
	public BufferedImage pattern;

	public boolean isBlack;
	public int column;
	public int row;
	public Pawn pawn;

	public Field(boolean black, Point2D topleftpoint, int col, int row) {
		this.pawn = null;

		this.column = col;
		this.row = row;

		this.isBlack = black;

		try {
			pattern = ImageIO.read(getClass()
					.getResource(
							"/images/field_" + (black ? "black" : "white")
									+ ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.topleftpoint = topleftpoint;
		square = new Rectangle2D.Double((int) topleftpoint.getX(),
				(int) topleftpoint.getY(), GameComponent.GAME_FIELD_SIZE,
				GameComponent.GAME_FIELD_SIZE);

	}

	public String toString() {
		return "(" + this.column + "." + this.row + ")";

	}

}
