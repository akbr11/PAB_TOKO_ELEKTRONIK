package com.example.pab_toko_elektronik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText l_email, l_password;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        l_email = findViewById(R.id.et_email);
        l_password = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = l_email.getText().toString();
                String password = l_password.getText().toString();

                if (email.equals("admin") && password.equals("admin")) {
                    Toast.makeText(getApplicationContext(), "Login berhasil", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else if (email.equals("")) {
                    l_email.setError("Email harus diisi!");
                } else if (password.equals("")) {
                    l_password.setError("Password harus diisi!");
                } else {
                    Toast.makeText(LoginActivity.this, "Username dan Password Salah!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}