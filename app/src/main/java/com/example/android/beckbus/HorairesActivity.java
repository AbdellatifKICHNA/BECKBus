package com.example.android.beckbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HorairesActivity extends AppCompatActivity {

    private Button RechercherBtn;
    private TableLayout Table;
    private TextView Depart,Arrivee;
    private Spinner DepartSpin, ArriveeSpin;

    DatabaseReference RootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horaires);

        RootRef = FirebaseDatabase.getInstance().getReference();

        RechercherBtn = (Button) findViewById(R.id.rechercher_button);
        Table = (TableLayout) findViewById(R.id.table_layout);
        Depart = (TextView) findViewById(R.id.depart);
        Arrivee = (TextView) findViewById(R.id.arrivee);
        DepartSpin = (Spinner) findViewById(R.id.depart_spinner);
        ArriveeSpin = (Spinner) findViewById(R.id.arrivee_spinner);

        RechercherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Table.setVisibility(View.VISIBLE);
                Depart.setVisibility(View.INVISIBLE);
                Arrivee.setVisibility(View.INVISIBLE);
                DepartSpin.setVisibility(View.INVISIBLE);
                ArriveeSpin.setVisibility(View.INVISIBLE);
                RechercherBtn.setVisibility(View.INVISIBLE);
            }
        });
    }
}
