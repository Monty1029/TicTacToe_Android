package com.example.monty.tictactoe;

/**
 * Tic Tac Toe game
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    TicTacToe ttt;
    Move m;
    boolean gameOver;

    //Creates the screen and its contents from its respective XML file.
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ttt = new TicTacToe();
    }

    //The next two classes create the Options Menu bar at the top. This can be removed if you want.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //The listener method for when a button gets clicked.
    public void click(View v) {
        Button button = (Button) v; //Casts the clicked item as a button to use later on.
        //Checks if someone has won the game already.
        if (gameOver) {
            button.setEnabled(false);
        }
        //If the game is still ongoing, then set the buttons and turns accordingly
        else {
            String bTag = button.getTag().toString();
            int row = ((int) bTag.charAt(0)) - 48;
            int col = ((int) bTag.charAt(1)) - 48;
            Move m = new Move(row, col);
            int result = ttt.play(m);
            if ((result == 3) || (result == 4)) {
                button.setText("" + ttt.getTurn());
                gameOver = true;
                button.setEnabled(false);
            } else {
                button.setText("" + ttt.getTurn());
                ttt.toggleTurn();
                button.setEnabled(false);
            }
        }
    }
}