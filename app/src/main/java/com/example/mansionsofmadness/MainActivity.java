package com.example.mansionsofmadness;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;


public class MainActivity extends AppCompatActivity {

    public static characterInfo[] characterArray = new characterInfo[8];

    public static characterInfo selectedCharacter = new characterInfo();

    public void readDataFiles(){
        //Initialize the class objects in the array
        characterArray[0] = new characterInfo();
        characterArray[1] = new characterInfo();
        characterArray[2] = new characterInfo();
        characterArray[3] = new characterInfo();
        characterArray[4] = new characterInfo();
        characterArray[5] = new characterInfo();
        characterArray[6] = new characterInfo();
        characterArray[7] = new characterInfo();


        //This line opens the file and returns an Input Stream data type
        InputStream charIs = getResources().openRawResource(R.raw.character_csv);

        //Read the file line by line
        BufferedReader reader = new BufferedReader(new InputStreamReader(charIs, Charset.forName("UTF-8")));

        //Initialize a var to track the line of the file being read in
        String line ="";

        // error proofing if the file is weird
        try {
            int i = 0;
            while ((line = reader.readLine()) != null){
                //split data by commas into an array of strings
                String[] token = line.split(",");

                //Read the data

                characterArray[i].setName(token[0]);
                characterArray[i].setAbility(token[1]);
                characterArray[i].setBio(token[2]);
                characterArray[i].setStat_Health(Integer.parseInt(token[3]));
                characterArray[i].setStat_Sanity(Integer.parseInt(token[4]));
                characterArray[i].setStat_Strength(Integer.parseInt(token[5]));
                characterArray[i].setStat_Agility(Integer.parseInt(token[6]));
                characterArray[i].setStat_Observation(Integer.parseInt(token[7]));
                characterArray[i].setStat_Lore(Integer.parseInt(token[8]));
                characterArray[i].setStat_Influence(Integer.parseInt(token[9]));
                characterArray[i].setStat_Will(Integer.parseInt(token[10]));

                Log.d("My Activity", "Just added: "+characterArray[i]);
            }

        } catch (IOException e) {
            Log.wtf("MyActivity","Error reading data file on line"+line,e);
            e.printStackTrace();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ////////////////////////////////////////////////
        //Initialize the class objects in the array
        characterArray[0] = new characterInfo();
        characterArray[1] = new characterInfo();
        characterArray[2] = new characterInfo();
        characterArray[3] = new characterInfo();
        characterArray[4] = new characterInfo();
        characterArray[5] = new characterInfo();
        characterArray[6] = new characterInfo();
        characterArray[7] = new characterInfo();


        //This line opens the file and returns an Input Stream data type
        InputStream charIs = getResources().openRawResource(R.raw.character_csv);

        //Read the file line by line
        BufferedReader reader = new BufferedReader(new InputStreamReader(charIs, Charset.forName("UTF-8")));

        //Initialize a var to track the line of the file being read in
        String line ="";

        // error proofing if the file is weird
        try {
            int i = 0;
            while ((line = reader.readLine()) != null){
                //split data by commas into an array of strings
                String[] token = line.split(",");

                //Read the data

                characterArray[i].setName(token[0]);
                characterArray[i].setAbility(token[1]);
                characterArray[i].setBio(token[2]);
                characterArray[i].setStat_Health(Integer.parseInt(token[3]));
                characterArray[i].setStat_Sanity(Integer.parseInt(token[4]));
                characterArray[i].setStat_Strength(Integer.parseInt(token[5]));
                characterArray[i].setStat_Agility(Integer.parseInt(token[6]));
                characterArray[i].setStat_Observation(Integer.parseInt(token[7]));
                characterArray[i].setStat_Lore(Integer.parseInt(token[8]));
                characterArray[i].setStat_Influence(Integer.parseInt(token[9]));
                characterArray[i].setStat_Will(Integer.parseInt(token[10]));

                Log.d("My Activity", "Just added: "+characterArray[i]);

                i = i + 1;
            }

        } catch (IOException e) {
            Log.wtf("MyActivity","Error reading data file on line"+line,e);
            e.printStackTrace();
        }


        /////////////////////////////////////////////
        setContentView(R.layout.activity_main);


        //Initialize the character selector
        //selectedCharacter = characterArray[0];
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
                        switch (menuItem.getTitle().toString()){
                            case "Agatha Crane":
                                selectedCharacter = characterArray[0];
                                break;

                            case "Carson Sinclair":
                                selectedCharacter = characterArray[1];
                                break;

                            case "Father Matteo":
                                selectedCharacter = characterArray[2];
                                break;

                            case "Minh Thi Phan":
                                selectedCharacter = characterArray[3];
                                break;

                            case "Preston Fairmont":
                                selectedCharacter = characterArray[4];
                                break;

                            case "Rita Young":
                                selectedCharacter = characterArray[5];
                                break;

                            case "Wendy Adams":
                                selectedCharacter = characterArray[6];
                                break;

                            case "William Yorick":
                                selectedCharacter = characterArray[7];
                                break;
                        }

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

                        return false;
                    }
                });
                charPopUpMenu.show();
            }
        });

    }

    public void changePage(View view) {
        Intent intent = new Intent(this,ItemActivity.class);
        startActivity(intent);
    }

}

