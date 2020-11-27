package com.example.newyoutube2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.database.ValueEventListener;

import static com.example.newyoutube2.R.layout.activity_register1;


public class Register1 extends AppCompatActivity {
    EditText mFullName, mEmail, mPassword, mPhone;
    Button mRegistrationBtn,mnext;
    //TextView mLoginBtn;
    //FirebaseAuth fAuth;

    DatabaseReference reff;
    Member member;
    long maxid = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_register1);

        mFullName = findViewById(R.id.fullname);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.pass);
        mPhone = findViewById(R.id.phone);
        mRegistrationBtn = findViewById(R.id.registerButton);
        mnext = findViewById(R.id.next);

        //mLoginBtn=  findViewById(R.id.createText) ;


        member = new Member();
        reff = FirebaseDatabase.getInstance().getReference().child("Member");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                    maxid = (snapshot.getChildrenCount());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mRegistrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = (mEmail.getText().toString().trim());
                String pass = (mPassword.getText().toString().trim());
                int pho = Integer.parseInt(mPhone.getText().toString().trim());

                member.setfName(mFullName.getText().toString().trim());
                member.setEmail(mail);
                member.setPassword(pass);
                member.setPhone(pho);

                reff.child(String.valueOf(maxid+1)).setValue(member);
                Toast.makeText(Register1.this, "data insert successfully", Toast.LENGTH_LONG).show();

            }
        });

        mnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity1();

            }
        });








        //fAuth = FirebaseAuth.getInstance();

        //if (fAuth.getCurrentUser() != null){
        // startActivity(new Intent(getApplicationContext(), Logout.class));
        //finish();
        //}


       /* mRegistrationBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    mPassword.setError("Password is required");
                    return;
                }
                if(password.length()<6){
                    mPassword.setError("Password must be >= 6 characters");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Register1.this, "User created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Logout.class));
                        }
                        else {
                            Toast.makeText(Register1.this,"Error !" +task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });



            }
        });




    }*/


    }
    public void openActivity1(){
        Intent intent = new Intent(this,Register2.class);
        startActivity(intent);
    }
}
