package my.edu.utar.secret_hotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.huawei.hmf.tasks.OnSuccessListener;

import java.util.HashMap;

public class Payment extends AppCompatActivity {

    private TextView tv_payment_Amount;
    private EditText edt_cardHolderName;
    private EditText edt_cardNumber;
    private EditText edt_expiryDate_mon, edt_expiryDate_year;
    private EditText edt_cvv;
    private Button btn_pay;
    private ProgressBar progressBar_payment;
    String payment_amount;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        tv_payment_Amount = findViewById(R.id.tv_paymentAmount);
        edt_cardHolderName = findViewById(R.id.edt_cardHolderName);
        edt_cardNumber = findViewById(R.id.edt_cardNumber);
        edt_expiryDate_mon = findViewById(R.id.edt_cardExpiryDateMonth);
        edt_expiryDate_year = findViewById(R.id.edt_cardExpiryDateYear);
        edt_cvv = findViewById(R.id.edt_cvv);
        btn_pay = findViewById(R.id.btn_pay);
        progressBar_payment = findViewById(R.id.progressBar_payment);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference node = database.getReference("Cart");
        Intent intent = getIntent();
        payment_amount = intent.getStringExtra("payment_amount");
        tv_payment_Amount.setText(payment_amount);

        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cardHolderName = edt_cardHolderName.getText().toString().trim();
                String cardNumber = edt_cardNumber.getText().toString().trim();
                String expiryDate_mon = edt_expiryDate_mon.getText().toString().trim();
                String expiryDate_year = edt_expiryDate_year.getText().toString().trim();
                String cvv = edt_cvv.getText().toString().trim();

                if (cardHolderName.isEmpty()) {
                    edt_cardHolderName.setError("Card holder name is required!");
                    edt_cardHolderName.requestFocus();
                    return;
                }

                if (cardNumber.isEmpty()) {
                    edt_cardNumber.setError("Card number is required!");
                    edt_cardNumber.requestFocus();
                    return;
                }

                if (cardNumber.length() != 12) {
                    edt_cardNumber.setError("Card number should be 12-digits!");
                    edt_cardNumber.requestFocus();
                    return;
                }

                if (expiryDate_mon.isEmpty()) {
                    edt_expiryDate_mon.setError("The month of card expiry date is required!");
                    edt_expiryDate_mon.requestFocus();
                    return;
                }

                if (expiryDate_mon.length() != 2) {
                    edt_expiryDate_mon.setError("The month of card expiry date should be 2-digits!");
                    edt_expiryDate_mon.requestFocus();
                    return;
                }

                if (expiryDate_year.isEmpty()) {
                    edt_expiryDate_year.setError("The year of card expiry date is required!");
                    edt_expiryDate_year.requestFocus();
                    return;
                }

                if (expiryDate_year.length() != 4) {
                    edt_expiryDate_year.setError("The year of card expiry date should be 2-digits!");
                    edt_expiryDate_year.requestFocus();
                    return;
                }

                if (cvv.isEmpty()) {
                    edt_cvv.setError("CVV is required!");
                    edt_cvv.requestFocus();
                    return;
                }

                if (cvv.length() != 3){
                    edt_cvv.setError("CVV should be 3-digits!");
                    edt_cvv.requestFocus();
                }

                progressBar_payment.setVisibility(View.VISIBLE);

                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                if(!uid.isEmpty()) {
                    databaseReference = FirebaseDatabase.getInstance().getReference("Payment");
                    String cartID = databaseReference.push().getKey();
                    HashMap<String, String> parameters = new HashMap<>();
                    parameters.put("card_holder_name", cardHolderName);
                    parameters.put("card_number", cardNumber);
                    parameters.put("card_expiry_date_mon", expiryDate_mon);
                    parameters.put("card_expiry_date_year", expiryDate_year);
                    parameters.put("cvv", cvv);
                    parameters.put("payment_amount", payment_amount);
                    databaseReference.child(uid).setValue(parameters);
                    Log.i("Database: ", "Add is Successful");
                    Toast.makeText(Payment.this, "Add To Cart Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Payment.this,MainActivity.class));
                }

            }
        });
    }
}