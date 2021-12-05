import java.util.*;

public class CelestialBody {
    String name;
    double mass;
    double xCoord, yCoord;
    double xVel, yVel;
    int size;
    float[] rgb;

    /**
     * Constructor
     * @param name
     * @param mass mass in kg
     * @param initialXCoord initial x coordinate on the Jframe; upscaled
     * @param initialYCoord initial y coordinate on the Jframe; upscaled
     * @param initialXVel initial x velocity on the Jframe; upscaled
     * @param initialYVel initial y velocity on the Jframe; upscaled
     * @param size size in pixels for Jframe rendering
     */
    public CelestialBody(String name, double mass, int initialXCoord, int initialYCoord, double initialXVel, double initialYVel, int size) {
        this.name = name;
        this.mass = mass;
        xCoord = initialXCoord * CelestialBodyDemo.scale;
        yCoord = initialYCoord * CelestialBodyDemo.scale;
        xVel = initialXVel * CelestialBodyDemo.scale;
        yVel = initialYVel * CelestialBodyDemo.scale;
        this.size = size;
        rgb = randomColor();
    }

    /**
     * Generates a random rgb color
     * @return array of floats representing a color in rgb form
     */
    private float[] randomColor() {
        Random rng = new Random();
        return new float[] {rng.nextFloat(), rng.nextFloat(), rng.nextFloat()};
    }

    /**
     * Gets the distance between two celestial bodies
     * @param body second body
     * @return distance between method caller and second body
     */
    private double getDist(CelestialBody body) {
        return Math.hypot(xCoord - body.xCoord, yCoord - body.yCoord);
    }

    /**
     * Gets the force between two celestial bodies (Newton's law of universal gravitation)
     * @param body second body
     * @return force between method caller and second body
     */
    private double getForce(CelestialBody body) {
        double  G = 6.67408e-11,
                m1 = mass,
                m2 = body.getMass(),
                r = getDist(body);
        return G * (m1 * m2) / (r * r);
    }

    /**
     * Gets the x component of the force between two celestial bodies
     * @param body second body
     * @return x component of the force between method caller and second body
     */
    private double getXForce(CelestialBody body) {
        return getForce(body) * (xCoord - body.xCoord) / getDist(body);
    }

    /**
     * Gets the y component of the force between two celestial bodies
     * @param body second body
     * @return y component of the force between method caller and second body
     */
    private double getYForce(CelestialBody body) {
        return getForce(body) * (yCoord - body.yCoord) / getDist(body);
    }

    /**
     * Gets the x component of the net force acting on one body due to all the other bodies
     * @param bodies list of celestial bodies
     * @return x component of the net force between method caller and all the other bodies
     */
    private double getNetXForce(List<CelestialBody> bodies) {
        double netXForce = 0.0;
        for (int i = 0; i < bodies.size(); i++) {
            if (!bodies.get(i).getName().equals(getName()))
                netXForce += getXForce(bodies.get(i));
        }
        return netXForce;
    }

    /**
     * Gets the y component of the net force acting on one body due to all the other bodies
     * @param bodies list of celestial bodies
     * @return y component of the net force between method caller and all the other bodies
     */
    private double getNetYForce(List<CelestialBody> bodies) {
        double netYForce = 0.0;
        for (int i = 0; i < bodies.size(); i++) {
            if (!bodies.get(i).getName().equals(getName())) {
                netYForce += getYForce(bodies.get(i));
            }
        }
        return netYForce;
    }

    /**
     * Updates the velocities and positions of a body byway of... physics
     * @param bodies list of celestial bodies
     */
    public void update(List<CelestialBody> bodies) {
        double  xAcc = getNetXForce(bodies) / mass,
                yAcc = getNetYForce(bodies) / mass;
        xCoord += xVel += xAcc;
        yCoord += yVel += yAcc;
    }

    public String getName() {
        return name;
    }

    public double getMass() {
        return mass;
    }

    /**
     * @return downscaled x coordinate for Jframe rendering
     */
    public double getXCoord() {
        return xCoord / CelestialBodyDemo.scale;
    }

    /**
     * @return downscaled y coordinate for Jframe rendering
     */
    public double getYCoord() {
        return yCoord / CelestialBodyDemo.scale;
    }

    public int getSize() {
        return size;
    }

    public float[] getRgb() {
        return rgb;
    }

    @Override
    public String toString() {
        return "CelestialBody{" +
                "name='" + name + '\'' +
                ", mass=" + mass +
                ", xCoord=" + xCoord +
                ", yCoord=" + yCoord +
                ", xVel=" + xVel +
                ", yVel=" + yVel +
                ", size=" + size +
                ", rgb=" + Arrays.toString(rgb) +
                '}';
    }
}