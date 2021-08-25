package com.example.mansionsofmadness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.ViewHolder>{

    //debugging logger
    private static final String TAG = "Cannot invoke method length() on null object";

    //private ArrayList<String> cardArray = new ArrayList<>();
    private itemInfo[] playerCards = new itemInfo[20];

    private Context context;



    //Strings to print, dunno how to pass these yet
    String name = "name";
    String type = "type";
    String description = "description";
    String damage = "damage";
    String duration = "duration";

    public recyclerViewAdapter(itemInfo[] playerCards, String name, String type, String description, String damage, String duration,Context Context) {
        this.playerCards = playerCards;
        this.name = name;
        this.type = type;
        this.description = description;
        this.damage = damage;
        this.duration = duration;
        this.context = Context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemName.setText(name);
        holder.itemType.setText(type);
        holder.itemDescription.setText(description);
        holder.itemDamage.setText(damage);
        holder.itemDuration.setText(duration);

    }

    @Override
    public int getItemCount() {
        //Update this line to the length of the array or it will be unpleasant
        return 20;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView itemName;
        TextView itemType;
        TextView itemDescription;
        TextView itemDamage;
        TextView itemDuration;

        RelativeLayout listLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.itemName);
            itemType = itemView.findViewById(R.id.itemType);
            itemDescription = itemView.findViewById(R.id.itemDescription);
            itemDamage = itemView.findViewById(R.id.itemDamage);
            itemDuration = itemView.findViewById(R.id.itemDuration);

            listLayout = itemView.findViewById(R.id.listLayout);
        }
    }

}
