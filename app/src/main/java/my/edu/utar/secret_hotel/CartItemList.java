package my.edu.utar.secret_hotel;

import android.util.Log;

import java.util.ArrayList;

public class CartItemList {
    private ArrayList<Cart> cartArrayList;
    private double totalPrice;

    public CartItemList(){
        this(new ArrayList<>());
    }

    public CartItemList(ArrayList<Cart> cartArrayList) {
        this.cartArrayList = cartArrayList;
        this.calcTotalPrice();
    }

        // Calculate total price
    public void calcTotalPrice(){
        double p = 0;
        for(Cart c : this.cartArrayList){
            Double d = (c.getDuration() == null) ? 0 : Double.valueOf(c.getDuration());
            p += Double.valueOf(c.getPrice()) * Double.valueOf(c.getQuantity()) * d;
        }
        Log.i("Testing", p + "");
        this.setTotalPrice(p);
    }

    public void addItem(Cart c){
        // Avoid duplication record in room cart and check out
        // When one booking is occurring the duplicated room name, check-in date and check-out date, the system will automatically added on the quantity
        if(cartArrayList.contains(c)){
            Cart tmp = getItem(c.getName(), c.getCheckin(), c.getCheckout());
            int q = Integer.valueOf(tmp.getQuantity()) + Integer.valueOf(c.getQuantity());
            tmp.setQuantity(q + "");

        } else {
            cartArrayList.add(c);
        }
        this.calcTotalPrice();
    }

    public void removeItem(Cart c){
        cartArrayList.remove(c);
    }

    public Cart getItem(String name, String checkin, String checkout){
        Cart c = new Cart();
        c.setName(name);
        c.setCheckin(checkin);
        c.setCheckout(checkout);
        if(cartArrayList.contains(c)){
            int ind = 0;
            ind = cartArrayList.indexOf(c);
            return cartArrayList.get(ind);
        }
        return null;
    }

    public ArrayList<Cart> getCartArrayList() {
        return cartArrayList;
    }

    public void setCartArrayList(ArrayList<Cart> cartArrayList) {
        this.cartArrayList = cartArrayList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
