package marketplace.controller;

import marketplace.entity.User;
import marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public User getCurrentUser(Authentication authentication){
        return (authentication != null) ? (User) authentication.getPrincipal() : null;
    }

    @GetMapping("/user/{userId}")
    public User getPointsByCoord(
            @PathVariable String userId
    ) throws Exception {
        return userService.findById(userId);
    }
}
