# PassGenerator - Modern CMake Build

## Overview

PassGenerator is a C++20 library and executable for encoding and decoding secure passes. It's designed to be used both as a standalone CLI tool and as a shared library that can be integrated into external applications like the E_Commerce_app_vendor_api.

## Project Structure

```
PassGenerator/
├── CMakeLists.txt              # Root CMake configuration
├── .gitignore
├── PassGenerator/              # Main source directory
│   └── src/
│       ├── include/            # Public headers (for Vendor API)
│       │   ├── PassGenerator.h
│       │   └── Security.h
│       ├── Data/               # Data files
│       │   ├── Data.h
│       │   └── time_to_UTC.txt
│       ├── Data fetching/
│       │   ├── DataFetch.h
│       │   └── DataFetch.cpp
│       ├── Decoder/
│       │   ├── BitwiseDecoding.cpp
│       │   └── LookupTable.h
│       ├── Encoders/
│       │   ├── Base64Encoding.cpp
│       │   └── BitWiseEncoding.cpp
│       ├── EncodingProcess.cpp
│       ├── DecodingProcess.cpp
│       ├── Security.cpp
│       ├── Test.cpp
│       └── Run.cpp             # Entry point for executable
└── dependencies/
    ├── hash-library/           # SHA256 implementation
    │   ├── sha256.h
    │   ├── sha256.cpp
    │   ├── hmac.h
    │   └── hash.h
    └── mbedtls/                # (Currently disabled - incomplete)
        ├── include/
        └── library/

```

## Build Targets

The CMake configuration produces **TWO** targets:

### 1. **libPassGenerator** (Shared Library)
- **Output**: `libPassGenerator.dll` (Windows) / `libPassGenerator.so` (Linux)
- **Purpose**: Link against this library from your Vendor API
- **Contains**: All encoding, decoding, data, and security logic
- **Public Headers**: `PassGenerator/src/include/`
  - `PassGenerator.h`
  - `Security.h`

### 2. **PassGenerator** (Executable)
- **Output**: `PassGenerator.exe` (Windows) / `PassGenerator` (Linux)
- **Purpose**: CLI tool for testing or standalone pass generation
- **Links to**: `libPassGenerator.dll`
- **Entry Point**: `Run.cpp`

## Build Instructions

### Prerequisites
- CMake 3.14 or higher
- C++20 compatible compiler (MSVC 2019+, GCC 10+, Clang 10+)
- Windows: Visual Studio 2019 or later
- Linux: GCC/Clang with C++20 support

### Windows (Visual Studio)

```powershell
# Create build directory
mkdir build
cd build

# Configure CMake
cmake ..

# Build Release configuration
cmake --build . --config Release

# Build Debug configuration (optional)
cmake --build . --config Debug
```

### Linux/macOS

```bash
# Create build directory
mkdir build
cd build

# Configure CMake
cmake -DCMAKE_BUILD_TYPE=Release ..

# Build
cmake --build .
```

## Output Files

After a successful build, you'll find:

### Release Build (`build/Release/` on Windows, `build/` on Linux)
- `libPassGenerator.dll` / `libPassGenerator.so` - Shared library
- `libPassGenerator.lib` - Import library (Windows only)
- `PassGenerator.exe` / `PassGenerator` - Executable
- `hash-library.lib` - Static hash library (internal)
- `time_to_UTC.txt` - Data file (copied from src/Data/)

## Integrating with Vendor API

### Step 1: Link the Library

In your `E_Commerce_app_vender_api` CMakeLists.txt:

```cmake
# Add PassGenerator library
add_library(PassGenerator SHARED IMPORTED)
set_target_properties(PassGenerator PROPERTIES
    IMPORTED_LOCATION "path/to/libPassGenerator.dll"
    IMPORTED_IMPLIB "path/to/libPassGenerator.lib"  # Windows only
)

# Add include directories
target_include_directories(YourVendorTarget PRIVATE
    "path/to/PassGenerator/PassGenerator/src/include"
)

# Link against the library
target_link_libraries(YourVendorTarget PRIVATE PassGenerator)
```

