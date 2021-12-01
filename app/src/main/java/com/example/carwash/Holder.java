package com.example.carwash;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Holder  extends RecyclerView.ViewHolder {

    View view;
    public Holder(@NonNull View itemView) {
        super(itemView);

        view = itemView;


    }

    public void setView(Context context, String time, String date, String number){

        TextView selectdate = view.findViewById(R.id.date);
        TextView selecttime = view.findViewById(R.id.time);
        TextView regno = view.findViewById(R.id.regno);

        selectdate.setText(date);
        selecttime.setText(time);
        regno.setText(number);
    }
}
