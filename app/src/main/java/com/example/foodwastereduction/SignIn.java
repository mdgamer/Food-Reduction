package com.example.foodwastereduction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class SignIn extends AppCompatActivity {
EditText password,email;
Button btn;
FirebaseAuth firebaseAuth;
String User;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        password=(EditText)findViewById(R.id.editpassword);
        email=(EditText)findViewById(R.id.editemail);
        firebaseAuth=FirebaseAuth.getInstance();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
       User = preferences.getString("user", "");
btn=(Button)findViewById(R.id.btnSignIn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                          //  if(User.equals("food")){
                                Intent in = new Intent(SignIn.this,FoodHome.class);
                                startActivity(in);

                         //   }
                         //   else if(User.equals("ngo")){

                            //    Intent in = new Intent(SignIn.this,NgoHome.class);
                            //    startActivity(in);

                            //}

                        }
                        else{

                            String error=task.getException().getMessage();
                            Toast.makeText(getApplicationContext(),error,Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

    }
}
