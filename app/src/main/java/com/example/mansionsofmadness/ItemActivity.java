package com.example.mansionsofmadness;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.recyclerview.widget.RecyclerView.Adapter;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
//import androidx.appcompat.widget.Toolbar;
// import android.widget.Toolbar;


public class ItemActivity extends AppCompatActivity {

    //Create a 115 row array to store all the cards
    public static itemInfo[] itemArray = new itemInfo[115];

    //Create an array for all card information
    private static String[] itemSearchArray = new String[107];

    //Create an array for item cards, and remove damage and horror
    private static String[] itemsOnlyList = new String[66];

    //Create an array of the players selected cards
    public static String[] playerCardNames = new String[20];
    public static itemInfo[] playerCards = new itemInfo[20];
    public static itemInfo playerCard = new itemInfo();

    private static int l = 0;
    private static int n = 0;
    private static int o = 0;

    //Parse the card data and load it into memory
    public void plzreadDataFiles(){

        //initRecyclerView();


        //This line opens the file and returns an Input Stream data type
        InputStream itemIs = getResources().openRawResource(R.raw.card_data);

        //Read the file line by line
        BufferedReader reader = new BufferedReader(new InputStreamReader(itemIs, Charset.forName("UTF-8")));

        //Initialize a var to track the line of the file being read in
        String line ="";

        // error proofing if the file is weird
        try {
            int i = 0;
            int k = 0;
            while ((line = reader.readLine()) != null){
                //split data by commas into an array of strings
                String[] token = line.split(",");

                itemArray[i] = new itemInfo();

                //Read the data
                itemArray[i].setCardType(token[0]);
                itemArray[i].setCardName(token[1]);
                itemArray[i].setItemType(token[2]);
                itemArray[i].setFrontText(token[3]);
                itemArray[i].setRearText(token[4]);


                // Create a list of ITEM names for the autocomplete
                if ((itemArray[i].getCardType().intern()) == ("Common Item")){
                    //itemSearchArray[i] = token[1];
                    itemsOnlyList[k] = token[1];
                    k = k + 1;
                }

                else if ((itemArray[i].getCardType().intern()) == ("Unique Item")){
                    //itemSearchArray[i] = token[1];
                    itemsOnlyList[k] = token[1];
                    k = k + 1;
                }

                else if ((itemArray[i].getCardType().intern()) == ("Spell")){
                    //itemSearchArray[i] = token[1];
                    itemsOnlyList[k] = token[1];
                    k = k + 1;
                }

                //Check that the damage column has a number in it, and write it in
                if (token[5].intern() != "-"){
                    itemArray[i].setDamage(Integer.parseInt(token[5]));
                }
                else{
                    //Set it to zero if the dash is detected
                    itemArray[i].setDamage(0);
                }
                itemArray[i].setDuration(token[6]);

                i = i + 1;

                Log.d("My Activity", "Just added: "+itemArray[i]);
            }

        } catch (IOException e) {
            Log.wtf("MyActivity","Error reading data file on line"+line,e);
            e.printStackTrace();
        }

        /////// Code to run the autocomplete search bar
        //Reference the widget
        AutoCompleteTextView editText = findViewById(R.id.search_items);

        //Create an array adapter to supply suggestions to the bar
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,itemsOnlyList);

        //Pass the adapter to the text field
        editText.setAdapter(itemAdapter);

        //Add items to the list based on click
        ImageButton buttonAddItem = findViewById(R.id.button_AddItem);

        buttonAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerCardNames[l] = String.valueOf(editText.getText());
                l = l + 1;

                int m = 0;

                for (m = 0; m>itemsOnlyList.length; m++){
                    if(playerCardNames[l] == itemArray[m].getCardName().intern()){
                        playerCards[n] = itemArray[m];
                        playerCard = itemArray[m];
                        n = n + 1;

                        initRecyclerView(playerCard);
                    }
                }
            }
        });

    }


    private void initRecyclerView(itemInfo playerCard){

        playerCards[o] = playerCard;
        o = o + 1;


        RecyclerView recyclerView = findViewById(R.id.itemRecycler);
        recyclerViewAdapter recyclerViewAdapter = new recyclerViewAdapter(
                playerCards,
                playerCards[o].cardName,
                playerCards[o].cardType,
                (playerCards[o].frontText + ", " + playerCards[o].rearText),
                String.valueOf(playerCards[o].damage),
                playerCards[o].duration,
                this);



        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //recyclerViewAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        plzreadDataFiles();



    }
}