//#pragma once
//#include <array>
//#include <cstdint>
//#include <string_view>
//
//namespace encoders {
//
//    // URL-safe Base64 alphabet (RFC 4648 §5)
//    inline constexpr std::string_view Base64WebAlphabet =
//        "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
//        "abcdefghijklmnopqrstuvwxyz"
//        "0123456789-_";
//
//    // Build a reverse lookup table (char -> index), -1 means invalid
//    inline std::array<int8_t, 256> buildBase64WebReverse() {
//        std::array<int8_t, 256> rev;
//        rev.fill(-1);
//        for (int i = 0; i < 64; ++i) {
//            unsigned char c = static_cast<unsigned char>(Base64WebAlphabet[i]);
//            rev[c] = static_cast<int8_t>(i);
//        }
//        return rev;
//    }
//
//    inline const std::array<int8_t, 256> Base64WebReverse = buildBase64WebReverse();
//
//} // namespace encoders