import java.util.Date;

public class User {
    private String user_id;
    private String fullName;
    private Date date;
    private String address;
    private int phoneNo;
    private String email;
    private String userType;
    private String status;

    void login(){
        System.out.println("You are now logged in! ");

    }
    void logout(){
        System.out.println("You are now logged out! ");

    }

}