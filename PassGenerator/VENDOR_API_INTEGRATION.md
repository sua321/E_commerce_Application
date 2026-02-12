# PassGenerator - Vendor API Integration Example

This is a quick reference for integrating `libPassGenerator` into your E_Commerce_app_vendor_api.

## Quick Integration Steps

### 1. Copy Required Files

Copy these files to your Vendor API project:

```
From: PassGenerator/build/Release/
  → libPassGenerator.dll
  → libPassGenerator.lib (Windows only)
  → time_to_UTC.txt (if needed)

From: PassGenerator/PassGenerator/src/include/
  → PassGenerator.h
  → Security.h
```

### 2. Update Your CMakeLists.txt

```cmake
# Find the PassGenerator library
find_library(PASSGENERATOR_LIB
    NAMES PassGenerator libPassGenerator
    HINTS "${CMAKE_CURRENT_SOURCE_DIR}/vendor/PassGenerator/lib"
)

# Import the library
add_library(PassGenerator::Library SHARED IMPORTED)
set_target_properties(PassGenerator::Library PROPERTIES
    IMPORTED_LOCATION "${PASSGENERATOR_LIB}"
)

if(WIN32)
    set_target_properties(PassGenerator::Library PROPERTIES
        IMPORTED_IMPLIB "${CMAKE_CURRENT_SOURCE_DIR}/vendor/PassGenerator/lib/libPassGenerator.lib"
    )
endif()

# Add include directory
target_include_directories(YourVendorAPITarget PRIVATE
    "${CMAKE_CURRENT_SOURCE_DIR}/vendor/PassGenerator/include"
)

# Link the library
target_link_libraries(YourVendorAPITarget PRIVATE
    PassGenerator::Library
)
```

### 3. Example Usage in Your Code

```cpp
#include "PassGenerator.h"
#include "Security.h"
#include <iostream>

int main() {
    // Example: Use PassGenerator functions
    // (Replace with actual function signatures from your headers)
    
    try {
        // Initialize or use PassGenerator functionality
        // std::string encoded = encodePass(userData);
        // std::string decoded = decodePass(encodedData);
        
        std::cout << "PassGenerator integration successful!" << std::endl;
    }
    catch (const std::exception& ex) {
        std::cerr << "Error: " << ex.what() << std::endl;
        return 1;
    }
    
    return 0;
}
```

### 4. Deployment Checklist

- [ ] `libPassGenerator.dll` is in the same directory as your executable
- [ ] OR `libPassGenerator.dll` is in a directory listed in your system PATH
- [ ] `time_to_UTC.txt` is in the working directory (if required by the library)
- [ ] Headers (`PassGenerator.h`, `Security.h`) are accessible during compilation
- [ ] Import library (`libPassGenerator.lib`) is available for linking (Windows)

## Recommended Project Structure

```
E_Commerce_app_vendor_api/
├── CMakeLists.txt
├── src/
│   └── main.cpp
└── vendor/
    └── PassGenerator/
        ├── include/
        │   ├── PassGenerator.h
        │   └── Security.h
        ├── lib/
        │   ├── libPassGenerator.dll
        │   └── libPassGenerator.lib
        └── data/
            └── time_to_UTC.txt
```

## Troubleshooting

### DLL Not Found Error (Windows)
```
Error: The program can't start because libPassGenerator.dll is missing
```

**Solutions**:
1. Copy `libPassGenerator.dll` to the same directory as your `.exe`
2. Add the DLL directory to your PATH environment variable
3. Use `add_custom_command` in CMake to copy the DLL post-build:

```cmake
add_custom_command(TARGET YourVendorAPITarget POST_BUILD
    COMMAND ${CMAKE_COMMAND} -E copy_if_different
        "${PASSGENERATOR_DLL_PATH}"
        "$<TARGET_FILE_DIR:YourVendorAPITarget>"
)
```

### Linker Error: Undefined References
```
Error: undefined reference to `function_from_PassGenerator`
```

**Solutions**:
1. Ensure you're linking against the import library (`.lib` on Windows)
2. Check that function declarations in headers match the library exports
3. Verify the library was built with the same compiler and architecture

### Header Not Found
```
Error: PassGenerator.h: No such file or directory
```

**Solutions**:
1. Add the include directory to `target_include_directories`
2. Use absolute paths or properly reference relative paths
3. Check that header files were copied correctly

## Alternative: Using as a Git Submodule

Instead of copying files, you can include PassGenerator as a submodule:

```bash
cd E_Commerce_app_vendor_api
git submodule add <PassGenerator-repo-url> vendor/PassGenerator
git submodule update --init --recursive
```

Then in your CMakeLists.txt:

```cmake
add_subdirectory(vendor/PassGenerator)
target_link_libraries(YourVendorAPITarget PRIVATE libPassGenerator)
```

This approach automatically builds PassGenerator with your project.

---

**Need Help?** Check the main README.md for detailed build instructions and technical details.
