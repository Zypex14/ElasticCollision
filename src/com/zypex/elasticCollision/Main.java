package com.zypex.elasticCollision;

import RMath.Point;
import RMath.Vector;
import processing.core.PApplet;

public class Main extends PApplet {

    private CollisionManager collisions;

    public void settings(){
        fullScreen();
    }

    public void setup(){
        collisions = new CollisionManager();

//        Generates 100 balls at random positions, for now I just have all of them at mass 1
        for(int i = 0; i < 100; i++){
            collisions.bodies.add(new Ball(25, random(25, displayWidth - 25), random(25, displayHeight - 25), 1));
        }

//        Gets the initial momentum going
        ((Ball) collisions.bodies.get(0)).impulse(new Vector(50, 0));
    }

    public void draw(){
        background(21, 0, 38);
        collisions.update(this);
    }

    public static void main(String[] args){
        PApplet.main("com.zypex.elasticCollision.Main");
    }


}
