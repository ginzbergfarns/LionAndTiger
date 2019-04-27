package com.olex.lionandtiger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    enum Player {
        ONE, TWO, NO
    }

    private boolean gameOver = false;
    private Button buttonReset;
    private Player currentPlayer = Player.ONE;
    private Player[] playersChoices = new Player[9];
    private GridLayout gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set views
        buttonReset = findViewById(R.id.button);
        gridView = findViewById(R.id.gridLayout);

        for (int i = 0; i < gridView.getChildCount(); i++) {
            playersChoices[i] = Player.NO;
        }
    }

    public void imageViewOnClick(View imageV) {
        if (!gameOver) {
            ImageView image = (ImageView) imageV;
            image.setTranslationX(-1000);

            int imgTag = Integer.parseInt(image.getTag().toString());
            playersChoices[imgTag] = currentPlayer;

            int [][] winnerRowsColumns = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                    {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

            if (currentPlayer == Player.ONE) {
                image.setImageResource(R.drawable.lion);
                currentPlayer = Player.TWO;
            } else if (currentPlayer == Player.TWO) {
                image.setImageResource(R.drawable.tiger);
                currentPlayer = Player.ONE;
            }


            image.setOnClickListener(null);
            image.animate().translationX(0)
                    .alpha(1).rotation(3600).setDuration(1000);

            for (int[] winnerColumns : winnerRowsColumns) {
                if (playersChoices[winnerColumns[0]] == playersChoices[winnerColumns[1]]
                        && playersChoices[winnerColumns[1]] == playersChoices[winnerColumns[2]]
                        && playersChoices[winnerColumns[0]] != Player.NO) {

                    buttonReset.setVisibility(View.VISIBLE);
                    gameOver = true;

                    String winnerOfGame;
                    if (currentPlayer == Player.ONE) {
                        winnerOfGame = "Player two ";
                    } else {
                        winnerOfGame = "Player one ";
                    }

                    Toast.makeText(MainActivity.this,
                            winnerOfGame + "is the Winner",
                            Toast.LENGTH_LONG).show();
                }
            }
        }

    }

    public void resetGame(View buttonView) {
        buttonView.setVisibility(View.INVISIBLE);
        currentPlayer = Player.ONE;
        gameOver = false;

        for (int i = 0; i < gridView.getChildCount(); i++) {
            playersChoices[i] = Player.NO;
            ImageView imageView = (ImageView) gridView.getChildAt(i);
            imageView.setImageDrawable(null);
            imageView.setAlpha(0.4f);
            imageView.setRotation(0);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageViewOnClick(v);
                }
            });
        }
    }
}
