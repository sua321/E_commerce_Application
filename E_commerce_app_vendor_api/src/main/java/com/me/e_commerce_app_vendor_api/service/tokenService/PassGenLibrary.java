package com.me.e_commerce_app_vendor_api.service.tokenService;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public interface PassGenLibrary extends Library {
    // Load the DLL (make sure libPassGenerator.dll is in your project root)
    PassGenLibrary INSTANCE = Native.load("libPassGenerator", PassGenLibrary.class);

    // Matches: EXPORT char* generate_token(const char* email, int expireSeconds, const char* secretKey)
    Pointer generate_token(String email, int expireSeconds, String secretKey);

    // Matches: EXPORT char* decode_token_payload(const char* token)
    Pointer decode_token_payload(String token);

    // Matches: EXPORT void free_string(char* ptr)
    void free_string(Pointer ptr);
}
