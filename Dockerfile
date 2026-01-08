# Use Eclipse Temurin OpenJDK 17 image
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy Java file
COPY Hello.java .

# Compile Java program
RUN javac Hello.java

# Run Java program
CMD ["java", "Hello"]