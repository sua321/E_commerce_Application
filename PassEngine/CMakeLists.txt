cmake_minimum_required(VERSION 3.10)

# Project name
project(PassEngine VERSION 1.0)

# Set C++ standard
set(CMAKE_CXX_STANDARD 21)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

# Output directories
set(CMAKE_RUNTIME_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR}/bin)
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR}/lib)

# Include directories
include_directories(${PROJECT_SOURCE_DIR}/include)

# Source files
set(SOURCES
    src/main.cpp
    src/license_generator.cpp
    src/encryption.cpp
    src/pass_validator.cpp
)

# Header files (optional, for IDE organization)
set(HEADERS
    include/license_generator.h
    include/encryption.h
    include/pass_validator.h
)

# Create executable
add_executable(pass_engine ${SOURCES} ${HEADERS})

# If you need OpenSSL for encryption (optional)
# find_package(OpenSSL REQUIRED)
# target_link_libraries(pass_engine PRIVATE OpenSSL::SSL OpenSSL::Crypto)

# Compiler warnings
if(MSVC)
    target_compile_options(pass_engine PRIVATE /W4)
else()
    target_compile_options(pass_engine PRIVATE -Wall -Wextra -pedantic)
endif()

# Installation rules (optional)
install(TARGETS pass_engine DESTINATION bin)