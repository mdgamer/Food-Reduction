package com.example.foodwastereduction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NgoHome extends AppCompatActivity {
CardView cardView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_home);
        cardView1=(CardView)findViewById(R.id.show_pickup);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(NgoHome.this,NgoPickUp.class);
                startActivity(in);
            }
        });
    }
}
