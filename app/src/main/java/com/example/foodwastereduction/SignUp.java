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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
EditText email,name,password,phone;
Button signup;
TextView txt;
FirebaseAuth firebaseAuth;
FirebaseFirestore firebaseFirestore;
DatabaseReference databaseReference;
FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        phone=(EditText)findViewById(R.id.phone);
        name=(EditText)findViewById(R.id.name);
        signup=(Button)findViewById(R.id.signup);
        txt=(TextView)findViewById(R.id.login);
        firebaseAuth= FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
databaseReference=FirebaseDatabase.getInstance().getReference();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final   SharedPreferences.Editor editor = preferences.edit();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            editor.putString("phn",phone.getText().toString());
                            Map<Object,String> userdata= new HashMap<>();
                            userdata.put("Name",name.getText().toString());
                            userdata.put("Email",email.getText().toString());
                            userdata.put("Phn",phone.getText().toString());
                            FirebaseDatabase.getInstance().getReference().child("Customer").push().setValue(userdata);
                            Intent in = new Intent(SignUp.this,SignIn.class);
                            startActivity(in);


                        }else{
                            String error = task.getException().getMessage();
                            Toast.makeText(getApplicationContext(),error,Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });
    }
}
