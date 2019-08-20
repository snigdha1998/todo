package com.example.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

FirebaseDatabase database;
    Button logout;
    RecyclerView recyclerView;
    FloatingActionButton add;
    DatabaseReference myref;
    Notesadapter notesadapter;
    List<Notesmodel> notesmodelList;
    List<Notesmodel> retrivedDataList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logout=findViewById(R.id.logout);
        database=FirebaseDatabase.getInstance();
        myref=FirebaseDatabase.getInstance().getReference("notes_row");
        recyclerView=findViewById(R.id.recycleone);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        notesmodelList=new ArrayList<>();
        add=findViewById(R.id.addbutton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpDialog();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutActivity();
            }
        });
    }



    private void logoutActivity() {

        Intent intent=new Intent(this,Login.class);
        startActivity(intent);
    }

    private void setUpDialog() {
        View view= LayoutInflater.from(this).inflate(R.layout.notes_row,null);
        final EditText TitleEditText=view.findViewById(R.id.notestitle);
        final  EditText DescEditText=view.findViewById(R.id.notesdesc);
        Button save=view.findViewById(R.id.save);
        final AlertDialog alertDialog=new AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title=TitleEditText.getText().toString();
                String desc=DescEditText.getText().toString();

                Notesmodel notesmodel=new Notesmodel();
                notesmodel.settitle(title);
                notesmodel.setdesc(desc);

                notesmodelList.add(notesmodel);
                String id =myref.push().getKey();
                myref.child(id).setValue(notesmodel);
                notesadapter=new Notesadapter(retrivedDataList,getApplicationContext());
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(notesadapter);


                alertDialog.dismiss();


            }
        });

        alertDialog.show();
    }
    @Override
    protected void onStart() {

        super.onStart();
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                retrivedDataList.clear();
                for (DataSnapshot noteShot:dataSnapshot.getChildren()){
                    Notesmodel notesmodel=noteShot.getValue(Notesmodel.class);
                    retrivedDataList.add(notesmodel);

                    notesadapter=new Notesadapter(retrivedDataList,getApplicationContext());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(notesadapter);}

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}


