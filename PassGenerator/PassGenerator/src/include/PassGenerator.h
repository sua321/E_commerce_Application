#pragma once

#ifdef PASSGEN_EXPORTS
    #define PASSGEN_API __declspec(dllexport)
#else
    #define PASSGEN_API __declspec(dllimport)
#endif

#include <array>
#include <string>
#include <cstdint>
#include "../Decoder/LookupTable.h"
#include "../Data/Data.h"

// Original internal/helper functions (exposed if needed, but not requested to be exported explicitly yet, though usually should be if used across DLL boundary)
// Assuming these are internal or already handled, but for cleanliness:
void bitWiseEncoding(std::array<char, 3> (&letters), std::array<uint8_t, 4>& out);
void base64Encoding(std::array< uint8_t, 4> encodedInts, std::string& encodedString);
std::string encodingProcess(std::string credentials);
void bitwiseDecoding(std::array<uint8_t, 4>& encodedCharacters, std::array<char, 3>& words);
void decodingProcess(std::string& encoded, std::string& credentials);

namespace PassGen {
    PASSGEN_API std::string generateToken(const Data& user, int& expireSeconds, const std::string& secretKey);
    PASSGEN_API bool decodeToken(std::string& token, std::string& header, std::string& payload);
}
