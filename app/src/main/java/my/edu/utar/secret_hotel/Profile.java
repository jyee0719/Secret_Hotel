package my.edu.utar.secret_hotel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;

public class Profile extends AppCompatActivity {

    EditText email, ic, phone;
    Button btnLogout, btnUpdate;
    DatabaseReference databaseReference;
    String userID;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //enable Back button to back to Main Menu
        getSupportActionBar().setTitle("Profile Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        email = findViewById(R.id.profileEmail);
        email.setEnabled(false);
        ic = findViewById(R.id.profileIC);
        phone = findViewById(R.id.profilePhone);
        btnLogout = findViewById(R.id.btnLogout);
        btnUpdate = findViewById(R.id.btnUpdate);

        //initialize firebase
        fAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        //get current userID
        userID = fAuth.getUid();

        //find the userID and retrieve its data
        databaseReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                //if the user is login, get the user details from Firebase
                if(userProfile != null){
                    String email1 = userProfile.getEmail();
                    String ic1 = userProfile.getIC();
                    String phone1 = userProfile.getPhone();

                    //display the user details
                    email.setText(email1);
                    ic.setText(ic1);
                    phone.setText(phone1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });

        //logout button and direct the user to Main Menu
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        //update profile
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //retrieve the value entered by the user in the EditText
                String newIC = ic.getText().toString();
                String newPhone = phone.getText().toString();
                HashMap hashMap = new HashMap();
                //update the value to Firebase
                hashMap.put("ic", newIC);
                hashMap.put("phone", newPhone);

                databaseReference.child(userID).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(Profile.this, "Your profile is updated successfully.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


}