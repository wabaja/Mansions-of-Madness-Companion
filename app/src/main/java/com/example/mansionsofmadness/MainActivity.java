package com.example.mansionsofmadness;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Random;
import java.lang.Math;


public class MainActivity extends AppCompatActivity {

    public static characterInfo[] characterArray = new characterInfo[8];
    public static characterInfo selectedCharacter = new characterInfo();
    public static String[] diceResults = new String[8];

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private RadioGroup radioGroup;



    private RadioButton radioButton;
    private RadioButton radioStrength, radioAgility, radioLore, radioObservation, radioInfluence, radioWill;
    private Button buttonRollDice, buttonCancel;
   // private FloatingActionButton diceRollButton;

    private ImageButton diceRollButton;
    private ImageButton imageButton;
    private ImageButton increaseDice, decreaseDice;

    private TextView passCount, clueCount, failCount;
    private TextView diceToRollOutput;

    public int[] diceResult;
    public int skillLevel;
    public int diceDelta;


    // Read the character info from a csv file
    public characterInfo[] readDataFiles(){
        setContentView(R.layout.activity_main);

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

                i = i +1;
            }

        } catch (IOException e) {
            Log.wtf("MyActivity","Error reading data file on line"+line,e);
            e.printStackTrace();
        }

        return characterArray;
    }

    // Update the character text info
    public void updatedCharacterInfo(characterInfo selectedCharacter){
        // Create links to the output boxes
        TextView text_name = (TextView) findViewById(R.id.heading_Name);
        TextView text_bio = (TextView) findViewById(R.id.heading_Bio);

        TextView text_strength = (TextView) findViewById(R.id.output_strength);
        TextView text_agility = (TextView) findViewById(R.id.output_agility);
        TextView text_will = (TextView) findViewById(R.id.output_will);
        TextView text_lore = (TextView) findViewById(R.id.output_lore);
        TextView text_observation = (TextView) findViewById(R.id.output_observation);
        TextView text_influence = (TextView) findViewById(R.id.output_influence);

        //Link to progress bars
        ProgressBar healthProgress = (ProgressBar) findViewById(R.id.healthProgress);
        ProgressBar sanityProgress = (ProgressBar) findViewById(R.id.sanityProgress);

        //Write the output values to the boxes
        text_name.setText(MainActivity.selectedCharacter.name);
        text_bio.setText(MainActivity.selectedCharacter.bio);

        text_strength.setText(""+ MainActivity.selectedCharacter.stat_Strength);
        text_agility.setText(""+ MainActivity.selectedCharacter.stat_Agility);
        text_will.setText(""+ MainActivity.selectedCharacter.stat_Will);
        text_lore.setText(""+ MainActivity.selectedCharacter.stat_Lore);
        text_observation.setText(""+ MainActivity.selectedCharacter.stat_Observation);
        text_influence.setText(""+ MainActivity.selectedCharacter.stat_Influence);

        //Update the progress bar limits
        healthProgress.setMax(selectedCharacter.stat_Health);
        sanityProgress.setMax(selectedCharacter.stat_Sanity);

        ////////////////////////////////////////// REMOVE THESE LINES
        healthProgress.setProgress(3);
        sanityProgress.setProgress(2);

        //Update the progress bar limits
        healthProgress.setMin(0);
        sanityProgress.setMin(0);
    }

    //Dice roller function
    public int[] diceRoller(int skillLevel){
        //Clear the output variable
        diceResult[0] = 0;
        diceResult[1] = 0;
        diceResult[2] = 0;
        diceResult[3] = 0;


        //Loop thru the number of dice up to the skill level
        int currentDie;
        //Create a var to track the dice outcomes
        int diceRoll;



        for(currentDie = 0; currentDie < skillLevel; currentDie++){
            //Random rand = new Random();
            //diceRoll = rand.nextInt((8)+1);

            diceRoll = (int)(Math.random()*8+1);


            //Track a loss
            if(diceRoll <= 3){
                diceResults[currentDie] = "Blank";
                diceResult[0] = diceResult[0] + 1;
            }
            //Track a success
            else if(diceRoll > 5){
                diceResults[currentDie] = "Success";
                diceResult[1] = diceResult[1] + 1;
            }

            //Track a clue
            else{
                diceResults[currentDie] = "Clue";
                diceResult[2] = diceResult[2] + 1;
            }
        }

        //Right some blanks to remaining dice
        for(currentDie = skillLevel + 1; currentDie == 8; currentDie++){
            diceResults[currentDie] = "Not Rolled";
            diceResult[3] = 8 - (diceResult[0] + diceResult[1] + diceResult[2]);
        }

        return diceResult;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Read character data
        readDataFiles();

        //Start the player off as Agatha for simplicity
        selectedCharacter = characterArray[0];
        updatedCharacterInfo(selectedCharacter);

        //init the dice array\
        diceResult = new int[4];

        /////////////////////////////////////////////


        //Activate the dice roller pop up button

        diceRollButton = (ImageButton) findViewById(R.id.buttonDiceDialog);
        diceRollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diceRollerDialog();
            }
        });


        //Initialize the character selector
        // Enables a drop down list from the character portrait
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

                        updatedCharacterInfo(selectedCharacter);

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

    public void diceRollerDialog(){
    dialogBuilder = new AlertDialog.Builder(this);
    final View diceDialog = getLayoutInflater().inflate(R.layout.dice_popup,null);

    dialogBuilder.setView(diceDialog);

    //Button inits
    buttonRollDice = (Button) diceDialog.findViewById(R.id.buttonRollDice);
    buttonCancel = (Button) diceDialog.findViewById(R.id.buttonCancel);
    increaseDice = (ImageButton) diceDialog.findViewById(R.id.buttonIncrease);
    decreaseDice = (ImageButton) diceDialog.findViewById(R.id.buttonDecrease);

    dialog = dialogBuilder.create();
    dialogBuilder.setView(diceDialog);
    dialog.show();

    diceToRollOutput = (TextView) diceDialog.findViewById(R.id.text_DiceToRoll);


    //Radio button group
    //radioGroup = findViewById(R.id.radioGroupStats);
    radioGroup = (RadioGroup) diceDialog.findViewById(R.id.radioGroupStats);

    //Update the UI if the user selects a diffenet skill
    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {

            int radioID = radioGroup.getCheckedRadioButtonId();
            radioButton = diceDialog.findViewById(radioID);

            //switch statement to pick the stat value based on choice
            switch (radioID) {
                case 1:
                    skillLevel = selectedCharacter.stat_Strength;
                    break;

                case 2:
                    skillLevel = selectedCharacter.stat_Agility;
                    break;

                case 3:
                    skillLevel = selectedCharacter.stat_Lore;
                    break;

                case 4:
                    skillLevel = selectedCharacter.stat_Observation;
                    break;

                case 5:
                    skillLevel = selectedCharacter.stat_Influence;
                    break;

                case 6:
                    skillLevel = selectedCharacter.stat_Will;
                    break;
            }
            diceToRollOutput.setText(String.valueOf(skillLevel + diceDelta));

        }
    });

    increaseDice.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Increase dice
            diceDelta = diceDelta + 1;
            diceToRollOutput.setText(String.valueOf(skillLevel + diceDelta));
        }
    });

    decreaseDice.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Decrease dice
            diceDelta = diceDelta - 1;
            diceToRollOutput.setText(String.valueOf(skillLevel + diceDelta));
        }
    });


    // Submit the selected stat and retur the output from the dice roller
    buttonRollDice.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Define the roll dice button here
            //radioGroup = (RadioGroup) diceDialog.findViewById(R.id.radioGroupStats);

            int radioID = radioGroup.getCheckedRadioButtonId();
            radioButton = diceDialog.findViewById(radioID);

            //switch statement to pick the stat value based on choice
            switch (radioID) {
                case 1:
                    skillLevel = selectedCharacter.stat_Strength;
                    break;

                case 2:
                    skillLevel = selectedCharacter.stat_Agility;
                    break;

                case 3:
                    skillLevel = selectedCharacter.stat_Lore;
                    break;

                case 4:
                    skillLevel = selectedCharacter.stat_Observation;
                    break;

                case 5:
                    skillLevel = selectedCharacter.stat_Influence;
                    break;

                case 6:
                    skillLevel = selectedCharacter.stat_Will;
                    break;
            }

            int[] outputRoll = new int[4];

            outputRoll = diceRoller(skillLevel);

            passCount = (TextView) diceDialog.findViewById(R.id.textViewPass);
            clueCount = (TextView) diceDialog.findViewById(R.id.textViewClue);
            failCount = (TextView) diceDialog.findViewById(R.id.textViewFail);

            passCount.setText(String.valueOf(outputRoll[0]));
            clueCount.setText(String.valueOf(outputRoll[1]));
            failCount.setText(String.valueOf(outputRoll[2]));

        }
    });

    buttonCancel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Define the cancel button here
            dialog.dismiss();
            diceDelta = 0;
        }
    });

    //return null;
    }
}