package com.example.foodwastereduction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FinalAddFood extends AppCompatActivity {
    String qty_array[]=new String[]{"Select Quantity","1","2","3","4","5"};
    Spinner qty1,qty2,qty3,qty4;
    String foodName1,foodName2,foodName3,foodName4;
    EditText food1,food2,food3,food4;
    String qtty1,qtty2,qtty3,qtty4;
    Button btn;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_add_food);

        qty1=(Spinner)findViewById(R.id.qty1);
        qty2=(Spinner)findViewById(R.id.qty2);
        qty3=(Spinner)findViewById(R.id.qty3);
        qty4=(Spinner)findViewById(R.id.qty4);
        food1=(EditText)findViewById(R.id.fn1);
        food2=(EditText)findViewById(R.id.fn2);
        food3=(EditText)findViewById(R.id.fn3);
        food4=(EditText)findViewById(R.id.fn4);
        btn=(Button)findViewById(R.id.done) ;
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,qty_array);
        qty1.setAdapter(arrayAdapter);
        qty2.setAdapter(arrayAdapter);
        qty3.setAdapter(arrayAdapter);
        qty4.setAdapter(arrayAdapter);

databaseReference=FirebaseDatabase.getInstance().getReference();
        qty1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                String p=parent.getItemAtPosition(position).toString();
                if(p.equals("Select Quantity")){

                }

                switch (p){
                    case "1":
                        qtty1="1";
                        break;
                    case "2":
                        qtty1="2";
                        break;
                    case "3":
                        qtty1="3";
                        break;
                    case "4":
                        qtty1="4";
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        qty4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                String p=parent.getItemAtPosition(position).toString();
                if(p.equals("Select Quantity")){

                }

                switch (p){
                    case "1":
                        qtty4="1";
                        break;
                    case "2":
                        qtty4="2";
                        break;
                    case "3":
                        qtty4="3";
                        break;
                    case "4":
                        qtty4="4";
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        qty3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                String p=parent.getItemAtPosition(position).toString();
                if(p.equals("Select Quantity")){

                }

                switch (p){
                    case "1":
                        qtty3="1";
                        break;
                    case "2":
                        qtty3="2";
                        break;
                    case "3":
                        qtty3="3";
                        break;
                    case "4":
                        qtty3="4";
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        qty2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                String p=parent.getItemAtPosition(position).toString();
                if(p.equals("Select Quantity")){

                }

                switch (p){
                    case "1":
                        qtty2="1";
                        break;
                    case "2":
                        qtty2="2";
                        break;
                    case "3":
                        qtty2="3";
                        break;
                    case "4":
                        qtty2="4";
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

       final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodName1=food1.getText().toString();
                foodName2=food2.getText().toString();
                foodName3=food3.getText().toString();
                foodName4=food4.getText().toString();


                String City = preferences.getString("City", "");
                String State = preferences.getString("State", "");
                String Add1 = preferences.getString("Add1", "");
                String Add2 = preferences.getString("Add2", "");
                String Res = preferences.getString("Restraunt", "");
                String phn=preferences.getString("phn", "");
                Map<Object,String> userdata= new HashMap<>();
                userdata.put("RestrauntName",Res);
                userdata.put("Address1",Add1);
                userdata.put("Address2",Add2);
                userdata.put("City",City);
                userdata.put("State",State);
                userdata.put("Food Name 1",foodName1 + " Qty - " +qtty1);
                userdata.put("Food Name 2",foodName2 + " Qty - " +qtty2);
                userdata.put("Food Name 3",foodName3 + " Qty - " +qtty3);
                userdata.put("Food Name 4",foodName4 + " Qty - " +qtty4);

                FirebaseDatabase.getInstance().getReference().child("Order").child("9999999999").setValue(userdata);
                Toast.makeText(FinalAddFood.this, "Order Created", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(FinalAddFood.this,FoodHome.class);
                startActivity(in);

            }
        });
    }


    }

