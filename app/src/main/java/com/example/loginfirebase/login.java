package com.example.loginfirebase;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
private EditText email,password;
private Button login;
private FirebaseAuth firebaseAuth;
private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        email =  findViewById(R.id.email);
        password =findViewById(R.id.password);
        login =  findViewById(R.id.login);
        progressBar=  findViewById(R.id.progressBar);
        login.setOnClickListener(view -> Registeration());
    }
    public void Registeration() {
        progressBar.setVisibility(View.VISIBLE);
        String emails, passwords;
        emails = email.getText().toString();
        passwords = password.getText().toString();
        if (TextUtils.isEmpty(emails)) {
            Toast.makeText(getApplicationContext(), "Please enter email!!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(passwords)) {
            Toast.makeText(getApplicationContext(), "Please enter password!!", Toast.LENGTH_LONG).show();
            return;

        }
        firebaseAuth.signInWithEmailAndPassword(emails, passwords).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                String emailmm=email.getText().toString();
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(getApplicationContext(), Profile.class);
                    intent.putExtra("T","Welcome : "+emailmm);
                   // intent.putExtra("Welcome",);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Registration failed!!" + " Please try again later", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }

        });
    }
}