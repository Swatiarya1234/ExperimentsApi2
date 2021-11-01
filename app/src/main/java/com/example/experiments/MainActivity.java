package com.example.experiments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.experiments.ItemsCard.ItemsCardname;

public class MainActivity extends AppCompatActivity {

    private CardView cardview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardview = findViewById(R.id.SignIn);
        cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inetnt = new Intent(MainActivity.this, ItemsCardname.class);
                startActivity(inetnt);
            }
        });
    }

}