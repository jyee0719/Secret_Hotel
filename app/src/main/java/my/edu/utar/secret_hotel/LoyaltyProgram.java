package my.edu.utar.secret_hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoyaltyProgram extends AppCompatActivity {


    TextView currentProgress, checkoutPromo, promoCode;
    ProgressBar progress;
    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loyalty_program);

        currentProgress = findViewById(R.id.currentprogress);
        checkoutPromo = findViewById(R.id.checkoutPromo);
        promoCode = findViewById(R.id.promocode);
        progress = findViewById(R.id.progressbar);
        home = findViewById(R.id.homenav);

        progress.setProgress(3);
        int progressCount = progress.getProgress();
        int offer10 = 5 - progressCount;
        int offer15 = 10 -progressCount;


        currentProgress.setText("Current Progress: " + progressCount +" Booking(s)");
        currentProgress.setBackgroundColor(Color.parseColor("#715943"));

        checkoutPromo.setText("Book for " + offer10 +" more time(s) to get a 10 % promo!"
                + "\nBook for " + offer15 + " more time(s) to get a 15 % promo!" );
        checkoutPromo.setBackgroundColor(Color.parseColor("#715943"));

        if(progress.getProgress() == 5)
        {
            promoCode.setText("PROMO CODE: 10OFF");
            promoCode.setBackgroundColor(Color.parseColor("#A07855"));
            checkoutPromo.setText("Book for " + offer15 + " more time(s) to get a 15 % promo!");
        }
        else if(progress.getProgress() == 10)
        {
            promoCode.setText("PROMO CODE: 15OFF");
            promoCode.setBackgroundColor(Color.parseColor("#A07855"));
            checkoutPromo.setText("");
            checkoutPromo.setBackgroundColor(Color.parseColor("#F5F5EF"));
        }

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoyaltyProgram.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}