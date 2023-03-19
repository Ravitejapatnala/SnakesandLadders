package com.example.snakeandladder;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {
    private String name;
    private int position;
    private Circle coin;

    private static Board gameboard=new Board();

    public Player(String name, Color coincolor, int coinSize)
    {
        this.name=name;
        position=0;
        coin=new Circle(coinSize);
        coin.setFill(coincolor);
        movePlayer(1);
    }

    public void setStart()
    {
        position=0;
        movePlayer(1);
    }

    public Circle getCoin() {
        return coin;
    }

    public void movePlayer(int diceValue)
    {
        if(position + diceValue <=100) {
            position=position+diceValue;
            TranslateTransition secondmove=null, firstmove = translatePlayer();

            int newpos=gameboard.getSnakeLadder(position);
            if(newpos!=position)
            {
                position=newpos;
                secondmove = translatePlayer();
            }
            if(secondmove==null)
            {
                firstmove.play();
            }
            else
            {
                SequentialTransition seq= new SequentialTransition(firstmove, new PauseTransition(Duration.millis(500)), secondmove);
                seq.play();
            }
            //coin.setTranslateX(gameboard.getXcordinate(position));
            //coin.setTranslateY(gameboard.getYcordinate(position));
        }
    }

    private TranslateTransition translatePlayer(){
        TranslateTransition move = new TranslateTransition(Duration.millis(1000), coin);
        move.setToX(gameboard.getXcordinate(position));
        move.setToY(gameboard.getYcordinate(position));
        move.setAutoReverse(false);
        return move;
    }

    public boolean CheckWinner()
    {
        if(position==100)
        {
            return true;
        }
        else {
            return false;
        }
    }

    public String getName() {
        return name;
    }
}
