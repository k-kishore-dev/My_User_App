package com.example.my_user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class user_signup extends AppCompatActivity {
    TextView ahaa;
    EditText username,password,phone,mail;
    Button signup;
    Database_user databaseuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_signup);

        ahaa=findViewById(R.id.ahaa);
        phone=findViewById(R.id.user_phone);
        mail=findViewById(R.id.user_mail);
        username=findViewById(R.id.user_name);
        password=findViewById(R.id.user_password);
        databaseuser=new Database_user(this);
        signup=findViewById(R.id.signup_btn);

        ahaa.setOnClickListener(view -> {
            Intent intent =new Intent(getApplicationContext(),user_login.class);
            startActivity(intent);
            finish();
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString().trim();
                String pass=password.getText().toString().trim();
                String u_mail=mail.getText().toString().trim();
                String u_phone=phone.getText().toString().trim();
                if(user.isEmpty() ||  pass.isEmpty() || u_phone.isEmpty() || u_mail.isEmpty() ){
                    Toast.makeText(user_signup.this,"Please enter all requirements",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(databaseuser.insertdata(user,pass,u_phone,u_mail)){
                        Toast.makeText(user_signup.this,"Registration sucessful",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(user_signup.this, welcome.class);
                        intent.putExtra("USERNAME",user);
                        startActivity(intent);

                    }
                    else{
                        Toast.makeText(user_signup.this,"Username already exist",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.signup_page), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}