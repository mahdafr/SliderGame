package edu.utep.cs.cs4330.slider;

public class Board {
    private int size;
    private Place[][] board;
    private boolean[] used;

    public Board(int s) {
        size = s;
        board = new Place[size][size];
        used = new boolean[size*size];
        initBoard();
        unuse();
    }

    /* Maps the Board's Places to its Place */
    private void initBoard() {
        for ( int i=0 ; i<size ; i++ )
            for ( int j=0 ; j<size ; j++ )
                board[i][j] = new Place(i,j);
    }

    /* Sets the Boards' values */
    public void setValues() {
        java.util.Random r = new java.util.Random();
        for ( int i=0 ; i<size ; i++ )
            for ( int j=0 ; j<size ; j++ )
                board[i][j].setValue(r.nextInt(size)+1);
    }

    /* Resets numbers used */
    private void unuse() {
        for ( int i=0 ; i<used.length ; i++ )
            used[i] = false;
    }

    /* Mutators */
    public boolean setValue(int i, int j, int x) {
        if ( !used[x-1] ) {
            used[x] = true;
            board[i][j].setValue(x);
            return true;
        }
        return false;
    }
    /* Gameplay: can swap with the empty space? */
    public boolean move(int x, int y) {
        if ( board[x][y].isEmpty() )
            return false;
        return true;
    }
    /* Shuffles the Board's Places */
    public void shuffle() {
        //TODO shuffle the Board
    }

    /* Accessors */
    public int size() {
        return size;
    }
    public int at(int i, int j) {
        return board[i][j].getValue();
    }
}