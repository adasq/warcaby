package com.ap.put.sr.common;

import java.awt.Point;
import java.awt.geom.Point2D;

import com.ap.put.sr.game.Field;
import com.ap.put.sr.game.GameComponent;

public class Arrow {

	public Point2D source;
	public Point2D destination;
	
	
	public Arrow(){
		this.reset();
	}
	
	public void reset(){
		
		this.source=null;
		this.destination=null;
		
	}
	
	public boolean isset(){
		if(this.source !=null && this.destination !=null){
			return true;
		}else{
			return false;
		}
	}
	
	public String print(){


		if(this.isset()){
			return "source: [x:"+this.source.getX()+",y: "+this.source.getY()+"], destination: [x:"+this.destination.getX()+",y: "+this.destination.getY()+"]";	
			
		}else{
			return "brak";
		}
		
		
	}
	
	public void setSource(Field f){
		
		int x= (int)(f.topleftpoint.getX())+(GameComponent.GAME_FIELD_SIZE/2);
		int y= (int)(f.topleftpoint.getY())+(GameComponent.GAME_FIELD_SIZE/2);
		
		this.source = new Point(x, y);
		
	}
	
	
	public void setDestination(Field f){
		
		int x= (int)(f.topleftpoint.getX())+(GameComponent.GAME_FIELD_SIZE/2);
		int y= (int)(f.topleftpoint.getY())+(GameComponent.GAME_FIELD_SIZE/2);
		
		this.destination = new Point(x, y);
		
	}
	
}
