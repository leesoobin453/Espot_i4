package com.example.espot_i4;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UserAccount extends AppCompatActivity {



    public UserAccount() {

    }

    public String getIdToken() { return idToken; }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    private String idToken;

    public String getEmailId() { return emailId; }

    public void setEmailId (String emailId) { this.emailId = emailId; }

    private String emailId;

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    private String password;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    private String name;

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    private String phone;

    /*
    public String getCar1() { return car1; }

    public void setCar1(String car1) { this.car1 = car1; }

    private String car1;
*/
    public String getCar2() { return car2; }

    public void setCar2(String car2) { this.car2 = car2; }

    private String car2;

    public String getAdminNum() { return adminNum; }

    public void setAdminNum(String adminNum) { this.adminNum = adminNum; }

    private String adminNum;

    public String getRole() {return role;}
    public void setRole(String tole) { this.role = role; }
    private String role; // 사용자 역할

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}