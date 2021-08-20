package my.edu.utar.secret_hotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RoomList extends AppCompatActivity {

    private DatabaseReference databaseReference, root;
    private ArrayList<Room> roomArrayList;
    private RoomAdapter roomAdapter;
    private ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);
        getSupportActionBar().setTitle("Book a room");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView=findViewById(R.id.listview);
        root = FirebaseDatabase.getInstance().getReference("Rooms");

        roomArrayList=new ArrayList<>();

            //get the Rooms path
            databaseReference = FirebaseDatabase.getInstance().getReference("Rooms");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    //get the database data according to the attribute name and set it to the room
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        Room room = new Room();
                        room.setRoomType(dataSnapshot.child("type").getValue().toString());
                        room.setRoomDesc(dataSnapshot.child("description").getValue().toString());
                        room.setRoomImageURL(dataSnapshot.child("image").getValue().toString());
                        room.setRoomPrice(dataSnapshot.child("price").getValue().toString());
                        //add the single room item to arraylist
                        roomArrayList.add(room);
                    }
                    //set the adapter to the listview
                    roomAdapter = new RoomAdapter(roomArrayList,RoomList.this);
                    listView.setAdapter(roomAdapter);
                    //update the view when data change
                    roomAdapter.notifyDataSetChanged();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        //listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //start different activity according to the on click position of the listview
                if(position == 0)
                {
                    Intent intent = new Intent(view.getContext(),SingleRoom.class);
                    startActivity(intent);
                }
                if(position == 1)
                {
                    Intent intent = new Intent(view.getContext(),Room2.class);
                    startActivity(intent);
                }
                if(position == 2)
                {
                    Intent intent = new Intent(view.getContext(),Room3.class);
                    startActivity(intent);
                }
                if(position == 3)
                {
                    Intent intent = new Intent(view.getContext(),Room4.class);
                    startActivity(intent);
                }
                if(position == 4)
                {
                    Intent intent = new Intent(view.getContext(),Room5.class);
                    startActivity(intent);
                }
                if(position == 5)
                {
                    Intent intent = new Intent(view.getContext(),Room6.class);
                    startActivity(intent);
                }

            }
        });
        }

}