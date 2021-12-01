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
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Pickup extends AppCompatActivity {

    private CheckBox checkBox, checkBox2, checkBox3, checkBox4, checkBox5;
    private Button total, next;
    private TextView totalprice, pickup;

    int body_wash_price;
    int interior_cleaning_price;
    int extreme_body_wash_price;
    int extreme_interior_cleaning_price;
    int interior_disinfectant_price;
    int total_price;

    DatabaseReference databaseReference;
    FirebaseDatabase database;
    pickuptype pickuptype;
    int i = 0;
    int counter = 0;

    private androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Select Wash Type");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        checkBox = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);
        checkBox5 = findViewById(R.id.checkBox5);
        total = findViewById(R.id.total);
        next = findViewById(R.id.next);
        totalprice = findViewById(R.id.totalprice);
        pickup = findViewById(R.id.pickup);

        pickuptype = new pickuptype();

        databaseReference = FirebaseDatabase.getInstance().getReference("Type");

        final String c1 = "Body Wash";
        final String c2 = "Int. Cleaning";
        final String c3 = "Ext Body Wash";
        final String c4 = "Ext Int. Cleaning";
        final String c5 = "Int. Disinfectant";

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                i = (int) dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //checkbox 1
                if(checkBox.isChecked()==true){
                    body_wash_price = 1000;
                    counter++;
                }
                if(checkBox.isChecked()==false){
                    body_wash_price = 0;
                }

                //checkbox 2
                if(checkBox2.isChecked()==true){
                    interior_cleaning_price = 500;
                    counter++;
                }
                if(checkBox2.isChecked()==false){
                    interior_cleaning_price = 0;
                }

                //checkbox 3
                if(checkBox3.isChecked()==true){
                    extreme_body_wash_price = 1500;
                    counter++;
                }
                if(checkBox3.isChecked()==false){
                    extreme_body_wash_price = 0;
                }

                //checkbox 4
                if(checkBox4.isChecked()==true){
                    extreme_interior_cleaning_price = 750;
                    counter++;
                }
                if(checkBox4.isChecked()==false){
                    extreme_interior_cleaning_price = 0;
                }

                //checkbox 5
                if(checkBox5.isChecked()==true){
                    interior_disinfectant_price = 1500;
                    counter++;
                }
                if(checkBox5.isChecked()==false){
                    interior_disinfectant_price = 0;
                }

                total_price = body_wash_price + interior_cleaning_price + extreme_body_wash_price + extreme_interior_cleaning_price + interior_disinfectant_price;

                //converting the integer to a string for the toast message
                String total_string = String.valueOf(total_price);
                totalprice.setText("KSH." + total_string);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(counter>0){

                //checkbox 1
                if(checkBox.isChecked()){
                    pickuptype.setType1(c1);
                    databaseReference.child(String.valueOf(i + 1)).setValue(pickuptype);
                }


                //checkbox 2
                if(checkBox2.isChecked()){
                    pickuptype.setType2(c2);
                    databaseReference.child(String.valueOf(i + 1)).setValue(pickuptype);
                }


                //checkbox 3
                if(checkBox3.isChecked()){
                    pickuptype.setType3(c3);
                    databaseReference.child(String.valueOf(i + 1)).setValue(pickuptype);
                }


                //checkbox 4
                if(checkBox4.isChecked()){
                    pickuptype.setType4(c4);
                    databaseReference.child(String.valueOf(i + 1)).setValue(pickuptype);
                }


                //checkbox 5
                if(checkBox5.isChecked()){
                    pickuptype.setType5(c5);
                    databaseReference.child(String.valueOf(i + 1)).setValue(pickuptype);
                }

                pickuptype.setPrice(totalprice.getText().toString());
                pickuptype.setVehicletype(pickup.getText().toString());
                databaseReference.child(String.valueOf(i + 1 )).setValue(pickuptype);


                startActivity(new Intent(getApplicationContext(), TimeAndDate.class));
            } else{
                    Toast.makeText(Pickup.this, "Select Atleast 1 Wash Type.", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
                Toast.makeText(Pickup.this, "You are Logged Out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Pickup.this, MainActivity.class));
                return true;

            case R.id.Location:
                startActivity(new Intent(Pickup.this, Location.class));
                return true;

            case R.id.Detail:
                startActivity(new Intent(Pickup.this, Details.class));
                return true;

            case R.id.Payment:
                startActivity(new Intent(Pickup.this, PaymentMain.class));
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}


class pickuptype {

    private String type1, type2, type3, type4, type5, vehicletype, price;

    public pickuptype() {
    }

    public String getType1() {
        return type1;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public String getVehicletype() {
        return vehicletype;
    }

    public void setVehicletype(String vehicletype) {
        this.vehicletype = vehicletype;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getType3() {
        return type3;
    }

    public void setType3(String type3) {
        this.type3 = type3;
    }

    public String getType4() {
        return type4;
    }

    public void setType4(String type4) {
        this.type4 = type4;
    }

    public String getType5() {
        return type5;
    }

    public void setType5(String type5) {
        this.type5 = type5;
    }
}
