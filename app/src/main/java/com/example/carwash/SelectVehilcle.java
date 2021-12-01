package com.example.carwash;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SelectVehilcle extends AppCompatActivity {

    private androidx.appcompat.widget.Toolbar toolbar;

    ListView listView;

    int [] images={R.drawable.sedan,R.drawable.hatchback,R.drawable.pickup,R.drawable.suv2,R.drawable.van3,R.drawable.truck};
    String[] Names={"Salloon","Hatchback","Pickup","SUV","Van(Minivan)","Truck"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_vehilcle);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Select Vehicle Type");

        listView=(ListView)findViewById(R.id.listView);
        CustomAdapter adapter =new CustomAdapter();
        listView.setAdapter(adapter);
//he has used a list view to display the car types
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0) {

                    Toast.makeText(SelectVehilcle.this, "Choose Salloon Wash Type", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(view.getContext(), Sallon.class);
                    startActivity(intent);
                }

                if (position == 1) {
                    Toast.makeText(SelectVehilcle.this, "Choose Hatchback Wash Type", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(view.getContext(), Hatchback.class);
                    startActivity(intent);
                }

                if (position == 2) {
                    Toast.makeText(SelectVehilcle.this, "Choose Pickup Wash Type", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(view.getContext(), Pickup.class);
                    startActivity(intent);
                }
                if (position==3){
                    Toast.makeText(SelectVehilcle.this, "Choose SUV Wash Type", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(view.getContext(), SUV.class);
                    startActivity(intent);
                }
                if (position==4){
                    Toast.makeText(SelectVehilcle.this, "Choose Van(Minivan) Wash Type", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(view.getContext(), Van.class);
                    startActivity(intent);
                }
                if (position==5){
                    Toast.makeText(SelectVehilcle.this, "Choose Truck Wash Type", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(view.getContext(), Truck.class);
                    startActivity(intent);
                }
            }
        });

    }
    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup parent) {
            View view=getLayoutInflater().inflate(R.layout.customlayout,null);
            ImageView mImageView=view.findViewById(R.id.imageView2);
            TextView mTextView=view.findViewById(R.id.textView);
            mImageView.setImageResource(images[i]);
            mTextView.setText(Names[i]);
            return view;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Logout:
                Toast.makeText(SelectVehilcle.this, "You are Logged Out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SelectVehilcle.this, MainActivity.class));
                return true;

            case R.id.Location:
                startActivity(new Intent(SelectVehilcle.this, Location.class));
                return true;

            case R.id.Detail:
                startActivity(new Intent(SelectVehilcle.this, Details.class));
                return true;

            case R.id.Payment:
                startActivity(new Intent(SelectVehilcle.this, PaymentMain.class));
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}