package my.edu.utar.secret_hotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CheckoutList extends AppCompatActivity {

        private TextView currentDate;
        private Calendar calendar;
        private String date,promocode;
        private SimpleDateFormat dateFormat;
        private DatabaseReference databaseReference, root;
        private CartItemList cartArrayList;
        private Context context;
        private checkoutAdapter checkout_Adapter;
        private ListView checkout_listView;
        private TextView total_price;
        private Button proceedPayment,use;
        private EditText promo;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_checkout_list);

            currentDate = findViewById(R.id.tv_currentDate);

            calendar = Calendar.getInstance();

            dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            date = dateFormat.format(calendar.getTime());
            currentDate.setText(date);

            proceedPayment = findViewById(R.id.btn_proceedPayment);
            checkout_listView = findViewById(R.id.checkout_listView);
            proceedPayment = findViewById(R.id.btn_proceedPayment);
            total_price = findViewById(R.id.tv_totalPrice);
            promo = findViewById(R.id.promo);
            use = findViewById(R.id.addcode);
            root = FirebaseDatabase.getInstance().getReference("Checkout");

            cartArrayList = new CartItemList();

            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            if(!uid.isEmpty()) {
                databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                databaseReference.child(uid).child("Cart").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Cart check_out = new Cart();
                            check_out.setName(dataSnapshot.child("roomName").getValue().toString());
                            check_out.setQuantity(dataSnapshot.child("quantity").getValue().toString());
                            check_out.setImage(dataSnapshot.child("image").getValue().toString());
                            check_out.setPrice(dataSnapshot.child("unitprice").getValue().toString());
                            check_out.setCheckin(dataSnapshot.child("checkindate").getValue().toString());
                            check_out.setCheckout(dataSnapshot.child("checkoutdate").getValue().toString());
                            check_out.setDuration(dataSnapshot.child("duration").getValue().toString());
                            cartArrayList.addItem(check_out);
                        }

                        checkout_Adapter = new checkoutAdapter(cartArrayList.getCartArrayList(), CheckoutList.this);
                        checkout_listView.setAdapter(checkout_Adapter);
                        checkout_Adapter.notifyDataSetChanged();

                        double totalPrice = cartArrayList.getTotalPrice();
                        total_price.setText("RM "+totalPrice + "");
                        //If the user inputs promocode and press the "USE" button, the totalPrice will be deducted
                        use.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                double totalPrice = cartArrayList.getTotalPrice();
                                promocode =promo.getText().toString().trim();
                                if(promocode.equals("10OFF"))
                                {
                                    totalPrice = totalPrice*0.9;
                                    total_price.setText("RM "+ totalPrice + "");
                                }
                                else if(promocode.equals("15OFF"))
                                {
                                    totalPrice = totalPrice*0.85;
                                    total_price.setText("RM "+ totalPrice + "");
                                }
                                else {
                                    Toast.makeText(CheckoutList.this,"Invalid code Entered",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                Log.i("Database: ", "Checkout is added successful Successful");

            }

            proceedPayment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String total_price2 = total_price.getText().toString();
                    Intent intent = new Intent(CheckoutList.this, Payment.class);
                    intent.putExtra("payment_amount", total_price2);
                    startActivity(intent);
                }
            });

        }
}