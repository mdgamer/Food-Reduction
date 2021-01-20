package com.example.foodwastereduction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
CardView cardView1,cardView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final   SharedPreferences.Editor editor = preferences.edit();
        cardView1=(CardView)findViewById(R.id.food);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("User","food");
                editor.apply();
                Intent in = new Intent(MainActivity.this,MainScreen.class);
                startActivity(in);
            }
        });

        cardView2=(CardView)findViewById(R.id.ngo);
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("User","ngo");
                editor.apply();
                Intent in = new Intent(MainActivity.this,SignIn_Ngo.class);
                startActivity(in);
            }
        });
    }
}
