package application;

import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

import javafx.scene.paint.Color;

public class BackgroundLogic extends Main {
	//creates all of the variables we need to use in multiple methods
	private static Rectangle a = new Rectangle();
	private static Rectangle b = new Rectangle();
	private static Rectangle c = new Rectangle();
	private static Rectangle d = new Rectangle();
	private static Rectangle e = new Rectangle();
	private static Rectangle f = new Rectangle();
	private static Rectangle g = new Rectangle();
	private static Rectangle h = new Rectangle();
	private static Rectangle i = new Rectangle();
	private static Rectangle j = new Rectangle();
	private static ArrayList<Rectangle> upperPoles = new ArrayList<Rectangle>();
	private static ArrayList<Rectangle> lowerPoles = new ArrayList<Rectangle>();
	private static Rectangle hitbox = new Rectangle(Xpos - 15, Ypos - 15, 30, 30);

	//creates the hitboxes for the poles
	public static void create() {
		upperPoles.add(a);
		upperPoles.add(b);
		upperPoles.add(c);
		upperPoles.add(d);
		upperPoles.add(e);
		lowerPoles.add(f);
		lowerPoles.add(g);
		lowerPoles.add(h);
		lowerPoles.add(i);
		lowerPoles.add(j);
		//sets all of the poles' hitbox parameters
		for (int i = 0; i < upperPoles.size(); i++) {
			upperPoles.get(i).setWidth(50);
			upperPoles.get(i).setHeight((int) rectHeight[i]);
			lowerPoles.get(i).setWidth(50);
			lowerPoles.get(i).setHeight(600);
			lowerPoles.get(i).setY((int) rectHeight[i] + 150);
		}
	}
	//updates all of the hitboxes' positions
	public static void logicUpdate() {
		for (int i = 0; i < upperPoles.size(); i++) {
			upperPoles.get(i).setX(rectX[i]);
			upperPoles.get(i).setHeight(rectHeight[i]);
			lowerPoles.get(i).setX(rectX[i]);
			lowerPoles.get(i).setHeight(600);
			lowerPoles.get(i).setY((int) rectHeight[i] + 150);
		}
		//the - 15 is because Xpos and Ypos are the middle of the circle, and hitbox needs to be 
		hitbox.setX(Xpos - 15);
		hitbox.setY(Ypos - 15);
	}
	
	//detects if the hitbox has hit a boundary or pole
	public static int collisionDetection() {
		for (int i = 0; i < upperPoles.size(); i++) {
			if (hitbox.getX() + hitbox.getWidth() > lowerPoles.get(i).getX()
					&& hitbox.getX() < lowerPoles.get(i).getX() + 50) {
				if (hitbox.getY() < upperPoles.get(i).getHeight() || hitbox.getY() + hitbox.getHeight() > lowerPoles.get(i).getY()) {
					isAlive = 1;
				}
			}
		}
		if(hitbox.getY() > 600){
			isAlive = 1;
		}
		if(hitbox.getY() < 0){
			Yspeed = 0;
			Ypos = 30;
		}
		return isAlive;
	}
}
