package com.aga.hosen.hosenapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.aga.hosen.hosenapplication.Data.Queue;
import com.aga.hosen.hosenapplication.Data.QueueAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private FirebaseAuth mAuth;
    private FirebaseUser user;

    private ListView listview;
    private QueueAdapter queueAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.listview);
        queueAdapter = new QueueAdapter(this, R.layout.item_queue);
        mAuth=FirebaseAuth.getInstance();
        listview.setAdapter(queueAdapter);
        readAndListen();
        listview.setOnItemClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnItmAddIem:
                Intent i = new Intent(this, AddQueueActivity.class);
                startActivity(i);
                break;
            case R.id.mnSignOut:
                Intent s = new Intent(this, LogInActivity.class);
                startActivity(s);
                break;
        }
        return true;
    }

    private void readAndListen() {

        FirebaseUser user = mAuth.getCurrentUser();
        String email = user.getEmail();
        email = email.replace('.', '*');
        DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference();
        reference.child("mylist").orderByChild("email").equalTo(email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                queueAdapter.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Queue q = ds.getValue(Queue.class);
                    Log.d("SL", q.toString());
                    queueAdapter.add(q);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final String[] a = {"DELETE"};
        final Queue q = (Queue) parent.getItemAtPosition(position);


        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Delete");
        builder.setCancelable(true);
        builder.setSingleChoiceItems(a, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, a[i], Toast.LENGTH_SHORT).show();
                if (i == 0) {
                    DatabaseReference reference;
                    //todo לקבלת קישור למסד הנתונים שלנו
                    //todo  קישור הינו לשורש של המסד הנתונים

                    reference = FirebaseDatabase.getInstance().getReference();
                    reference.child("mylist").child(q.getKeyId()).removeValue(new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError == null) {
                                Toast.makeText(MainActivity.this, "delete successful", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "delete failed", Toast.LENGTH_SHORT).show();
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