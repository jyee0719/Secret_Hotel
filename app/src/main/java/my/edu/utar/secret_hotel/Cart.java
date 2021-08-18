package my.edu.utar.secret_hotel;

public class Cart {
    private String name, price, quantity, checkin,checkout,duration, image;

    public Cart() {

    }

    public Cart(String name, String price, String quantity, String checkin, String checkout, String duration, String image) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.checkin = checkin;
        this.checkout = checkout;
        this.duration = duration;
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

    public void setImage(String duration) {
        this.image = image;
    }
}
