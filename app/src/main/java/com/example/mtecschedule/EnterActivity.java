package com.example.mtecschedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EnterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
    }

    public void openSchedule(View view){
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }


    public void openAdmin(View view){
        Intent intent = new Intent(this, AdminActivity.class);
        this.startActivity(intent);
    }
}