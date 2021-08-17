package my.edu.utar.secret_hotel;

public class Booking {

    private int quantity;

    public Booking() {
    }

    public Booking(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
