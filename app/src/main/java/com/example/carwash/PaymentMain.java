package com.example.carwash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PaymentMain extends AppCompatActivity {

    private androidx.appcompat.widget.Toolbar toolbar;
    private Button button1, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Payment");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        button1 = findViewById(R.id.Button);
        button2 = findViewById(R.id.Button2);
        button3 = findViewById(R.id.button3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PaymentMain.this, "You can pay at the Cashier in the Office", Toast.LENGTH_SHORT).show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMpesaActivity();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAirtelActivity();
            }
        });

    }
        public void openAirtelActivity()
        {
            Intent intent = new Intent(this, PaymentAirtel.class);
            startActivity(intent);

        }
        public void openMpesaActivity()
        {
            Intent intent = new Intent(this, PaymentMpesa.class);
            startActivity(intent);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();
        }

        switch (item.getItemId()){
            case R.id.Logout:
                Toast.makeText(PaymentMain.this, "You are Logged Out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PaymentMain.this, MainActivity.class));
                return true;

            case R.id.Location:
                startActivity(new Intent(PaymentMain.this, Location.class));
                return true;

            case R.id.Detail:
                startActivity(new Intent(PaymentMain.this, Details.class));
                return true;

            case R.id.Payment:
                startActivity(new Intent(PaymentMain.this, PaymentMain.class));
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    }


