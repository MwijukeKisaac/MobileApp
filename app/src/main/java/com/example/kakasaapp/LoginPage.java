package com.example.kakasaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
    }

    public void OpenHomePage(View view) {
        Intent intent = new Intent(LoginPage.this, HomePage.class);
        startActivity(intent);
        finish();
    }

    public void OpenCreateAccountPage(View view) {
        Intent intent = new Intent(LoginPage.this, CreateAccount.class);
        startActivity(intent);
    }

    public void OpenForgotPasswordPage(View view) {
        Intent intent = new Intent(LoginPage.this, ForgotPassword.class);
        startActivity(intent);
    }
}