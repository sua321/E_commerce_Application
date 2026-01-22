#include"../include/PassGenerator.h"
#include<string_view>
#include<array>
#include<string>
#include<cstdint>
void base64Encoding(std::array< uint8_t, 4> encodedInts, std::string& encodedString) {

	constexpr std::string_view base64WebAlphabet = 
		"ABCDEFGHIJKLMNOPQRSTUVWXYZ"
		"abcdefghijklmnopqrstuvwxyz"
		"0123456789-_";

	for (int i = 0; i < 4; i++) {
		uint8_t encodedNumber = encodedInts[i];
		if (!encodedNumber) {
			encodedString += '=';
		}
		encodedString += base64WebAlphabet[encodedNumber];
	}
	
}