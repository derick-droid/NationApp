package com.example.nation;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registeractivity extends AppCompatActivity {


    private TextInputEditText userNameEdt,pwdEdt,cnfPwdEdt;
    private Button registerBtn;
    private ProgressBar loadingPB;
    private TextView loginTv;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeractivity);
        userNameEdt = findViewById(R.id.idEdtUserName);
        pwdEdt = findViewById(R.id.idEdtPwd);
        cnfPwdEdt= findViewById(R.id.idedtConfPWd);
        registerBtn = findViewById(R.id.idBtnRegister);
        loadingPB = findViewById(R.id.idPBloading);
        loginTv = findViewById(R.id.idTVLogin);
        mAuth = FirebaseAuth.getInstance();
        loginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(registeractivity.this,loginactivity.class);
                startActivity(i);


            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPB.setVisibility(view.VISIBLE);
                String userName = userNameEdt.getText().toString();
                String pwd = pwdEdt.getText().toString();
                String confPwd = cnfPwdEdt.getText().toString();
                if (!pwd.equals(confPwd)) {
                    Toast.makeText(registeractivity.this, "please check both passwords", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(userName) && TextUtils.isEmpty(pwd) && TextUtils.isEmpty(confPwd)) {
                    Toast.makeText(registeractivity.this, "please fill the credentials", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(userName, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                loadingPB.setVisibility(View.GONE);
                                Toast.makeText(registeractivity.this, "User Name Registration", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(registeractivity.this, loginactivity.class);
                                startActivity(i);
                                finish();


                            } else {
                                loadingPB.setVisibility(View.GONE);
                                Toast.makeText(registeractivity.this, "Failed to register user..", Toast.LENGTH_SHORT).show();
                            }
                        }


                    });
                }
            }
        });



    }
}