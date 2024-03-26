package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowResult extends Dialog {

    private final String message;
    private TextView tvMessage;
    private Button  btnPlayAgain, btnNewGame;
    private final MainActivity mainActivity;

    public ShowResult(@NonNull Context context, String message, MainActivity mainActivity) {
        super(context);
        this.message = message;
        this.mainActivity = mainActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View decorView = getWindow().getDecorView();
        decorView.setBackgroundResource(R.drawable.gradiant_background_result);

        tvMessage = findViewById(R.id.tvMessage);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);
        btnNewGame = findViewById(R.id.btnNewGame);

        tvMessage.setText(message);

        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.restartMatch();
                dismiss();
            }
        });

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddPlayers.class);
                getContext().startActivity(intent);
                mainActivity.finish();
                dismiss();
            }
        });

    }
}