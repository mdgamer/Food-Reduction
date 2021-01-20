package com.example.foodwastereduction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NgoPickUp extends AppCompatActivity {
DatabaseReference databaseReference;
    String Add1,Add2,City,State,food1,food2,food3,food4,rest;
    TextView edt1,edt2,edt3,edt4,edt5,edt6,edt7;
    Button btn1;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_pick_up);
        edt1=(TextView) findViewById(R.id.tt1) ;
        edt2=(TextView) findViewById(R.id.tt2) ;
        edt3=(TextView) findViewById(R.id.tt3) ;
        edt4=(TextView) findViewById(R.id.tt4) ;
        edt5=(TextView) findViewById(R.id.tt5) ;
        edt6=(TextView) findViewById(R.id.tt6) ;
        edt7=(TextView) findViewById(R.id.tt8) ;
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Order").child("9999999999");
        btn1=(Button)findViewById(R.id.final_btn) ;
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Add1=dataSnapshot.child("Address1").getValue().toString();
                 Add2=dataSnapshot.child("Address2").getValue().toString();
                City=dataSnapshot.child("City").getValue().toString();
                State=dataSnapshot.child("State").getValue().toString();
               food1= dataSnapshot.child("Food Name 1").getValue().toString();
                food2= dataSnapshot.child("Food Name 2").getValue().toString();
                food3= dataSnapshot.child("Food Name 3").getValue().toString();
                food4= dataSnapshot.child("Food Name 4").getValue().toString();
                rest= dataSnapshot.child("RestrauntName").getValue().toString();

                edt1.setText(rest);
                edt2.setText(Add1);
                edt3.setText(Add2 + " , "+City+ " , "+State );
                edt4.setText(food1);
                edt5.setText(food2 + " Kg");
                edt6.setText(food3);
                edt7.setText(food4);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Order Accepted", Toast.LENGTH_SHORT).show();
                sendMail();
                Intent in = new Intent(NgoPickUp.this,NgoHome.class);
                startActivity(in);
            }
        });
    }

    private void sendMail() {

        String mail = "mohammadsaqulain18@gmail.com";
        String message = "Your order has been accepted our pickup truck is on its way.";
        String subject = "Order Accepted";

        //Send Mail
        JavaMailAPI javaMailAPI = new JavaMailAPI(this,mail,subject,message);

        javaMailAPI.execute();

    }

}
