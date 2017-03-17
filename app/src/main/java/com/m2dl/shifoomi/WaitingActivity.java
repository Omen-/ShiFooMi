package com.m2dl.shifoomi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.m2dl.shifoomi.database.game.Game;
import com.m2dl.shifoomi.repository.game.GameRepository;
import com.m2dl.shifoomi.repository.game.GameRepositoryFirebase;
import com.m2dl.shifoomi.repository.game.GameRepositoryListener;
import com.m2dl.shifoomi.repository.room.RoomRepository;
import com.m2dl.shifoomi.repository.room.RoomRepositoryFirebase;

public class WaitingActivity extends AppCompatActivity {
    private String roomId;
    static boolean isFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);

        isFirst = true;

        Intent intent = getIntent();
        roomId = intent.getStringExtra("roomId");

        final String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        GameRepositoryListener listener = new GameRepositoryListener() {
            @Override
            public void gameUpdate(Game game) {
                if (isFirst) {
                    isFirst = false;
                    Intent intent = new Intent(WaitingActivity.this, GameActivity.class);
                    intent.putExtra("userId", userId);
                    intent.putExtra("gameId", game.getId());
                    startActivity(intent);
                }

            }
        };
        GameRepositoryFirebase.getInstance().addGameRepositoryListener(userId, listener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        RoomRepository repository = RoomRepositoryFirebase.getInstance();
        repository.deleteRoom(roomId);
    }
}
