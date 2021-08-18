package my.edu.utar.secret_hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Button btnMenu, btnProfile, btnBook, btnService;
    ImageButton btnCart;
    FirebaseAuth fAuth;
    DatabaseReference databaseReference;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        userID = fAuth.getUid();
        btnMenu = findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Menu.class);
                startActivity(intent);
            }
        });

        btnProfile = findViewById(R.id.btnProfile);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(userID != null){
                    //User is signed in
                    Intent intent = new Intent(getApplicationContext(),Profile.class);
                    startActivity(intent);
                } else{
                    //No user is signed in
                    Intent intent = new Intent(getApplicationContext(),Login.class);
                    startActivity(intent);
                }
            }
        });

        btnBook = findViewById(R.id.btnBook);
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RoomList.class);
                startActivity(intent);
            }
        });

        btnService = findViewById(R.id.btnService);
        btnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Service.class);
                startActivity(intent);
            }
        });

        btnCart = findViewById(R.id.btnCart);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CartList.class);
                startActivity(intent);
            }
        });
    }
}