package com.example.espot_i4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class RegisterActivity_Admin extends AppCompatActivity {
    private static final String TAG = "RegisterActivity_Admin";

    private FirebaseAuth mFirebaseAuth; // 파이어베이스 연동
    private DatabaseReference mDatabaseRef; // 실시간 데이터베이스
    private EditText mEtID, mEtPwd, mEtName, mEtPhone, mEtAdminNum;
    private Button mBtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_admin);
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
        mEtAdminNum = findViewById(R.id.et_adminNum);
        mBtnRegister = findViewById(R.id.btn_registerAdmin);

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strID = mEtID.getText().toString();
                String strPwd = mEtPwd.getText().toString();
                String strName = mEtName.getText().toString();
                String strPhone = mEtPhone.getText().toString();
                String strAdminNum = mEtAdminNum.getText().toString();

                if (strAdminNum.equals("1234")) {
                    mFirebaseAuth.createUserWithEmailAndPassword(strID, strPwd).addOnCompleteListener(RegisterActivity_Admin.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
                                UserAccount account = new UserAccount();
                                account.setIdToken(firebaseUser.getUid());
                                account.setEmailId(firebaseUser.getEmail());
                                account.setPassword(strPwd);
                                account.setRole("Admin");

                                mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account)
                                        .addOnSuccessListener(aVoid -> {
                                            Toast.makeText(RegisterActivity_Admin.this, "회원가입에 성공했습니다", Toast.LENGTH_SHORT).show();
                                            Log.d(TAG, "User information saved to database successfully");
                                        })
                                        .addOnFailureListener(e -> {
                                            Toast.makeText(RegisterActivity_Admin.this, "회원가입에 실패했습니다", Toast.LENGTH_SHORT).show();
                                            Log.w(TAG, "Failed to save user information to database", e);
                                        });
                            } else {
                                Toast.makeText(RegisterActivity_Admin.this, "회원가입에 실패했습니다", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(RegisterActivity_Admin.this, "관리자 번호가 올바르지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public class UserAccount {
        private String idToken;
        private String emailId;
        private String password;
        private String role;

        public UserAccount() {
            // Default constructor required for calls to DataSnapshot.getValue(UserAccount.class)
        }

        public String getIdToken() { return idToken; }
        public void setIdToken(String idToken) { this.idToken = idToken; }
        public String getEmailId() { return emailId; }
        public void setEmailId(String emailId) { this.emailId = emailId; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
    }
}