### Step 2: Include Headers

In your C++ source files:

```cpp
#include "PassGenerator.h"
#include "Security.h"

// Use the functions from the library
```

### Step 3: Deploy

Ensure `libPassGenerator.dll` is in the same directory as your executable or in the system PATH.

## Dependencies

### hash-library (Included)
- **Purpose**: SHA256 hashing implementation
- **Status**: ✅ Fully integrated
- **Location**: `dependencies/hash-library/`
- **Build Type**: Static library
- **Author**: Stephan Brumme

### mbedtls (Disabled)
- **Status**: ⚠️ Currently disabled
- **Reason**: Incomplete distribution (missing `tf-psa-crypto` components)
- **Alternative**: hash-library provides SHA256
- **Future**: If full mbedtls is needed, install via:
  - **vcpkg**: `vcpkg install mbedtls`
  - **Git Submodule**: `git submodule add https://github.com/Mbed-TLS/mbedtls.git dependencies/mbedtls`

## Technical Details

### C++ Standard
- **Required**: C++20
- **Reason**: Uses `consteval` for compile-time lookup table generation (`LookupTable.h`)

### Build System
- **Modern CMake**: Target-based architecture
- **Export Control**: Symbols automatically exported on Windows (`WINDOWS_EXPORT_ALL_SYMBOLS`)
- **Installation**: Configured with `GNUInstallDirs` for cross-platform deployment

### Dependencies Linking
```
PassGenerator.exe
    └─→ libPassGenerator.dll
            └─→ hash-library (static)
```

## Common Issues

### Issue: `consteval` not recognized
**Solution**: Ensure C++20 support. The CMakeLists.txt sets `CMAKE_CXX_STANDARD 20`.

### Issue: Missing `time_to_UTC.txt`
**Solution**: The file is automatically copied during build. If missing, check `PassGenerator/src/Data/time_to_UTC.txt` exists.

### Issue: Cannot find `PassGenerator.h`
**Solution**: Add `PassGenerator/src/include` to your include directories.

## Missing Files Analysis

Based on the project structure, here are potential files you might want to add:

### Recommended Additions:
1. **`dependencies/mbedtls/`** - Complete installation
   - Download from: https://github.com/Mbed-TLS/mbedtls
   - Or use vcpkg: `vcpkg install mbedtls`

2. **`.gitignore`** - ✅ Created

3. **`README.md`** - ✅ This file

4. **`LICENSE`** - Add your license file

5. **Unit Tests** - Create a `tests/` directory with:
   - `CMakeLists.txt`
   - Test files using Catch2/GoogleTest

6. **CI/CD Configuration**:
   - `.github/workflows/build.yml` (GitHub Actions)
   - `.gitlab-ci.yml` (GitLab CI)

## Installation (Optional)

To install the library system-wide:

```bash
# From build directory
cmake --install . --prefix /path/to/install
```

This will install:
- Headers to `<prefix>/include/`
- Libraries to `<prefix>/lib/`
- Executables to `<prefix>/bin/`

## Build Summary

### ✅ Successfully Configured Dependencies:
1. **hash-library**
   - Built as static library
   - Provides SHA256 hashing
   - Sources: `sha256.cpp`

### ✅ Target Outputs:
1. **libPassGenerator.dll** (43 KB)
   - Shared library containing all PassGenerator logic
   - Links hash-library statically
   - Exports public API from `include/`

2. **PassGenerator.exe** (33 KB)
   - Standalone executable
   - Links to libPassGenerator.dll
   - Built from Run.cpp

3. **Supporting Files**:
   - `libPassGenerator.lib` (62 KB) - Import library
   - `hash-library.lib` (77 KB) - Static hash library
   - `time_to_UTC.txt` - Data file

### 🔧 Configuration Details:
- **C++ Standard**: C++20
- **Build Configuration**: Release
- **Platform**: Windows (MSVC 19.50)
- **Windows SDK**: 10.0.26100.0
- **Build System**: MSBuild 18.0.5

---

**Built with Modern CMake** | **C++20** | **Target-Based Architecture**
