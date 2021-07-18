package com.example.TVK;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.TVK.View.MainCustomerActivity;
import com.example.TVK.View.RegisterActivity;

public class MainActivity extends AppCompatActivity {

    EditText etdangnhap, etmatkhau;
    Button btndangnhap;
    TextView tvdangki;
    String username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        anhxa();
        username = "admin";
        password = "1";
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainCustomerActivity.this, username, Toast.LENGTH_LONG).show(); //Vị trí, nội dung, thời gian
                if (etdangnhap.getText().length() == 0 || etmatkhau.getText().length() == 0)
                {
                    Toast.makeText(MainActivity.this, "Bạn chưa nhập đủ thông tin", Toast.LENGTH_LONG).show();
                }
                else if (etdangnhap.getText().toString().equals(username) && etmatkhau.getText().toString().equals(password))
                {
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, MainCustomerActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(MainActivity.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_LONG).show();
            }
        });
        tvdangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    private void anhxa()
    {
        etdangnhap = findViewById(R.id.etdangnhap);
        etmatkhau = findViewById(R.id.etmatkhau);
        btndangnhap = findViewById(R.id.btndangnhap);
        tvdangki = findViewById(R.id.tvdangki);
    }
}