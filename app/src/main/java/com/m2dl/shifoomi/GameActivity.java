package com.m2dl.shifoomi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.m2dl.shifoomi.actions.PlayTurn;
import com.m2dl.shifoomi.database.game.GameMoveType;
import com.m2dl.shifoomi.service.game.GameListener;
import com.m2dl.shifoomi.service.game.GameScore;

public class GameActivity extends AppCompatActivity implements GameListener {

    private ImageView imageViewRock;
    private ImageView imageViewPaper;
    private ImageView imageViewScissors;
    private ImageView imageViewOpponentHand;

    private String userId;
    private String gameId;

    private boolean playReady;
    private int turn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        gameId = intent.getStringExtra("gameId");
        userId = intent.getStringExtra("userId");

        imageViewRock = (ImageView) findViewById(R.id.imageViewRock);
        imageViewPaper = (ImageView) findViewById(R.id.imageViewPaper);
        imageViewScissors = (ImageView) findViewById(R.id.imageViewScissors);
        imageViewOpponentHand = (ImageView) findViewById(R.id.imageViewOpponentHand);

        playReady = false;
        imageViewRock.setEnabled(false);
        imageViewPaper.setEnabled(false);
        imageViewScissors.setEnabled(false);

        imageViewRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageViewRock.setSelected(true);
                imageViewRock.setEnabled(false);
                imageViewPaper.setEnabled(false);
                imageViewScissors.setEnabled(false);
                imageViewPaper.setVisibility(View.INVISIBLE);
                imageViewScissors.setVisibility(View.INVISIBLE);

                new PlayTurn(gameId, userId, GameMoveType.ROCK, turn);
                turn++;
            }
        });

        imageViewPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageViewPaper.setSelected(true);
                imageViewRock.setEnabled(false);
                imageViewPaper.setEnabled(false);
                imageViewScissors.setEnabled(false);
                imageViewRock.setVisibility(View.INVISIBLE);
                imageViewScissors.setVisibility(View.INVISIBLE);

                new PlayTurn(gameId, userId, GameMoveType.PAPER, turn);
                turn++;
            }
        });

        imageViewScissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageViewScissors.setSelected(true);
                imageViewRock.setEnabled(false);
                imageViewPaper.setEnabled(false);
                imageViewScissors.setEnabled(false);
                imageViewRock.setVisibility(View.INVISIBLE);
                imageViewPaper.setVisibility(View.INVISIBLE);

                new PlayTurn(gameId, userId, GameMoveType.SCISSORS, turn);
                turn++;
            }
        });
        //GameService.getInstance().addListener(playerId, this);
    }

    @Override
    public void opponentPlayed(GameMoveType gameMoveType) {

        switch (gameMoveType) {
            case PAPER:
        }
    }

    @Override
    public void scoreUpdated(GameScore score) {

    }

    @Override
    public void roundStart() {

        playReady = true;
        imageViewRock.setEnabled(true);
        imageViewPaper.setEnabled(true);
        imageViewScissors.setEnabled(true);
        imageViewRock.setVisibility(View.VISIBLE);
        imageViewPaper.setVisibility(View.VISIBLE);
        imageViewScissors.setVisibility(View.VISIBLE);
        imageViewRock.setSelected(false);
        imageViewPaper.setSelected(false);
        imageViewScissors.setSelected(false);
    }
}
