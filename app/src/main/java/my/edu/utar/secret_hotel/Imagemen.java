package my.edu.utar.secret_hotel;

public class Imagemen {

    private String imageUri;

    public Imagemen(){

    }
    public Imagemen(String imageUri){
        this.imageUri=imageUri;
    }
    public String getImageUri(){
        return imageUri;
    }
    public void setImageUri(String imageUri){
        this.imageUri=imageUri;
    }
}
