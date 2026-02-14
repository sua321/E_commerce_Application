// =============================================================================
// JavaBridge.cpp — JNA / extern "C" Bridge
// =============================================================================
// Exposes the PassGen C++ library to Java (Spring Boot) via JNA.
//
// How it works:
//   1. Java loads the DLL with JNA:  Native.load("libPassGenerator", ...)
//   2. JNA calls the plain-C functions exported below.
//   3. Each bridge function delegates to the real C++ PassGen implementation.
//
// String ownership:
//   - generate_token() returns a heap-allocated C-string.
//   - The Java side MUST call free_string() when done to avoid memory leaks.
// =============================================================================

#include <cstring>   // std::strcpy, std::strlen
#include <cstdlib>   // std::malloc, std::free
#include <string>

#include "Data/Data.h"
#include "include/PassGenerator.h"
#include "include/Security.h"

// ---- Windows DLL export macro ------------------------------------------------
#ifdef _WIN32
    #define EXPORT __declspec(dllexport)
#else
    #define EXPORT __attribute__((visibility("default")))
#endif

// Forward-declare the PassGen namespace functions
namespace PassGen {
    std::string generateToken(const Data& user, int& expireSeconds, const std::string& secretKey);
    bool decodeToken(std::string& token, std::string& header, std::string& payload);
}

// ---- Helper: copy a std::string onto the heap as a C-string ------------------
static char* to_heap_cstr(const std::string& s) {
    char* buf = static_cast<char*>(std::malloc(s.size() + 1));
    if (buf) {
        std::memcpy(buf, s.c_str(), s.size() + 1);
    }
    return buf;
}

// ==============================================================================
// Exported C API
// ==============================================================================
extern "C" {

    /// Generate a JWT token.
    ///
    /// @param email          User e-mail address (UTF-8 C-string).
    /// @param expireSeconds  Token lifetime in seconds.
    /// @param secretKey      HMAC secret key (UTF-8 C-string).
    /// @return Heap-allocated JWT string. Caller MUST call free_string().
    EXPORT char* generate_token(const char* email, int expireSeconds, const char* secretKey) {
        Data user(email);
        std::string key(secretKey);
        std::string jwt = PassGen::generateToken(user, expireSeconds, key);
        return to_heap_cstr(jwt);
    }

    /// Decode a JWT token and return the payload portion.
    ///
    /// @param token  The full JWT string (header.payload.signature).
    /// @return Heap-allocated decoded payload, or nullptr on failure.
    ///         Caller MUST call free_string().
    EXPORT char* decode_token_payload(const char* token) {
        std::string tok(token);
        std::string header;
        std::string payload;
        bool ok = PassGen::decodeToken(tok, header, payload);
        if (!ok) return nullptr;
        return to_heap_cstr(payload);
    }

    /// Decode a JWT token and return the header portion.
    ///
    /// @param token  The full JWT string.
    /// @return Heap-allocated decoded header, or nullptr on failure.
    ///         Caller MUST call free_string().
    EXPORT char* decode_token_header(const char* token) {
        std::string tok(token);
        std::string header;
        std::string payload;
        bool ok = PassGen::decodeToken(tok, header, payload);
        if (!ok) return nullptr;
        return to_heap_cstr(header);
    }

    /// Free a string previously returned by generate_token / decode_token_*.
    /// Call this from Java to prevent memory leaks.
    EXPORT void free_string(char* ptr) {
        std::free(ptr);
    }

} // extern "C"
