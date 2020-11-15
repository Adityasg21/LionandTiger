package com.example.lionandtiger;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

public class MainActivity extends AppCompatActivity {

    enum Player {                  //for count of players

        ONE, TWO, No
    }
    Player currentPlayer = Player.ONE;         //currentPLayer//

    Player[] playerChoices = new Player[9];               //array to keep which grid is filled by a Player//

    int[][] winnerRowsColumns = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},    //array to keep a check a win is there//
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    private boolean gameOver = false;              //variable to game is finished or not//

    private Button btnReset;                        //btn to reset//

    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i=0;i<9;i++){
            playerChoices[i]=Player.No;                       // intialize tha no player has chosen
                                                              // anything
        }

        btnReset = findViewById(R.id.btnReset);
        gridLayout = findViewById(R.id.gridLayout);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                              //onclick to listen the game //

                resetTheGame();
            }
        });

    }

    public void imageViewIsTapped(View imageView) {

        ImageView tappedImageView = (ImageView) imageView;
        int tiTag = Integer.parseInt(tappedImageView.getTag().toString());

        if (playerChoices[tiTag] == Player.No && gameOver == false) {             //check if grid has been chosen or not//

            tappedImageView.setTranslationX(-2000);

            playerChoices[tiTag] = currentPlayer;

            if (currentPlayer == Player.ONE) {

                tappedImageView.setImageResource(R.drawable.lion);
                currentPlayer = Player.TWO;

            } else if (currentPlayer == Player.TWO) {

                tappedImageView.setImageResource(R.drawable.tiger);
                currentPlayer = Player.ONE;
            }

            tappedImageView.animate().translationXBy(2000).
                    alpha(1).rotation(36000).setDuration(2000);

            Toast.makeText(this, tappedImageView.getTag().toString(),
                    Toast.LENGTH_SHORT).show();

            for (int[] winnerColumns : winnerRowsColumns) {                                 //checking after every click that win is achieved//

                if (playerChoices[winnerColumns[0]] ==
                        playerChoices[winnerColumns[1]]
                        && playerChoices[winnerColumns[1]]
                        == playerChoices[winnerColumns[2]] && playerChoices[winnerColumns[0]] != Player.No) {

                    btnReset.setVisibility(View.VISIBLE);
                    gameOver = true;
                    String winnerOfGame = "";

                    if (currentPlayer == Player.ONE) {

                        winnerOfGame = "Player Two ";

                    } else if (currentPlayer == Player.TWO) {

                        winnerOfGame = "Player One ";


                    }

                    Toast.makeText(this, winnerOfGame + "is the Winner",
                            Toast.LENGTH_LONG).show();


                }
            }
        }

    }

    // Reset Game Function
    private void resetTheGame() {

        for (int index = 0; index < gridLayout.getChildCount(); index++) {

            ImageView imageView = (ImageView) gridLayout.getChildAt(index);
            imageView.setImageDrawable(null);
            imageView.setAlpha(0.2f);
        }

        currentPlayer = Player.ONE;
        for(int i=0;i<9;i++){
            playerChoices[i]=Player.No;
        }


        gameOver = false;

    }



}

