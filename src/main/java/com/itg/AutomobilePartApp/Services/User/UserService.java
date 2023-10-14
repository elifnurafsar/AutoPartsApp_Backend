package com.itg.AutomobilePartApp.Services.User;

import com.itg.AutomobilePartApp.Config.JwtService;
import com.itg.AutomobilePartApp.DTO.User.AuthenticationDTO;
import com.itg.AutomobilePartApp.DTO.User.CreditCardInfoDTO;
import com.itg.AutomobilePartApp.DTO.User.UserDTO;
import com.itg.AutomobilePartApp.Entities.User;
import com.itg.AutomobilePartApp.Entities.Util.CreditCardInfo;
import com.itg.AutomobilePartApp.Mappers.User.UserMapper;
import com.itg.AutomobilePartApp.Mappers.Util.CreditCardInfoMapper;
import com.itg.AutomobilePartApp.Repositories.User.CreditCardInfoRepository;
import com.itg.AutomobilePartApp.Repositories.User.UserRepository;
import com.itg.AutomobilePartApp.Responses.User.AuthenticationResponse;
import com.itg.AutomobilePartApp.Responses.User.UserResponse;
import com.itg.AutomobilePartApp.Responses.Util.CreditCardInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CreditCardInfoRepository creditCardInfoRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserService(UserRepository _userRepository, CreditCardInfoRepository _creditCardInfoRepository, JwtService _jwtService, AuthenticationManager _authenticationManager) {
        this.userRepository = _userRepository;
        this.creditCardInfoRepository = _creditCardInfoRepository;
        this.jwtService = _jwtService;
        this.authenticationManager = _authenticationManager;
    }

    @Transactional
    public AuthenticationResponse createUser(UserDTO new_user){
        new_user.setPassword(new BCryptPasswordEncoder().encode(new_user.getPassword()));
        userRepository.insertUser(new_user);
        User user = userRepository.getByEmail(new_user.getUsername()).orElse(null);
        if(user != null){
            var jwt_token = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwt_token)
                    .build();
        }
        else
            return null;
    }

    public AuthenticationResponse authenticate(AuthenticationDTO user) {
        //CONTROL
        authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        User _user = userRepository.getByEmail(user.getUsername()).orElseThrow();
        var jwt_token = jwtService.generateToken(_user);
        return AuthenticationResponse.builder()
                .token(jwt_token)
                .build();
    }


    public UserResponse getUserByUserName(String e_mail){
        User user = userRepository.getByEmail(e_mail).orElse(null);
        if(user != null){
            return UserMapper.INSTANCE.entityToResponse(user);
        }
        else
            return null;
    }

    public List<UserResponse> getAll(){
        List<User> list = userRepository.getAll();
        return UserMapper.INSTANCE.entityListToResponseList(list);
    }

    public boolean deleteUserByUserName(String e_mail){
        userRepository.deleteSelf(e_mail);
        Optional<User> _admin = userRepository.getByEmail(e_mail);
        if(_admin.isPresent())
            return false;
        return true;
    }

    public List<CreditCardInfoResponse> getAllCreditCards(AuthenticationDTO user) {
        User _user = userRepository.getByEmail(user.getUsername()).orElseThrow();
        boolean x = new BCryptPasswordEncoder().matches(user.getPassword(), _user.getPassword());
        if(x){
            List<CreditCardInfo> list = creditCardInfoRepository.getAllCreditCards(user.getUsername());
            return CreditCardInfoMapper.INSTANCE.entityListToResponseList(list);
        }
        else return new ArrayList<CreditCardInfoResponse>();
    }

    public CreditCardInfoResponse addCreditCard(CreditCardInfoDTO creditcardinfo) {
        User _user = userRepository.getByEmail(creditcardinfo.getUsername()).orElse(null);
        if(_user != null){
            int b = creditCardInfoRepository.addNewCreditCard(creditcardinfo);
            if(b>=0){
                CreditCardInfo _creditCardInfo = creditCardInfoRepository.getRecentCreditCardInfo(creditcardinfo.getCardnumber());
                return CreditCardInfoMapper.INSTANCE.entityToResponse(_creditCardInfo);
            }
            else return null;
        }
        else return null;
    }

    /*public List<UserResponse> getUserByRole(String role) {
        List<User> list = userRepository.getAllByRole(role);
        return UserMapper.INSTANCE.entityListToResponseList(list);
    }*/
}
