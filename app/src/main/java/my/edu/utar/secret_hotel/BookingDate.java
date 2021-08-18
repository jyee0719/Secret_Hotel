package my.edu.utar.secret_hotel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
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
    String type, price, image;
    TextView days;
    EditText checkindate,checkoutdate;
    DatePickerDialog datePickerDialog;
    Button counButton, addtocartbutt;
    Calendar c1,c2;

    //popup
    private AlertDialog.Builder dialogBuilder;
    AlertDialog dialog;
    ImageView imageViewforpopup;
    TextView name,quantity;
    ImageButton add,remove;
    Button save;
    int qty;

    //save to database
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_date);

        Intent intent=getIntent();
        type=intent.getStringExtra("type");
        price=intent.getStringExtra("price");
        image= intent.getStringExtra("image");
        //getSupportActionBar().setTitle(type);
        getSupportActionBar().setTitle("Book Your Date");

        checkindate = findViewById (R.id.checkindate);
        checkoutdate = findViewById (R.id.checkoutdate);
        days = findViewById (R.id.date);
        counButton = findViewById (R.id.countdate);
        addtocartbutt =findViewById(R.id.cart);

        addtocartbutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPopupDialog(type, image, price);
            }
        });

        counButton.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {

                //  loadsave();
                try {
                    String d1 = checkindate.getText ().toString ();
                    String d2 = checkoutdate.getText ().toString ();

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
                    Date date1 = simpleDateFormat.parse(d1);
                    Date date2 = simpleDateFormat.parse(d2);
                    long difference = Math.abs(date1.getTime() - date2.getTime());


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

            }
        });



        checkindate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                c1 = Calendar.getInstance();
                int mYear = c1.get(Calendar.YEAR); // current year
                int mMonth = c1.get(Calendar.MONTH); // current month
                int mDay = c1.get(Calendar.DAY_OF_MONTH); // current day

                // date picker dialog
                datePickerDialog = new DatePickerDialog(BookingDate.this,new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        // set day of month , month and year value in the edit text

                        String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
                        String mon=MONTHS[monthOfYear];

                        checkindate.setText(dayOfMonth + "-"
                                + (mon) + "-" + year);

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
                int mYear = c2.get(Calendar.YEAR); // current year
                int mMonth = c2.get(Calendar.MONTH); // current month
                int mDay = c2.get(Calendar.DAY_OF_MONTH); // current day
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
    }


    private void createPopupDialog(String type, String image, String price) {
        dialogBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.popup, null);

        name = view.findViewById(R.id.name);
        quantity = view.findViewById(R.id.quantity);
        //price = view.findViewById(R.id.price);
        imageViewforpopup = view.findViewById(R.id.iv);

        add = view.findViewById(R.id.addbutton);
        remove = view.findViewById(R.id.removebutton);
        save = view.findViewById(R.id.save);
        //int qty=1;

        dialogBuilder.setView(view);
        dialog = dialogBuilder.create();
        dialog.show();

        name.setText(type);
        //String price1=price;

        Glide.with(BookingDate.this)
                .load(image)
                .into(imageViewforpopup);



        quantity.setText(""+qty);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty++;
                quantity.setText(""+qty);
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty--;
                quantity.setText(""+qty);
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qty1 = String.valueOf(quantity.getText());
                String inDate = String.valueOf(checkindate.getText());
                String outDate = String.valueOf(checkoutdate.getText());
                String duration = String.valueOf(days.getText());
                String image1 = String.valueOf(image);
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                if(!uid.isEmpty()) {
                    databaseReference = FirebaseDatabase.getInstance().getReference("Cart");
                    String cartID = databaseReference.push().getKey();
                    HashMap<String, String> parameters = new HashMap<>();
                    parameters.put("roomName", type);
                    parameters.put("unitprice", price);
                    parameters.put("quantity",qty1);
                    parameters.put("checkindate",inDate);
                    parameters.put("checkoutdate",outDate);
                    parameters.put("duration",duration);
                    parameters.put("image",image1);
                    databaseReference.child(cartID).child(uid).setValue(parameters);
                    Log.i("Database: ", "Add is Successful");
                    dialog.dismiss();
                }
            }
        });

    }
}