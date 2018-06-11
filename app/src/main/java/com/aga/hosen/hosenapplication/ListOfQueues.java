package com.aga.hosen.hosenapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.aga.hosen.hosenapplication.Data.Queue;
import com.aga.hosen.hosenapplication.Data.QueueAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ListOfQueues extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listview;

    FirebaseDatabase db;
    DatabaseReference requests;

    QueueAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_queues);

        listview = (ListView)findViewById(R.id.listview);
        db= FirebaseDatabase.getInstance();
        requests= db.getReference("mylist");
        adapter = new QueueAdapter(this, R.layout.item_queue);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);
    }





    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
