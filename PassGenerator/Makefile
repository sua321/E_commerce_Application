CXX = g++
CXXFLAGS = -std=c++23 -O3
INCLUDES = -Idependencies/hash-library

# Source files
# Note: Using quotes for paths with spaces might differ by shell, but escaping via backslash 
# is common in Makefiles. However, on Windows, simple Makefiles often struggle with spaces.
# We will pass all sources to the compiler directly to avoid intermediate object file path issues.

SRCS = PassGenerator/src/Main.cpp \
       PassGenerator/src/Security.cpp \
       PassGenerator/src/EncodingProcess.cpp \
       "PassGenerator/src/Data fetching/DataFetch.cpp" \
       PassGenerator/src/Decoder/BitwiseDecoding.cpp \
       PassGenerator/src/Encoders/Base64Encoding.cpp \
       PassGenerator/src/Encoders/BitWiseEncoding.cpp \
       PassGenerator/src/Generator/Generator.cpp \
       dependencies/hash-library/sha256.cpp

TARGET = PassGen.exe

all: $(TARGET)

$(TARGET):
	$(CXX) $(CXXFLAGS) $(INCLUDES) $(SRCS) -o $(TARGET)

clean:
	cmd /C del $(TARGET)
