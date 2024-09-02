import java.util.Date;

public class User {
    private String fullName;
    private Date date;
    private String address;
    private int phoneNo;
    private String email;
    private String userType;
    private String status;

    public User(String fullName, Date date, String address, int phoneNo, String email, String userType, String status){
        this.fullName = fullName;
        this.date = date;
        this.address = address;
        this.phoneNo = phoneNo;
        this.email = email;
        this.userType = userType;
        this.status = status;
    }

    public String getFullName(){
        return fullName;
    }

    public void setFullName(String fullName){
        this.fullName = fullName;
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }






    //Methods
    void login(){
        System.out.println("You are now logged in! ");

    }
    void logout(){
        System.out.println("You are now logged out! ");

    }

}