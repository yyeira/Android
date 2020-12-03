package com.gcu.simple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String INFOKEY = "com.gcu.simple.infokey";
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
    }

    public void openMessage(View view){
        Intent intent = new Intent(this, InfoActivity.class);
        String msg = textView.getText().toString();
        intent.putExtra(INFOKEY, msg);
        startActivity(intent);
    }

    public void listBtn(View view){
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
}