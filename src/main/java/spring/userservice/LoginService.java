package spring.userservice;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class LoginService {

    private static List<User> users = new LinkedList<>();

    static {
        users.add(new User("user", "$2a$10$6mf3CesQx9eRGB4B3sjr8e1eSr5cYO/zt87bwYVdA4O8rmjDMDdHO"));
    }

    public UserDetails findUser(final String login) {
        User thisUser = null;
        for (User user : users) {
            if (user.getUsername().equals(login)) thisUser = user;
        }
        if (thisUser == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return new MyLogin(thisUser);
    }

}
