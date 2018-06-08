package spring.userservice;

public class User {

    private String username;
    private String hash;

    User(String username, String hash){
        this.username = username;
        this.hash = hash;
    }

    public String getUsername() {
        return username;
    }

    public String getHash() {
        return hash;
    }
}
