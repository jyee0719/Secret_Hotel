package my.edu.utar.secret_hotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    EditText loginEmail, loginPsw;
    Button btnLogin, btnToReg, btnFgtPsw;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //enable Back button to back to Main Menu
        getSupportActionBar().setTitle("Login Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loginEmail = findViewById(R.id.loginEmail);
        loginPsw = findViewById(R.id.loginPsw);
        btnLogin = findViewById(R.id.btnLogin);
        btnToReg = findViewById(R.id.btnToReg);
        btnFgtPsw = findViewById(R.id.btnFgtPsw);

        //initialize firebase
        fAuth = FirebaseAuth.getInstance();

        btnToReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Register.class));
                finish();
            }
        });

        btnFgtPsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText resetEmail = new EditText(v.getContext());

                //popup dialog for users to enter the email to receive the reset link
                AlertDialog.Builder resetPswDialog = new AlertDialog.Builder(v.getContext());
                resetPswDialog.setTitle("Reset password?");
                resetPswDialog.setMessage("Enter your email to receive reset link: ");
                resetPswDialog.setView(resetEmail);

                resetPswDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //extract the email and send reset link
                        String mail = resetEmail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Login.this, "Reset Link has been sent to your email.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login.this, "Error! Reset link is not sent." + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });
                resetPswDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //close the dialog
                    }
                });

                resetPswDialog.show();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginEmail.getText().toString().trim();
                String psw = loginPsw.getText().toString().trim();

                //check the value entered by user whether it fulfills condition or not
                if(TextUtils.isEmpty(email)){
                    loginEmail.setError("Email is required.");
                    return;
                }
                if(TextUtils.isEmpty(psw)){
                    loginPsw.setError("Password is required.");
                    return;
                }

                if(psw.length() < 6){
                    loginPsw.setError("Password must be more than 6 characters.");
                    return;
                }

                //authenticate the user
                fAuth.signInWithEmailAndPassword(email, psw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //check login successful or not
                        if(task.isSuccessful()){ //if user is created successfully
                            Toast.makeText(Login.this,"Login successfully.", Toast.LENGTH_SHORT).show();
                            //isUser();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else{
                            Toast.makeText(Login.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
    }

    public void isUser(){
        String userEnteredEmail = loginEmail.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query checkUser = reference.orderByChild("email").equalTo(userEnteredEmail);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String emailDB = snapshot.child("email").getValue().toString();
                    String icDB = snapshot.child("ic").getValue().toString();

                    Intent intent = new Intent(getApplicationContext(),Profile.class);
                    intent.putExtra("email", emailDB);
                    intent.putExtra("ic", icDB);

                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}