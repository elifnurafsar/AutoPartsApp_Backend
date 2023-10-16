package com.itg.AutomobilePartApp.Controllers.User;

import com.itg.AutomobilePartApp.DTO.User.AuthenticationDTO;
import com.itg.AutomobilePartApp.DTO.User.CreditCardInfoDTO;
import com.itg.AutomobilePartApp.DTO.User.UserDTO;
import com.itg.AutomobilePartApp.Entities.Util.CreditCardInfo;
import com.itg.AutomobilePartApp.Responses.User.AuthenticationResponse;
import com.itg.AutomobilePartApp.Responses.User.UserResponse;
import com.itg.AutomobilePartApp.Responses.Util.CreditCardInfoResponse;
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

    @GetMapping("/get_all")
    public List<UserResponse> getAllUsers(){
        return userService.getAll();
    }

    @GetMapping("/get_by_email")
    public UserResponse getUserByEMail(@RequestParam("e_mail") String e_mail){
        return userService.getUserByUserName(e_mail);
    }

    @GetMapping("/get_blocked_accounts")
    public List<UserResponse> getBlockedAccounts(){
        return userService.getBlockedAccounts();
    }

    @GetMapping("/filter_blocked_accounts")
    public List<UserResponse> FilterBlockedAccounts(@RequestParam("e_mail") String e_mail){
        return userService.getBlockedAccountsByName(e_mail);
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

    @DeleteMapping //, consumes = "application/json", produces = "application/json")
    public boolean DeleteUser(@RequestParam("e_mail") String e_mail){
        return userService.deleteUserByUserName(e_mail);
    }

    @PostMapping("/payment")
    public ResponseEntity<CreditCardInfoResponse> addPaymentMethod(@RequestBody CreditCardInfoDTO creditCardInfoDTO){
        return ResponseEntity.ok(userService.addCreditCard(creditCardInfoDTO));
    }

    @GetMapping("/my_payment_methods")
    public List<CreditCardInfoResponse> getAllPaymentMethods(@RequestBody AuthenticationDTO user){
        return userService.getAllCreditCards(user);
    }

    @PutMapping("/block_account")
    public boolean BlockAccount(@RequestParam("e_mail") String e_mail){
        return userService.BlockAccount(e_mail);
    }

    @PutMapping("/enable_account")
    public boolean enableAccount(@RequestParam("e_mail") String e_mail){
        return userService.EnableAccount(e_mail);
    }
}
