/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.min1D;

/**
 *
 * @author group12
 */
public class Interval {

    private double leftBoundary;
    private double rightBoundary;
    private double length;

    public Interval(double leftBoundary, double rightBoundary) {
        this.leftBoundary = leftBoundary;
        this.rightBoundary = rightBoundary;
        this.length = Math.abs(leftBoundary - rightBoundary);
    }

    public double getLeftBoundary() {
        return leftBoundary;
    }

    public double getRightBoundary() {
        return rightBoundary;
    }

    public double getMinBoundary() {
        return (rightBoundary < leftBoundary) ? rightBoundary : leftBoundary;
    }

    public double getMaxBoundary() {
        return (rightBoundary < leftBoundary) ? leftBoundary : rightBoundary;
    }

    public double getLength() {
        return length;
    }

    @Override
    public String toString() {
        return "[" + getMinBoundary() + "; " + getMaxBoundary() + "]";
    }
}
