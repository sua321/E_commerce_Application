#include <iostream>
#include "Data/Data.h"
#include <chrono>
#include <iomanip>
#include <ctime>
#include <string>
#include"include/PassGenerator.h"
#include<cstdint>
#include"include/Security.h"

std::string dataToEncodedString(const Data& data, int& passExpireAt) {
    auto tp = data.time_sec;
    std::time_t t = std::chrono::system_clock::to_time_t(tp); //Converts the high-level C++ "Time Point" into a time_t (an integer representing seconds).

    // converting to seconds
    auto epoch_secs = std::chrono::duration_cast<std::chrono::seconds>(tp.time_since_epoch()).count();
    std::cout << "epoch seconds: " << epoch_secs << std::endl;

    auto expire = epoch_secs + passExpireAt;

    // JSON Header
    std::string json_header =
        "{"
        "alg: HS256 ,"
        "type: JWT"
        "}";
    std::string json_payload =
        "{"
        "sub : 1234567890,"
        "email :" + data.e_mail + ","
        "exp :" + std::to_string(expire) +
        "}";
    // encoding
    std::string encoded_string_header = encodingProcess(json_header);
    std::string encoded_string_payload = encodingProcess(json_payload);

    return encoded_string_header + "." + encoded_string_payload;

}

void EncodedStringToData(){}

int main() {
    Data data("Lol@email.com");
    int one_year = 31556926;
    std::string output = dataToEncodedString(data, one_year);
    std::cout << output << std::endl;

    std::string key = "sampleKey";
    std::string encryptedData = hmac_sha256(key, output);
    std::string jwt = output + "." + encryptedData;
    std::cout << jwt << std::endl;
}