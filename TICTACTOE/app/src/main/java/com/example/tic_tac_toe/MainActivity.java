package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView result = findViewById(R.id.result);
    ImageView imageView0 = findViewById(R.id.imageView0);
    ImageView imageView1 = findViewById(R.id.imageView1);
    ImageView imageView2 = findViewById(R.id.imageView2);
    ImageView imageView3 = findViewById(R.id.imageView3);
    ImageView imageView4 = findViewById(R.id.imageView4);
    ImageView imageView5 = findViewById(R.id.imageView5);
    ImageView imageView6 = findViewById(R.id.imageView6);
    ImageView imageView7 = findViewById(R.id.imageView7);
    ImageView imageView8 = findViewById(R.id.imageView8);

    boolean isGameActive = true;
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    public static int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());


        if (!isGameActive) {
            gameReset(view);
        }


        if (gameState[tappedImage] == 2) {

            counter++;


            if (counter == 9) {

                isGameActive = false;
            }

            gameState[tappedImage] = activePlayer;

            img.setTranslationY(-1000f);

            if (activePlayer == 0) {
                img.setImageResource(R.drawable.crosssign);
                activePlayer = 1;
                TextView status = result;

                status.setText("First Player's Turn");
            } else {
                img.setImageResource(R.drawable.roundsign);
                activePlayer = 0;
                TextView status = result;

                status.setText("Second Player's Turn");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        int flag = 0;
        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2) {
                flag = 1;

                String winnerStr;

                isGameActive = false;
                if (gameState[winPosition[0]] == 0) {
                    winnerStr = "X has won";
                } else {
                    winnerStr = "O has won";
                }
                TextView status = result;
                status.setText(winnerStr);
            }
        }
        if (counter == 9 && flag == 0) {
            TextView status = result;
            status.setText("Match Is Draw");
        }
    }

    // reset the game
    public void gameReset(View view) {
        isGameActive = true;
        activePlayer = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        imageView0.setImageResource(0);
        imageView1.setImageResource(0);
        imageView2.setImageResource(0);
        imageView3.setImageResource(0);
        imageView4.setImageResource(0);
        imageView5.setImageResource(0);
        imageView6.setImageResource(0);
        imageView7.setImageResource(0);
        imageView8.setImageResource(0);

        TextView status = findViewById(R.id.result);
        status.setText("First Player's Turn");
    }


}