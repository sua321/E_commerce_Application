#pragma once
#include <string>
#include "Data/Data.h" 

// This namespace prevents naming conflicts with the Vendor API
namespace PassGen {

    // Function 1: Create a Token
    // Returns: "Header.Payload.Signature"
    std::string generateToken(const Data& user, int& expireSeconds, const std::string& secretKey);

    // Function 2: Decode a Token
    // Returns: true if format is valid, false if broken
    // Outputs: Fills 'header' and 'payload' by reference
    bool decodeToken(std::string& token, std::string& header, std::string& payload);

}