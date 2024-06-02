package com.example.espot_i4;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Manager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        Button naverButton = (Button) findViewById(R.id.homebutton);
        naverButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //화면 전환
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        // CCTV URL 연결
        Button CCTVBtn = (Button) findViewById(R.id.CCTVButton);
        CCTVBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("172.30.1.61"));
                startActivity(myIntent);
            }
        });

    }
}