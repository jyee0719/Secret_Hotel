package my.edu.utar.secret_hotel;

public class User {
    private String email, ic, phone;

    public User(){}

    public User(String email, String ic, String phone){
        this.email = email;
        this.ic = ic;
        this.phone = phone;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getIC(){
        return ic;
    }

    public void setIC(String ic){
        this.ic = ic;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }
}
