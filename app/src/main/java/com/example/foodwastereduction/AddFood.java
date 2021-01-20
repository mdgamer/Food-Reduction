package com.example.foodwastereduction;

import androidx.appcompat.app.AppCompatActivity;

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

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddFood extends AppCompatActivity {
    String state_array[]=new String[]{"Select State","Haryana","Punjab","Gujrat"};
    ArrayList<String> city_array=new ArrayList<String>();
    Spinner state,city;
    String state1,city1;
    EditText address1,address2,restrauntName;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        state=(Spinner)findViewById(R.id.state);
        city=(Spinner)findViewById(R.id.city);
address1=(EditText)findViewById(R.id.add1);
        address2=(EditText)findViewById(R.id.add2);
        restrauntName=(EditText)findViewById(R.id.res_name);
        btn=(Button)findViewById(R.id.add_food_btn) ;
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,state_array);
        state.setAdapter(arrayAdapter);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final   SharedPreferences.Editor editor = preferences.edit();

        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                String p=parent.getItemAtPosition(position).toString();
                if(p.equals("select state")){

                }

                switch (p){
                    case "Haryana":
                        state1="Haryana";
                        city_array.clear();
                        city_array.add("Select City");
                        city_array.add("Gurgaon");
                        city_array.add("Faridabad");
                        city_array.add("Ambala");
                        break;
                    case "Gujrat":
                        city_array.clear();


                        city_array.add("Select City");
                        city_array.add("Vadodara");
                        city_array.add("Gandhinagar");
                        city_array.add("Surat");
                        break;
                    case "Punjab":
                        city_array.clear();
                        city_array.add("Select City");
                        city_array.add("Amritsar");
                        city_array.add("Ludhiana");
                        city_array.add("Jalandhar");
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        city_array.add("Select City");
        ArrayAdapter arrayAdapter1 = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,city_array);
        city.setAdapter(arrayAdapter1);

   city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String p=parent.getItemAtPosition(position).toString();
                if(p.equals("Select City")){

                }
                else{
switch (p){

    case "Gurgaon": city1="Gurgaon";
}

                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


   btn.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           editor.putString("City",city1);
           editor.putString("State",state1);
           editor.putString("Add1",address1.getText().toString());
           editor.putString("Add2",address2.getText().toString());
           editor.putString("Restraunt",restrauntName.getText().toString());
           editor.apply();
           Intent in = new Intent(AddFood.this,FinalAddFood.class);
           startActivity(in);

       }
   });
    }
}
