package codeutsava.app.codeutsava.com.codeutsava.helper;

/**
 * Created by MOHIT on 12-Sep-16.
 */
public class User {

    private int user_id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;


    public User() {
    }

    public User(int user_id, String first_name, String last_name, String email,
                String phone, int verified, String college,
                String member_since, int count_event_registered) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String username) {
        this.last_name = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
