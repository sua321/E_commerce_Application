#include<iostream>
#include"include/PassGenerator.h"
#include<string>
#include<print>
#include"include/Security.h"
void run() {
	std::string credentials = "---";
	std::string encodedString = encodingProcess(credentials);
	std::array<char, 3> out;
	std::array<uint8_t, 4> encoded = { static_cast<uint8_t>(encodedString[0]), static_cast<uint8_t>(encodedString[1]), static_cast<uint8_t>(encodedString[2]), static_cast<uint8_t>(encodedString[3]) }; // std::copy_n is easy way
	bitwiseDecoding(encoded, out);
	std::cout << credentials << "\n" << encodedString << std::endl;
	for (int i = 0; i < 3; i++) {
		std::cout << out[i];
	}


	// Test values
	std::string test_key = "key";
	std::string test_data = "The quick brown fox jumps over the lazy dog";
	std::cout << "HMAC-SHA256: " << hmac_sha256(test_key, test_data) << std::endl;

}

//int main() {
//	run();
//}