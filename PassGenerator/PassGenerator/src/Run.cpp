#include <iostream>
#include "Data/Data.h"
#include <chrono>
#include <iomanip>
#include <ctime>

void dataToString(const Data& data) {
    auto tp = data.time_sec;
    std::time_t t = std::chrono::system_clock::to_time_t(tp);

    std::tm tm;
#if defined(_MSC_VER)
    // MSVC: use the "secure" thread-safe API
    localtime_s(&tm, &t);
#else
    // POSIX: localtime is thread-unsafe; use localtime_r if available
    tm = *std::localtime(&t);
#endif

    auto ms = std::chrono::duration_cast<std::chrono::milliseconds>(tp.time_since_epoch()) % 1000;
    std::cout << std::put_time(&tm, "%Y-%m-%d %H:%M:%S")
              << '.' << std::setfill('0') << std::setw(3) << ms.count() << '\n';

    auto epoch_secs = std::chrono::duration_cast<std::chrono::seconds>(tp.time_since_epoch()).count();
    std::cout << "epoch seconds: " << epoch_secs << std::endl;
}

int main() {
    Data data("Lol@email.com");
    dataToString(data);
}