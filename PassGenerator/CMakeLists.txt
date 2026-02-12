cmake_minimum_required(VERSION 3.14)

project(PassGenerator VERSION 1.0 LANGUAGES CXX C)

set(CMAKE_CXX_STANDARD 20)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

# -----------------------------------------------------------------------------
# NOTE: mbedtls dependency is DISABLED
# -----------------------------------------------------------------------------
# The mbedtls in dependencies/ is incomplete (missing tf-psa-crypto).
# Since hash-library already provides SHA256, we don't need mbedtls for this build.
# If the Vendor API specifically needs mbedtls in the future, install the full
# mbedtls distribution via vcpkg or git submodule.

# -----------------------------------------------------------------------------
# Dependency: hash-library
# -----------------------------------------------------------------------------
message(STATUS "Configuring hash-library...")
file(GLOB HASH_LIB_SOURCES "${CMAKE_CURRENT_SOURCE_DIR}/dependencies/hash-library/*.cpp")

add_library(hash-library STATIC ${HASH_LIB_SOURCES})
target_include_directories(hash-library PUBLIC 
    "${CMAKE_CURRENT_SOURCE_DIR}/dependencies/hash-library"
)

# -----------------------------------------------------------------------------
# Main Target: libPassGenerator (Shared Library)
# -----------------------------------------------------------------------------
# Gather all sources in src, but exclude executables
file(GLOB_RECURSE PASS_GEN_SOURCES "${CMAKE_CURRENT_SOURCE_DIR}/PassGenerator/src/*.cpp")

# Exclude Run.cpp and Test.cpp from the library
list(FILTER PASS_GEN_SOURCES EXCLUDE REGEX "Run.cpp$")
list(FILTER PASS_GEN_SOURCES EXCLUDE REGEX "Test.cpp$")

add_library(libPassGenerator SHARED ${PASS_GEN_SOURCES})

# Include directories
# PUBLIC: Vendors using this lib need 'include/PassGenerator.h' etc.
# PRIVATE: Internal sources need 'Data/', 'Encoders/', etc.
target_include_directories(libPassGenerator
    PUBLIC 
        "${CMAKE_CURRENT_SOURCE_DIR}/PassGenerator/src/include"
    PRIVATE
        "${CMAKE_CURRENT_SOURCE_DIR}/PassGenerator/src"
)

target_link_libraries(libPassGenerator PRIVATE hash-library)

# Windows: Ensure symbols are exported if using declspec, or just default visibility
if(WIN32)
    set_target_properties(libPassGenerator PROPERTIES WINDOWS_EXPORT_ALL_SYMBOLS ON)
endif()

# -----------------------------------------------------------------------------
# Executable: PassGenerator (Run.cpp)
# -----------------------------------------------------------------------------
add_executable(PassGenerator "${CMAKE_CURRENT_SOURCE_DIR}/PassGenerator/src/Run.cpp")

target_link_libraries(PassGenerator PRIVATE libPassGenerator)

# -----------------------------------------------------------------------------
# Post-Build: Resource Copying
# -----------------------------------------------------------------------------
# Copy time_to_UTC.txt if it exists
set(TIME_DATA "${CMAKE_CURRENT_SOURCE_DIR}/PassGenerator/src/Data/time_to_UTC.txt")
if(EXISTS "${TIME_DATA}")
    add_custom_command(TARGET PassGenerator POST_BUILD
        COMMAND ${CMAKE_COMMAND} -E copy_if_different
        "${TIME_DATA}"
        "$<TARGET_FILE_DIR:PassGenerator>/time_to_UTC.txt"
    )
endif()

# -----------------------------------------------------------------------------
# Installation (For Vendor API usage)
# -----------------------------------------------------------------------------
include(GNUInstallDirs)

install(TARGETS libPassGenerator PassGenerator
    LIBRARY DESTINATION ${CMAKE_INSTALL_LIBDIR}
    ARCHIVE DESTINATION ${CMAKE_INSTALL_LIBDIR}
    RUNTIME DESTINATION ${CMAKE_INSTALL_BINDIR}
)

install(DIRECTORY "${CMAKE_CURRENT_SOURCE_DIR}/PassGenerator/src/include/"
    DESTINATION ${CMAKE_INSTALL_INCLUDEDIR}
)
