# Task status
keywords: java8, spring, maven, testng, docker, bootstrap

Project started on August 6.

The task is done, two algorithms implemented. The problem is that one of the algorithms do wrong calculations in some cases :-).

I'm still working on solution of drawing Stairs using JS, however the other part of the project is finished. 

# Requirements

For linux you need

1. Docker 
2. JDK1.8

In order to run on Windows you need:
 
1. JDK1.8 

# Build

Enter project root directory and execute build using maven wrapper

`./mvnw clean install`

# Run

On Linux:

`./run.sh` or `./run.sh -h`

On Windows:

`java -jar target/stairs.water-1.0-SNAPSHOT.jar`

Then open browser, default url is http://localhost:8080/
