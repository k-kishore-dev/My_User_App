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

public class user_login extends AppCompatActivity {
    TextView textView;
    EditText username,password;
    Button login;
    Database_user databaseuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_login);

        textView=findViewById(R.id.not_having_acc);
        username=findViewById(R.id.user_name);
        password=findViewById(R.id.user_password);
        login=findViewById(R.id.login_btn);
        databaseuser=new Database_user(this);

        textView.setOnClickListener(view -> {
            Intent intent =new Intent(getApplicationContext(),user_signup.class);
            startActivity(intent);
            finish();
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString().trim();
                String pass=password.getText().toString().trim();
                if(user.isEmpty() ||  pass.isEmpty()){
                    Toast.makeText(user_login.this,"Please enter username and password",Toast.LENGTH_SHORT).show();
                }
                else {
                    String loggedIn=databaseuser.checklogin(user,pass);
                    if(loggedIn != null){
                        Intent intent=new Intent(user_login.this,Profile.class);
                        intent.putExtra("USERNAME",loggedIn);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(user_login.this,"invalid login",Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.profile_page), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}

///profile creation