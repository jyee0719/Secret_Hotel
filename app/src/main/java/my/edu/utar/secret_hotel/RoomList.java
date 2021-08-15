package my.edu.utar.secret_hotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RoomList extends AppCompatActivity {

    RecyclerView recyclerView;
    private DatabaseReference databaseReference, root;
    ArrayList<Room> roomArrayList;
    CustomAdapter customAdapter;
    Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        recyclerView=(RecyclerView) this.findViewById(R.id.recyclerView);
        root = FirebaseDatabase.getInstance().getReference("Rooms");
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
//        recyclerView.setLayoutManager(gridLayoutManager);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        roomArrayList=new ArrayList<>();
        customAdapter=new CustomAdapter(this,roomArrayList);

//        Query query = databaseReference.child("Rooms");
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                clearAll();
//                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
////                    Room room = new Room();
////                    room.setRoomType(dataSnapshot.child("type").getValue().toString());
////                    room.setRoomDesc(dataSnapshot.child("description").getValue().toString());
////                    room.setRoomImageURL(dataSnapshot.child("image").getValue().toString());
////                    room.setRoomPrice(Double.parseDouble(dataSnapshot.child("price").getValue().toString()));
////                    //Room room = dataSnapshot.getValue(Room.class);
////                    roomArrayList.add(room);
//                    String type = dataSnapshot.child("type").getValue().toString();
//                    String description = dataSnapshot.child("description").getValue().toString();
//                    String image = dataSnapshot.child("image").getValue().toString();
//                    double price = Double.parseDouble(dataSnapshot.child("price").getValue().toString());
//                    roomArrayList.add(new Room(type,description,image,price));
//                }
//                customAdapter = new CustomAdapter(getApplicationContext(), roomArrayList);
//                customAdapter.notifyDataSetChanged();
//                recyclerView.setAdapter(customAdapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


            //ArrayList<Room> roomArrayList = new ArrayList<>();
            databaseReference = FirebaseDatabase.getInstance().getReference("Rooms");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    clearAll();
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        //Room room = new Room();
//                        room.setRoomType(dataSnapshot.child("type").getValue().toString());
//                        room.setRoomDesc(dataSnapshot.child("description").getValue().toString());
//                        room.setRoomImageURL(dataSnapshot.child("image").getValue().toString());
//                        room.setRoomPrice(dataSnapshot.child("price").getValue().toString());
                        Room room = dataSnapshot.getValue(Room.class);
                        roomArrayList.add(room);
                    }
                    //customAdapter=new CustomAdapter(RoomList.this,roomArrayList);
                    recyclerView.setAdapter(customAdapter);
                    customAdapter.notifyDataSetChanged();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        private void clearAll(){
        if(roomArrayList!=null){
            roomArrayList.clear();

            if(customAdapter!=null){
                customAdapter.notifyDataSetChanged();
            }
        }
        roomArrayList = new ArrayList<>();
        }

}