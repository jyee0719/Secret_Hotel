package my.edu.utar.secret_hotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class Room2 extends AppCompatActivity {

    DatabaseReference databaseReference;
    TextView rn, rd, rp;
    ImageView imageView;
    Button book;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room2);

        getSupportActionBar().setTitle("Double Room");

        rn = findViewById(R.id.roomname1);
        rd = findViewById(R.id.roomdesc1);
        rp = findViewById(R.id.roomprice1);
        book = findViewById(R.id.book1);
        imageView = findViewById(R.id.roomimageview1);


        databaseReference = FirebaseDatabase.getInstance().getReference("Rooms");
        databaseReference.child("room2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String type = snapshot.child("type").getValue().toString();
                String desc = snapshot.child("description").getValue().toString();
                String image = snapshot.child("image").getValue().toString();
                String price = snapshot.child("price").getValue().toString();

                rn.setText(type);
                rd.setText(desc);
                rp.setText(price);

                Glide.with(Room2.this)
                        .load(image)
                        .into(imageView);

                book.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String type = rn.getText().toString();
                        String price = rp.getText().toString();
                        Intent intent = new Intent(Room2.this, BookingDate.class);
                        intent.putExtra("type", type);
                        intent.putExtra("price", price);
                        intent.putExtra("image", image);
                        startActivity(intent);

                    }
                });

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}