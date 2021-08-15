package my.edu.utar.secret_hotel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Room> roomArrayList;
    private LayoutInflater layoutInflater;

    public CustomAdapter(Context context, ArrayList<Room> roomArrayList){
        this.context=context;
        this.roomArrayList=roomArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_room, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        Room room = roomArrayList.get(position);
        if(room==null){
            return;
        }
        holder.roomname.setText(room.getRoomType());
        Glide.with(context)
                .load(room.getRoomImageURL())
                .into(holder.roomimageView);
        holder.roomdesc.setText(room.getRoomDesc());
        holder.roomprice.setText(room.getRoomPrice());
    }

    @Override
    public int getItemCount() {
        return roomArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView roomimageView;
        TextView roomname, roomdesc, roomprice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            roomimageView=itemView.findViewById(R.id.imageview);
            roomname=itemView.findViewById(R.id.roomname);
            roomdesc=itemView.findViewById(R.id.roomdesc);
            roomprice=itemView.findViewById(R.id.roomprice);
        }
    }
}
