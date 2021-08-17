package my.edu.utar.secret_hotel;

public class User {
    private String email, ic;

    public User(){}

    public User(String email, String ic){
        this.email = email;
        this.ic = ic;
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
}
