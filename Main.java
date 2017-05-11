package application;

import java.io.*;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import com.sun.javafx.scene.control.skin.VirtualFlow.ArrayLinkedList;
import javafx.scene.control.*;
import javafx.animation.AnimationTimer;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.image.Image;

public class Main extends Application {
	private static int Ypos= 300;
	private static JSONArray scoreData = new JSONArray();
	private static int Yspeed= 0;
	private static int k = 0;
	private static boolean alive = true;
	private static Label points = new Label("Score: " + 0);
	private static int[] rectX = { 800, 1000, 1200, 1400, 1600 };
	private static double[] rectHeight = {Math.random() * 400 + 50, Math.random() * 400 + 50, Math.random() * 400 + 50,
			Math.random() * 400 + 50, Math.random() * 400 + 50 };
	public static void updateValues() {
			Ypos += Yspeed;
			
			if(Yspeed < 7){
				Yspeed += 1;
			}
			if (alive = true){
			k++;
			}
			points.setText("Score: " + k);
			
			
			
	}
	public static void getScores() {
		try {
			FileReader reader = new FileReader("src/application/highscores.json");
			JSONParser parsepatel = new JSONParser();
			scoreData = (JSONArray) parsepatel.parse(reader);
			reader.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	public static void updateRectangles() {
		for (int i = 0; i < rectX.length; i++) {
			rectX[i] -= 2;
			if (rectX[i] < -200) {
				rectX[i] = 800;
				rectHeight[i] = Math.random() * 450 + 50;
			}
		}
	}
	
	@Override
	public void start(Stage primaryStage) {
			int counting = 0; 
			

			primaryStage.setTitle("Swimmy Duck");
			BorderPane root = new BorderPane();
			
			Scene scene = new Scene(root, 600, 600);
		
			Circle player = new Circle(15, 15, 15, Color.ORANGE);
			player.centerXProperty().bind(root.widthProperty().divide(3));
	  		player.setCenterY(Ypos);
	  		
			
			Button sgame = new Button("Start Game!");
			sgame.resize(400, 200);
			sgame.setLayoutX(100);
			Button highs = new Button("High Scores!");
			highs.resize(400, 200);
			highs.setLayoutX(100);
			highs.setLayoutY(200);

			Button exit = new Button("Exit Game :(");
			exit.resize(400, 200);
			exit.setLayoutX(100);
			exit.setLayoutY(400);
			root.getChildren().addAll(exit, sgame, highs);

			sgame.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					root.getChildren().remove(sgame);
					root.getChildren().remove(exit);
					root.getChildren().remove(highs);
					
					points.resize(200, 60);
				
					root.getChildren().addAll(player,points);
					Rectangle a = new Rectangle();
					Rectangle b = new Rectangle();
					Rectangle c = new Rectangle();
					Rectangle d = new Rectangle();
					Rectangle e = new Rectangle();
					
					ArrayList<Rectangle> upperPoles = new ArrayList<Rectangle>();
					upperPoles.add(a);
					upperPoles.add(b);
					upperPoles.add(c);
					upperPoles.add(d);
					upperPoles.add(e);
					
					Rectangle f = new Rectangle();
					Rectangle g = new Rectangle();
					Rectangle h = new Rectangle();
					Rectangle l = new Rectangle();
					Rectangle j = new Rectangle();
					ArrayList<Rectangle> lowerPoles = new ArrayList<Rectangle>();
					lowerPoles.add(f);
					lowerPoles.add(g);
					lowerPoles.add(h);
					lowerPoles.add(l);
					lowerPoles.add(j);
					
					for (int i = 0; i < upperPoles.size(); i++) {
						if(alive == true){
						upperPoles.get(i).setWidth(50);
						upperPoles.get(i).setHeight((int)rectHeight[i]);
						upperPoles.get(i).setFill(Color.FORESTGREEN);
						root.getChildren().add(upperPoles.get(i));
						}
					}
					
					for(int k = 0; k < lowerPoles.size(); k++){
						if(alive == true){
						lowerPoles.get(k).setWidth(50);
						lowerPoles.get(k).setHeight(600);
						lowerPoles.get(k).setY((int)rectHeight[k] + 100);
						lowerPoles.get(k).setFill(Color.FORESTGREEN);
						root.getChildren().add(lowerPoles.get(k));
						}
					}
					scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
					      
					      if(key.getCode()==KeyCode.SPACE) {
					    	  Yspeed = -11;
					    	  
					    	  RotateTransition rt = new RotateTransition(Duration.millis(3000), player);
					    	     rt.setByAngle(50);
					    	     rt.setCycleCount(4);
					    	     rt.setAutoReverse(true);
					    	 
					    	     rt.play();
					    	  }
					     
					    	  });
					 new AnimationTimer() {
							public void handle(long currentNanoTime) {
								updateValues();
								updateRectangles();
								for (int i = 0; i < upperPoles.size(); i++) {
									upperPoles.get(i).setX(rectX[i]);
									upperPoles.get(i).setHeight(rectHeight[i]);
									lowerPoles.get(i).setX(rectX[i]);
									lowerPoles.get(i).setHeight(600);
									lowerPoles.get(i).setY((int)rectHeight[i] + 125);
									
								}    
								RotateTransition fall = new RotateTransition(Duration.millis(3000), player);
						    	     fall.setByAngle(80);
						    	     fall.setCycleCount(4);
						    	     fall.setAutoReverse(true);
						    	 
						    	     fall.play();
								player.setCenterY(Ypos);
							
								
								
							}
						
						
					}.start();
					if(alive == false){
						root.getChildren().removeAll(player);
						Label num1 = new Label("1");
						num1.resize(200, 45);
						Label num2 = new Label("2");
						num2.resize(200, 45);
						num2.setLayoutX(0);
						num2.setLayoutY(45);
						Label num3 = new Label("3");
						num3.resize(200, 45);
						num3.setLayoutX(0);
						num3.setLayoutY(90);
						Label num4 = new Label("4");
						num4.resize(200, 45);
						num4.setLayoutX(0);
						num4.setLayoutY(135);
						Label num5 = new Label("5");
						num5.resize(200, 45);
						num5.setLayoutX(0);
						num5.setLayoutY(180);
						Label num6 = new Label("6");
						num6.resize(200, 45);
						num6.setLayoutX(0);
						num6.setLayoutY(225);
						Label num7 = new Label("7");
						num7.resize(200, 45);
						num7.setLayoutX(0);
						num7.setLayoutY(270);
						Label num8 = new Label("8");
						num8.resize(200, 45);
						num8.setLayoutX(0);
						num8.setLayoutY(315);
						Label num9 = new Label("9");
						num9.resize(200, 45);
						num9.setLayoutX(0);
						num9.setLayoutY(360);
						Label num10 = new Label("10");
						num10.resize(200, 45);
						num10.setLayoutX(0);
						num10.setLayoutY(405);
						Button main = new Button("Main Menu!");
						main.resize(100, 100);
						main.setLayoutX(150);
						main.setLayoutY(500);
						main.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {
								root.getChildren().remove(num1);
								root.getChildren().remove(num2);
								root.getChildren().remove(num3);
								root.getChildren().remove(num4);
								root.getChildren().remove(num5);
								root.getChildren().remove(num6);
								root.getChildren().remove(num7);
								root.getChildren().remove(num8);
								root.getChildren().remove(num9);
								root.getChildren().remove(num10);
								root.getChildren().remove(main);
								root.getChildren().addAll(sgame, exit, highs);

							}
						});
						root.getChildren().addAll(num1, num2, num3, num4, num5, num6, num7, num8, num9, num10, main);
					
						}
					      }
					      
					});
					
			   
		
		
	
			highs.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					root.getChildren().remove(sgame);
					root.getChildren().remove(exit);
					root.getChildren().remove(highs);
					Label num1 = new Label("1");
					num1.resize(200, 45);
					Label num2 = new Label("2");
					num2.resize(200, 45);
					num2.setLayoutX(0);
					num2.setLayoutY(45);
					Label num3 = new Label("3");
					num3.resize(200, 45);
					num3.setLayoutX(0);
					num3.setLayoutY(90);
					Label num4 = new Label("4");
					num4.resize(200, 45);
					num4.setLayoutX(0);
					num4.setLayoutY(135);
					Label num5 = new Label("5");
					num5.resize(200, 45);
					num5.setLayoutX(0);
					num5.setLayoutY(180);
					Label num6 = new Label("6");
					num6.resize(200, 45);
					num6.setLayoutX(0);
					num6.setLayoutY(225);
					Label num7 = new Label("7");
					num7.resize(200, 45);
					num7.setLayoutX(0);
					num7.setLayoutY(270);
					Label num8 = new Label("8");
					num8.resize(200, 45);
					num8.setLayoutX(0);
					num8.setLayoutY(315);
					Label num9 = new Label("9");
					num9.resize(200, 45);
					num9.setLayoutX(0);
					num9.setLayoutY(360);
					Label num10 = new Label("10");
					num10.resize(200, 45);
					num10.setLayoutX(0);
					num10.setLayoutY(405);
					Button main = new Button("Main Menu!");
					main.resize(100, 100);
					main.setLayoutX(150);
					main.setLayoutY(500);
					main.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							root.getChildren().remove(num1);
							root.getChildren().remove(num2);
							root.getChildren().remove(num3);
							root.getChildren().remove(num4);
							root.getChildren().remove(num5);
							root.getChildren().remove(num6);
							root.getChildren().remove(num7);
							root.getChildren().remove(num8);
							root.getChildren().remove(num9);
							root.getChildren().remove(num10);
							root.getChildren().remove(main);
							root.getChildren().addAll(sgame, exit, highs);

						}
					});
					root.getChildren().addAll(num1, num2, num3, num4, num5, num6, num7, num8, num9, num10, main);
				}
			});
			exit.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Platform.exit();

				}	
			});
			primaryStage.setScene(scene);
			primaryStage.show();

			
			
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
