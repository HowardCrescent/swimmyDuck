package application;

import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

import javafx.scene.paint.Color;

public class BackgroundLogic extends Main {

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
	private static Rectangle hitbox = new Rectangle(Xpos-15, Ypos-15, 30, 30);

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
		for (int i = 0; i < upperPoles.size(); i++) {
			upperPoles.get(i).setWidth(50);
			upperPoles.get(i).setHeight((int) rectHeight[i]);
			lowerPoles.get(i).setWidth(50);
			lowerPoles.get(i).setHeight(600);
			lowerPoles.get(i).setY((int) rectHeight[i] + 150);
		}
	}

	public static void logicUpdate() {
		for (int i = 0; i < upperPoles.size(); i++) {
			upperPoles.get(i).setX(rectX[i]);
			upperPoles.get(i).setHeight(rectHeight[i]);
			lowerPoles.get(i).setX(rectX[i]);
			lowerPoles.get(i).setHeight(600);
			lowerPoles.get(i).setY((int) rectHeight[i] + 150);
		}
		hitbox.setX(Xpos-15);
		hitbox.setY(Ypos-15);
	}

	public static int collisionDetection() {
		for (int i = 0; i < upperPoles.size(); i++) {
			if (hitbox.getX() + hitbox.getWidth() > lowerPoles.get(i).getX()
					&& hitbox.getX() < lowerPoles.get(i).getX() + 50) {
				if (hitbox.getY() < upperPoles.get(i).getHeight() || hitbox.getY() + 30 > lowerPoles.get(i).getY()) {
					isAlive = 1;
				}
				
			}
		}
		return isAlive;
	}
}
