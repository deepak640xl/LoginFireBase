package com.example.loginfirebase;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView textView = findViewById(R.id.text);
        Intent intent = getIntent();
        String str = intent.getStringExtra("T");
        textView.setText(str);
    }
}