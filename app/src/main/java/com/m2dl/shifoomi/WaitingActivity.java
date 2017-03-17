package com.m2dl.shifoomi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.m2dl.shifoomi.database.game.Game;
import com.m2dl.shifoomi.repository.game.GameRepository;
import com.m2dl.shifoomi.repository.game.GameRepositoryFirebase;
import com.m2dl.shifoomi.repository.game.GameRepositoryListener;

public class WaitingActivity extends AppCompatActivity {
    FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);
    }
}
