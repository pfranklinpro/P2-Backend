package franklin.paul.sylvester.controllers;

import franklin.paul.sylvester.dtos.requests.NewReplyRequest;
import franklin.paul.sylvester.dtos.responses.Principal;
import franklin.paul.sylvester.entities.Reply;
import franklin.paul.sylvester.entities.UserProfile;
import franklin.paul.sylvester.services.PostService;
import franklin.paul.sylvester.services.ReplyService;
import franklin.paul.sylvester.services.TokenService;
import franklin.paul.sylvester.services.UserProfileService;
import franklin.paul.sylvester.utils.custom_exceptions.InvalidAuthException;
import franklin.paul.sylvester.utils.custom_exceptions.InvalidPostException;
import franklin.paul.sylvester.utils.custom_exceptions.InvalidReplyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/replies")
public class ReplyController {
    private final ReplyService replyService;
    private final PostService postService;
    private final UserProfileService profileService;
    private final TokenService tokenService;

    public ReplyController(ReplyService replyService, PostService postService, UserProfileService profileService,
                           TokenService tokenService) {
        this.replyService = replyService;
        this.postService = postService;
        this.profileService = profileService;
        this.tokenService = tokenService;
    }

    @PostMapping
    public void create(@RequestBody NewReplyRequest req, HttpServletRequest servReq) {
        String token = servReq.getHeader("authorization");

        if(token == null || token.isEmpty())
            throw new InvalidAuthException("Invalid token");

        Principal principal = tokenService.extractRequesterDetails(token);

        if(principal == null)
            throw new InvalidAuthException("Please log in to create a reply");

        if(!principal.isActive())
            throw new InvalidAuthException("Your account is not active");

        String userId = principal.getUserId();
        UserProfile profile = profileService.getProfileByUserId(userId);

        if(replyService.isValidContent(req.getReply())) {
            if(postService.isValidPostId(req.getPostId()))
                replyService.saveReplyByUserId(req, userId, principal.getUsername(), profile.getDisplayName());
            else
                throw new InvalidPostException("Post does not exist");
        } else
            throw new InvalidReplyException("Reply must be 128 characters or less");
    }

    @GetMapping("/post")
    public List<Reply> getAllByPostId(@RequestParam String id) {
        return replyService.getAllRepliesByPostId(id);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(InvalidReplyException.class)
    public InvalidReplyException handledReplyException (InvalidReplyException e) {
        return e;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidPostException.class)
    public InvalidPostException handledPostException (InvalidPostException e) {
        return e;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidAuthException.class)
    public InvalidAuthException handledAuthException (InvalidAuthException e) {
        return e;
    }
}
