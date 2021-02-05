package com.example.loginfirebase;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText username, passwrd;
    private Button register;
    private TextView login;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    private TextView resets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        username = findViewById(R.id.email);
        passwrd = findViewById(R.id.password);
        register = findViewById(R.id.btnregister);
        login = findViewById(R.id.login);
        resets = findViewById(R.id.reset);
        resets.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Reset.class);
            startActivity(intent);
        });
        progressBar = findViewById(R.id.progressbar);
        register.setOnClickListener(view -> Registeration());
        login.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, login.class);
            startActivity(intent);
        });
        progressBar = findViewById(R.id.progressbar);
        register.setOnClickListener(view -> Registeration());
    }
    public void Registeration() {
        progressBar.setVisibility(View.VISIBLE);
        String email, password;
        email = username.getText().toString();
        password = passwrd.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email!!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!!", Toast.LENGTH_LONG).show();
            return;
        }
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
                Intent intent = new Intent(MainActivity.this, login.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Registration failed!!" + " Please try again later", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}