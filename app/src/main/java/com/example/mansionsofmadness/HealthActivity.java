package com.example.mansionsofmadness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HealthActivity extends AppCompatActivity {

    public int player_Health = 0;
    public int player_Sanity = 0;

    public TextView healthOutput, sanityOutput;
    public Button healthUpButton, healthDownButton, sanityUpButton, sanityDownButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health2);

        controlButtons();
    }


    public void controlButtons(){
        healthUpButton = (Button) findViewById(R.id.DamageUp);
        healthDownButton = (Button) findViewById(R.id.damageDown);
        sanityUpButton = (Button) findViewById(R.id.sanityUp);
        sanityDownButton = (Button) findViewById(R.id.sanityDown);

        // Buttons to control health values
        healthUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player_Health = player_Health + 1;
                updateTestText();
            }
        });
        healthDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player_Health = player_Health - 1;
                updateTestText();
            }
        });

        // Buttons to control sanity values
        sanityUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player_Sanity = player_Sanity + 1;
                updateTestText();
            }
        });
        sanityDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player_Sanity = player_Sanity - 1;
                updateTestText();
            }
        });


    }


    public void updateTestText(){
        healthOutput = (TextView) findViewById(R.id.healthText);
        sanityOutput = (TextView) findViewById(R.id.sanityText);

        healthOutput.setText(String.valueOf(player_Health));
        sanityOutput.setText(String.valueOf(player_Sanity));
    }


    // 'Intent' to pass the player stats to the main activity
    public void sendPlayerData(View view) {
        Intent dataTransferIntent = new Intent(this,MainActivity.class);
        dataTransferIntent.putExtra("health",player_Health);
        dataTransferIntent.putExtra("sanity",player_Sanity);
    }

}