package com.example.android.beckbus;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

import java.io.File;

public class AbonnementActivity extends AppCompatActivity {
    private CheckBox box1,box2,box3,box4,box5,box6,box7,boxAccepter;
    private TextView conditions,txt,date;
    private TextView prix,prixTotal,dhs;
    private Button rdvBtn;
    private ScrollView allBoxes;
    private int mCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abonnement);
        box1 = (CheckBox) findViewById(R.id.checkBox1);
        box2 = (CheckBox) findViewById(R.id.checkBox2);
        box3 = (CheckBox) findViewById(R.id.checkBox3);
        box4 = (CheckBox) findViewById(R.id.checkBox4);
        box5 = (CheckBox) findViewById(R.id.checkBox5);
        box6 = (CheckBox) findViewById(R.id.checkBox6);
        box7 = (CheckBox) findViewById(R.id.checkBox7);
        boxAccepter = (CheckBox) findViewById(R.id.accepter);
        prix = (TextView) findViewById(R.id.prix);
        prixTotal = (TextView) findViewById(R.id.prix_total);
        dhs = (TextView) findViewById(R.id.dhs);
        conditions = (TextView) findViewById(R.id.conditions);
        rdvBtn = (Button) findViewById(R.id.rdv);
        allBoxes = (ScrollView) findViewById(R.id.scroll);
        txt = (TextView) findViewById(R.id.texte);
        date = (TextView) findViewById(R.id.date);


        conditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                telecharger();
            }
        });

        box1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(box1.isChecked()){
                    ajouterPrix();
                } else{
                    retrancherPrix();
                }
            }
        });
        box2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(box2.isChecked()){
                    ajouterPrix();
                } else{
                    retrancherPrix();
                }
            }
        });
        box3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(box3.isChecked()){
                    ajouterPrix();
                } else{
                    retrancherPrix();
                }
            }
        });
        box4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(box4.isChecked()){
                    ajouterPrix();
                } else{
                    retrancherPrix();
                }
            }
        });
        box5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(box5.isChecked()){
                    ajouterPrix();
                } else{
                    retrancherPrix();
                }
            }
        });
        box6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(box6.isChecked()){
                    ajouterPrix();
                } else{
                    retrancherPrix();
                }
            }
        });
        box7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(box7.isChecked()){
                    ajouterPrix();
                } else{
                    retrancherPrix();
                }
            }
        });

        boxAccepter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(boxAccepter.isChecked()){
                    date.setVisibility(View.VISIBLE);
                } else{
                    date.setVisibility(View.INVISIBLE);
                }
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
                rdvBtn.setVisibility(View.VISIBLE);
            }
        });

        rdvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(box1.isChecked() || box2.isChecked() || box3.isChecked() || box4.isChecked() || box5.isChecked() || box6.isChecked() || box7.isChecked()){
                    txt.setVisibility(View.INVISIBLE);
                    allBoxes.setVisibility(View.INVISIBLE);
                    prixTotal.setVisibility(View.INVISIBLE);
                    prix.setVisibility(View.INVISIBLE);
                    dhs.setVisibility(View.INVISIBLE);
                    boxAccepter.setVisibility(View.INVISIBLE);
                    conditions.setVisibility(View.INVISIBLE);
                    rdvBtn.setVisibility(View.INVISIBLE);
                    date.setVisibility(View.INVISIBLE);
                }else{
                    Toast.makeText(AbonnementActivity.this, "Choisir au moins une ligne !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void telecharger() {
        Uri webpage = Uri.parse("https://firebasestorage.googleapis.com/v0/b/beckbus-898e4.appspot.com/o/Conditions.pdf?alt=media&token=4dad033c-ad0c-4959-87ae-ca3696b26894");
        Intent intent = new Intent(Intent.ACTION_VIEW,webpage);
        startActivity(intent);
    }

    private void ajouterPrix() {
        mCount=mCount+80;
        prix.setText(Integer.toString(mCount));
    }
    private void retrancherPrix() {
        mCount=mCount-80;
        prix.setText(Integer.toString(mCount));
    }
}
