package my.edu.utar.secret_hotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CartList extends AppCompatActivity {
    private DatabaseReference databaseReference, root;
    ArrayList<Cart> cartArrayList;
    Context context;
    CartAdapter cartAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        listView=findViewById(R.id.listview);
        root = FirebaseDatabase.getInstance().getReference("Cart");

        cartArrayList=new ArrayList<>();

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if(!uid.isEmpty()) {
            databaseReference = FirebaseDatabase.getInstance().getReference("Cart").child(uid);
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Cart cart = new Cart();
                        cart.setName(dataSnapshot.child("roomName").getValue().toString());
                        cart.setQuantity(dataSnapshot.child("quantity").getValue().toString());
                        cart.setImage(dataSnapshot.child("image").getValue().toString());
                        cart.setPrice(dataSnapshot.child("unitprice").getValue().toString());
                        cart.setCheckin(dataSnapshot.child("checkindate").getValue().toString());
                        cart.setCheckout(dataSnapshot.child("checkoutdate").getValue().toString());
                        //Room room = dataSnapshot.getValue(Room.class);
                        cartArrayList.add(cart);
                    }
                    cartAdapter = new CartAdapter(cartArrayList, CartList.this);
                    listView.setAdapter(cartAdapter);
                    cartAdapter.notifyDataSetChanged();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            Log.i("Database: ", "Add is Successful");
        }
    }
}