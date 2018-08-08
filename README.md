#Task status

I promised to send solution of the problem not later than August 8. That is why I'm sending what I have right now.

The task is done, two algorithms implemented. The problem is that both algorithm do wrong calculations is some cases :-).

I'm still working on solution of this problem, the other part of the project is finished. 

Why I'm sending project, with partially working solution?
1. Because I'm promised to send it not later than August 8
2. Because I understand that this is technical interview, and algorithm is not as important as other part of the project

keywords: java8, spring, maven, testng, docker, bootstrap

#Requirements

For linux you need

1. Docker

In order to run on Windows you need:
 
1. JDK1.8 

#Build

Enter project root directory and execute build using maven wrapper

`./mvnw clean install`

#Run

On Linux:

`./run.sh` or `./run.sh -h`

On Windows:

`java -jar target/stairs.water-1.0-SNAPSHOT.jar`

Then open browser, default url is http://localhost:8080/
