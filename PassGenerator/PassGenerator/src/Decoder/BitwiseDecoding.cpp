#include<cstdint>
#include<iostream>
#include<bitset>
void bitwiseDecoding() {
	uint8_t c1 = 'T';
	uint8_t c2 = 'W';
	uint8_t c3 = 'F';
	uint8_t c4 = 'u';

	uint32_t buffer;

	buffer = (c1 << 18) | ((c2 << 2) << 12) | ((c3 << 2) << 6) | (c4 << 2);

	uint32_t mask = 0x100;

	//std::cout << std::bitset<32>(buffer) << std::endl;

	uint8_t b1 = (buffer >> 16) & mask;
	uint8_t b2 = (buffer >> 8) & mask;
	uint8_t b3 = (buffer & mask);

	std::cout << (char)b1 << ", " << (char)b2 << ", " << (char)b3 << ", " << "end" << std::endl;

}
int main() {
	bitwiseDecoding();
 }
