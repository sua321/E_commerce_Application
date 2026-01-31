#include<cstdint>
#include<iostream>
#include<bitset>
#include"../include/PassGenerator.h"
#include<array>
void bitwiseDecoding(std::array<uint8_t,4>& encodedCharacters, std::array<char,3>& words ) {
	uint8_t c1 = lookup_table[static_cast<int>(encodedCharacters[0])];
	uint8_t c2 = lookup_table[static_cast<int>(encodedCharacters[1])];
	uint8_t c3 = lookup_table[static_cast<int>(encodedCharacters[2])];
	uint8_t c4 = lookup_table[static_cast<int>(encodedCharacters[3])];

	uint32_t buffer;

	buffer = (c1 << 18) | (c2 << 12) | (c3 << 6) | c4 ;

    uint32_t mask = 0xFF;

	//std::cout << std::bitset<32>(buffer) << std::endl;

	uint8_t b1 = (buffer >> 16) & mask;
	uint8_t b2 = (buffer >> 8) & mask;
	uint8_t b3 = (buffer & mask);

	words[0] = b1;
	words[1] = b2;
	words[2] = b3;

}

