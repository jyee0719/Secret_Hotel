package my.edu.utar.secret_hotel;

import android.widget.ImageView;

public class Room {
     private String roomType, roomDesc, roomImageURL, roomPrice;

     public Room(){}

    public Room(String roomType, String roomDesc, String roomImageURL, String roomPrice) {
        this.roomType = roomType;
        this.roomDesc = roomDesc;
        this.roomImageURL = roomImageURL;
        this.roomPrice = roomPrice;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setRoomDesc(String roomDesc) {
        this.roomDesc = roomDesc;
    }

    public void setRoomPrice(String roomPrice) {
        this.roomPrice = roomPrice;
    }

    public String getRoomDesc() {
        return roomDesc;
    }

    public String getRoomPrice() {
        return roomPrice;
    }

    public String getRoomImageURL() {
        return roomImageURL;
    }

    public void setRoomImageURL(String roomImageURL) {
        this.roomImageURL = roomImageURL;
    }
}
