package com.example.espot_i4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth; // 파이어 베이스 연동
    private DatabaseReference mDatabaseRef; // 실시간 데이터 베이스
    private EditText mEtID, mEtPwd, mEtName, mEtPhone, mEtCar1, mEtCar2;
    private Button mBtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();

        mEtID = findViewById(R.id.et_email);
        mEtPwd = findViewById(R.id.et_pwd);
        mEtName = findViewById(R.id.et_name);
        mEtPhone = findViewById(R.id.et_phone);
        mEtCar1 = findViewById(R.id.et_car1);
        mEtCar2 = findViewById(R.id.et_car2);
        mBtnRegister = findViewById(R.id.btn_register);

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 회원가입 처리 시작
                String strID = mEtID.getText().toString();
                String strPwd = mEtPwd.getText().toString();
                String strName = mEtName.getText().toString();
                String strPhone = mEtPhone.getText().toString();
                String strCar1 = mEtCar1.getText().toString();
                String strCar2 = mEtCar2.getText().toString();

                // Firebase Auth 진행
                mFirebaseAuth.createUserWithEmailAndPassword(strID, strPwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
                            UserAccount account = new UserAccount();
                            account.setIdToken(firebaseUser.getUid());
                            account.setEmailId(firebaseUser.getEmail());
                            account.setPassword(strPwd);

                            mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);
                            Toast.makeText(RegisterActivity.this, "회원가입에 성공했습니다", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, "회원가입에 실패했습니다", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }
}