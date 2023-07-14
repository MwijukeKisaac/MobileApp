package com.example.kakasaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }

    public void OpenUserGuide(View view) {
        Intent intent = new Intent(HomePage.this, UserGuide.class);
        startActivity(intent);
    }

    public void OpenVerifiedOutlets(View view) {
        Intent intent = new Intent(HomePage.this, VerifiedOutlets.class);
        startActivity(intent);
    }

    public void OpenTakeATestPage(View view) {
        Intent intent = new Intent(HomePage.this, TakeATestPage.class);
        startActivity(intent);

    }
}