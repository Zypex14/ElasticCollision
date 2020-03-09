package com.zypex.elasticCollision;

import processing.core.PApplet;

import java.util.ArrayList;

public interface Collidable {

    public void collide(Collidable other);

    public void update(PApplet app);

//    This is a log of all the checked collisions, so double checking does not occur
    public ArrayList<Collidable> checked = new ArrayList<>();
}
