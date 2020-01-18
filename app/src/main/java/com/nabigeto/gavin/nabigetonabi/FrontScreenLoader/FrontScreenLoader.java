package com.nabigeto.gavin.nabigetonabi.FrontScreenLoader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.nabigeto.gavin.nabigetonabi.MainActivity;
import com.nabigeto.gavin.nabigetonabi.R;

public class FrontScreenLoader extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_screen_loader);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}


