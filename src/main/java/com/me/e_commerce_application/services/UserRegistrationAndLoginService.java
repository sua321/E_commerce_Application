package com.me.e_commerce_application.services;

import com.me.e_commerce_application.daos.userDaos.UserRegistrationDao;
import com.me.e_commerce_application.models.Users;
import com.me.e_commerce_application.models.sub_dependencies.UserCredentials;
import com.me.e_commerce_application.repositories.UsersCredentialsRepository;
import com.me.e_commerce_application.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
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
    UsersCredentialsRepository usersCredentialsRepository;
    UsersRepository usersRepository;
    PasswordEncoder passwordEncoder;
    public String registeringCustomerOrVendor(UserRegistrationDao dao){  // admin needs separate registration method
        Users users = usersRepository.findUserByUserName(dao.userName());
        UserCredentials userCredentials = usersCredentialsRepository.findUsersCredentialsByEmail(dao.email());
        if(users == null && userCredentials==null){
            Users newUsers = Users.builder()
                    .userName(dao.userName())
                    .userType(dao.userType()) // need ot add DOB
                    .build();
            UserCredentials newUserCredentials = UserCredentials.builder()
                    .email(dao.email())
                    .password(passwordEncoder.encode(dao.password()))
                    .users(newUsers)
                    .build();
            usersCredentialsRepository.save(newUserCredentials); // hibernate will automatically save newUsers Object
            return "Registration Successfully";

        }
        else{
            return "Users already exist";
        }
    }

    @Override
    @NonNull
    public UserDetails loadUserByUsername(@NonNull String identifier) throws UsernameNotFoundException {
        //Note: in this The identifier(authorized) Should be userName or Email so first should check by userName and if its fail then email(this is Gemini's idea)
        //Note: but i think this is bad practice bcs in a big db there will be a million or more user so gambling like this will cause more time consuming and i also got some weird bugs
        // 1. Try to find the user by UserName first
        Users user = usersRepository.findUserByUserName(identifier);
        
        if (user != null) {
            // Found by username, fetch credentials
            UserCredentials creds = usersCredentialsRepository.findById(user.getId())
                    .orElseThrow(() -> new UsernameNotFoundException("User found but credentials missing"));
            return new User(user.getUserName(), creds.getPassword(), Collections.emptyList());
        }

        // 2. If not found by username, try to find by Email
        UserCredentials creds = usersCredentialsRepository.findUsersCredentialsByEmail(identifier);
        
        if (creds != null) {
            // Found by email
            return new User(creds.getEmail(), creds.getPassword(), Collections.emptyList());
        }

        // 3. If neither found
        throw new UsernameNotFoundException("User not found with username or email: " + identifier);
    }
}
