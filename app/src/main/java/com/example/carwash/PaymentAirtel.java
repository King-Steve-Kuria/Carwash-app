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

public class PaymentAirtel extends AppCompatActivity {

    private androidx.appcompat.widget.Toolbar toolbar;

    private Button button, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_airtel);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Payment via Airtel Money");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


//        button = findViewById(R.id.button5);
//        //button2 = findViewById(R.id.button);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openPaymentMain();
//            }
//        });
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "Payment complete", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    public void openPaymentMain() {
        Intent intent = new Intent(this, PaymentMain.class);
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

        switch (item.getItemId()) {
            case R.id.Logout:
                Toast.makeText(PaymentAirtel.this, "You are Logged Out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PaymentAirtel.this, MainActivity.class));
                return true;

            case R.id.Location:
                startActivity(new Intent(PaymentAirtel.this, Location.class));
                return true;

            case R.id.Detail:
                startActivity(new Intent(PaymentAirtel.this, Details.class));
                return true;

        }
        return super.onOptionsItemSelected(item);

    }
}

