package my.edu.utar.secret_hotel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

// To hold the checkout data
public class checkoutAdapter extends BaseAdapter {
    private final ArrayList<Cart> cartArrayList;
    private final Context context;

    public checkoutAdapter(ArrayList<Cart> cartArrayList, Context context){
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
        convertView = layoutInflater.inflate(R.layout.checkout, null);

        TextView name = convertView.findViewById(R.id.tv_roomtype_invoice);
        TextView quantity = convertView.findViewById(R.id.tv_quantity_invoice);
        TextView price = convertView.findViewById(R.id.tv_price_invoice);
        TextView checkin = convertView.findViewById(R.id.tv_checkInDate_invoice);
        TextView checkout = convertView.findViewById(R.id.tv_checkOutDate_invoice);
        TextView duration = convertView.findViewById(R.id.tv_duration_invoice);

        Cart check_out = cartArrayList.get(position);
        name.setText(check_out.getName());
        quantity.setText(check_out.getQuantity());
        price.setText(check_out.getPrice());
        checkin.setText(check_out.getCheckin());
        checkout.setText(check_out.getCheckout());
        duration.setText(check_out.getDuration());

        return convertView;
    }

}
