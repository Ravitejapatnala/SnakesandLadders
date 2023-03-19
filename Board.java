package com.example.snakeandladder;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {

    private ArrayList<Pair<Integer, Integer>> positioncordinates;

    private ArrayList<Integer>SnakeLadder;

    public Board()
    {
        populatepositioncordinates();
        setSnakeLadder();
    }
    private void populatepositioncordinates()
    {
        positioncordinates=new ArrayList<>();
        positioncordinates.add(new Pair<>(0, 0));
        for(int i=0;i<HelloApplication.height;i++)
        {
            for(int j=0;j<HelloApplication.width;j++)
            {
                int Xcord=0;
                if(i%2==0)
                {
                    Xcord=j*HelloApplication.tileSize + HelloApplication.tileSize/2;
                }
                else
                {
                    Xcord=HelloApplication.height * HelloApplication.tileSize- j*HelloApplication.tileSize - HelloApplication.tileSize/2;
                }

                int Ycord=HelloApplication.height * HelloApplication.tileSize- i*HelloApplication.tileSize - HelloApplication.tileSize/2;

                positioncordinates.add(new Pair<>(Xcord, Ycord));
            }
        }
    }

    private void setSnakeLadder()
    {
        SnakeLadder=new ArrayList<>();
        for(int i=0;i<101;i++)
        {
            SnakeLadder.add(i);
        }
        SnakeLadder.set(4,25);
        SnakeLadder.set(13,46);
        SnakeLadder.set(27,5);
        SnakeLadder.set(33,49);
        SnakeLadder.set(40,3);
        SnakeLadder.set(42,63);
        SnakeLadder.set(43,18);
        SnakeLadder.set(50,69);
        SnakeLadder.set(54,31);
        SnakeLadder.set(62,81);
        SnakeLadder.set(66,45);
        SnakeLadder.set(74,92);
        SnakeLadder.set(76,58);
        SnakeLadder.set(89,53);
        SnakeLadder.set(99,41);
    }
    public int getXcordinate(int position)
    {
        if(position>0 && position<=100)
        {
            return positioncordinates.get(position).getKey();
        }
        return -1;
    }

    public int getYcordinate(int position)
    {
        if(position>0 && position<=100)
        {
            return positioncordinates.get(position).getValue();
        }
        return -1;
    }

    public int getSnakeLadder(int position)
    {
        return SnakeLadder.get(position);
    }


    public static void main(String[] args) {
        Board board=new Board();
        for(int i=0;i<board.positioncordinates.size();i++)
        {
            System.out.println(i + " X: " + board.positioncordinates.get(i).getKey()+" Y: "+ board.positioncordinates.get(i).getValue());
        }
    }
}
