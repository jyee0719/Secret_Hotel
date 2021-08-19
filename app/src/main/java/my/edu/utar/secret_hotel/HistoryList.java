package my.edu.utar.secret_hotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HistoryList extends AppCompatActivity {

    private ListView history_listView;
    private DatabaseReference databaseReference, root;
    private CartItemList cartArrayList;
    private Context context;
    private HistoryAdapter history_Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);

        history_listView = findViewById(R.id.history_listView);
        root = FirebaseDatabase.getInstance().getReference("History");
        cartArrayList = new CartItemList();

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if (!uid.isEmpty()) {
            databaseReference = FirebaseDatabase.getInstance().getReference("Users");
            databaseReference.child(uid).child("Cart").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Cart history = new Cart();
                        history.setName(dataSnapshot.child("roomName").getValue().toString());
                        history.setQuantity(dataSnapshot.child("quantity").getValue().toString());
                        history.setImage(dataSnapshot.child("image").getValue().toString());
                        history.setPrice(dataSnapshot.child("unitprice").getValue().toString());
                        history.setCheckin(dataSnapshot.child("checkindate").getValue().toString());
                        history.setCheckout(dataSnapshot.child("checkoutdate").getValue().toString());
                        history.setDuration(dataSnapshot.child("duration").getValue().toString());
                        cartArrayList.addItem(history);
                    }

                    history_Adapter = new HistoryAdapter(cartArrayList.getCartArrayList(), HistoryList.this);
                    history_listView.setAdapter(history_Adapter);
                    history_Adapter.notifyDataSetChanged();

                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });

            Log.i("Database: ", "Checkout is added successful Successful");
        }
    }
}