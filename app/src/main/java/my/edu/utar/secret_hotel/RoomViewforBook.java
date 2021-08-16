package my.edu.utar.secret_hotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class RoomViewforBook extends AppCompatActivity {

    TextView rn, rd, rp;
    ImageView imageView;
    Button book;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_viewfor_book);

//        rn=findViewById(R.id.roomname0);
//        rd=findViewById(R.id.roomdesc0);
//        rp=findViewById(R.id.roomprice0);
//        book=findViewById(R.id.book0);
//        imageView=findViewById(R.id.roomimageview0);
//        databaseReference= FirebaseDatabase.getInstance().getReference().child("Rooms");
//
//        Intent intent =this.getIntent();
////        if(intent==null){
////            Log.i(Toast.makeText("nono",short));
////        }
//
//        String type = getIntent().getStringExtra("type");
//        String desc = getIntent().getStringExtra("desc");
//        String price = getIntent().getStringExtra("price");
//
//        rn.setText(type);
//        rd.setText(desc);
//        rp.setText(price);
        //rn.setText(getIntent().getStringExtra("type"));

//        databaseReference.child(room).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    //String type = getIntent().getStringExtra("type");
//                    String type = dataSnapshot.child("type").getValue().toString();
//                    String desc = dataSnapshot.child("description").getValue().toString();
//                    String image = dataSnapshot.child("image").getValue().toString();
//                    String price = dataSnapshot.child("price").getValue().toString();
//
//                    rn.setText(type);
//                    rd.setText(desc);
//                    rp.setText(price);
////                    Glide.with()
////                            .load(image)
////                            .into(imageView);
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }
}