package my.edu.utar.secret_hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BookingDate extends AppCompatActivity {
    String type, price;
    EditText datefrom,dateto,days;
    DatePickerDialog datePickerDialog;
    Button save_btn;
    Calendar c1,c2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_date);

        Intent intent=getIntent();
        type=intent.getStringExtra("type");
        price=intent.getStringExtra("price");
        //getSupportActionBar().setTitle(type);
        getSupportActionBar().setTitle("Book Your Date");

        datefrom = findViewById (R.id.checkindate);
        dateto = findViewById (R.id.checkoutdate);
        days = findViewById (R.id.date);
        save_btn = findViewById (R.id.countdate);

        save_btn.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {

                //  loadsave();
                try {
                    String d1 = datefrom.getText ().toString ();
                    String d2 = dateto.getText ().toString ();

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
                    Date date1 = simpleDateFormat.parse(d1);
                    Date date2 = simpleDateFormat.parse(d2);
                    long difference = Math.abs(date1.getTime() - date2.getTime());
//
//                    long difftDays = difference / (24 * 60 * 60 * 1000);
//
                    long differenceInMillis = date2.getTime() - date1.getTime();
                    float noOfDays = (differenceInMillis) / 1000f / 60f / 60f / 24f;
                    days.setText(""+noOfDays);
//                    Log.i("Testing","days" +difftDays);
//                    days.setText("days" +difftDays);

                    if(!days.getText().toString().isEmpty()) {
                        Toast.makeText(BookingDate.this, "Number of Day: " + noOfDays, Toast.LENGTH_SHORT).show();
                    }
//
               }
             catch(Exception ex)
                {

                    ex.printStackTrace();
                }

            }
        });



        datefrom.setOnClickListener(new View.OnClickListener() {
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

                        datefrom.setText(dayOfMonth + "-"
                                + (mon) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        dateto.setOnClickListener(new View.OnClickListener() {
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
                        dateto.setText(dayOfMonth + "-" + (mon) + "-" + year);



                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }
}