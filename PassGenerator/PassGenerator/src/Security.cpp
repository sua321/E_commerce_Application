#include <iostream>
#include <string>

// Correct the include path for sha256.h and hmac.h
#include "sha256.h"
#include "hmac.h"

std::string hmac_sha256(const std::string& key, const std::string& data) {
    // This library uses a template for HMAC. 
    // It creates an HMAC using the SHA256 algorithm.
    return hmac<SHA256>(data, key);
}