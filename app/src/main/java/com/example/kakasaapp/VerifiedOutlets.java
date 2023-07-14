package com.example.kakasaapp;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class VerifiedOutlets extends AppCompatActivity {

    private EditText searchEditText;
    private Button searchWebButton;
    private Button searchMapButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verified_outlets);

        // Initialize the UI elements
        searchEditText = findViewById(R.id.searchEditText);
        searchWebButton = findViewById(R.id.searchWebButton);
        searchMapButton = findViewById(R.id.searchMapButton);

        // Set up the search web button functionality
        searchWebButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = searchEditText.getText().toString().trim();
                if (!query.isEmpty()) {
                    searchOnWeb(query);
                }
            }
        });

        // Set up the search map button functionality
        searchMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location = searchEditText.getText().toString().trim();
                if (!location.isEmpty()) {
                    searchOnMap(location);
                }
            }
        });
    }

    private void searchOnWeb(String query) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.google.com/search?q=" + query));
        startActivity(intent);
    }

    private void searchOnMap(String location) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:0,0?q=" + location));
        startActivity(intent);
    }
}
