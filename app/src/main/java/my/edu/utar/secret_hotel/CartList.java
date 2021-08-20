package my.edu.utar.secret_hotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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
    private CartItemList cartArrayList;
    private CartAdapter cartAdapter;
    private ListView listView;
    private Button btn_checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);
        getSupportActionBar().setTitle("Cart");

        btn_checkout = findViewById(R.id.btn_checkout);
        listView=findViewById(R.id.listview);
        root = FirebaseDatabase.getInstance().getReference("Cart");

        cartArrayList=new CartItemList();

        //get the uid of current user
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        //check if uid is empty
        if(!uid.isEmpty()) {
            //get the Cart under Users and current uid
            databaseReference = FirebaseDatabase.getInstance().getReference("Users");
            databaseReference.child(uid).child("Cart").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    //get the database data according to the attribute name and set it to the cart
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Cart cart = new Cart();
                        cart.setName(dataSnapshot.child("roomName").getValue().toString());
                        cart.setQuantity(dataSnapshot.child("quantity").getValue().toString());
                        cart.setImage(dataSnapshot.child("image").getValue().toString());
                        cart.setPrice(dataSnapshot.child("unitprice").getValue().toString());
                        cart.setCheckin(dataSnapshot.child("checkindate").getValue().toString());
                        cart.setCheckout(dataSnapshot.child("checkoutdate").getValue().toString());
                        //add the single cart item to arraylist
                        cartArrayList.addItem(cart);
                    }
                    //set the adapter to the listview
                    cartAdapter = new CartAdapter(cartArrayList.getCartArrayList(), CartList.this);
                    listView.setAdapter(cartAdapter);
                    //update the view when data change
                    cartAdapter.notifyDataSetChanged();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
           Log.i("Database: ", "Add is Successful");
        }

        //start new activity when click the button
        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartList.this, CheckoutList.class));
            }
        });
    }
}