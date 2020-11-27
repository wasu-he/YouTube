package com.example.newyoutube2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register2 extends AppCompatActivity {

    EditText mFullName, mEmail;
    Button mRegistrationBtn;

    DatabaseReference reff;
    Member2 member2;
    long maxid = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        mFullName = findViewById(R.id.fullname);
        mEmail = findViewById(R.id.email);
        mRegistrationBtn = findViewById(R.id.registerButton);


        member2 = new Member2();
        reff = FirebaseDatabase.getInstance().getReference().child("Member2");
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


                member2.setfName(mFullName.getText().toString().trim());
                member2.setEmail(mail);


                reff.child(String.valueOf(maxid+1)).setValue(member2);
                Toast.makeText(Register2.this, "data insert successfully", Toast.LENGTH_LONG).show();

            }
        });


    }
}