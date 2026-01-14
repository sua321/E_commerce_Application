cmake_minimum_required(VERSION 3.10)
project(PassEngine)

set(CMAKE_CXX_STANDARD 17)

# For REST API option - using cpp-httplib
add_executable(pass_engine
        src/main.cpp
        src/license_generator.cpp
        src/encryption.cpp
)

# Or for library option
# add_library(pass_engine SHARED
#     src/license_generator.cpp
#     src/encryption.cpp
# )