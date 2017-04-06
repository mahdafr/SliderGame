/*
 * @author Mahdokht Afravi
 * @created 03.30 R
 *
 * Provides the GUI view of the Slider Game.
 */

package edu.utep.cs.cs4330.slider;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    /* Game Controller */
    private Slider game;
    /* GUI elements */
    private BoardView bv;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creating game
        game = game.getGame();
        setup();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        game.rotated();
    }

    /* Move the blank space in the correct direction */
    private void move(int x, int y) {
        if ( game.move(y,x) ) {
            setBoard();
            if ( game.isOver() ) {
                setText("Puzzle is complete. Start new game!");
                createDialog(2);
            }
        } else
            toast("Cannot move there.");
    }

    /* NewButton clicked to create new game */
    public void newClicked(View v) {
        createDialog(1);
    }
    private void createDialog(int i) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        if ( i==1 )
            alertDialogBuilder.setMessage(R.string.newGameDialog);
        else
            alertDialogBuilder.setMessage(R.string.gameOverDialog);
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        newGame();
                    }
                });
        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        continueGame();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    private void newGame() {
        game.newGame();
        setBoard();
    }
    private void continueGame() {
        if ( game.isOver() )
            bv.addBoardTouchListener(new BoardView.BoardTouchListener() {
            @Override
            public void onTouch(int x, int y) {
                toast("Start a new game to continue.");
                }
        });
    }
    /* ResetButton clicked to scramble the Board */
    public void shuffleClicked(View v) {
        game.shuffleBoard();
        setBoard();
    }
    /* Initializes the GUI elements */
    private void setup() {
        //BoardView
        bv = (BoardView) findViewById(R.id.boardView);
        bv.setRadius(game.getRadius());
        setBoardListener();
        setBoard();
        //TextView
        tv = (TextView) findViewById(R.id.textView);
    }
    private void setBoardListener() {
        bv.addBoardTouchListener(new BoardView.BoardTouchListener() {
            @Override
            public void onTouch(int x, int y) {
                //toast(String.format("Touched: %d, %d", x, y));
                move(x,y);
            }
        });
    }

    /* GUI changes */
    private void setBoard() {
        bv.setBoard(game.board());
    }
    private void setText(String msg) {
        tv.setText(msg);
    }

    /* Show a toast message. */
    protected void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
