package com.example.espot_i4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.widget.SwitchCompat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private FirebaseAuth mFirebaseAuth; // 파이어베이스 인증
    private DatabaseReference mDatabaseRef; // 파이어베이스 실시간 데이터베이스 참조
    private EditText mEtID, mEtPwd;
    private Button mBtnRegister, mBtnLogin;
    private SwitchCompat switchCompat; // 토글 버튼 추가

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 파이어베이스 Auth 및 데이터베이스 참조 초기화
        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("UserAccount");

        mEtID = findViewById(R.id.editTextText3);
        mEtPwd = findViewById(R.id.editTextText4);
        mBtnRegister = findViewById(R.id.JoinButton);
        mBtnLogin = findViewById(R.id.LoginButton);
        switchCompat = findViewById(R.id.switchButton); // 토글 버튼 초기화

        // 로그인 버튼 클릭 이벤트
        mBtnLogin.setOnClickListener(view -> {
            String strID = mEtID.getText().toString();
            String strPwd = mEtPwd.getText().toString();

            mFirebaseAuth.signInWithEmailAndPassword(strID, strPwd).addOnCompleteListener(MainActivity.this, task -> {
                if (task.isSuccessful()) {
                    // 로그인 성공
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String userId = user.getUid();
                        if (switchCompat.isChecked()) {
                            // 관리자 로그인 시도
                            mDatabaseRef.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    String role = dataSnapshot.child("role").getValue(String.class);
                                    Log.d(TAG, "사용자 역할: " + role); // 사용자 역할 로그
                                    if ("Admin".equals(role)) {
                                        // 관리자 로그인 성공
                                        Log.d(TAG, "관리자 로그인 성공");
                                        startActivity(new Intent(MainActivity.this, Manager.class));
                                    } else {
                                        // 관리자 권한 없음 처리
                                        Toast.makeText(MainActivity.this, "관리자 권한이 없습니다.", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    // 데이터베이스 오류 처리
                                    Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            // 일반 사용자 로그인
                            Log.d(TAG, "사용자 로그인 성공");
                            startActivity(new Intent(MainActivity.this, User_Main.class));
                        }
                    }
                } else {
                    // 로그인 실패
                    Toast.makeText(MainActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                }
            });
        });

        // 등록 버튼 클릭 이벤트
        mBtnRegister.setOnClickListener(view -> {
            if (switchCompat.isChecked()) {
                // 관리자 등록
                Intent intent = new Intent(MainActivity.this, RegisterActivity_Admin.class);
                startActivity(intent);
            } else {
                // 사용자 등록
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
