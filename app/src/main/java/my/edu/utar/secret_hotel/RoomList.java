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
    ArrayList<Room> roomArrayList;
    CustomAdapter customAdapter;
    Context context;
    RoomAdapter roomAdapter;
    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        listView=findViewById(R.id.listview);
        root = FirebaseDatabase.getInstance().getReference("Rooms");

        roomArrayList=new ArrayList<>();

            databaseReference = FirebaseDatabase.getInstance().getReference("Rooms");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        Room room = new Room();
                        room.setRoomType(dataSnapshot.child("type").getValue().toString());
                        room.setRoomDesc(dataSnapshot.child("description").getValue().toString());
                        room.setRoomImageURL(dataSnapshot.child("image").getValue().toString());
                        room.setRoomPrice(dataSnapshot.child("price").getValue().toString());
                        //Room room = dataSnapshot.getValue(Room.class);
                        roomArrayList.add(room);
                    }
                    roomAdapter = new RoomAdapter(roomArrayList,RoomList.this);
                    listView.setAdapter(roomAdapter);
                    roomAdapter.notifyDataSetChanged();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Room room = new Room();
                    Intent intent = new Intent(getApplicationContext(), RoomViewforBook.class);
                    //intent.putExtra("room",listView.getItemAtPosition(position).toString());
                    intent.putExtra("type", room.getRoomType());
                    startActivity(intent);

                }
            });
        }

}