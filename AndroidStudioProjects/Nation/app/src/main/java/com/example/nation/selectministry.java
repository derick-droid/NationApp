package com.example.nation;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class selectministry extends AppCompatActivity {

    private TextInputEditText MinistryNameEdt, MinistryCodeEdt,MinistrySuitedForEdt,MinistryImgEdt,MinistryLinkEdt,MinistryDescEdt;
    private Button addMinistryBtn;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String MinistryID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectministry);
        MinistryNameEdt = findViewById(R.id.idEdtMinistryName);
        MinistryCodeEdt = findViewById(R.id.idEdtMinistryCode);
        MinistrySuitedForEdt = findViewById(R.id.idEdtMinistrySuited);
        MinistryImgEdt = findViewById(R.id.idEdtMinistryImageLink);
        MinistryLinkEdt = findViewById(R.id.idEdtMinistryLink);
        MinistryDescEdt = findViewById(R.id.idEdtMinistryDesc);
        addMinistryBtn = findViewById(R.id.idBtnAddMinistry);
        loadingPB = findViewById(R.id.idPBloading);
        firebaseDatabase = firebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Ministries");

        addMinistryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPB.setVisibility(View.VISIBLE);
                String MinistryName = MinistryNameEdt.getText().toString();
                String MinistryCode = MinistryCodeEdt.getText().toString();
                String MinistrySuitedFor = MinistrySuitedForEdt.getText().toString();
                String MinistryImage = MinistryImgEdt.getText().toString();
                String MinistryLink = MinistryLinkEdt.getText().toString();
                String MinistryDesc = MinistryDescEdt.getText().toString();
                MinistryID = MinistryName;

                MinistryRVModal ministryRVModal = new MinistryRVModal(MinistryName,MinistryCode,MinistrySuitedFor,MinistryImage,MinistryLink, MinistryDesc, MinistryID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        databaseReference.child(MinistryID).setValue(ministryRVModal);
                        Toast.makeText(selectministry.this, "The Ministry added ", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(selectministry.this,MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(selectministry.this, "Error is " + error.toString(), Toast.LENGTH_SHORT).show();

                    }
                });



            }
        });




    }
}