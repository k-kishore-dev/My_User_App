package com.example.my_user;

import android.content.Intent;
//import android.graphics.Bitmap;
import android.os.Bundle;
//import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class preference extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    EditText seat, distance;
    Spinner shift, payment_method;
    ImageView imageView;
    TextView textView;
    Button submitButton;
    Database_preference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        shift = findViewById(R.id.shift);
        seat = findViewById(R.id.seat);
        distance = findViewById(R.id.distance);
        payment_method = findViewById(R.id.payment);
        imageView = findViewById(R.id.imageView3);
        textView = findViewById(R.id.P);
        submitButton = findViewById(R.id.submit);

        db = new Database_preference(this);
        //spinners
        ArrayAdapter<CharSequence> shiftAdapter = ArrayAdapter.createFromResource(this, R.array.shift_array, android.R.layout.simple_spinner_item);
        shiftAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shift.setAdapter(shiftAdapter);

        ArrayAdapter<CharSequence> paymentAdapter = ArrayAdapter.createFromResource(this, R.array.payment_array, android.R.layout.simple_spinner_item);
        paymentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        payment_method.setAdapter(paymentAdapter);



        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertPreferences();

                Intent intent=new Intent(preference.this, Profile.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.preferences_page), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    private void insertPreferences() {
        String seatText = seat.getText().toString().trim();
        String distanceText = distance.getText().toString().trim();
        String shiftText = shift.getSelectedItem().toString();
        String paymentMethodText = payment_method.getSelectedItem().toString();

        // Validation
        if (seatText.isEmpty() || distanceText.isEmpty() || shiftText.isEmpty() || paymentMethodText.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int carSeat = Integer.parseInt(seatText);
        int distanceValue = Integer.parseInt(distanceText);

        // insert data into database
        boolean isInserted = db.insertdata(carSeat,shiftText,distanceValue,paymentMethodText);

        if (isInserted) {
            Toast.makeText(this, "Preferences saved successfully", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Error saving preferences", Toast.LENGTH_SHORT).show();
        }
    }
}
