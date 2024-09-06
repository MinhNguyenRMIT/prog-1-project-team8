package Assignment.Users;
import java.util.Date;

public class Client extends User {
    private int clientID;
    private String membership;

    public Client (int clientID, String membership, String fullName, Date date, String address,
                   int phoneNo, String email, String userType, String status){
        super(fullName, date, address, phoneNo, email, userType, status);
        this.clientID = clientID;
        this.membership = membership;
    }

    public int getClientID(){
        return clientID;
    }
}
