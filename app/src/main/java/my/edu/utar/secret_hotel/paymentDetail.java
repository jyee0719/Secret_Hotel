package my.edu.utar.secret_hotel;

public class paymentDetail {
    private String total_price, card_holder_name, card_number, cardExpiryDate_Mon, cardExpiryDate_Year, cvv;

    public paymentDetail() {
    }

    public paymentDetail(String total_price, String card_holder_name, String card_number, String cardExpiryDate_Mon, String cardExpiryDate_Year, String cvv) {
        this.total_price = total_price;
        this.card_holder_name = card_holder_name;
        this.card_number = card_number;
        this.cardExpiryDate_Mon = cardExpiryDate_Mon;
        this.cardExpiryDate_Year = cardExpiryDate_Year;
        this.cvv = cvv;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getCard_holder_name() {
        return card_holder_name;
    }

    public void setCard_holder_name(String card_holder_name) {
        this.card_holder_name = card_holder_name;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getCardExpiryDate_Mon() {
        return cardExpiryDate_Mon;
    }

    public void setCardExpiryDate_Mon(String cardExpiryDate_Mon) {
        this.cardExpiryDate_Mon = cardExpiryDate_Mon;
    }

    public String getCardExpiryDate_Year() {
        return cardExpiryDate_Year;
    }

    public void setCardExpiryDate_Year(String cardExpiryDate_Year) {
        this.cardExpiryDate_Year = cardExpiryDate_Year;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
