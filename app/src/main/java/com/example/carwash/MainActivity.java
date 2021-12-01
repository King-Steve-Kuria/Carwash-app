package com.example.carwash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageView mainlogo;
    private Button OwnerBtn, CustomerBtn;
    private TextView comapanyname, CreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainlogo = findViewById(R.id.mainlogo);
        OwnerBtn = findViewById(R.id.OwnerBtn);
        CustomerBtn = findViewById(R.id.CustomerBtn);
        comapanyname = findViewById(R.id.companyname);
        CreateAccount = findViewById(R.id.CreateAccount);


        CustomerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });

        CreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterActivity();
            }
        });

        OwnerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOwnerLogin();
            }
        });

    }

    public void openLoginActivity(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);

    }

    public void openRegisterActivity(){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void openOwnerLogin(){
        Intent intent = new Intent(this, OwnerLogin.class);
        startActivity(intent);
    }

}
