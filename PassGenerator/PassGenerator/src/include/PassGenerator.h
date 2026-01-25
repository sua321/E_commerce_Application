#pragma once
#include <array>
#include<string>
#include<cstdint>
#include "../Decoder/LookupTable.h"
void bitWiseEncoding(std::array<char, 3> (&letters), std::array<uint8_t, 4>& out);
void base64Encoding(std::array< uint8_t, 4> encodedInts, std::string& encodedString);
std::string encodingProcess(std::string credentials);
