package com.example.android.beckbus;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class LignesActivity extends AppCompatActivity {
    private Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lignes);

        btn1 = (Button) findViewById(R.id.un);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLocation(v);
            }
        });
    }

    public void openLocation(View view) {
        Uri addressUri = Uri.parse("https://www.google.com/maps/dir/Riad+Salam,+Beni-Mellal/Bd+Ibn+Khaldoun,+Beni-Mellal/Boulevard+Mohammed+V,+B%C3%A9ni+Mellal/Facult%C3%A9+polydisciplinaire+sultan+moulay+slimane/@32.3630602,-6.3553078,14z/data=!4m26!4m25!1m5!1m1!1s0xda3871028505699:0xa49444a250355b20!2m2!1d-6.3797902!2d32.3182522!1m5!1m1!1s0xda387abb00d5811:0x35ff6ce00ddeb322!2m2!1d-6.3690608!2d32.3362127!1m5!1m1!1s0xda38706d5fb43bb:0xa333cd7ad4e3267e!2m2!1d-6.3563877!2d32.3357076!1m5!1m1!1s0xda385fee999f689:0x4e7fbccedbb34938!2m2!1d-6.318748!2d32.3753585!3e0");
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        startActivity(intent);
    }
}
