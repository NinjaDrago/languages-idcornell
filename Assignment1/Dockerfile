# Use an official GCC image to build and run the program
FROM gcc:latest

# Set the working directory inside the container
WORKDIR /app

# Copy the source code into the container
COPY hello.cpp .

# Compile the C++ program
RUN g++ -o hello hello.cpp

# Command to run the compiled program
CMD ["./hello"]
