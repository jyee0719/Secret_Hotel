package my.edu.utar.secret_hotel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {
    Button btnMain, btnDrink, btnDessert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        //enable Back button to back to Main Menu
        getSupportActionBar().setTitle("Restaurant Menu");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnMain = findViewById(R.id.btnMain);
        btnDrink = findViewById(R.id.btnDrink);
        btnDessert = findViewById(R.id.btnDessert);

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
