package com.example.carwash;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Register extends AppCompatActivity {

    private EditText Name, EmailRegister, PhoneNumber, PasswordRegister;
    private Button SignupBtn;
    private ProgressBar progressBar2;
    private TextView Signin;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    String userID;
//    String isAdmin = "0";
//    //String type = "customer";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Name = findViewById(R.id.Name);
        EmailRegister = findViewById(R.id.EmailRegister);
        PhoneNumber = findViewById(R.id.PhoneNumber);
        PasswordRegister = findViewById(R.id.PasswordRegister);
        SignupBtn = findViewById(R.id.SignupBtn);
        Signin = findViewById(R.id.Signin);
        progressBar2 = findViewById(R.id.progressBar2);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        SignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = EmailRegister.getText().toString().trim();
                final String password = PasswordRegister.getText().toString().trim();
                final String fullname = Name.getText().toString().trim();
                final String phone = PhoneNumber.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    EmailRegister.setError("Email is Required!");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    PasswordRegister.setError("Password is Required.");
                    return;
                }

                if(password.length() < 6){
                    PasswordRegister.setError("Password must be atleast 6 Characters Long");
                    return;
                }

                if(TextUtils.isEmpty(phone)){
                    PhoneNumber.setError("Phone Number is Required.");
                    return;
                }

                progressBar2.setVisibility(View.VISIBLE);

                //registering the user in the db

                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "User Account Successfully Created", Toast.LENGTH_SHORT).show();
                            userID = firebaseAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = firebaseFirestore.collection("Users").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("FullName", fullname);
                            user.put("Email", email);
                            user.put("PhoneNumber", phone);
//                            user.put("Password", password);
//                            user.put("Admin", isAdmin);

                            documentReference.set(user);
                            //.addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void aVoid) {
//
//                                }
//                            });
                            startActivity(new Intent(getApplicationContext(), Login.class));
                        } else {
                            Toast.makeText(Register.this, "Error Occured!", Toast.LENGTH_SHORT).show();
                            progressBar2.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });




        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
    }


}