package com.example.my_user;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
//import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {

    TextView nameTextView, mobileNumberTextView, shiftTextView, carSeatTextView;
    Database_user userDb;
    Database_preference preferenceDb;
    Button logout, change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // initialize UI elements
        logout = findViewById(R.id.logout);
        change = findViewById(R.id.change);
        nameTextView = findViewById(R.id.NAME);
        mobileNumberTextView = findViewById(R.id.MN);
        shiftTextView = findViewById(R.id.SH);
        carSeatTextView = findViewById(R.id.CS);

        // initialize databases
        userDb = new Database_user(this);
        preferenceDb = new Database_preference(this);

        // fetch and display user and preferences data
        displayUserData();
        displayPreferenceData();

        logout.setOnClickListener(view -> {
            // Logout and return to the login screen
            Intent intent = new Intent(Profile.this, user_login.class);
            startActivity(intent);
            finish();  // Close the current activity after logout
        });

        change.setOnClickListener(view -> {

            Intent intent = new Intent(Profile.this, preference.class);
            startActivity(intent);
        });
    }

    private void displayUserData() {
        String currentUserId = getIntent().getStringExtra("USERNAME");
        Cursor cursor = userDb.getUserData(currentUserId);
        if (cursor != null && cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndex("USERNAME"));
            String mobileNumber = cursor.getString(cursor.getColumnIndex("PHONE"));

            nameTextView.setText("NAME: " + name);
            mobileNumberTextView.setText("Mobile Number: " + mobileNumber);

            cursor.close();
        } else {
            Toast.makeText(this, "No user data found", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayPreferenceData() {
        String currentUserId = getIntent().getStringExtra("USERNAME"); // Use the username passed from login activity

        Cursor cursor = preferenceDb.getPreferenceData(currentUserId);
        if (cursor != null && cursor.moveToFirst()) {
            String shift = cursor.getString(cursor.getColumnIndex("SHIFT"));
            String carSeat = cursor.getString(cursor.getColumnIndex("CAR_SEAT"));

            shiftTextView.setText("Shift: " + shift);
            carSeatTextView.setText("Car Seat: " + carSeat);

            cursor.close();
        } else {
            Toast.makeText(this, "No preference data found", Toast.LENGTH_SHORT).show();
        }
    }
}
