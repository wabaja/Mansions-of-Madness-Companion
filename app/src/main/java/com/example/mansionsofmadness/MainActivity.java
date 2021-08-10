package com.example.mansionsofmadness;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enables a drop down list from the character portrait
        ImageButton imageButton;
        imageButton = (ImageButton) findViewById(R.id.characterImageButton);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu charPopUpMenu = new PopupMenu(MainActivity.this, imageButton);
                charPopUpMenu.getMenuInflater().inflate(R.menu.character_menu, charPopUpMenu.getMenu());
                charPopUpMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        return false;
                    }
                });
                charPopUpMenu.show();
            }
        });

        // Create the array of character info
        characterInfo[] characterArray = new characterInfo[8];
        //Initialize the class objects in the array
        characterArray[0] = new characterInfo();
        characterArray[1] = new characterInfo();
        characterArray[2] = new characterInfo();
        characterArray[3] = new characterInfo();
        characterArray[4] = new characterInfo();
        characterArray[5] = new characterInfo();
        characterArray[6] = new characterInfo();
        characterArray[7] = new characterInfo();

        //Map the selected array element to the transit variable
        characterInfo selectedCharacter = new characterInfo();
        selectedCharacter = characterArray[0];

        // Populate character info
        // Agatha Crane
        characterArray[0].name = "Agatha Crane";
        characterArray[0].bio = "The Parapsychologist";
        characterArray[0].ability = "After you resolve a horror check, gain 1 Clue";
        characterArray[0].image = "";
        characterArray[0].stat_Health = 5;
        characterArray[0].stat_Sanity = 9;
        characterArray[0].stat_Strength = 2;
        characterArray[0].stat_Agility = 3;
        characterArray[0].stat_Lore = 5;
        characterArray[0].stat_Will = 4;
        characterArray[0].stat_Influence = 3;
        characterArray[0].stat_Observation = 4;

        //AWilliam Yorick
        characterArray[1].name = "William Yorick";
        characterArray[1].bio = "The Parapsychologist";
        characterArray[1].ability = "Whenever a monster is defeated, gain one clue.";
        characterArray[1].image = "";
        characterArray[1].stat_Health = 7;
        characterArray[1].stat_Sanity = 7;
        characterArray[1].stat_Strength = 4;
        characterArray[1].stat_Agility = 3;
        characterArray[1].stat_Lore = 3;
        characterArray[1].stat_Will = 4;
        characterArray[1].stat_Influence = 4;
        characterArray[1].stat_Observation = 3;

        // Create links to the output boxes
        TextView text_name = (TextView) findViewById(R.id.heading_Name);
        TextView text_bio = (TextView) findViewById(R.id.heading_Bio);


        TextView text_strength = (TextView) findViewById(R.id.output_strength);
        TextView text_agility = (TextView) findViewById(R.id.output_agility);
        TextView text_will = (TextView) findViewById(R.id.output_will);
        TextView text_lore = (TextView) findViewById(R.id.output_lore);
        TextView text_observation = (TextView) findViewById(R.id.output_observation);
        TextView text_influence = (TextView) findViewById(R.id.output_influence);

        //Write the output values to the boxes
        text_name.setText(selectedCharacter.name);
        text_bio.setText(selectedCharacter.bio);

        text_strength.setText(""+selectedCharacter.stat_Strength);
        text_agility.setText(""+selectedCharacter.stat_Agility);
        text_will.setText(""+selectedCharacter.stat_Will);
        text_lore.setText(""+selectedCharacter.stat_Lore);
        text_observation.setText(""+selectedCharacter.stat_Observation);
        text_influence.setText(""+selectedCharacter.stat_Influence);
    }

    public void changePage(View view) {
        Intent intent = new Intent(this,ItemActivity.class);
        startActivity(intent);
    }

}

