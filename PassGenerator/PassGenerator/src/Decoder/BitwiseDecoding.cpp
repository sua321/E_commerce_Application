#include<cstdint>
#include<iostream>
#include<bitset>
#include"../include/PassGenerator.h"
void bitwiseDecoding() {
	uint8_t c1 = lookup_table[static_cast<int>('T')];
	uint8_t c2 = lookup_table[static_cast<int>('W')];
	uint8_t c3 = lookup_table[static_cast<int>('F')];
	uint8_t c4 = lookup_table[static_cast<int>('u')];

	uint32_t buffer;

	buffer = (c1 << 18) | (c2 << 12) | (c3 << 6) | c4 ;

    uint32_t mask = 0xFF;

	//std::cout << std::bitset<32>(buffer) << std::endl;

	uint8_t b1 = (buffer >> 16) & mask;
	uint8_t b2 = (buffer >> 8) & mask;
	uint8_t b3 = (buffer & mask);

	std::cout << (char)b1 << ", " << (char)b2 << ", " << (char)b3 << ", " << "end" << std::endl;

}
int main() {
	bitwiseDecoding();
 }
