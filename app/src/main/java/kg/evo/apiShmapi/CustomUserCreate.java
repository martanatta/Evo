package kg.evo.apiShmapi;

public class CustomUserCreate {
    int id;
    String first_name;
    String email;
    String password;

    public String getFirst_name() {
        return first_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CustomUserCreate(String first_name, String email, String password) {
        this.first_name = first_name;
        this.email = email;
        this.password = password;
        this.id = id;
    }
}
