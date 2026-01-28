### DD2480 Assignment 1
# Launch Interceptor Program

This project implements and tests a program that determines wether to launch an interceptor from a hypothetical anti-ballistic missile system.

The decision of launching the interceptor from the missile system or not is based on input radar tracking information and the evaluation of several *Launch Interceptor Conditions* (LIC’s). In order to unlock the system's launch button, not only must all conditions be fulfilled individually, but the logical relationships between LIC’s are also considered. The launch unlock signal will be generated if and only if every condition in the *Final Unlocking Vector* (FUV) is satisfied.

## Dependencies
The project requires the following software to be installed:
- **Java**: JDK version 17  
- **Maven**: version 3.8 or later

The project also uses the library JUnit Jupiter (5.10.0) for unit testing

## How to Run
This project has no executable main program. Its behavior is evaluated only using unit tests, which can be run using the following commands from the `/Interceptor` directory:
```
mvn clean compile
mvn test
```

## Essence
Assessing our way of working, we believe it is currently at the “In Use” state. The team is actively using the selected tools and practices to perform real work. The way of working is supported by the team and enables communication and collaboration, although it is still evolving. To evolve further, the whole team needs to be more consistently involved in the inspection and adaptation of the way of working, so that it becomes more naturally applied across different contexts.

## Contributions
 - **Ammar Alzeno:** LIC #1, LIC #8, LIC #13, final DECIDE function, corresponding unit tests, MIT License, setup of automated tests for CI
 - **Jens Cancio:** LIC #2, LIC #6, LIC #9, corresponding unit tests
 - **Amanda Henrion Eskeus:** LIC #0, LIC #7, LIC #12, corresponding unit tests, README
 - **Anna Remmara:** LIC #3, LIC #10, LIC #14, PUM, corresponding unit tests, README
 - **Nora Wennerström:** LIC #4, LIC #5, LIC #11, FUV, corresponding unit tests