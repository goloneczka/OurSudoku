package pl.exercise;

public class SudokuField {

    private int x = 0;
    private int y = 0;
    private int value=0;
    private boolean constPoint=false;

    SudokuField(int x, int y, int value, boolean constPoint) {
        this.x=x;
        this.y=y;
        this.value=value;
        this.constPoint=constPoint;
    }
    SudokuField(int x, int y, int value) {
        this.x=x;
        this.y=y;
        this.value=value;
        this.constPoint=false;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getFieldValue() {
        return value;
    }
    public void setFieldValue(int value) {
        this.value = value;
    }

    public boolean isConstPoint() {
        return constPoint;
    }



}
