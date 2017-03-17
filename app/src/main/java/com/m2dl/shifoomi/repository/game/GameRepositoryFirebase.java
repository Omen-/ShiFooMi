package com.m2dl.shifoomi.repository.game;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.m2dl.shifoomi.database.game.Game;
import com.m2dl.shifoomi.database.game.GameMove;

import java.util.Collections;
import java.util.Iterator;

public class GameRepositoryFirebase implements GameRepository {

    private static final String REPOSITORY_GAMES = "games";
    private static GameRepository instance = new GameRepositoryFirebase();

    public static GameRepository getInstance() {
        return instance;
    }

    @Override
    public void addGameMove(String gameId, GameMove gameMove) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(REPOSITORY_GAMES).child(gameId).child("gameMoves")
                .updateChildren(Collections.<String, Object>singletonMap(gameMove.getTurn() + "_" + gameMove.getUserId(), gameMove));
    }

    @Override
    public void createGame(Game game) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(REPOSITORY_GAMES).updateChildren(Collections.<String, Object>singletonMap(game.getId(), game));
    }

    @Override
    public void addGameRepositoryListener(final String userId, final GameRepositoryListener gameRepositoryListener) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                if(iterator.hasNext()) {
                    Game value = iterator.next().getValue(Game.class);
                    if (value != null)
                        gameRepositoryListener.gameUpdate(value);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        databaseReference.child(REPOSITORY_GAMES).orderByChild("firstPlayerId")
                .equalTo(userId).addValueEventListener(valueEventListener);
        databaseReference.child(REPOSITORY_GAMES).orderByChild("secondPlayerId")
                .equalTo(userId).addValueEventListener(valueEventListener);
    }

    @Override
    public void removeGameRepositoryListener(String userId, GameRepositoryListener gameRepositoryListener) {

    }
}
