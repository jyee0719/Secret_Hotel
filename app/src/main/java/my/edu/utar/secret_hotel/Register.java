package my.edu.utar.secret_hotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

public class Register extends AppCompatActivity {

    EditText regEmail, regPsw, regIC, regPhone;
    Button btnReg, btnToLogin;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //enable Back button to back to Main Menu
        getSupportActionBar().setTitle("Register A New Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        regIC = findViewById(R.id.regIC);
        regEmail = findViewById(R.id.regEmail);
        regPsw = findViewById(R.id.regPsw);
        regPhone = findViewById(R.id.regPhone);
        btnReg = findViewById(R.id.btnReg);
        btnToLogin = findViewById(R.id.btnToLogin);

        //initialize firebase
        fAuth = FirebaseAuth.getInstance();
        //realtime database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference node = database.getReference("Users");

        btnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //retrieve value from the EditText
                String email = regEmail.getText().toString().trim();
                String psw = regPsw.getText().toString().trim();
                String ic = regIC.getText().toString().trim();
                String phone = regPhone.getText().toString().trim();

                //check if the value entered by user fulfill the condition or not
                if(TextUtils.isEmpty(email)){
                    regEmail.setError("Email is required.");
                    return;
                }
                if(TextUtils.isEmpty(psw)){
                    regPsw.setError("Password is required.");
                    return;
                }
                if(TextUtils.isEmpty(ic)){
                    regIC.setError("IC number is required.");
                    return;
                }
                if(TextUtils.isEmpty(phone)){
                    regPhone.setError("Phone is required.");
                    return;
                }

                if(psw.length() < 6){
                    regPsw.setError("Password must be more than 6 characters.");
                    return;
                }
                if(ic.length() < 12 || ic.length() > 12){
                    regIC.setError("IC number must be 12 numbers.");
                    return;
                }
                if(phone.length() < 10 || phone.length() > 11){
                    regPhone.setError("Phone must be between 10 to 11 numbers.");
                    return;
                }

                //register the user in Firebase
                fAuth.createUserWithEmailAndPassword(email, psw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){ //if user is created successfully
                            //put user email, ic and phone into realtime database
                            User userData = new User(email, ic, phone);
                            String uID = fAuth.getCurrentUser().getUid();
                            node.child(uID).setValue(userData);
                            Toast.makeText(Register.this,"Account created.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else{
                            Toast.makeText(Register.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
    }
}