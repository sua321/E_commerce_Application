#pragma once
#pragma once
#include <string>

// This is just a "promise" that the function exists somewhere
std::string hmac_sha256(const std::string& key, const std::string& data);
