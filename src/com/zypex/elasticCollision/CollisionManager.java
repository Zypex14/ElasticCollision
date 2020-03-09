package com.zypex.elasticCollision;

import processing.core.PApplet;

import java.util.ArrayList;

public class CollisionManager{

    public ArrayList<Collidable> bodies = new ArrayList<>();

    public void update(PApplet app){
        for(Collidable body : bodies) {
            body.checked.clear();
            for(Collidable other : bodies){
//                Avoids double collisions
                if(body == other || body.checked.contains(other)) continue;
                body.collide(other);
//                Adds other body to list of collisions
                body.checked.add(other);
            }
//            Calls the update function on the body
            body.update(app);
        }
    }

}
