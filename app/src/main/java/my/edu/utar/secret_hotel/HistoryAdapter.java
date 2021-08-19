package my.edu.utar.secret_hotel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryAdapter extends BaseAdapter {
    private final ArrayList<Cart> cartArrayList;
    private final Context context;

    public HistoryAdapter(ArrayList<Cart> cartArrayList,Context context){
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
        convertView = layoutInflater.inflate(R.layout.history, null);

        TextView name = convertView.findViewById(R.id.tv_roomtype_invoice);
        TextView quantity = convertView.findViewById(R.id.tv_quantity_invoice);
        TextView price = convertView.findViewById(R.id.tv_price_invoice);
        TextView checkin = convertView.findViewById(R.id.tv_checkInDate_invoice);
        TextView checkout = convertView.findViewById(R.id.tv_checkOutDate_invoice);
        TextView duration = convertView.findViewById(R.id.tv_duration_invoice);

        Cart history = cartArrayList.get(position);
        name.setText(history.getName());
        quantity.setText(history.getQuantity());
        price.setText(history.getPrice());
        checkin.setText(history.getCheckin());
        checkout.setText(history.getCheckout());
        duration.setText(history.getDuration());

        return convertView;
    }

}
