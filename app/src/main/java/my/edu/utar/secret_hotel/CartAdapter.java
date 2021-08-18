package my.edu.utar.secret_hotel;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.time.Instant;
import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {

    private final ArrayList<Cart> cartArrayList;
    private final Context context;

    public CartAdapter(ArrayList<Cart> cartArrayList, Context context){
        this.cartArrayList = cartArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.cartArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.cart, null);


        TextView name = convertView.findViewById(R.id.roomname);
        TextView quantity = convertView.findViewById(R.id.roomqty);
        TextView price = convertView.findViewById(R.id.roomprice);
        ImageView image = convertView.findViewById(R.id.roomimageview);
        TextView checkin = convertView.findViewById(R.id.checkin);
        TextView checkout = convertView.findViewById(R.id.checkout);

        Cart cart = cartArrayList.get(position);
        name.setText(cart.getName());
        quantity.setText(cart.getQuantity());
        price.setText(cart.getPrice());
        checkin.setText(cart.getCheckin());
        checkout.setText(cart.getCheckout());

        Glide.with(context)
                .load(cart.getImage())
                .into(image);


        return convertView;
    }

}
