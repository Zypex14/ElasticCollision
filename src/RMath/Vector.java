package RMath;

public class Vector{

    private double x;
    private double y;
    private double r;
    private double theta;

    public Vector(Point p){
        x = p.x;
        y = p.y;
        r = Util.dist(p.x, p.y);
        theta = Util.angle(p.x, p.y);
    }

    public Vector(PolarPoint p){
        r = p.r;
        theta = p.theta;
        x = r * Math.cos(theta);
        y = r * Math.sin(theta);
    }

    public Vector(double x, double y){
        this.x = x;
        this.y = y;
        r = Util.dist(x, y);
        theta = Util.angle(x, y);
    }

    /***
     * @param v1 x component of the vector if the mode is rectangular, but magnitude of the vector if mode is polar
     * @param v2 y component of the vector if the mode is rectangular, but theta of the vector if mode is polar
     * @param m how the vector is constructed
     */
    public Vector(double v1, double v2, Mode m){
        switch (m){
            case RECT:
                x = v1;
                y = v2;
                r = Util.dist(x, y);
                theta = Util.angle(x, y);
                break;

            case POLAR:
                r = v1;
                theta = v2;
                x = r * Math.cos(theta);
                y = r * Math.sin(theta);
                break;
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getMagnitude() {
        return r;
    }

    public double getTheta() {
        return theta;
    }

    public void setX(double x) {
        this.x = x;
        this.r = Util.dist(x, y);
        this.theta = Util.angle(x, y);
    }

    public void setY(double y) {
        this.y = y;
        this.r = Util.dist(x, y);
        this.theta = Util.angle(x, y);
    }

    public void setMagnitude(double r) {
        this.r = r;
        this.x = r * Math.cos(theta);
        this.y = r * Math.sin(theta);
    }

    public void setTheta(double theta) {
        this.theta = theta;
        this.x = r * Math.cos(theta);
        this.y = r * Math.sin(theta);
    }

    /***
     * Creates a unit vector with the angle of theta
     * @param theta
     */
    public Vector(double theta){
        this(1, theta, Mode.POLAR);
    }

    /***
     * returns the dot product of the two vectors
     * @param other
     * @return
     */
    public double dot(Vector other){
        return x * other.getX() + y * other.getY();
    }

    public double getAngleDiff(Vector other){
        return Math.acos(dot(other) / (r * other.getMagnitude()));
    }

    /***
     * Creates a copy of the vector
     * @param v the vector that is being copied
     */
    public Vector(Vector v){
        this(v.x, v.y);
    }

    public Vector scale(double scalar){
        return new Vector(x * scalar, y * scalar);
    }

    /***
     * Returns a new vector with this and the other vector
     * @param other
     * @return
     */
    public Vector add(Vector other){
        Vector out = new Vector(this);

        out.x += other.x;
        out.y += other.y;

        out.r = Util.dist(out.x, out.y);
        out.theta = Util.angle(out.x, out.y);

        return out;
    }

    public Point toPoint(){
        return new Point(x, y);
    }

    public PolarPoint toPolarPoint(){
        return new PolarPoint(r, theta);
    }

    public Segment toSegment(){
        return new Segment(new Point(0,0), toPoint());
    }

    public Segment toSegment(Point start){
        return new Segment(start, toPoint());
    }

    public String toString(){
        return "[" + x + ", " + y + ", " + r + ", " + theta + "]";
    }

    public String toString(Mode m){
        switch (m){
            case RECT: return toPoint().toString();
            case POLAR: return toPolarPoint().toString();
        }
        return "";
    }

    public enum Mode{
        RECT,
        POLAR
    }
}
