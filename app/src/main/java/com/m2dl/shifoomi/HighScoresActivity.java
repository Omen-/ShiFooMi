package com.m2dl.shifoomi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.m2dl.shifoomi.database.user.User;

import java.util.Comparator;
import java.util.TreeSet;

import static android.content.ContentValues.TAG;

public class HighScoresActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private DatabaseReference mUsersReference;
    private TreeSet<User> usersByEloTreeSet;
    private TableLayout highScoresTableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        usersByEloTreeSet = new TreeSet<User>(new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                if (u1.getEloScore() > u2.getEloScore()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });

        highScoresTableLayout = (TableLayout)findViewById(R.id.highScoresTableLayout);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mUsersReference = mDatabase.child("users");
        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                User user = dataSnapshot.getValue(User.class);
                if (usersByEloTreeSet.size() < 10) {
                    usersByEloTreeSet.add(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        mUsersReference.addValueEventListener(userListener);

        fillHighScoresTable();
    }

    private void fillHighScoresTable() {

        for (User user : usersByEloTreeSet) {
            TableRow row = new TableRow(getApplicationContext());

            TextView userViewName = new TextView(getApplicationContext());
            userViewName.setText(Float.toString(user.getEloScore()));

            TextView userViewElo = new TextView(getApplicationContext());
            userViewElo.setText(Float.toString(user.getEloScore()));
            userViewElo.setGravity(Gravity.RIGHT);

            row.addView(userViewName);
            row.addView(userViewElo);
            highScoresTableLayout.addView(row);
        }
    }
}
