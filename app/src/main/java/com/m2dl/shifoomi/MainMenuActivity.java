package com.m2dl.shifoomi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.m2dl.shifoomi.actions.CreateRoom;
import com.m2dl.shifoomi.database.game.Game;
import com.m2dl.shifoomi.database.room.Room;
import com.m2dl.shifoomi.repository.game.GameRepository;
import com.m2dl.shifoomi.repository.game.GameRepositoryFirebase;
import com.m2dl.shifoomi.repository.game.GameRepositoryListener;
import com.m2dl.shifoomi.repository.room.RoomRepositoryFirebase;

import java.util.List;

public class MainMenuActivity extends AppCompatActivity {

    private Button mPlayView;

    private Button buttonRules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        buttonRules = (Button) findViewById(R.id.buttonRules);
        buttonRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(MainMenuActivity.this, RulesActivity.class);
                MainMenuActivity.this.startActivity(myIntent);
            }
        });

        mPlayView = (Button) findViewById(R.id.buttonPlay);

        mPlayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findRoom();
            }
        });
    }

    private void findRoom() {
        List<Room> rooms = RoomRepositoryFirebase.getInstance().getRooms();
        final String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if (rooms.isEmpty()) {
            CreateRoom createRoom = new CreateRoom("Default", userId);
            createRoom.execute();
            startActivity(new Intent(this, WaitingActivity.class));
        } else {
            GameRepositoryFirebase.getInstance().addGameRepositoryListener(userId, new GameRepositoryListener() {
                @Override
                public void gameUpdate(Game game) {
                    GameRepositoryFirebase.getInstance().removeGameRepositoryListener(userId, this);
                    Intent intent = new Intent(MainMenuActivity.this, GameActivity.class);
                    intent.putExtra("userId", userId);
                    intent.putExtra("gameId", game.getId());
                    startActivity(intent);
                }
            });

        }
    }
}
