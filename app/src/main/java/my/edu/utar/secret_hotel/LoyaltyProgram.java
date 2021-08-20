package my.edu.utar.secret_hotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

public class LoyaltyProgram extends AppCompatActivity {


    TextView currentProgress, checkoutPromo, promoCode;
    ProgressBar progress;
    Button home;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loyalty_program);

        currentProgress = findViewById(R.id.currentprogress);
        checkoutPromo = findViewById(R.id.checkoutPromo);
        promoCode = findViewById(R.id.promocode);
        progress = findViewById(R.id.progressbar);
        home = findViewById(R.id.homenav);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Payment");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                // create user object and call it as user profile
                int count = Integer.parseInt(snapshot.child("count").getValue().toString());
                progress.setProgress(count);
                int progressCount = progress.getProgress();
                int offer10 = 5 - progressCount;
                int offer15 = 10 -progressCount;

                currentProgress.setText("Current Progress: " + progressCount +" Booking(s)");
                currentProgress.setBackgroundColor(Color.parseColor("#715943"));

                checkoutPromo.setText("Book for " + offer10 +" more time(s) to get a 10 % promo!"
                        + "\nBook for " + offer15 + " more time(s) to get a 15 % promo!" );
                checkoutPromo.setBackgroundColor(Color.parseColor("#715943"));

                if(progress.getProgress() >= 5 && progress.getProgress()< 10)
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
                    checkoutPromo.setBackgroundColor(Color.parseColor("#D4B996"));
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(LoyaltyProgram.this,"Something wrong happened",Toast.LENGTH_LONG).show();
            }
        });


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoyaltyProgram.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}