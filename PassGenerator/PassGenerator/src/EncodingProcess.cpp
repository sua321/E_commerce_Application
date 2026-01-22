#include"include/PassGenerator.h"
#include<string>
#include<array>
#include<cstdint>

std::string encodingProcess(std::string credentials) {
	int realSize = credentials.size();
	int loopLimit = (realSize / 3) -1;
	int count = 0;

	if (realSize % 3 != 0) {
		loopLimit += 1;
	}
	std::array<char,3> letters;
	std::array<uint8_t, 4> encodedNumbers;
	std::string encodedString;
	encodedString.reserve(100);

	for (int i = 0; i < loopLimit; i++) {
		letters.fill(0);
		for (int j = 0; j < 3; j++) {
			
			if (count == realSize -1) {
				break;
			}
			letters[j] = credentials[count];
				count++;
		}

		bitWiseEncoding(letters,encodedNumbers);
		base64Encoding(encodedNumbers, encodedString);

	}
	return encodedString;;
}

