package com.itg.AutomobilePartApp.Controllers.User;

import com.itg.AutomobilePartApp.DTO.User.AuthenticationDTO;
import com.itg.AutomobilePartApp.DTO.User.UserDTO;
import com.itg.AutomobilePartApp.Responses.User.AuthenticationResponse;
import com.itg.AutomobilePartApp.Responses.User.UserResponse;
import com.itg.AutomobilePartApp.Services.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/User")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService _userService) {
        this.userService = _userService;
    }

    @GetMapping
    public List<UserResponse> getAllUsers(){
        return userService.getAll();
    }

    @GetMapping("/get_by_email/")
    public UserResponse getUserByEMail(@RequestParam("e_mail") String e_mail){
        return userService.getUserByUserName(e_mail);
    }

    // REGISTER
    @PostMapping
    public ResponseEntity<AuthenticationResponse> createUser(@RequestBody UserDTO new_user){
        return ResponseEntity.ok(userService.createUser(new_user));
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationDTO user){
        return ResponseEntity.ok(userService.authenticate(user));
    }

    @DeleteMapping( path = "/{e_mail}") //, consumes = "application/json", produces = "application/json")
    public boolean DeleteUser(@PathVariable("e_mail") String e_mail){
        return userService.deleteUserByUserName(e_mail);
    }

}
