package com.example.nation;

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

import java.util.HashMap;
import java.util.Map;

public class editministry extends AppCompatActivity {

    private TextInputEditText MinistryNameEdt, MinistryCodeEdt,MinistrySuitedForEdt,MinistryImgEdt,MinistryLinkEdt,MinistryDescEdt;
    private Button updateMinistryBtn, deleteMinistryBtn;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String MinistryID;
    private MinistryRVModal ministryRVModal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Ministries").child(MinistryID);
        MinistryNameEdt = findViewById(R.id.idEdtMinistryName);
        MinistryCodeEdt = findViewById(R.id.idEdtMinistryCode);
        MinistrySuitedForEdt = findViewById(R.id.idEdtMinistrySuited);
        MinistryImgEdt = findViewById(R.id.idEdtMinistryImageLink);
        MinistryLinkEdt = findViewById(R.id.idEdtMinistryLink);
        MinistryDescEdt = findViewById(R.id.idEdtMinistryDesc);
        updateMinistryBtn = findViewById(R.id.idBtnUpdateMinistry);
        deleteMinistryBtn = findViewById(R.id.idBtnDeleteMinistry);
        loadingPB = findViewById(R.id.idPBloading);

        ministryRVModal = getIntent().getParcelableExtra("Ministry");
        if (ministryRVModal!= null){
            MinistryNameEdt.setText(ministryRVModal.getMinistryName());
            MinistryCodeEdt.setText(ministryRVModal.getMinistryCode());
            MinistrySuitedForEdt.setText(ministryRVModal.getMinistryBestSuitedFor());
            MinistryImgEdt.setText(ministryRVModal.getMinistryImage());
            MinistryLinkEdt.setText(ministryRVModal.getMinistryLink());
            MinistryDescEdt.setText(ministryRVModal.getMinistryDescription());
            MinistryID = ministryRVModal.getMinistryID();

        }

        databaseReference = firebaseDatabase.getReference("Ministries").child(MinistryID);
        updateMinistryBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                loadingPB.setVisibility(View.VISIBLE);
                String MinistryName = MinistryNameEdt.getText().toString();
                String MinistryCode = MinistryCodeEdt.getText().toString();
                String MinistrySuitedFor = MinistrySuitedForEdt.getText().toString();
                String MinistryImage = MinistryImgEdt.getText().toString();
                String MinistryLink = MinistryLinkEdt.getText().toString();
                String MinistryDesc = MinistryDescEdt.getText().toString();

                Map<String, Object> map = new HashMap<>();
                map.put("MinistryName", MinistryName);
                map.put("MinistryDescription", MinistryDesc);
                map.put("MinistryCode", MinistryCode);
                map.put("MinistryBestSuitedFor", MinistrySuitedFor);
                map.put("MinistryImage", MinistryImage);
                map.put("MinistryLink", MinistryLink);
                map.put("MinistryID", MinistryID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        databaseReference.updateChildren(map);
                        Toast.makeText(editministry.this, "Ministry updated ..", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(editministry.this, MainActivity.class));
                        

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(editministry.this, "Failed to update course ..", Toast.LENGTH_SHORT).show();

                    }
                });



            }
        });
        deleteMinistryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteMinistry();

            }
        });
    }
    private  void deleteMinistry(){
        databaseReference.removeValue();
        Toast.makeText(this, "course deleted successfully ..", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(editministry.this, MainActivity.class));

    }
}