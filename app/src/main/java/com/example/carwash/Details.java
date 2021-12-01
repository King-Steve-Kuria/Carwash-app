package com.example.carwash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Details extends AppCompatActivity {

    ImageView imageView;
    private int myimage = R.drawable.carwash;
    // private int myimage2=R.drawable.wallpaper;
    TextView textView, textView1, textView2, textView3;

    private androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("About Us");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView2 = (TextView) findViewById(R.id.textView2);

        imageView.setImageResource(myimage);

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
                Toast.makeText(Details.this, "You are Logged Out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Details.this, MainActivity.class));
                return true;

            case R.id.Location:
                startActivity(new Intent(Details.this, Location.class));
                return true;

            case R.id.Detail:
                startActivity(new Intent(Details.this, Details.class));
                return true;

            case R.id.Payment:
                startActivity(new Intent(Details.this, PaymentMain.class));
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
