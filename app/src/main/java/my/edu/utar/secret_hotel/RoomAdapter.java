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
    //update
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

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

        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.single_room, null);


        TextView type = convertView.findViewById(R.id.roomname);
        TextView desc = convertView.findViewById(R.id.roomdesc);
        TextView price = convertView.findViewById(R.id.roomprice);
        ImageView image = convertView.findViewById(R.id.roomimageview);


        Room room = roomArrayList.get(position);
        type.setText(room.getRoomType());
        desc.setText(room.getRoomDesc());
        price.setText(room.getRoomPrice());

        Glide.with(context)
                .load(room.getRoomImageURL())
                .into(image);

//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //openSingleRoom(room.getRoomType(),room.getRoomDesc(),room.getRoomPrice());
//                //openSingleRoom(room.getRoomDesc());
//                Intent intent= new Intent(context, RoomViewforBook.class);
//                //intent.putExtra("room",getItemId(position));
//                intent.putExtra("type",room.getRoomType());
//                intent.putExtra("image",room.getRoomImageURL());
//                intent.putExtra("desc",room.getRoomDesc());
//                intent.putExtra("price",room.getRoomPrice());
//                context.startActivity(intent);
//            }
//        });

        return convertView;
    }

    private void openSingleRoom(String...details){
        Intent intent = new Intent(context,RoomViewforBook.class);
        //intent.putExtra("type",details[0]);
        intent.putExtra("desc",details[0]);
        //intent.putExtra("price",details[2]);
        context.startActivity(intent);
    }
}
