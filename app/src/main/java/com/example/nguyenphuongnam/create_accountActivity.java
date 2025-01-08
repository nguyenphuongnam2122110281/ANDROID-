package com.example.nguyenphuongnam;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class create_accountActivity extends AppCompatActivity {

    private EditText emailField, passwordField, confirmPasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // Liên kết các thành phần trong giao diện XML
        emailField = findViewById(R.id.editTextTextEmailAddress);
        passwordField = findViewById(R.id.editTextTextPassword2);
        confirmPasswordField = findViewById(R.id.editTextTextPassword3);
        Button signUpButton = findViewById(R.id.button4);


        // Nút back quay lại
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button btnBackRegister = findViewById(R.id.btnBackRegister);
        btnBackRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(it);
            }
        });
    }
}
