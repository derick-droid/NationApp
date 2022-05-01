package com.example.nation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MinistryRvAdapter.MinistryClickInterface{
    private RecyclerView ministryRV;
    private ProgressBar loadingPB;
    private FloatingActionButton addFAB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<MinistryRVModal> ministryRVModalArrayList;
    private RelativeLayout bottomSheetRL;
    private MinistryRVAdapter ministryRVAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ministryRV = findViewById(R.id.idRvMinistry);
        loadingPB = findViewById(R.id.idPBloading);
        addFAB = findViewById(R.id.idAddFAB);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Ministries");
        ministryRVModalArrayList = new ArrayList<>();
        ministryRVAdapter = new MinistryRvAdapter(ministryRVModalArrayList, this, this);
        ministryRV.setLayoutManager(new LinearLayoutManager(this));
        ministryRV.setAdapter(ministryRVAdapter);
        addFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

}