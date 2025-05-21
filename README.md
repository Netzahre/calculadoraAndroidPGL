# Calculator App

This is a simple Android calculator application developed in Kotlin. It allows users to perform basic arithmetic calculations and some scientific operations.

## Features

*   **Basic Arithmetic Operations:** Addition (+), Subtraction (-), Multiplication (*), Division (/).
*   **Scientific Operations:** Square Root (√), Squaring (x²).
*   **Decimal Input:** Support for numbers with decimal points.
*   **Clear Functions:**
    *   `AC` (All Clear): Resets the entire expression and result.
    *   `C` (Clear): Deletes the last entered digit or operator.
*   **Memory:**
    *   Displays the current expression being entered.
    *   Shows the result of the previous calculation.
    *   Allows using the previous result in subsequent calculations.

## Project Structure

The project follows a standard Android application structure:

*   `app/src/main/java/com/example/practica1_calculadora/MainActivity.kt`: Contains the core logic for the calculator's functionality, including UI event handling and calculation processing.
*   `app/src/main/res/layout/activity_main.xml`: Defines the user interface layout for the calculator.
*   `app/src/main/res/values/`: Contains resources like strings, colors, and themes used in the app.
*   `build.gradle.kts` (Project & App level): Gradle build scripts for managing dependencies and build configurations.

## Building and Running the Project

1.  **Clone the repository:**
    ```bash
    git clone <repository_url>
    ```
    (Replace `<repository_url>` with the actual URL of the repository)
2.  **Open in Android Studio:**
    *   Launch Android Studio.
    *   Select "Open an existing Android Studio project".
    *   Navigate to the cloned repository directory and select it.
3.  **Build the project:**
    *   Wait for Android Studio to sync and build the project automatically.
    *   If needed, you can manually build by selecting "Build" > "Make Project" from the menu.
4.  **Run the application:**
    *   Select a target device (emulator or physical device).
    *   Click the "Run" button (green play icon) or select "Run" > "Run 'app'" from the menu.

## Future Improvements

*   **Advanced Scientific Functions:** Implement trigonometric functions (sin, cos, tan), logarithms, etc.
*   **Calculation History:** Store and display a history of previous calculations.
*   **Themes:** Allow users to customize the look and feel of the calculator (e.g., dark mode, different color schemes).
*   **Input Validation:** More robust handling of invalid input sequences.
*   **Unit Tests:** Add comprehensive unit tests to ensure the reliability of calculation logic.
*   **Support for parentheses:** Allow users to group operations using parentheses.
