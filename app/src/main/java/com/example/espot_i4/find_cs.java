package com.example.espot_i4;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class find_cs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_find_cs);

        // WindowInsets 처리
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets.consumeSystemWindowInsets(); // 람다식의 마지막에 위치해야 함
        });

        // WebView 설정
        WebView webView = (WebView) findViewById(R.id.webView); // 레이아웃에 정의된 WebView의 ID
        webView.getSettings().setJavaScriptEnabled(true); // JavaScript 활성화
        webView.loadUrl("file:///android_asset/map3.html"); // assets 폴더 내 map.html 로드

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // 화면 비율
        //webSettings.setUseWideViewPort(true); // wide viewport를 사용하도록 설정
        webSettings.setLoadWithOverviewMode(true); // 컨텐츠가 웹뷰보다 클 경우 스크린 크기에 맞게 조정

        // 웹뷰 멀티 터치 가능하게 (줌기능)
        webSettings.setBuiltInZoomControls(true); // 줌 아이콘 사용
        webSettings.setSupportZoom(true);

    }
}
