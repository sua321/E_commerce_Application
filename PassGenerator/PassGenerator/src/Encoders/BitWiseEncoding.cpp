#include<cstdint>
#include<bitset>
#include<iostream>

static void bitWiseEncoding() {

	uint8_t c1 = 'M'; // unsigned 8bit int 
	uint8_t c2 = 'a'; // 'c' mean character
	uint8_t c3 = 'n'; // defautl the data will be hold as bits

	uint32_t buffer; //32bit unsigned int to hold 24 bit of characters

	//using bitwise operater(or) and left shifting(<<) to move the 8bit chars to their position
	
	buffer = (c1 << 16) | (c2 << 8) | (c3);

	std::cout << std::bitset<24>(buffer) << std::endl;

	//creating mask to filter(...00111111 in 32bit) out the buffer to get 6 bits of chunks

	uint32_t mask = 0x3F;

	//moving the buffer to filter out 

	uint8_t b64c_1 = (buffer >> 18) & mask; // "b64c_1" mean base64char_1
	uint8_t b64c_2 = (buffer >> 12) & mask;
	uint8_t b64c_3 = (buffer >> 6) & mask;
	uint8_t b64c_4 = buffer & mask;

	std::cout << std::bitset<8>(b64c_1) << " " 
		<< std::bitset<8>(b64c_2) << " " 
		<< std::bitset<8>(b64c_3) << " "
		<< std::bitset<8>(b64c_4) 
		<< std::endl;
	
}

int main() {
	bitWiseEncoding();
}