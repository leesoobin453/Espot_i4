package com.example.espot_i4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class User_Main extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        // 충전소 찾기
        Button findButton = (Button) findViewById(R.id.findbutton);
        findButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                //화면 전환
                Intent intent = new Intent(getApplicationContext(), find_cs.class);
                startActivity(intent);
            }
        });

        // 사용 가능한 충전기 확인
        Button checkcgButton = (Button) findViewById(R.id.checkcgbutton);
        checkcgButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                //화면 전환
                Intent intent = new Intent(getApplicationContext(), check_charger.class);
                startActivity(intent);
            }
        });

        // 홈 버튼
        Button homeButton = (Button) findViewById(R.id.homebutton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                //화면 전환
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        // 설정 버튼
        Button settingButton = (Button) findViewById(R.id.settingbutton);
        settingButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                //화면 전환
                Intent intent = new Intent(getApplicationContext(), setting.class);
                startActivity(intent);
            }
        });
    }
}