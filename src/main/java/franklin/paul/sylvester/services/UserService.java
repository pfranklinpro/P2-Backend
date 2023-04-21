package franklin.paul.sylvester.services;

import franklin.paul.sylvester.dtos.requests.NewLoginRequest;
import franklin.paul.sylvester.dtos.requests.NewUserRequest;
import franklin.paul.sylvester.dtos.responses.Principal;
import franklin.paul.sylvester.entities.User;
import franklin.paul.sylvester.repositories.UserRepository;
import franklin.paul.sylvester.utils.custom_exceptions.InvalidAuthException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User signup(NewUserRequest req) {
        User createdUser = new User(UUID.randomUUID().toString(), req.getUsername(), req.getPassword1(),
                req.getEmail(), new Date(), true, null);

        userRepo.save(createdUser);
        return createdUser;
    }

    public User setActive(String userId) {
        User activeUser = userRepo.findByUserId(userId);
        if(activeUser.isActive())
            activeUser.setActive(false);
        else
            activeUser.setActive(true);
        return activeUser;
    }

    public Principal login(NewLoginRequest req) {
        if(req.getUsername().isEmpty() || req.getPassword().isEmpty())
            throw new InvalidAuthException("Please enter a username and password");

        User validUser = userRepo.findByUsernameAndPassword(req.getUsername(), req.getPassword());

        if(validUser == null)
            throw new InvalidAuthException("Incorrect username or password");

        return new Principal(validUser.getUserId(), validUser.getUsername(), validUser.getEmail(),
                validUser.getRegistered(), validUser.isActive(), validUser.getRoleId());
    }

    public Principal login(User user) {
        if(user.getUsername().isEmpty() || user.getPassword().isEmpty())
            throw new InvalidAuthException("Please enter a username and password");

        User validUser = userRepo.findByUsernameAndPassword(user.getUsername(), user.getPassword());

        if(validUser == null)
            throw new InvalidAuthException("Incorrect username or password");

        return new Principal(validUser.getUserId(), validUser.getUsername(), validUser.getEmail(),
                validUser.getRegistered(), validUser.isActive(), validUser.getRoleId());
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepo.findAll();
    }

    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public boolean isValidUsername(String username) {
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    }

    public boolean isDuplicateUsername(String username) {
        List<String> usernames = userRepo.findAllUsernames();
        return usernames.contains(username);
    }

    public boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }

    public boolean isSamePassword(String password1, String password2) {
        return password1.equals(password2);
    }

    public boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*$");
    }

    public boolean isDuplicateEmail(String email) {
        List<String> emails = userRepo.findAllEmails();
        return emails.contains(email);
    }
}
