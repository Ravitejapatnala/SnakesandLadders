package com.example.snakeandladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static final int tileSize=40, width=10, height=10, buttonline=height*tileSize+50, infoline=height*tileSize+20;

    Player playerFirst, playerSecond;
    boolean firstPlayerturn=true;
    boolean gamestart=false;
    int diceValue;
    private Pane createContent()
    {
        Pane root=new Pane();
        root.setPrefSize(width*tileSize, height*tileSize+100);
        for(int i=0;i<height;i++)
        {
            for(int j=0;j<width;j++)
            {
                Tile tile=new Tile(tileSize);
                tile.setTranslateX(i*tileSize);
                tile.setTranslateY(j*tileSize);
                root.getChildren().addAll(tile);
            }
        }

        //putting image on the root
        Image img=new Image("C:\\Users\\akhil\\IdeaProjects\\SnakeandLadder\\src\\img.png"); //loads the image.
        ImageView boarImage=new ImageView(); //creating an object for image to show it on the UI.
        boarImage.setFitWidth(width*tileSize); //setting width for viewing the image
        boarImage.setFitHeight(height*tileSize);//setting height for viewing the image
        boarImage.setImage(img); //setting the image.


        Button StartButton=new Button("Start");
        StartButton.setTranslateX(180);
        StartButton.setTranslateY(buttonline);

        Button Player1= new Button("Player 1");
        Player1.setTranslateX(10);
        Player1.setTranslateY(buttonline);

        Button Player2=new Button("Player 2");
        Player2.setTranslateX(330);
        Player2.setTranslateY(buttonline);

        Label label=new Label("Start the game");
        label.setTranslateX(160);
        label.setTranslateY(infoline);


        playerFirst=new Player("P1", Color.BLACK, tileSize/2);
        playerSecond=new Player("P2", Color.WHITE, tileSize/2-5);

        Player1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gamestart) {
                    if (firstPlayerturn) {
                        diceValue = rollDice();
                        label.setText("Dice : " + diceValue);
                        playerFirst.movePlayer(diceValue);
                        firstPlayerturn =! firstPlayerturn;
                        if(playerFirst.CheckWinner())
                        {
                            label.setText("Winner is "+playerFirst.getName());
                            StartButton.setText("Restart");
                            StartButton.setDisable(false);
                            firstPlayerturn=true;
                            gamestart=false;
                        }
                    }
                }
            }
        });

        Player2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gamestart) {
                    if (!firstPlayerturn) {
                        diceValue = rollDice();
                        label.setText("Dice : " + diceValue);
                        playerSecond.movePlayer(diceValue);
                        firstPlayerturn =! firstPlayerturn;
                        if(playerSecond.CheckWinner())
                        {
                            label.setText("Winner is "+playerSecond.getName());
                            StartButton.setText("Restart");
                            StartButton.setDisable(false);
                            firstPlayerturn=true;
                            gamestart=false;
                        }
                    }
                }
            }
        });

        StartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gamestart=true;
                StartButton.setDisable(true);
                playerFirst.setStart();
                playerSecond.setStart();
            }
        });
        root.getChildren().addAll(boarImage,StartButton,Player1,Player2,label,playerFirst.getCoin(), playerSecond.getCoin());

        return root;
    }

    private int rollDice()
    {
        return (int)(Math.random()*6+1);
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}