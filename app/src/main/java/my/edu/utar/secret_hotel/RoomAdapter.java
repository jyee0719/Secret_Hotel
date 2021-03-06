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

public class RoomAdapter extends BaseAdapter {

    private final ArrayList<Room> roomArrayList;
    private final Context context;

    public RoomAdapter(ArrayList<Room> roomArrayList, Context context){
        this.roomArrayList = roomArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.roomArrayList.size();
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

        //set the view to single_room.xml
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.single_room, null);

        //Declaration
        TextView type = convertView.findViewById(R.id.roomname);
        TextView desc = convertView.findViewById(R.id.roomdesc);
        TextView price = convertView.findViewById(R.id.roomprice);
        ImageView image = convertView.findViewById(R.id.roomimageview);

        //get the item in the array list and set the data
        Room room = roomArrayList.get(position);
        type.setText(room.getRoomType());
        desc.setText(room.getRoomDesc());
        price.setText(room.getRoomPrice());

        //set the image using url through Glide
        Glide.with(context)
                .load(room.getRoomImageURL())
                .into(image);

        // return view
        return convertView;
    }

}
