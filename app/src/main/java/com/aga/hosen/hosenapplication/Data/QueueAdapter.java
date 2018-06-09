package com.aga.hosen.hosenapplication.Data;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.aga.hosen.hosenapplication.R;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by aodai on 09/06/2018.
 */

public class QueueAdapter extends ArrayAdapter<Queue> {


    public QueueAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_queue, parent, false);
        TextView tvName = (TextView) view.findViewById(R.id.tvName);
        TextView tvAddress = (TextView) view.findViewById(R.id.tvAddress);
        TextView tvPhone = (TextView) view.findViewById(R.id.tvPhone);


        final Queue h = getItem(position);



        tvName.setText(h.getName() + "");
        tvPhone.setText(h.getPhone() + "");
        tvAddress.setText(h.getAddress() + "");

        return view;


    }
}