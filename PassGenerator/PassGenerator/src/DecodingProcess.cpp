#include<iostream>
#include<string>
#include"include/PassGenerator.h"
#include<array>
#include<cstdint>

void decodingProcess(std::string& encoded, std::string& credentials) {
	//removing "="
	if (encoded[encoded.size()-1] == '=') {  //note: the allocated(while initalizing) size wont change
		encoded[encoded.size()-1] = '\0';		// but to change size you can use pop_back() or erase() or resize() or encoded.shrink_to_fit()
		if (encoded[encoded.size()-2] == '=') // for now i dosnt need to use these but for larger userbase usage i need to clear those empty memory
			encoded[encoded.size()-2] = 0;
	}
	//loop container for the letters
	std::array<uint8_t, 4> letters;
	std::array<char, 3> words;

	// We check (i + 3) to ensure the whole block of 4 exists
	for (int i = 0; (i+3) < encoded.size() ; i += 4) {
		letters[0] = encoded[i];
		letters[1] = encoded[i+1];
		letters[2] = encoded[i+2];
		letters[3] = encoded[i+3];
		bitwiseDecoding(letters, words);
		credentials += {words[0], words[1], words[2]};

		// or
		/*if (words[0] != '\0') credentials += words[0]; // but not necessary bcs the words already decleared with 3 reseverd space
		if (words[1] != '\0') credentials += words[1];
		if (words[2] != '\0') credentials += words[2];*/
		
		
	}
	
}

//int main() {
//	std::string encoded = "SGVsbG8=";
//	std::string credentials;
//	credentials.reserve(encoded.size());
//	decodingProcess(encoded, credentials);
//	std::cout << credentials << std::endl;
//}