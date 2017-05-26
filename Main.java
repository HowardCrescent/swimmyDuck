package application;

import java.io.*;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
import javafx.scene.Node;
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
	public static int Ypos = 300;
	public static JSONArray scoreData = new JSONArray();
	public static long[] highScore = new long[10];
	public static int Yspeed = 0;
	public static int score = 0;
	public static int isAlive = 0;
	public static Label points = new Label("Score: " + 0);
	public static int Xpos = 200;
	public static Label score1;
	public static Label score2;
	public static Label score3;
	public static Label score4;
	public static Label score5;
	public static Label score6;
	public static Label score7;
	public static Label score8;
	public static Label score9;
	public static Label score10;

	public static int[] rectX = { 800, 1000, 1200, 1400, 1600 };
	public static double[] rectHeight = { Math.random() * 400 + 50, Math.random() * 400 + 50, Math.random() * 400 + 50,
			Math.random() * 400 + 50, Math.random() * 400 + 50 };

	public static void getScores() {
		try {
			FileReader reader = new FileReader("src/application/highscores.json");
			JSONParser parsepatel = new JSONParser();
			scoreData = (JSONArray) parsepatel.parse(reader);
			reader.close();
			for(int i = 0; i < 10; i++){
				highScore[i] =(long) ((JSONObject) scoreData.get(i)).get("score");
			}
			Arrays.sort(highScore);
			
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public static void updateValues() {
		Ypos += Yspeed;

		if (Yspeed < 7) {
			Yspeed += 1;
		}
		if (isAlive == 0) {
			score++;
		}
		points.setText("Score: " + score);
	}

	public static void submitScore() {
		try {
			
			for(int i = 0; i < 10 && score > highScore[i]; i++){
				if(i == 0){
					highScore[i] = score;
				}
				else{
					long temp = highScore[i];
					highScore[i] = highScore[i-1];
					highScore[i-1] = temp;
				}
			}
			
			FileWriter writer = new FileWriter("src/application/highscores.json");
			JSONArray newHighScores = new JSONArray();
			for(int i = 0; i < 10; i++){
				JSONObject newScore = new JSONObject();
				newScore.put("score", highScore[i]);
				
				newHighScores.add(newScore);
			}
			
			writer.write(newHighScores.toJSONString());
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void updateRectangles() {
		if (isAlive == 0) {
			for (int i = 0; i < rectX.length; i++) {
				rectX[i] -= 2;
				if (rectX[i] < -200) {
					rectX[i] = 800;
					rectHeight[i] = Math.random() * 450 + 50;
				}
			}
		}
	}

	public void drawHighScores() {
		score1 = new Label("" + highScore[9]);
		score1.resize(200, 45);
		score1.setLayoutX(100);
		score1.setLayoutY(0);

		score2 = new Label("" + highScore[8]);
		score2.resize(200, 45);
		score2.setLayoutX(100);
		score2.setLayoutY(45);

		score3 = new Label("" + highScore[7]);
		score3.resize(200, 45);
		score3.setLayoutX(100);
		score3.setLayoutY(90);

		score4 = new Label("" + highScore[6]);
		score4.resize(200, 45);
		score4.setLayoutX(100);
		score4.setLayoutY(135);

		score5 = new Label("" + highScore[5]);
		score5.resize(200, 45);
		score5.setLayoutX(100);
		score5.setLayoutY(180);

		score6 = new Label("" + highScore[4]);
		score6.resize(200, 45);
		score6.setLayoutX(100);
		score6.setLayoutY(225);

		score7 = new Label("" + highScore[3]);
		score7.resize(200, 45);
		score7.setLayoutX(100);
		score7.setLayoutY(270);

		score8 = new Label("" + highScore[2]);
		score8.resize(200, 45);
		score8.setLayoutX(100);
		score8.setLayoutY(315);

		score9 = new Label("" + highScore[1]);
		score9.resize(200, 45);
		score9.setLayoutX(100);
		score9.setLayoutY(360);

		score10 = new Label("" + highScore[0]);
		score10.resize(200, 45);
		score10.setLayoutX(100);
		score10.setLayoutY(405);
	}

	@Override
	public void start(Stage primaryStage) {

		drawHighScores();

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

		BackgroundLogic.create();
		primaryStage.setTitle("Swimmy Duck");
		BorderPane root = new BorderPane();

		Scene scene = new Scene(root, 600, 600);

		Circle player = new Circle(15, 15, 15, Color.ORANGE);
		player.centerXProperty().bind(root.widthProperty().divide(3));
		player.setCenterY(Ypos);

		Label instructions = new Label("Press spacebar");
		root.getChildren().add(instructions);
		instructions.resize(150, 60);

		Label instructions2 = new Label("to jump!");
		root.getChildren().add(instructions2);
		instructions2.resize(150, 60);
		instructions2.setLayoutY(60);

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
				root.getChildren().removeAll(sgame, instructions, instructions2, exit, highs);
				root.getChildren().remove(exit);
				root.getChildren().remove(highs);

				points.resize(200, 60);

				root.getChildren().addAll(player, points);
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
					if (isAlive == 0) {
						upperPoles.get(i).setWidth(50);
						upperPoles.get(i).setHeight((int) rectHeight[i]);
						upperPoles.get(i).setFill(Color.FORESTGREEN);
						root.getChildren().add(upperPoles.get(i));

					}
				}

				for (int k = 0; k < lowerPoles.size(); k++) {
					if (isAlive == 0) {
						lowerPoles.get(k).setWidth(50);
						lowerPoles.get(k).setHeight(600);
						lowerPoles.get(k).setY((int) rectHeight[k] + 125);
						lowerPoles.get(k).setFill(Color.FORESTGREEN);
						root.getChildren().add(lowerPoles.get(k));
					}
				}
				scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {

					if (key.getCode() == KeyCode.SPACE) {
						Yspeed = -11;
					}
				});
				new AnimationTimer() {
					public void handle(long currentNanoTime) {
						BackgroundLogic.logicUpdate();
						updateValues();
						updateRectangles();

						Xpos = (int) root.getWidth() / 3;
						for (int i = 0; i < upperPoles.size(); i++) {
							upperPoles.get(i).setX(rectX[i]);
							upperPoles.get(i).setHeight(rectHeight[i]);
							lowerPoles.get(i).setX(rectX[i]);
							lowerPoles.get(i).setHeight(600);
							lowerPoles.get(i).setY((int) rectHeight[i] + 125);
						}
						player.setCenterY(Ypos);
						if (BackgroundLogic.collisionDetection() == 1) {
							if (isAlive == 1) {
								submitScore();
								getScores();
								drawHighScores();
								root.getChildren().removeAll(lowerPoles);
								root.getChildren().removeAll(upperPoles);
								root.getChildren().removeAll(a, b, c, d, e, f, g, h, l, j);
								root.getChildren().removeAll(player, points);
								Label curScore = new Label("Your score was: " + score);
								curScore.resize(200, 45);
								curScore.setLayoutX(300);
								curScore.setLayoutY(45);

								main.setOnAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent event) {
										root.getChildren().removeAll(num1, num2, num3, num4, num5, num6, num7, num8,
												num9, num10, main, curScore, score1, score2, score3, score4, score5,
												score6, score7, score8, score9, score10);
										root.getChildren().addAll(sgame, exit, highs, instructions, instructions2);
										isAlive = 0;
										score = 0;
										Yspeed = 0;
										Ypos = 300;

										Xpos = 200;
										for (int i = 0; i < rectX.length; i++) {
											rectX[i] = 800 + i * 200;
											rectHeight[i] = Math.random() * 400 + 50;
										}
									}
								});
								root.getChildren().addAll(num1, num2, num3, num4, num5, num6, num7, num8, num9, num10,
										main, curScore, score1, score2, score3, score4, score5, score6, score7, score8,
										score9, score10);
								stop();
							}
						}
					}
				}.start();
			}
		});

		highs.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				root.getChildren().removeAll(sgame, instructions, instructions2, exit, highs);

				main.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {

						root.getChildren().removeAll(num1, num2, num3, num4, num5, num6, num7, num8, num9, num10, main,
								score1, score2, score3, score4, score5, score6, score7, score8, score9, score10);
						root.getChildren().addAll(sgame, exit, highs, instructions, instructions2);

					}
				});
				drawHighScores();
				root.getChildren().addAll(num1, num2, num3, num4, num5, num6, num7, num8, num9, num10, main, score1,
						score2, score3, score4, score5, score6, score7, score8, score9, score10);
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
		getScores();
		launch(args);
	}

}
