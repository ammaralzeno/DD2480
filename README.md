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

### Checklist
| State                   | Checklist |
|-------------------------|-----------|
| **Principles Established** | [x] Principles and constraints are committed to by the team. <br> [x] Principles and constraints are agreed to by the stakeholders. <br> [x] The tool needs of the work and its stakeholders are agreed. <br> [x] A recommendation for the approach to be taken is available. <br> [x] The context within which the team will operate is understood. <br> [x] The constraints that apply to the selection, acquisition, and use of practices and tools are known. |
| **Foundation Established** | [x] The key practices and tools that form the foundation of the way-of-working are selected. <br> [x] Enough practices for work to start are agreed to by the team. <br> [x] All non-negotiable practices and tools have been identified. <br> [x] The gaps that exist between the practices and tools that are needed and the practices and tools that are available have been analyzed and understood. <br> [x] The capability gaps that exist between what is needed to execute the desired way of working and the capability levels of the team have been analyzed and understood. <br> [x] The selected practices and tools have been integrated to form a usable way-of-working. |
| **In Use** | [x] The practices and tools are being used to do real work. <br> [x] The use of the practices and tools selected are regularly inspected. <br> [x] The practices and tools are being adapted to the team’s context. <br> [x] The use of the practices and tools is supported by the team. <br> [x] Procedures are in place to handle feedback on the team’s way of working. <br> [x] The practices and tools support team communication and collaboration. |
| **In Place** | [x] The practices and tools are being used by the whole team to perform their work. <br> [x] All team members have access to the practices and tools required to do their work. <br> [ ] The whole team is involved in the inspection and adaptation of the way-of-working. |
| **Working Well** | [x] Team members are making progress as planned by using and adapting the way-of working to suit their current context. <br> [ ] The team naturally applies the practices without thinking about them. <br> [ ] The tools naturally support the way that the team works. <br> [x] The team continually tunes their use of the practices and tools. |
| **Retired** | [ ] The team's way of working is no longer being used. <br> [x] Lessons learned are shared for future use. |

## Contributions
 - **Ammar Alzeno:** LIC #1, LIC #8, LIC #13, final DECIDE function, corresponding unit tests, setup of automated tests for CI
 - **Jens Cancio:** LIC #2, LIC #6, LIC #9, corresponding unit tests, MIT License
 - **Amanda Henrion Eskeus:** LIC #0, LIC #7, LIC #12, corresponding unit tests, README
 - **Anna Remmare:** LIC #3, LIC #10, LIC #14, PUM, corresponding unit tests, README
 - **Nora Wennerström:** LIC #4, LIC #5, LIC #11, FUV, corresponding unit tests

## License 
This project is licensed under the MIT License.