package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinearLayout llPlayerOne,llPlayerTwo;
    private TextView tvPlayerOne,tvPlayerTwo,tvPlayerOneWins,tvPlayerTwoWins;
    private ImageView iv1,iv2,iv3,iv4,iv5,iv6,iv7,iv8,iv9;
    private final List<int[]> winPositions = new ArrayList<>();
    private int[] boxPositions = {0,0,0,0,0,0,0,0,0};
    private int playerTurn = 1;
    private int playerOneWin = 0;
    private int playerTwoWin = 0;
    private int totalSelectedBox = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        iv3 = findViewById(R.id.iv3);
        iv4 = findViewById(R.id.iv4);
        iv5 = findViewById(R.id.iv5);
        iv6 = findViewById(R.id.iv6);
        iv7 = findViewById(R.id.iv7);
        iv8 = findViewById(R.id.iv8);
        iv9 = findViewById(R.id.iv9);

        tvPlayerOne = findViewById(R.id.tvPlayerOne);
        tvPlayerOneWins = findViewById(R.id.tvPlayerOneWins);
        llPlayerOne = findViewById(R.id.llPlayerOne);
        tvPlayerTwo = findViewById(R.id.tvPlayerTwo);
        tvPlayerTwoWins = findViewById(R.id.tvPlayerTwoWins);
        llPlayerTwo = findViewById(R.id.llPlayerTwo);

        winPositions.add(new int[] {0,1,2});
        winPositions.add(new int[] {3,4,5});
        winPositions.add(new int[] {6,7,8});
        winPositions.add(new int[] {0,3,6});
        winPositions.add(new int[] {1,4,7});
        winPositions.add(new int[] {2,5,8});
        winPositions.add(new int[] {0,4,8});
        winPositions.add(new int[] {2,4,6});

        String getPlayerOneName = getIntent().getStringExtra("playerOne");
        String getPlayerTwoName = getIntent().getStringExtra("playerTwo");

        tvPlayerOne.setText(getPlayerOneName);
        tvPlayerTwo.setText(getPlayerTwoName);

        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(0)){
                    performAction((ImageView) view, 0);
                }
            }
        });

        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(1)){
                    performAction((ImageView) view, 1);
                }
            }
        });
        
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(2)){
                    performAction((ImageView) view, 2);
                }
            }
        });
        
        iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(3)){
                    performAction((ImageView) view, 3);
                }
            }
        });
        
        iv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(4)){
                    performAction((ImageView) view, 4);
                }
            }
        });
        
        iv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(5)){
                    performAction((ImageView) view, 5);
                }
            }
        });
        
        iv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(6)){
                    performAction((ImageView) view, 6);
                }
            }
        });
        
        iv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(7)){
                    performAction((ImageView) view, 7);
                }
            }
        });
        
        iv9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(8)){
                    performAction((ImageView) view, 8);
                }
            }
        });
    }

    private void performAction(ImageView  imageView, int selectedBoxPosition) {
        boxPositions[selectedBoxPosition] = playerTurn;

        if (playerTurn == 1) {
            imageView.setImageResource(R.drawable.ximage);
            if (checkResult()) {
                ShowResult resultDialog = new ShowResult(MainActivity.this, tvPlayerOne.getText().toString() + "\n" + "Is The Winner!", MainActivity.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
                playerOneWin++;
                tvPlayerOneWins.setText("Total Wins = " + playerOneWin);
            } else if(totalSelectedBox == 8) {
                ShowResult resultDialog = new ShowResult(MainActivity.this, "Match Draw", MainActivity.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                changePlayer(2);
                totalSelectedBox++;
            }
        } else {
            imageView.setImageResource(R.drawable.oimage);
            if (checkResult()) {
                ShowResult resultDialog = new ShowResult(MainActivity.this, tvPlayerTwo.getText().toString() + "\n" + "Is The Winner!", MainActivity.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
                playerTwoWin++;
                tvPlayerTwoWins.setText("Total Wins = " + playerTwoWin);
            } else if(totalSelectedBox == 8) {
                ShowResult resultDialog = new ShowResult(MainActivity.this, "Match Draw", MainActivity.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                changePlayer(1);
                totalSelectedBox++;
            }
        }
    }

    private void changePlayer(int currentPlayer) {
        playerTurn = currentPlayer;
        if (playerTurn == 1) {
            llPlayerOne.setBackgroundResource(R.drawable.black_border);
            llPlayerTwo.setBackgroundResource(R.drawable.white_box);
        } else {
            llPlayerTwo.setBackgroundResource(R.drawable.black_border);
            llPlayerOne.setBackgroundResource(R.drawable.white_box);
        }
    }

    private boolean checkResult(){
        boolean result = false;
        for (int i = 0; i < winPositions.size(); i++){
            final int[] winPosition = winPositions.get(i);

            if (boxPositions[winPosition[0]] == playerTurn && boxPositions[winPosition[1]] == playerTurn &&
                    boxPositions[winPosition[2]] == playerTurn) {
                result = true;
            }
        }
        return result;
    }

    private boolean isBoxSelectable(int boxPosition) {
        boolean selectable = false;
        if (boxPositions[boxPosition] == 0) {
            selectable = true;
        }
        return selectable;
    }

    public void restartMatch(){
        boxPositions = new int[] {0,0,0,0,0,0,0,0,0};
        playerTurn = 1;
        totalSelectedBox = 0;

        llPlayerOne.setBackgroundResource(R.drawable.black_border);
        llPlayerTwo.setBackgroundResource(R.drawable.white_box);

        iv1.setImageResource(R.drawable.white_box);
        iv2.setImageResource(R.drawable.white_box);
        iv3.setImageResource(R.drawable.white_box);
        iv4.setImageResource(R.drawable.white_box);
        iv5.setImageResource(R.drawable.white_box);
        iv6.setImageResource(R.drawable.white_box);
        iv7.setImageResource(R.drawable.white_box);
        iv8.setImageResource(R.drawable.white_box);
        iv9.setImageResource(R.drawable.white_box);
    }
}