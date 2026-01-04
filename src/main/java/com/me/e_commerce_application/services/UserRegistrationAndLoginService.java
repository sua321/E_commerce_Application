package com.me.e_commerce_application.services;

import com.me.e_commerce_application.daos.userDaos.UserLoginDao;
import com.me.e_commerce_application.daos.userDaos.UserRegistrationDao;
import com.me.e_commerce_application.models.User;
import com.me.e_commerce_application.models.sub_dependencies.UserCredentials;
import com.me.e_commerce_application.repositories.UserCredentialsRepository;
import com.me.e_commerce_application.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class UserRegistrationAndLoginService {
    UserCredentialsRepository userCredentialsRepository;
    UserRepository userRepository;
    public String registeringCustomerOrVendor(UserRegistrationDao dao){  // admin needs separate registration method
        User user = userRepository.findUserByUserName(dao.username());
        if(user == null){
            User newUser = User.builder()
                    .userName(dao.username())
                    .userType(dao.userType()) // need ot add DOB
                    .build();
            UserCredentials newUserCredentials = UserCredentials.builder()
                    .email(dao.email())
                    .password(dao.password())
                    .user(newUser)
                    .build();
            userCredentialsRepository.save(newUserCredentials); // hibernate will automatically save newUser Object
            return "Registration Successfully";

        }
        else{
            return "User already exist";
        }
    }

    public String userLogin(UserLoginDao userLoginDao){ // admin, customer, vendor all can login through this
        if((userLoginDao.email() == null && userLoginDao.userName() == null) || userLoginDao.password() == null)
            return "Login failed";
        if(userLoginDao.email() == null){
            User user = userRepository.findUserByUserName(userLoginDao.userName());
            UserCredentials userCredentials = userCredentialsRepository.findById(user.getId()).orElseThrow();
            if(userCredentials.getPassword().equals(userLoginDao.password())){
                return "login Successfully";
            } else {
                return "Username or Password is wrong";
            }
        } else if (userLoginDao.userName() == null) {
            UserCredentials userCredential = userCredentialsRepository.findUserCredentialsByEmail(userLoginDao.email());
            if (userCredential.getPassword().equals(userLoginDao.password())) {
                return "login Successfully";
            } else{
                return "Email or Password is wrong";
            }
        }

        return "Some problem occur Please retry";
    }
}
