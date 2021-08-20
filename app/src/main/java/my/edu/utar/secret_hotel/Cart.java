package my.edu.utar.secret_hotel;

import java.util.Objects;

public class Cart {
    private String name, price, quantity, checkin,checkout,duration, image;

    //constructor of Cart
    public Cart() {

    }

    public Cart(String name, String price, String quantity, String checkin, String checkout, String duration, String image) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.checkin = checkin;
        this.checkout = checkout;
        this.duration = duration;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getCheckin() {
        return checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public String getDuration() {
        return duration;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(name, cart.name) &&
                Objects.equals(checkin, cart.checkin) &&
                Objects.equals(checkout, cart.checkout);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, checkin, checkout);
    }
}
