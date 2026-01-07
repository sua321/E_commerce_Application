package com.me.e_commerce_application.services;

import com.me.e_commerce_application.daos.userDaos.UserLoginDao;
import com.me.e_commerce_application.daos.userDaos.UserRegistrationDao;
import com.me.e_commerce_application.models.Users;
import com.me.e_commerce_application.models.sub_dependencies.UserCredentials;
import com.me.e_commerce_application.repositories.UserCredentialsRepository;
import com.me.e_commerce_application.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@AllArgsConstructor
@Service
public class UserRegistrationAndLoginService implements UserDetailsService {
    UserCredentialsRepository userCredentialsRepository;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    public String registeringCustomerOrVendor(UserRegistrationDao dao){  // admin needs separate registration method
        Users users = userRepository.findUserByUserName(dao.userName());
        if(users == null){
            Users newUsers = Users.builder()
                    .userName(dao.userName())
                    .userType(dao.userType()) // need ot add DOB
                    .build();
            UserCredentials newUserCredentials = UserCredentials.builder()
                    .email(dao.email())
                    .password(passwordEncoder.encode(dao.password()))
                    .users(newUsers)
                    .build();
            userCredentialsRepository.save(newUserCredentials); // hibernate will automatically save newUsers Object
            return "Registration Successfully";

        }
        else{
            return "Users already exist";
        }
    }

//    public String userLogin(UserLoginDao userLoginDao){ // admin, customer, vendor all can login through this
//        if((userLoginDao.email() == null && userLoginDao.userName() == null) || userLoginDao.password() == null)
//            return "Login failed";
//        if(userLoginDao.email() == null){
//            Users users = userRepository.findUserByUserName(userLoginDao.userName());
//            if(users == null)
//                return "Username or Password is wrong(userName)";
//
//            UserCredentials userCredentials = userCredentialsRepository.findById(users.getId()).orElseThrow();
//            if(passwordEncoder.matches(userLoginDao.password(), userCredentials.getPassword())){
//                return "login Successfully";
//            } else {
//                return "Username or Password is wrong";
//            }
//        } else if (userLoginDao.userName() == null) {
//            UserCredentials userCredential = userCredentialsRepository.findUserCredentialsByEmail(userLoginDao.email());
//            if(userCredential == null)
//                return "Email or Password is wrong(Email)";
//            if (passwordEncoder.matches(userLoginDao.password(), userCredential.getPassword())) {
//                return "login Successfully";
//            } else{
//                return "Email or Password is wrong";
//            }
//        }
//
//        return "Some problem occur Please retry";
//    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return null;
    }

    // overloading
    public UserDetails loadUserByUsername(UserLoginDao userLoginDao) throws UsernameNotFoundException{// admin, customer, vendor all can login through this
        if((userLoginDao.email() == null && userLoginDao.userName() == null) || userLoginDao.password() == null) {
            System.out.println("Login failed");
        }
        //Through UserName
        if(userLoginDao.email() == null) {
            Users users = userRepository.findUserByUserName(userLoginDao.userName());
            try{
                UserCredentials userCredentials = userCredentialsRepository.findById(users.getId()).orElseThrow();
                return new User(users.getUserName(), userCredentials.getPassword(), Collections.emptyList());
            }
            catch (Exception e){
                System.out.println("Username or Password is wrong(userName)");
            }
        //Through Email
        }else if (userLoginDao.userName() == null) {
            UserCredentials userCredential = userCredentialsRepository.findUserCredentialsByEmail(userLoginDao.email());
            try{
                return new User(
                        userCredential.getEmail(),
                        userCredential.getPassword(),
                        Collections.emptyList()
                );
            }
            catch (Exception e){
                System.out.println("Email or Password is wrong(Email)");
            }
        }
        return null;
    }
}
