#include<iostream>
#include"include/PassGenerator.h"
#include<string>
#include<print>
int main() {
	std::string credentials = "Man";
	std::string encodedString = encodingProcess(credentials);
	std::cout << "Before Encoding : " << credentials << ", After Encoding" << encodedString << std::endl;
	int count = 0;
	for (int i = 0; i < lookup_table.size(); i++) { //lookup_table is in include/PassGenerator.h
		std::cout << i << " : " << lookup_table[i] << std::endl;
		if (lookup_table[i] != -1) {
			count++;
		}
	}
	std::cout << "Count : " << count << std::endl;
}