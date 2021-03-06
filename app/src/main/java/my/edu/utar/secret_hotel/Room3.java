package my.edu.utar.secret_hotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Room3 extends AppCompatActivity {

    DatabaseReference databaseReference;
    TextView rn, rd, rp;
    ImageView imageView;
    Button book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room3);

        getSupportActionBar().setTitle("Triple Room");

        rn=findViewById(R.id.roomname1);
        rd=findViewById(R.id.roomdesc1);
        rp=findViewById(R.id.roomprice1);
        book=findViewById(R.id.book1);
        imageView=findViewById(R.id.roomimageview1);

        //get the Rooms path in firebase and get the child
        databaseReference = FirebaseDatabase.getInstance().getReference("Rooms");
        databaseReference.child("room3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //get and set the firebase data according to attributes name
                String type = snapshot.child("type").getValue().toString();
                String desc = snapshot.child("description").getValue().toString();
                String image = snapshot.child("image").getValue().toString();
                String price = snapshot.child("price").getValue().toString();

                //set text
                rn.setText(type);
                rd.setText(desc);
                rp.setText(price);

                //set room image
                Glide.with(Room3.this)
                        .load(image)
                        .into(imageView);

                //when users click on book button
                book.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String type = rn.getText().toString();
                        String price = rp.getText().toString();
                        Intent intent = new Intent(Room3.this, BookingDate.class);
                        //for data passing to another activity
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

//        book.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String type = rn.getText().toString();
//                String price = rp.getText().toString();
//                Intent intent = new Intent(Room3.this,BookingDate.class);
//                intent.putExtra("type",type);
//                intent.putExtra("price",price);
//                startActivity(intent);
//            }
//        });
    }
}