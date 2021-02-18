# Latitude Exercise 1

## How It Works
This is a stand-alone Java library for calculating the best profit that could be made from 1 purchase and 1 sale of 1 Latitude Financial stock yesterday as explained in the [following exercise project](https://gist.github.com/jonog/54e46b5b1200758d222e3c4cf61baaa6)  

## Technology Stack
Here is a list of technologies, frameworks, and libraries used for delivering this financial library:
* **Programming Language**: Java (JDK 11)
* **Unit Testing Framework**: JUnit 5
* **Build Tool**: Gradle


## How To Build The Library
Run the following command to build the library and run unit tests:
```
./gradlew build
```

To launch unit tests only run the following command:
```
./gradlew cleanTest test
```

## Important Implementation Details 

Here is a list of important considerations:
* **xUnit Test Patterns are adopted** for delivering high quality unit tests (http://xunitpatterns.com/). For example, there are multiple test classes for testing the same SUT (system under test) to separate the concerns and to make it easier to maintain unit tests.


* **Clean code principles are used** for delivering both code and unit tests (https://www.amazon.com.au/Clean-Code-Handbook-Software-Craftsmanship/dp/0132350882)


* **A True Test Driven Development was used** for developing code. Unit tests were added first. Please note that while fixing unit tests failures new abstractions were added such as BuySellTransaction. Those ones were not tested to save time and get to the point. But normally they would be tested after the current failing tests were fixed.


* **BigDecimal type is used to handle monetary values with precision** and to avoid costly financial rounding problems. A proper class could be implemented but for the sake of simplicity BigDecimal will do for now


* **Throwing runtime exceptions when invalid data is provided** - the clients of this library need to provide valid data, otherwise a standard runtime exception will be thrown. The clients are required to handle such exceptions as they see fit.  


* **Stock market times are from 10AM till 4PM** meaning that if the input data are not within this range a runtime exception will be thrown   


* **At least 2 entries are expected in the input** if less than two a runtime exception will be thrown

