#pragma once
#include<iostream>
#include<cstdint>
#include<array>

consteval std::array<int, 256> lookupTable() {
	constexpr std::string_view alphabet =
		"ABCDEFGHIJKLMNOPQRSTUVWXYZ"
		"abcdefghijklmnopqrstuvwxyz"
		"0123456789-_";

	std::array<int, 256> lookup_table;
	lookup_table.fill(-1);
	for (int i = 0; i < 64; i++) {
		lookup_table[static_cast<uint8_t>(alphabet[i])] = i;
	}

	return lookup_table;
}

inline constexpr std::array<int, 256> lookup_table = lookupTable();
