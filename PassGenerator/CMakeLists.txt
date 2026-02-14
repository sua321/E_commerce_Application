cmake_minimum_required(VERSION 3.14)

project(PassGenerator VERSION 1.0 LANGUAGES CXX C)

set(CMAKE_CXX_STANDARD 20)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

# =============================================================================
# NOTE: mbedtls dependency is DISABLED
# =============================================================================
# The mbedtls in dependencies/ is incomplete (missing tf-psa-crypto).
# Since hash-library already provides SHA256, we don't need mbedtls for this
# build.  If the Vendor API specifically needs mbedtls in the future, install
# the full mbedtls distribution via vcpkg or git submodule.

# =============================================================================
# Dependency: hash-library  (static)
# =============================================================================
message(STATUS "Configuring hash-library...")
file(GLOB HASH_LIB_SOURCES "${CMAKE_CURRENT_SOURCE_DIR}/dependencies/hash-library/*.cpp")

add_library(hash-library STATIC ${HASH_LIB_SOURCES})
target_include_directories(hash-library PUBLIC
    "${CMAKE_CURRENT_SOURCE_DIR}/dependencies/hash-library"
)

# =============================================================================
# Target 1: libPassGenerator  (SHARED library  →  .dll / .so)
# =============================================================================
# Sources: All .cpp files in src/ EXCEPT Test.cpp
#   - PassGenerator.cpp   (PassGen namespace implementation)
#   - JavaBridge.cpp      (extern "C" bridge for JNA)
#   - Security.cpp        (HMAC-SHA256 wrapper)
#   - EncodingProcess.cpp, DecodingProcess.cpp
#   - Encoders/*.cpp, Decoder/*.cpp
# =============================================================================
file(GLOB_RECURSE PASS_GEN_SOURCES "${CMAKE_CURRENT_SOURCE_DIR}/PassGenerator/src/*.cpp")

# Exclude the test executable and legacy Run.cpp from the library
list(FILTER PASS_GEN_SOURCES EXCLUDE REGEX "Test\\.cpp$")
list(FILTER PASS_GEN_SOURCES EXCLUDE REGEX "Run\\.cpp$")

add_library(libPassGenerator SHARED ${PASS_GEN_SOURCES})

target_include_directories(libPassGenerator
    PUBLIC
        "${CMAKE_CURRENT_SOURCE_DIR}/PassGenerator/src/include"
    PRIVATE
        "${CMAKE_CURRENT_SOURCE_DIR}/PassGenerator/src"
)

target_link_libraries(libPassGenerator PRIVATE hash-library)

# Define PASSGEN_EXPORTS when building the library so the header uses __declspec(dllexport)
target_compile_definitions(libPassGenerator PRIVATE PASSGEN_EXPORTS)

# Windows: export all symbols automatically so JNA / consumers can see them
if(WIN32)
    set_target_properties(libPassGenerator PROPERTIES WINDOWS_EXPORT_ALL_SYMBOLS ON)
endif()

# =============================================================================
# Target 2: PassGenerator_Test  (EXECUTABLE)
# =============================================================================
add_executable(PassGenerator_Test "${CMAKE_CURRENT_SOURCE_DIR}/PassGenerator/src/Test.cpp")

target_include_directories(PassGenerator_Test PRIVATE
    "${CMAKE_CURRENT_SOURCE_DIR}/PassGenerator/src"
)

target_link_libraries(PassGenerator_Test PRIVATE libPassGenerator)

# =============================================================================
# Post-Build: Resource Copying
# =============================================================================
set(TIME_DATA "${CMAKE_CURRENT_SOURCE_DIR}/PassGenerator/src/Data/time_to_UTC.txt")
if(EXISTS "${TIME_DATA}")
    add_custom_command(TARGET PassGenerator_Test POST_BUILD
        COMMAND ${CMAKE_COMMAND} -E copy_if_different
        "${TIME_DATA}"
        "$<TARGET_FILE_DIR:PassGenerator_Test>/time_to_UTC.txt"
    )
endif()

# =============================================================================
# Installation  (for Vendor API / deployment)
# =============================================================================
include(GNUInstallDirs)

install(TARGETS libPassGenerator PassGenerator_Test
    LIBRARY DESTINATION ${CMAKE_INSTALL_LIBDIR}
    ARCHIVE DESTINATION ${CMAKE_INSTALL_LIBDIR}
    RUNTIME DESTINATION ${CMAKE_INSTALL_BINDIR}
)

install(DIRECTORY "${CMAKE_CURRENT_SOURCE_DIR}/PassGenerator/src/include/"
    DESTINATION ${CMAKE_INSTALL_INCLUDEDIR}
)
