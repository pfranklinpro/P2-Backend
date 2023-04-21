package franklin.paul.sylvester.controllers;

import franklin.paul.sylvester.dtos.requests.NewUserRequest;
import franklin.paul.sylvester.dtos.responses.Principal;
import franklin.paul.sylvester.entities.User;
import franklin.paul.sylvester.entities.UserProfile;
import franklin.paul.sylvester.services.TokenService;
import franklin.paul.sylvester.services.UserProfileService;
import franklin.paul.sylvester.services.UserService;
import franklin.paul.sylvester.utils.custom_exceptions.InvalidAuthException;
import franklin.paul.sylvester.utils.custom_exceptions.InvalidProfileException;
import franklin.paul.sylvester.utils.custom_exceptions.InvalidUserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserProfileService profileService;
    private final TokenService tokenService;

    public UserController(UserService userService, UserProfileService profileService, TokenService tokenService) {
        this.userService = userService;
        this.profileService = profileService;
        this.tokenService = tokenService;
    }

    @PostMapping
    public Principal signup(@RequestBody NewUserRequest req) {
        User createdUser;
        Principal principal;

        if(userService.isValidUsername(req.getUsername())) {
            if(!userService.isDuplicateUsername(req.getUsername())) {
                if(userService.isValidPassword(req.getPassword1())) {
                    if(userService.isSamePassword(req.getPassword1(), req.getPassword2())) {
                        if(userService.isValidEmail(req.getEmail())) {
                            if(!userService.isDuplicateEmail(req.getEmail())) {
                                if(!profileService.isEmptyDisplayName(req.getDisplayName())) {
                                    if(profileService.isValidBirthDate(req.getBirthDate())) {
                                        createdUser = userService.signup(req);
                                        UserProfile createdProfile = profileService.createProfile(req, createdUser);
                                        principal = userService.login(createdUser);
                                        String token = tokenService.generateToken(principal);
                                        principal.setToken(token);
                                    } else
                                        throw new InvalidProfileException("Must be 13 years or older to create a " +
                                                "profile");
                                } else
                                    throw new InvalidProfileException("Please enter a display name");
                            } else
                                throw new InvalidUserException("Email address is already taken");
                        } else
                            throw new InvalidUserException("Invalid email address");
                    } else
                        throw new InvalidUserException("Passwords do not match");
                } else
                    throw new InvalidUserException("Invalid password");
            } else
                throw new InvalidUserException("Username is already taken");
        } else
            throw new InvalidUserException("Invalid username");

        return principal;
    }

    @GetMapping
    public List<Principal> getAll() {
        List<User> users = userService.getAllUsers();
        List<Principal> principals = new ArrayList<>();

        for(User user : users){
            Principal currentPrincipal = new Principal(user.getUserId(), user.getUsername(), user.getEmail(),
                    user.getRegistered(), user.isActive(), user.getRoleId());

            principals.add(currentPrincipal);
        }

        return principals;
    }

    @GetMapping("/username")
    public Principal getByUsername(@RequestParam String username) {
        User user = userService.getUserByUsername(username);
        Principal principal = new Principal(user.getUserId(), user.getUsername(), user.getEmail(),
                user.getRegistered(), user.isActive(), user.getRoleId());

        return principal;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(InvalidUserException.class)
    public InvalidUserException handledUserException (InvalidUserException e) {
        return e;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidAuthException.class)
    public InvalidAuthException handledAuthException (InvalidAuthException e) {
        return e;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(InvalidProfileException.class)
    public InvalidProfileException handledProfileException (InvalidProfileException e) {
        return e;
    }
}
