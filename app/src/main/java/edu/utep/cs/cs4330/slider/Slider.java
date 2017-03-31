package edu.utep.cs.cs4330.slider;

public class Slider {
    /* Board is final size */
    private final int boardSize = 4;
    private Board board;
    /* Singleton design */
    private Slider game;

    private Slider() {
        newGame();
    }

    public Slider getGame() {
        if ( game!=null )
            return game;
        return game = new Slider();
    }

    /* Create a new game */
    public void newGame() {
        board = new Board(boardSize);
    }

    /* Mutators */
    public boolean move(int x, int y) {
        return board.move(x,y);
    }
    public void shuffleBoard() {
        board.shuffle();
    }

    /* Accessors */
    public Board board() {
        return board;
    }
}