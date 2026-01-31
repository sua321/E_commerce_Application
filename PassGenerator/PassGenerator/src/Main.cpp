#include<iostream>
#include"include/PassGenerator.h"
#include<string>
#include<print>
void run() {
	std::string credentials = "---";
	std::string encodedString = encodingProcess(credentials);
	std::array<char, 3> out;
	std::array<uint8_t, 4> encoded = { encodedString[0], encodedString[1], encodedString[2], encodedString[3] }; // std::copy_n is easy way
	bitwiseDecoding(encoded, out);
	std::cout << credentials << "\n" << encodedString << std::endl;
	for (int i = 0; i < 3; i++) {
		std::cout << out[i];
	}
}

int main() {
	run();
}