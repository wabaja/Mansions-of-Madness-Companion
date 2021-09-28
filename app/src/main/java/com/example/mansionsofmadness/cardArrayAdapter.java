package com.example.mansionsofmadness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mansionsofmadness.R;
import com.example.mansionsofmadness.itemInfo;

import java.util.ArrayList;


//Create an extension to the standard array adapter class
public class cardArrayAdapter extends ArrayAdapter<itemInfo> {

    // I think this acts as a constructor for the Array Adapter class
    public cardArrayAdapter(Context context, ArrayList<itemInfo> cards){
        super(context,0, cards);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //Get the data item for this position
        itemInfo card = getItem(position);

        //Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null){

            //Look CAREFULLY at the layout name, and for god's sake clean up the unused stuff
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_listitem,parent,false);
        }

        //Look up the relevant widgets for population
        TextView itemNameOutput = (TextView) convertView.findViewById(R.id.itemName);
        TextView itemTypeOutput = (TextView) convertView.findViewById(R.id.itemType);
        TextView itemDescriptionOutput = (TextView) convertView.findViewById(R.id.itemDescription);
        TextView itemDurationOutput = (TextView) convertView.findViewById(R.id.itemDuration);
        TextView itemDamageOutput = (TextView) convertView.findViewById(R.id.itemDamage);


        //Protect against null references
        if ((card.getCardName() != null) && (itemNameOutput != null)){
            //Write the data to the output widgets
            itemNameOutput.setText(card.getCardName());
            itemTypeOutput.setText(card.getCardType());

            // Prepare strings
            String combinedDescription;
            combinedDescription = card.getFrontText() + card.getRearText();
            itemDescriptionOutput.setText(combinedDescription);


            //Prep the damage output string
            String damageString;
            if(card.getDamage() == 0){
                damageString = "Damage: " + "N/A";
            }

            else{
                damageString = "Damage: " + Integer.toString(card.getDamage());
            }
            itemDamageOutput.setText(damageString);

            //Prep the duration output text
            String durationString;
            if(card.getDuration().intern() == ""){
                durationString = "Duration: " + "N/A";
            }

            else{
                durationString = "Duration: " + (card.getDuration());
            }

            itemDurationOutput.setText(durationString);
        }

        //Return the completed view to render on screen
        return convertView;
    }
}
