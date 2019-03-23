package pl.exercise;

public class Point{

    private int x = 0;
    private int y = 0;
    private int value=0;
    private boolean constPoint=false;

    Point(int x, int y, int value, boolean constPoint) {
        this.x=x;
        this.y=y;
        this.value=value;
        this.constPoint=constPoint;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }

    public boolean isConstPoint() {
        return constPoint;
    }



}
