/*
 * @author Mahdokht Afravi
 * @created 03.30 R
 *
 * Models the Board of the Slider Game.
 */

package edu.utep.cs.cs4330.slider;

public class Board {
    private int size;
    private Place[][] board;
    private boolean[] used;
    private Place empty;
    private int bounds;
    private boolean isOver;

    public Board(int s) {
        size = s;
        board = new Place[size][size];
        bounds = size*size;
        used = new boolean[bounds-1];
        isOver = false;
        initBoard();
        unuse();
    }

    /* Maps the Board's Places to its Place */
    private void initBoard() {
        for ( int i=0 ; i<size ; i++ )
            for ( int j=0 ; j<size ; j++ )
                board[i][j] = new Place(i,j);
    }

    /* Resets numbers used */
    private void unuse() {
        for ( int i=0 ; i<used.length ; i++ )
            used[i] = false;
    }

    /* Mutators */
    /* Sets the Boards' values */
    public void setValues() {
        java.util.Random r = new java.util.Random();
        setEmpty(r.nextInt(size),r.nextInt(size));
        int t = r.nextInt(bounds)+1;
        for ( int i=0 ; i<size ; i++ )
            for ( int j=0 ; j<size ; j++ ) {
                if ( board[i][j].getValue()==-2 ) //i==empty.getX() && j==empty.getY() )
                    continue;
                while ( !canSet(i,j,t) )
                    t = r.nextInt(bounds)+1;
                board[i][j].setValue(t);
            }
        empty = new Place(r.nextInt(size)+1,r.nextInt(size)+1);
    }
    private boolean canSet(int i, int j, int x) {
        if ( x==16 )
            return false;
        if ( !used[x-1] )
            return used[x-1] = true;
        return false;
    }
    private void setEmpty(int x, int y) {
        empty = new Place(x,y);
        board[x][y].setValue(-2);
    }
    /* Gameplay: can swap with the empty space? */
    public boolean move(int x, int y) {
        if ( board[x][y].isEmpty() ) {
            return false;
        }
        if ( trySwap(x,y) ) {
            checkWin();
            return true;
        }
        return false;
    }
    private boolean trySwap(int x, int y) {
        //north
        if ( isEmpty(x+1,y) ) {
            swap(x, y, x+1, y);
            return true;
        }
        //south
        if ( isEmpty(x-1,y) ) {
            swap(x, y, x-1, y);
            return true;
        }
        //east
        if ( isEmpty(x,y+1) ) {
            swap(x, y, x, y+1);
            return true;
        }
        //west
        if ( isEmpty(x,y-1) ) {
            swap(x, y, x, y-1);
            return true;
        }
        return false;
    }
    private boolean isEmpty(int x, int y) {
        if ( x<0 || x>=size )
            return false;
        if ( y<0 || y>=size )
            return false;
        if ( board[x][y].getValue()==-2 || board[x][y].isEmpty() )
            return true;
        return false;
    }
    private void swap(int fromX, int fromY, int toX, int toY) {
        Place t = board[fromX][fromY];
        board[fromX][fromY] = board[toX][toY];
        board[toX][toY] = t;
        empty = board[toX][toY];
    }
    /* Shuffles the Board's Places */
    public void shuffle() {
        initBoard();
        unuse();
        setValues();
    }
    private boolean checkWin() {
        int count = 1;
        for ( int i=0 ; i<size ; i++ )
            for ( int j=0 ; j<size ; j++ )
                if ( board[i][j].getValue()!=count++ )
                    return isOver = false;
        return isOver = true;
    }

    /* Accessors */
    public int size() {
        return size;
    }
    public int at(int i, int j) {
        return board[i][j].getValue();
    }
    public boolean isOver() {
        return isOver;
    }
}