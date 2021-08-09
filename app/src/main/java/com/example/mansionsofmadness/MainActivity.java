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


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton imageButton;
        imageButton = (ImageButton) findViewById(R.id.characterImageButton);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu charPopUpMenu = new PopupMenu(MainActivity.this,imageButton);
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
    }

    public void changePage(View view) {
        Intent intent = new Intent(this,ItemActivity.class);
        startActivity(intent);
    }



    // Declare all character objects

}

