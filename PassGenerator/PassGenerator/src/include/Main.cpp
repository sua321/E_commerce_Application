#include<iostream>
#include"PassGenerator.h"
#include<string>
int main() {
	std::string credentials = "Man";
	std::string encodedString = encodingProcess(credentials);
	std::cout << "Before Encoding : " << credentials << ", After Encoding" << encodedString << std::endl;
}