package com.example.carwash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;

import java.util.Calendar;

public class TimeAndDate extends AppCompatActivity {

    private androidx.appcompat.widget.Toolbar toolbar;

    TextView dateTextView, timeTextView;
    Button confirm_btn;
    EditText editText;
    DatabaseReference databaseReference;
    FirebaseDatabase database;
    Customer customer;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_and_date);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Select Date and Time");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        dateTextView = findViewById(R.id.dateTextView);
        timeTextView = findViewById(R.id.timeTextView);
        editText = findViewById(R.id.editText); // car reg number
        confirm_btn = findViewById(R.id.confirm_btn);

        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleDateButton();
            }
        });
        timeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleTimeButton();
            }
        });

        customer = new Customer();
        databaseReference = FirebaseDatabase.getInstance().getReference("Dateandtime");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    i = (int) dataSnapshot.getChildrenCount();
                } else {
                    ///
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //
            }
        });

        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String regno = editText.getText().toString().trim();

                if(TextUtils.isEmpty(regno)){
                    editText.setError("Vehicle Registration Number is Required!");
                    return;
                }


                customer.setDate(dateTextView.getText().toString());
                customer.setTime(timeTextView.getText().toString());
                customer.setRegNo(editText.getText().toString());

                databaseReference.child(String.valueOf(i + 1)).setValue(customer);

                openLastActivity();
            }
        });

    }

    public void openLastActivity(){
        Intent intent = new Intent(this, Closing.class);
        startActivity(intent);
    }

//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                customer.setDate(dateTextView.getText().toString());
//                customer.setTime(timeTextView.getText().toString());
//                customer.setRegNo(editText.getText().toString());
//
//                databaseReference.child(String.valueOf(i + 1)).setValue(customer);
//            }
//        });
//    }

    private void handleDateButton() {

        Calendar calendar = Calendar.getInstance();
        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DATE = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.YEAR, year);
                calendar1.set(Calendar.MONTH, month);
                calendar1.set(Calendar.DATE, date);
                String dateText = DateFormat.format("EEEE, MMM d, yyyy", calendar1).toString();

                dateTextView.setText(dateText);
            }
        }, YEAR, MONTH, DATE);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();

    }

    private void handleTimeButton() {
        final Calendar calendar = Calendar.getInstance();
        int HOUR = calendar.get(Calendar.HOUR);
        int MINUTE = calendar.get(Calendar.MINUTE);
        boolean is24HourFormat = DateFormat.is24HourFormat(this);

//        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
//                Log.i(TAG, "onTimeSet: " + hour + minute);
//                Calendar calendar1 = Calendar.getInstance();
//                calendar1.set(Calendar.HOUR, hour);
//                calendar1.set(Calendar.MINUTE, minute);
//                String dateText = DateFormat.format("h:mm a", calendar).toString();
//               timeTextView.setText(dateText);
//            }
//        }, HOUR, MINUTE, true);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String timeString = hourOfDay + ":" + minute;
                timeTextView.setText(timeString);
            }
        }, HOUR, MINUTE, is24HourFormat);

        timePickerDialog.show();

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
                Toast.makeText(TimeAndDate.this, "You are Logged Out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(TimeAndDate.this, MainActivity.class));
                return true;

            case R.id.Location:
                startActivity(new Intent(TimeAndDate.this, Location.class));
                return true;

            case R.id.Detail:
                startActivity(new Intent(TimeAndDate.this, Details.class));
                return true;

            case R.id.Payment:
                startActivity(new Intent(TimeAndDate.this, PaymentMain.class));
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

}
