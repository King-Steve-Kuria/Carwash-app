package com.example.carwash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.internal.$Gson$Types;

public class OwnerLogin extends AppCompatActivity {


    private Button LoginBtn;
    private EditText Email, Password;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_owner_login);

        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);
        LoginBtn = findViewById(R.id.LoginBtn);
        progressBar = findViewById(R.id.progressBar);

    }

    private boolean validateName () {
        String val = Email.getText().toString();
        if (val.isEmpty()) {
            Email.setError("Field cannot be empty");
            return false;
        } else {
            Email.setError(null);
            return true;

        }
    }

    private boolean validatePasswd () {
        String val = Password.getText().toString();
        if (val.isEmpty()) {
            Password.setError("Field cannot be empty");
            return false;
        } else {
            Password.setError(null);
            return true;
        }
    }

    public void loginUser (View view){
        //validate login info
        if (!validateName() || !validatePasswd()) {
            return;
        }
        else {
            isUser();
        }
    }

    private void isUser() {

        final String userEnteredUsername = Email.getEditableText().toString().trim();
        final String userEnteredPassword = Password.getEditableText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query checkUser = reference.orderByChild("Username").equalTo(userEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("Password").getValue(String.class);

                    if (passwordFromDB.equals(userEnteredPassword)) {

                        String usernameFromDB = dataSnapshot.child(userEnteredUsername).child("Username").getValue(String.class);
                        String firstnameFromDB = dataSnapshot.child(userEnteredUsername).child("Firstname").getValue(String.class);
                        String lastnameFromDB = dataSnapshot.child(userEnteredUsername).child("Lastname").getValue(String.class);


                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        intent.putExtra("Username", usernameFromDB);
                        intent.putExtra("Password", passwordFromDB);
                        intent.putExtra("Firstname", firstnameFromDB);
                        intent.putExtra("Lastname", lastnameFromDB);


                        startActivity(intent);

                    } else {
                        Password.setError("Incorrect Password");
                        Password.requestFocus();
                    }
                } else {
                    Email.setError("No such Admin Exist");
                    Email.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }


        });

    }
}


























































//        LoginBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                 final String email = Email.getText().toString().trim();
//                 final String password = Password.getText().toString().trim();
//
//                if(TextUtils.isEmpty(email)){
//                    Email.setError("Email is Required!");
//                    return;
//                }
//
//                if(TextUtils.isEmpty(password)){
//                    Password.setError("Password is Required!");
//                    return;
//                }
//
//                if(password.length() < 6){
//                    Password.setError("Password Must be alteast 6 Characters Long");
//                    return;
//                }
//
//                progressBar.setVisibility(View.VISIBLE);
//
//                //authenticating the user
//
////                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
////                    @Override
////                    public void onComplete(@NonNull Task<AuthResult> task) {
////
////                        if(task.isSuccessful()){
////                            Toast.makeText(OwnerLogin.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
////                            startActivity(new Intent(getApplicationContext(),Main2Activity.class));
////                        } else {
////                            Toast.makeText(OwnerLogin.this, "Error Occured!", Toast.LENGTH_SHORT).show();
////                            progressBar.setVisibility(View.GONE);
////                        }
////                    }
////                });
//
//
//                FirebaseFirestore roofRef = FirebaseFirestore.getInstance();
//                CollectionReference userRef = roofRef.collection("Users");
//                Query query = userRef.whereEqualTo("Email", "jay.p2604@gmail.com");
//                query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if(task.isSuccessful()){
//                            for (QueryDocumentSnapshot queryDocumentSnapshot :task.getResult()){
//                                //String email = queryDocumentSnapshot.getString("Email");
//                                //String password = queryDocumentSnapshot.getString("Password");
//
//                                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<AuthResult> task) {
//
//                                    }
//                                });
//                            }
//                        }
//                    }
//                });
//
//                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
//                userRef.document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                        if(task.isSuccessful()){
//                            DocumentSnapshot document = task.getResult();
//                            if(document.exists()){
//                                String type = document.getString("Admin");
//                                if(type.equals ("1")){
//                                    Toast.makeText(OwnerLogin.this, "Welcome back Admin!", Toast.LENGTH_SHORT).show();
//                                    startActivity(new Intent(OwnerLogin.this, Main2Activity.class));
//                                } else {
//                                    Toast.makeText(OwnerLogin.this, "You are not an Admin!", Toast.LENGTH_SHORT).show();
//                                  // startActivity(new Intent(OwnerLogin.this, Main2Activity.class));
//                                }
//                                progressBar.setVisibility(View.GONE);
//                            }
//                        }
//                    }
//                });
//
//            }
//        });
//
//    }
//
//}

