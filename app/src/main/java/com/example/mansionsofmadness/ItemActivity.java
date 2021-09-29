package com.example.mansionsofmadness;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.recyclerview.widget.RecyclerView.Adapter;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
//import androidx.appcompat.widget.Toolbar;
// import android.widget.Toolbar;



public class ItemActivity extends AppCompatActivity {

    //public ArrayList<itemInfo>;

    //Create a 115 row array to store all the cards
    public static itemInfo[] itemArray = new itemInfo[115];

    //Create an array for all card information
    //public static String[] itemSearchArray = new String[107];

    //Create an array for item cards, and remove damage and horror
    public static String[] itemsOnlyList = new String[66];

    //Create an array of the players selected cards
    public static String[] playerCardNames = new String[20];
    //public static itemInfo[] playerCards = new itemInfo[20];

    public static ArrayList<itemInfo> playerCards = new ArrayList<itemInfo>();

    public static itemInfo playerCard = new itemInfo();

    private static int l = 0;
    private static int n = 0;
    private static int o = 0;
    public int m = 0;

    public int cardCounter = 0;
    public int listCount = 0;

    //replacing any old index used for card tracking. far too confusing.
    public int cardNumber;

    public RecyclerView recyclerView;
    public AutoCompleteTextView searchBar;
    public ImageButton addItemButton;
    public Boolean recyclerInitialized;

    //Parse the card data and load it into memory
    public void plzreadDataFiles(){

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
                itemArray[i].setDuration(token[5]);


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
    }

    //Set up the search bar and button
    private void initSearchBar(){
        /////// Code to run the autocomplete search bar

        //setContentView(R.layout.activity_item);

        //Reference the widget
        //AutoCompleteTextView searchBar = findViewById(R.id.search_items);
        searchBar = findViewById(R.id.search_items);

        //Create an array adapter to supply suggestions to the bar
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,itemsOnlyList);

        //Pass the adapter to the text field
        searchBar.setAdapter(itemAdapter);

        searchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
            }
        });


        //Add items to the list based on click
        addItemButton = findViewById(R.id.button_AddItem);

        //Interact with the recycler view if the user clicks the plus button
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Behaviour to add a new item to the recycler view

                // Find the card info class based on the user selection
                for (int m = 0; m < itemsOnlyList.length; m++){

                    if(searchBar.getText().toString().equals(itemArray[m].getCardName().intern())){
                        //Add the new card to the player hand array??
                        playerCards.add(itemArray[m]);

                        //Add the one card in question to this variable.
                        playerCard = itemArray[m];

                        //Leave the loop if a match is found
                        break;
                    }
                }

                updateListView(playerCards);
            }
        });

    }

/*
    private void initRecyclerView(itemInfo playerCard){
        setContentView(R.layout.activity_item);
        //RecyclerView recyclerView = findViewById(R.id.itemRecycler);
        recyclerView = findViewById(R.id.itemRecycler);


        if(recyclerInitialized == Boolean.FALSE){
            //Init the arrays to avoid a null reference
            for(int w = 0; w < 20; w++){
                playerCards[w] = new itemInfo();
                playerCards[w].cardName = "";
                playerCards[w].cardType = "";
                playerCards[w].frontText = "";
                playerCards[w].rearText = "";

                playerCards[w].damage = 0;
                playerCards[w].duration = "";
            }

        }

        playerCards[cardNumber] = playerCard;

        recyclerViewAdapter recyclerViewAdapter = new recyclerViewAdapter(
                playerCards,
                playerCards[cardNumber].cardName,
                playerCards[cardNumber].cardType,
                (playerCards[cardNumber].frontText + ", " + playerCards[cardNumber].rearText),
                String.valueOf(playerCards[cardNumber].damage),
                playerCards[cardNumber].duration,
                this);


        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //increment the card counter
        cardNumber = cardNumber + 1;

        if(recyclerInitialized == Boolean.FALSE){
            recyclerInitialized = Boolean.TRUE;
        }

        else{
            recyclerViewAdapter.notifyItemInserted(playerCards.length);
        }
    }

 */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        //Set the init flag FALSE to start
        recyclerInitialized = Boolean.FALSE;

        //Read all the card data
        plzreadDataFiles();

        //Initialize the search bar
        initSearchBar();

        //updateListView(playerCard);

    }

    @Override
    protected void onResume() {
        super.onResume();

        updateListView(playerCards);
    }

    private List<itemInfo> getNewEntries() {

        // Let's setup some test data.
        // Normally this would come from some asynchronous fetch into a data source
        // such as a sqlite database, or an HTTP request

        final List<itemInfo> entries = new ArrayList<itemInfo>();
        return entries;
    }

    public void updateListView(ArrayList<itemInfo> playerCards){


        //Set up the list view
        final ListView itemListView = findViewById(R.id.itemListView);

        // Standard list view adapter
        /*
        final listViewAdapter listAdapter = new listViewAdapter(this,R.layout.itemlistlayout);
        itemListView.setAdapter(listAdapter);
         */

        //Array list adapter
        cardArrayAdapter myArrayAdapter = new cardArrayAdapter(this,playerCards);
        itemListView.setAdapter(myArrayAdapter);

        //Refresh the adapter data from the arraylist
        myArrayAdapter.notifyDataSetChanged();

        //playerCard = playerCards.get(cardCounter);

        //listAdapter.add(playerCard);

        listCount = itemListView.getAdapter().getCount();

        //cardCounter = cardCounter + 1;

    }


}