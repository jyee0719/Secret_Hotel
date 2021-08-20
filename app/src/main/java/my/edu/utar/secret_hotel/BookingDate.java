package my.edu.utar.secret_hotel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class BookingDate extends AppCompatActivity {
    private String type, price, image;
    private TextView days;
    private EditText checkindate,checkoutdate;
    private DatePickerDialog datePickerDialog;
    private Button counButton, addtocartbutt;
    private Calendar c1,c2;

    //data initializatin for the popup dialog (xml)
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private ImageView imageViewforpopup;
    private TextView name,quantity;
    private ImageButton add,remove;
    private Button save;
    private int qty=1;

    //save to database
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_date);

        // data passing from the previous activity (SingleRoom, Room2,Room3,Room4,Room5,Room6) using getintent
        // get the data using the name set in previous activity
        Intent intent=getIntent();
        type=intent.getStringExtra("type");
        price=intent.getStringExtra("price");
        image= intent.getStringExtra("image");

        //set the title for supportActionBar
        getSupportActionBar().setTitle("Book Your Date");

        //match the xml item using id
        checkindate = findViewById (R.id.checkindate);
        checkoutdate = findViewById (R.id.checkoutdate);
        days = findViewById (R.id.date);
        addtocartbutt =findViewById(R.id.cart);


        checkindate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                c1 = Calendar.getInstance();
                int mYear = c1.get(Calendar.YEAR); // set current year
                int mMonth = c1.get(Calendar.MONTH); // set current month
                int mDay = c1.get(Calendar.DAY_OF_MONTH); // set current day

                // date picker dialog
                datePickerDialog = new DatePickerDialog(BookingDate.this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // set day of month , month and year value in the edit text
                        String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
                        String mon=MONTHS[monthOfYear];
                        checkindate.setText(dayOfMonth + "-" + (mon) + "-" + year);
                    }
                }, mYear, mMonth, mDay);

                datePickerDialog.show();
            }
        });

        checkoutdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                c2 = Calendar.getInstance();
                int mYear = c2.get(Calendar.YEAR); // set current year
                int mMonth = c2.get(Calendar.MONTH); // set current month
                int mDay = c2.get(Calendar.DAY_OF_MONTH); // set current day

                // date picker dialog
                datePickerDialog = new DatePickerDialog(BookingDate.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // set day of month , month and year value in the edit text
                        String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
                        String mon=MONTHS[monthOfYear];
                        checkoutdate.setText(dayOfMonth + "-" + (mon) + "-" + year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        addtocartbutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // calculate the number of booking days
                try {
                    String d1 = checkindate.getText ().toString ();
                    String d2 = checkoutdate.getText ().toString ();

                    //check validation for checkindate and checkoutdate if user doesnot fill in anything
                    if(TextUtils.isEmpty(d1)){
                        checkindate.setError("Check In Date is required.");
                        return;
                    }

                    if(TextUtils.isEmpty(d2)){
                        checkoutdate.setError("Check Out Date is required.");
                        return;
                    }

                    //parse the data from string to Date format
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
                    Date date1 = simpleDateFormat.parse(d1);
                    Date date2 = simpleDateFormat.parse(d2);

                    //calculate different of time in mills and convert to days
                    long differenceInMillis = date2.getTime() - date1.getTime();
                    float noOfDays = (differenceInMillis) / 1000f / 60f / 60f / 24f;
                    days.setText(""+ noOfDays);
                    Log.i("Count days success", "days"+days );

                    if(!days.getText().toString().isEmpty()) {
                        Toast.makeText(BookingDate.this, "Number of Day: " + noOfDays, Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(BookingDate.this, "nono: " + noOfDays, Toast.LENGTH_SHORT).show();
                    }

                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
                createPopupDialog(type, image, price);
            }
        });
    }


    private void createPopupDialog(String type, String image, String price) {
        dialogBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.popup, null);

        name = view.findViewById(R.id.name);
        quantity = view.findViewById(R.id.quantity);
        imageViewforpopup = view.findViewById(R.id.iv);
        add = view.findViewById(R.id.addbutton);
        remove = view.findViewById(R.id.removebutton);
        save = view.findViewById(R.id.save);

        //show the dialog box
        dialogBuilder.setView(view);
        dialog = dialogBuilder.create();
        dialog.show();

        //set room name, image, quantity
        name.setText(type);
        Glide.with(BookingDate.this)
                .load(image)
                .into(imageViewforpopup);
        quantity.setText(""+qty);


        // add quantity when onclick
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty++;
                quantity.setText(""+qty);
                String qty1 = String.valueOf(quantity.getText());
                // if the quantity is more than 0 set the button enable
                int qty2=Integer.parseInt(qty1);
                if(qty2>0){
                    save.setEnabled(true);
                }
            }
        });

        // minus quantity when onclick
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty--;
                quantity.setText(""+qty);
                String qty1 = String.valueOf(quantity.getText());
                // if the quantity is less than equal 0 set the button not clickable
                int qty2=Integer.parseInt(qty1);
                if(qty2<=0){
                    save.setEnabled(false);
                    Toast.makeText(BookingDate.this, "The Quantity Cannot be Zero or Negative", Toast.LENGTH_SHORT).show();
                }
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set value to the variable after getting the value
                String qty1 = String.valueOf(quantity.getText());
                String inDate = String.valueOf(checkindate.getText());
                String outDate = String.valueOf(checkoutdate.getText());
                String duration = String.valueOf(days.getText());
                String image1 = String.valueOf(image);

                //get the uid of current user
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                //check if uid is empty
                if(!uid.isEmpty()) {

                    //put the new attribute and value to the firebase under Users path, current user and set to the Cart
                    databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Cart");

                    //get cart key
                    String cartID = databaseReference.push().getKey();

                    //set the new input data to the attributes
                    HashMap<String, String> parameters = new HashMap<>();
                    parameters.put("roomName", type);
                    parameters.put("unitprice", price);
                    parameters.put("quantity",qty1);
                    parameters.put("checkindate",inDate);
                    parameters.put("checkoutdate",outDate);
                    parameters.put("duration",duration);
                    parameters.put("image",image1);

                    //set it to firebase
                    databaseReference.child(cartID).setValue(parameters);

                    Log.i("Database: ", "Add is Successful");
                    Toast.makeText(BookingDate.this, "Add To Cart Successfully", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                    //start new activity
                    Intent intent = new Intent(BookingDate.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}