#include <iostream>
#include "Data/Data.h"
#include <chrono>
#include <iomanip>
#include <ctime>
#include <string>
#include"include/PassGenerator.h"
#include<cstdint>
#include"include/Security.h"
#include<stdexcept>

namespace PassGen {
        std::string generateToken(const Data& user, int& expireSeconds, const std::string& secretKey) {
            auto tp = user.time_sec;
            std::time_t t = std::chrono::system_clock::to_time_t(tp); //Converts the high-level C++ "Time Point" into a time_t (an integer representing seconds).

            // converting to seconds
            auto epoch_secs = std::chrono::duration_cast<std::chrono::seconds>(tp.time_since_epoch()).count();
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
            // encoding
            std::string encoded_string_header = encodingProcess(json_header);
            std::string encoded_string_payload = encodingProcess(json_payload);

            std::string unsigned_token = encoded_string_header + "." + encoded_string_payload;
            std::string encryptedData = hmac_sha256(unsigned_token, secretKey);
            std::string jwt = unsigned_token + "." + encryptedData;
            return  jwt;
        }

        bool decodeToken(std::string& token, std::string& header, std::string& payload) {

            size_t index_1st_dot = token.find('.');
            size_t index_2nd_dot = token.rfind('.');
            if (index_1st_dot == std::string::npos || index_2nd_dot == std::string::npos ) {
                return false;
            }
            std::string header_encoded = token.substr(0, index_1st_dot);
            std::string payload_encoded = token.substr(index_1st_dot + 1,  index_2nd_dot - (index_1st_dot+1));
            //std::cout << header_encoded << std::endl;
            //std::cout << payload_encoded << std::endl;
            
            decodingProcess(header_encoded, header),
            decodingProcess(payload_encoded, payload);

            return true;


        }
}

//int main() {
//    Data data("Lol@email.com");
//    int one_year = 31556926;
//    std::string key = "very secure";
//    std::string jwt = PassGen::generateToken(data, one_year, key);
//    std::cout << jwt << std::endl;
//
//    std::string header;
//    std::string payload;
//   auto decoded_pair =  PassGen::decodeToken(jwt, header, payload);
//   std::cout <<  decoded_pair << std::endl;
//    std::cout << header<< "\n" << payload << std::endl;
//
//}