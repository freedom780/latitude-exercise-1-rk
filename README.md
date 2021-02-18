# Latitude Exercise 1

## How It Works
This is a stand-alone Java library for calculating the best profit that could be made from 1 purchase and 1 sale of 1 Latitude Financial stock yesterday as explained in the [following exercise project](https://gist.github.com/jonog/54e46b5b1200758d222e3c4cf61baaa6)  

## Technology Stack
Here is a list of technologies, frameworks, and libraries used for delivering this financial library:
* **Programming Language**: Java
* **Unit Testing Framework**: JUnit 5
* **Build Tool**: Gradle


## How To Build The Library
Run the following command to build the library and run unit tests:
```
./gradlew build
```


## Important Implementation Details 

Here is a list of important considerations:
* **BigDecimal type is used to handle monetary values with precision** and to avoid costly financial rounding problems. A proper class could be implemented but for the sake of simplicity BigDecimal will do for now
  

* **xUnit Test Patterns are adopted** for delivering high quality unit tests (http://xunitpatterns.com/). For example, there are multiple test classes for testing the same SUT (system under test) to separate the concerns and to make it easier to maintain unit tests. 
  

* **Clean code principles are used** for delivering both code and unit tests (https://www.amazon.com.au/Clean-Code-Handbook-Software-Craftsmanship/dp/0132350882)