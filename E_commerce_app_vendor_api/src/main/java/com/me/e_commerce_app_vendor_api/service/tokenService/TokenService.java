package com.me.e_commerce_app_vendor_api.service.tokenService;

import com.sun.jna.Pointer;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    public String createToken(String email) {
        // 1. Call the C++ library
        Pointer ptr = PassGenLibrary.INSTANCE.generate_token(email, 3600, "my_secret_key");

        // 2. Extract the string value from the memory pointer
        String token = ptr.getString(0);

        // 3. IMPORTANT: Free the memory allocated in C++
        PassGenLibrary.INSTANCE.free_string(ptr);

        return token;
    }
}