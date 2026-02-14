// =============================================================================
// Test.cpp — Test Executable
// =============================================================================
// Standalone executable that links against libPassGenerator to verify that
// token generation and decoding work correctly.
// =============================================================================

#include <iostream>
#include <string>

#include "Data/Data.h"
#include "include/PassGenerator.h"

// Header already included


int main() {
    // --- Generate a JWT ---
    Data data("Lol@email.com");
    int one_year = 31556926;
    std::string key = "very secure";
    std::string jwt = PassGen::generateToken(data, one_year, key);
    std::cout << "Generated JWT:\n" << jwt << std::endl;

    // --- Decode the JWT ---
    std::string header;
    std::string payload;
    bool ok = PassGen::decodeToken(jwt, header, payload);
    std::cout << "\nDecode success: " << ok << std::endl;
    std::cout << "Header:  " << header  << std::endl;
    std::cout << "Payload: " << payload << std::endl;

    return 0;
}
