package com.worldconquest;

public class Player extends Business{

    public Player(String name,WorldConquest wc) {
        super(name, wc);
        money = 1_000_000_000;
    }

}
