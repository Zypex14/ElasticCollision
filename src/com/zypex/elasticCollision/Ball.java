package com.zypex.elasticCollision;

import RMath.Point;
import RMath.PolarPoint;
import RMath.Util;
import RMath.Vector;
import processing.core.PApplet;

public class Ball implements Collidable{

    public double radius;
    public Point position;
    public double mass;
    public Vector velocity = new Vector(new Point(0,0));

    @Override
    public void collide(Collidable other) {
//        This is the only case for now, because the only collidable is a ball
        if(other instanceof Ball){
                Ball otherBall = (Ball) other;
            if(Util.dist(position, otherBall.position) <= radius + otherBall.radius) {
                Vector angle = new Vector(Util.angle(position, otherBall.position));

//                The math formula is the dot product of the velocity and the unit vector of the center to center vector
//                This returns how much of the momentum is transferred, then the unit vector with the angle is scaled
//                to that magnitude, and that is the transferred momentum vector
                Vector impulse = angle.scale(velocity.dot(angle));
//                Same thing, but with the other balls velocity
                Vector impulseOther = angle.scale(otherBall.velocity.dot(angle));

//                Adding up the impulses
                Vector netImpulse = impulse.add(impulseOther.scale(-1));

                Vector translation = new Vector(radius + otherBall.radius - Util.dist(position, otherBall.position), angle.getTheta(), Vector.Mode.POLAR);
//                Moving the balls away from each other to prevent clipping
                position.translate(translation.scale(-0.5));
                otherBall.position.translate(translation.scale(0.5));

                impulse(netImpulse.scale(-1));
                otherBall.impulse(netImpulse);
            }
        }
    }

    public Ball(double radius, double x, double y, double mass){
        this.radius = radius;
        this.position = new Point(x,y);
        this.mass = mass;
    }

    @Override
    public void update(PApplet app) {
        app.fill(49, 46, 219);
        app.noStroke();
        app.circle((float)position.x, (float)position.y, (float)radius * 2);
        position = position.translate(velocity);

        if(position.x - radius < 0 || position.x + radius > app.displayWidth){
            velocity.setX(-velocity.getX());
            position.x = (position.x > app.displayWidth / 2f)? app.displayWidth - radius : radius;
        }

        if(position.y - radius < 0 || position.y + radius > app.displayHeight){
            velocity.setY(-velocity.getY());
            position.y = (position.y > app.displayHeight / 2f)? app.displayHeight - radius : radius;
        }

//        Adds friction
//        velocity = velocity.scale(0.99);
    }

    public void impulse(Vector momentum){
        velocity = velocity.add(momentum.scale(1d / mass));
    }

    public Vector getMomentum(){
        return velocity.scale(mass);
    }
}
