# Constraint Satisfaction for Smartphone buying

## What is this?

This is a full-stack application that was a school project, but also showcases a few skills. This project
shows my familiarity with popular frameworks, language build patterns, decently clean code (though it could
be improved), and testing. While this isn't a full-blown and fleshed out applications, it does touch many 
facets of a would be production application. A very interesting addition to this application is a unique
implementation of a constraint satisfaction based algorithm. I implore you to look into the algorithm 
section along with the source code.

## The Stack

The main framework that encapsulates the all facets of the project is Spring Boot. Spring Boot was chosen for
its vast array of features and implementations with other libraries, even though only a fraction are used here.
As for the database, the project uses PostgreSQL because that's what I prefer for a relational database and am
familiar with. When it comes to the front-end, Spring Boot provides a Tomcat instance to host the webapp. The 
webapp is built with the JavaScript language and AngularJS framework. 

## The Algorithm

As mentioned above, the search algorithm that I've created is based on constraint satisfaction and more 
specifically using backtracking. Here are the three main steps

1) Given constraints provided by the user, I construct a tree based on all permutations of the constraints.

2) With a smartphone data set, I iterate through each smartphone and determine how much does it satisfy the
constraint tree. To calculate this, I traverse through the constraint tree in a depth-first search manner and
find the maximum depth that the smartphone satisfied. This way if a smartphone doesn’t pass some child 
constraint, the algorithm will backtrack to its’ parent and attempt the other children. This helps to eliminate 
all of the subsequent children states from being visited and thus saves computational time.

3) Keep a collection of the smartphone(s) that most satisfy the user constraint tree.

## Getting Started

Per any other typical Gradle and Spring Boot project, the fastest way to get started is to run 
`./gradlew bootRun`. From a high level this will build the project and execute the jar. You can ensure this 
by accessing `localhost:8080` in a browser (preferably Chrome as that is the only one that this project was 
developed on and thus supported). If you would like to seed the database with real data add 
`-Dspring.profiles.active=prod` to the above gradle command. To run a complete Gradle build cycle that 
includes tests simply run `./gradlew build`.

## Some Queries

select * from smartphone where lower(name) like '%nexus%';
select * from inventory where smartphone_id = 33;

