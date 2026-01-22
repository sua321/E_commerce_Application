#include"include/PassGenerator.h"
#include<string>
#include<array>
#include<cstdint>

std::string encodingProcess(std::string credentials) {
	std::string encodedString;
	encodedString.reserve(credentials.size() * 2); // Reserve enough space

	std::array<char, 3> letters;
	std::array<uint8_t, 4> encodedNumbers;

	int realSize = credentials.size();

	for (int i = 0; i < realSize; i += 3) {
		letters.fill(0);
		int charsCount = 0;

		for (int j = 0; j < 3; j++) {
			if (i + j < realSize) {
				letters[j] = credentials[i + j];
				charsCount++;
			}
		}

		bitWiseEncoding(letters, encodedNumbers);
		base64Encoding(encodedNumbers, encodedString);

		// Handle padding logic
		if (charsCount < 3) {
			if (charsCount == 1) {
				encodedString[encodedString.size() - 1] = '=';
				encodedString[encodedString.size() - 2] = '=';
			}
			else if (charsCount == 2) {
				encodedString[encodedString.size() - 1] = '=';
			}
		}
	}
	return encodedString;
}

