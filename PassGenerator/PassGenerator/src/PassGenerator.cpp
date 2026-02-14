// =============================================================================
// PassGenerator.cpp — Library Implementation
// =============================================================================
// Contains the PassGen namespace functions extracted from the former Run.cpp.
// This file is compiled into the libPassGenerator shared library (.dll/.so).
// =============================================================================

#include <iostream>
#include <chrono>
#include <iomanip>
#include <ctime>
#include <string>
#include <cstdint>
#include <stdexcept>

#include "Data/Data.h"
#include "include/PassGenerator.h"
#include "include/Security.h"

namespace PassGen {

    std::string generateToken(const Data& user, int& expireSeconds, const std::string& secretKey) {
        auto tp = user.time_sec;
        std::time_t t = std::chrono::system_clock::to_time_t(tp);

        // Converting time-point to epoch seconds
        auto epoch_secs = std::chrono::duration_cast<std::chrono::seconds>(
            tp.time_since_epoch()
        ).count();
        std::cout << "epoch seconds: " << epoch_secs << std::endl;

        auto expire = epoch_secs + expireSeconds;

        // JSON Header
        std::string json_header =
            "{"
                "alg: HS256 ,"
                "type: JWT"
            "}";
        std::string json_payload =
            "{"
                "sub: 1234567890,"
                "email :" + user.e_mail + " ,"
                "exp: " + std::to_string(expire) +
            "}";

        // Base64 encoding
        std::string encoded_string_header  = encodingProcess(json_header);
        std::string encoded_string_payload = encodingProcess(json_payload);

        // Assemble unsigned token, sign with HMAC-SHA256
        std::string unsigned_token = encoded_string_header + "." + encoded_string_payload;
        std::string encryptedData  = hmac_sha256(unsigned_token, secretKey);
        std::string jwt = unsigned_token + "." + encryptedData;
        return jwt;
    }

    bool decodeToken(std::string& token, std::string& header, std::string& payload) {
        size_t index_1st_dot = token.find('.');
        size_t index_2nd_dot = token.rfind('.');

        if (index_1st_dot == std::string::npos || index_2nd_dot == std::string::npos) {
            return false;
        }

        std::string header_encoded  = token.substr(0, index_1st_dot);
        std::string payload_encoded = token.substr(
            index_1st_dot + 1,
            index_2nd_dot - (index_1st_dot + 1)
        );

        decodingProcess(header_encoded, header);
        decodingProcess(payload_encoded, payload);

        return true;
    }

} // namespace PassGen
