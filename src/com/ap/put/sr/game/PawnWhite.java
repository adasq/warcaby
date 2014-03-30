package com.ap.put.sr.game;

import java.io.IOException;

import javax.imageio.ImageIO;

public class PawnWhite extends Pawn {

	public PawnWhite(Field currentField, int id) {
		super(currentField, id);
		isPawnBlack = false;
		try {
			pattern = ImageIO.read(getClass().getResource(
					"/images/pawn_white.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}// construct


	public void setAsDamka(){
		
		try {
			pattern = ImageIO.read(getClass().getResource(
					"/images/pawn_white_damka.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.isPawnDamka=true;
	}








}