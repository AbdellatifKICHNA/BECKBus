package com.example.android.beckbus;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SettingsActivity extends AppCompatActivity {

    private Button UpdateAccountSettings;
    private EditText userNom, userPrenom, userAge;
    private String currentUserID;
    private FirebaseAuth mAuth;
    private DatabaseReference RootRef;

    private Spinner userCycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        RootRef = FirebaseDatabase.getInstance().getReference();

        UpdateAccountSettings = (Button) findViewById(R.id.update_settings_button);
        userNom = (EditText) findViewById(R.id.set_last_name);
        userPrenom = (EditText) findViewById(R.id.set_first_name);
        userAge = (EditText) findViewById(R.id.set_user_age);

        userCycle = (Spinner) findViewById(R.id.chooser_spinner);

        UpdateAccountSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateSettings();
            }
        });

        RetrieveUserInfo();

    }

    private void RetrieveUserInfo() {
        RootRef.child("Etudiants").child(currentUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if ((dataSnapshot.exists()) && (dataSnapshot.hasChild("nom"))){
                    String retrieveUserNom = dataSnapshot.child("nom").getValue().toString();
                    String retrieveUserPrenom = dataSnapshot.child("prenom").getValue().toString();
                    String retrieveAge = dataSnapshot.child("age").getValue().toString();
                    String retrieveCycle = dataSnapshot.child("cycle").getValue().toString();

                    userNom.setText(retrieveUserNom);
                    userPrenom.setText(retrieveUserPrenom);
                    userAge.setText(retrieveAge);
                    //userCycle.setSelection();

                }
                else{
                    Toast.makeText(SettingsActivity.this, "Please set and update your profile information", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void UpdateSettings() {
        String setNom = userNom.getText().toString();
        String setPrenom = userPrenom.getText().toString();
        String setAge = userAge.getText().toString();
        String setSpinner = userCycle.getSelectedItem().toString();
        if(TextUtils.isEmpty(setNom)){
            Toast.makeText(this, "Please Enter your username", Toast.LENGTH_LONG).show();
        }

        else{
            HashMap<String,Object> profileMap = new HashMap<>();
            profileMap.put("uid",currentUserID);
            profileMap.put("nom",setNom);
            profileMap.put("prenom",setPrenom);
            profileMap.put("age",setAge);
            profileMap.put("cycle",setSpinner);
            RootRef.child("Etudiants").child(currentUserID).setValue(profileMap)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SettingsActivity.this, "Profile updated Successfully", Toast.LENGTH_SHORT).show();
                                SendUserToPrincipalActivity();
                            }
                            else{
                                String message = task.getException().toString();
                                Toast.makeText(SettingsActivity.this, "Error: "+ message, Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

    private void SendUserToPrincipalActivity() {
        Intent principalIntent = new Intent(SettingsActivity.this,MainActivity.class);
        startActivity(principalIntent);
    }
}
