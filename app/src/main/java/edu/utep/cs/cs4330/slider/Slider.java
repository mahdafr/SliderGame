/*
 * @author Mahdokht Afravi
 * @created 03.30 R
 *
 * The Controller of the Slider Game.
 */

package edu.utep.cs.cs4330.slider;

public class Slider {
    /* Board is final size */
    private final int boardSize = 4;
    private Board board;
    /* Singleton design */
    private static Slider instance = new Slider();
    private boolean portrait;

    private Slider() {
        portrait = true;
        newGame();
    }

    public static Slider getGame() {
        if ( instance.equals(null) )
            return instance = new Slider();
        return instance;
    }

    /* Create a new game */
    public void newGame() {
        board = new Board(boardSize);
        board.setValues();
    }

    /* Mutators */
    public boolean move(int x, int y) {
        return board.move(x,y);
    }
    public void shuffleBoard() {
        board.shuffle();
    }
    public void rotated() {
        portrait = !portrait;
    }

    /* Accessors */
    public Board board() {
        return board;
    }
    public int getRadius() {
        if ( portrait )
            return 50;
        else
            return 25;
    }
    public boolean isOver() {
        return board.isOver();
    }
}