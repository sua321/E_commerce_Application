package com.me.e_commerce_application.services;

import com.me.e_commerce_application.daos.userDaos.UserRegistrationDao;
import com.me.e_commerce_application.models.Users;
import com.me.e_commerce_application.models.sub_dependencies.UserCredentials;
import com.me.e_commerce_application.repositories.UsersCredentialsRepository;
import com.me.e_commerce_application.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.regex.Pattern;

@AllArgsConstructor
@Service
public class UserRegistrationAndLoginService implements UserDetailsService {
    private final UsersCredentialsRepository usersCredentialsRepository;
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final Pattern emailPattern;

    public ResponseEntity<?> registeringCustomerOrVendor(UserRegistrationDao dao) {  // admin needs separate registration method
        try{
            Users users = usersRepository.findUserByUserName(dao.userName());
            UserCredentials userCredentials = usersCredentialsRepository.findUsersCredentialsByEmail(dao.email());
            if (users == null && userCredentials == null) {
                Users newUsers = Users.builder()
                        .userName(dao.userName())
                        .userType(dao.userType()) // need ot add DOB
                        .fullName(dao.fullName())
                        .dob(dao.DOB())
                        .build();
                UserCredentials newUserCredentials = UserCredentials.builder()
                        .email(dao.email())
                        .password(passwordEncoder.encode(dao.password()))
                        .users(newUsers)
                        .build();
                usersCredentialsRepository.save(newUserCredentials); // hibernate will automatically save newUsers Object
                return ResponseEntity.ok().body("Registration Successful");

            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Users already exist");
            }
        }
        catch (Exception e){
            System.out.println("registration" + e.getMessage());
            return ResponseEntity.badRequest().body("Something went wrong in Registration Service");
        }
    }

    @Override
    @NonNull
    //Ok i fixed Gemini's version Performance issue
    public UserDetails loadUserByUsername(@NonNull String identifier) throws UsernameNotFoundException {

        //Checking, is identifier an email?
        if (emailPattern.matcher(identifier).matches()) {
            UserCredentials creds = usersCredentialsRepository.findUsersCredentialsByEmail(identifier);

            if (creds != null) {
                // Found by email
                return new User(creds.getEmail(), creds.getPassword(), Collections.emptyList());
            }
            throw new UsernameNotFoundException("User not found with  email: " + identifier);

        }
        //Try to find the user by UserName
           Users user = usersRepository.findUserByUserName(identifier);
        if (user != null) {
            // Found by username, fetch credentials
            UserCredentials creds = usersCredentialsRepository.findById(user.getId())
                    .orElseThrow(() -> new UsernameNotFoundException("User found but something went wrong"));
            return new User(user.getUserName(), creds.getPassword(), Collections.emptyList());
        }

        // 3. If neither found
        throw new UsernameNotFoundException("User not found with username: " + identifier);

    }
}