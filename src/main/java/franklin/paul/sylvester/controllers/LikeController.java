package franklin.paul.sylvester.controllers;

import franklin.paul.sylvester.dtos.responses.Principal;
import franklin.paul.sylvester.entities.Like;
import franklin.paul.sylvester.entities.UserProfile;
import franklin.paul.sylvester.services.LikeService;
import franklin.paul.sylvester.services.TokenService;
import franklin.paul.sylvester.services.UserProfileService;
import franklin.paul.sylvester.utils.custom_exceptions.InvalidAuthException;
import franklin.paul.sylvester.utils.custom_exceptions.InvalidLikeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/likes")
public class LikeController {
    private final LikeService likeService;
    private final UserProfileService profileService;
    private final TokenService tokenService;

    public LikeController(LikeService likeService, UserProfileService profileService, TokenService tokenService) {
        this.likeService = likeService;
        this.profileService = profileService;
        this.tokenService = tokenService;
    }

    @PostMapping
    public void likePost(@RequestParam String id, HttpServletRequest servReq) {
        String token = servReq.getHeader("authorization");

        if(token == null || token.isEmpty())
            throw new InvalidAuthException("Invalid token");

        Principal principal = tokenService.extractRequesterDetails(token);

        if(principal == null)
            throw new InvalidAuthException("Please log in to like a post");

        String userId = principal.getUserId();
        UserProfile profile = profileService.getProfileByUserId(userId);

        if(!likeService.isLiked(userId, id))
            likeService.saveLikeByUserIdAndPostId(userId, id, principal.getUsername(), profile.getDisplayName());
        else
            throw new InvalidLikeException("You have already liked this post");
    }

    @DeleteMapping
    public void unlikePost(@RequestParam String id, HttpServletRequest servReq) {
        String token = servReq.getHeader("authorization");

        if(token == null || token.isEmpty())
            throw new InvalidAuthException("Invalid token");

        Principal principal = tokenService.extractRequesterDetails(token);

        if(principal == null)
            throw new InvalidAuthException("You are not logged in");

        if(!principal.isActive())
            throw new InvalidAuthException("Your account is not active");

        String userId = principal.getUserId();

        if(likeService.isLiked(userId, id))
            likeService.deleteLikeByUserIdAndPostId(userId, id);
        else
            throw new InvalidLikeException("You have not liked this post");
    }

    @GetMapping("/user")
    public List<Like> getAllByUserId(@RequestParam String id) {
        return likeService.getAllLikesByUserId(id);
    }

    @GetMapping("/post")
    public List<Like> getAllByPostId(@RequestParam String id) {
        return likeService.getAllLikesByPostId(id);
    }

    @GetMapping("/count")
    public int getCountByPostId(@RequestParam String id) {
        return likeService.getLikeCountByPostId(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidLikeException.class)
    public InvalidLikeException handledLikeException (InvalidLikeException e) {
        return e;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidAuthException.class)
    public InvalidAuthException handledAuthException (InvalidAuthException e) {
        return e;
    }
}
