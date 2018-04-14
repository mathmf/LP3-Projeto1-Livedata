package com.example.mathe_000.lp3projetolive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void menuBtn(View clickedButton){
        if(clickedButton==findViewById(R.id.ButtonCli)){
            Intent activityIntent =
                    new Intent(this, ClienteMain.class);
            startActivity(activityIntent);
        }
        else if(clickedButton==findViewById(R.id.ButtonPro)){
            Intent activityIntent =
                    new Intent(this, ProdutoMain.class);
            startActivity(activityIntent);
        }



    }
}
