package com.example.newyoutube2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShowData extends AppCompatActivity {

    TextView a,b,c;
    Button button;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        a=(TextView)findViewById(R.id.showName);
        b=(TextView)findViewById(R.id.showEmail);
        c=(TextView)findViewById(R.id.showphone);
        button =(Button)findViewById(R.id.showButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reff= FirebaseDatabase.getInstance().getReference().child("Member").child("4");
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        String fName = snapshot.child("fName").getValue().toString();
                        String email = snapshot.child("email").getValue().toString();
                        String phone = snapshot.child("phone").getValue().toString();

                        a.setText(fName);
                        b.setText(email);
                        c.setText(phone);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}