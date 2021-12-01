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

public class PaymentMpesa extends AppCompatActivity {

    private androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_mpesa);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Payment via Mpesa");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openPaymentMain();
//            }
//        });
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

//    }
//    public void openPaymentMain(){
//        Intent intent = new Intent(this, PaymentMain.class);
//        startActivity(intent);
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
                Toast.makeText(PaymentMpesa.this, "You are Logged Out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PaymentMpesa.this, MainActivity.class));
                return true;

            case R.id.Location:
                startActivity(new Intent(PaymentMpesa.this, Location.class));
                return true;

            case R.id.Detail:
                startActivity(new Intent(PaymentMpesa.this, Details.class));
                return true;

            case R.id.Payment:
                startActivity(new Intent(PaymentMpesa.this, PaymentMain.class));
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}

