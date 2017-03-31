package edu.utep.cs.cs4330.slider;

public class Place {
    /* The coordinates to this Place */
    private int x;
    private int y;
    /* The Value at this Place */
    private int value;

    public Place(int i, int j) {
        x = i;
        y = j;
        value = -1;
    }

    /* Checks equivalency to another value */
    public boolean is(int t) {
        return t==value;
    }

    /* Mutators */
    public void setValue(int v) {
        value = v;
    }

    /* Accessors */
    public int getValue() {
        return value;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public boolean isEmpty() {
        return value==-1;
    }
}