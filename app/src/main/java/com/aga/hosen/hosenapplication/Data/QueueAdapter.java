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
        final ImageButton btnDel = (ImageButton) view.findViewById(R.id.btnDel);


        final Queue h = getItem(position);



        tvName.setText(h.getName() + "");
        tvPhone.setText(h.getPhone() + "");
        tvAddress.setText(h.getAddress() + "");

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v==btnDel)
                {
                    del(h);
                }
            }
        });


        return view;


    }

    private void del ( final Queue q)
    {

        final String[] a = {"DELETE"};


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setCancelable(true);
        builder.setSingleChoiceItems(a, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
                Toast.makeText(getContext(), a[i], Toast.LENGTH_SHORT).show();
                if (i == 0) {
                    DatabaseReference reference;
                    //todo לקבלת קישור למסד הנתונים שלנו
                    //todo  קישור הינו לשורש של המסד הנתונים

                    reference = FirebaseDatabase.getInstance().getReference();
                    reference.child("mylist").child(q.getKeyId()).removeValue(new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError == null) {
                                Toast.makeText(getContext(), "delete successful", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getContext(), "delete failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }


        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }




}