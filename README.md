# sogetiPOC

I have followed BDD Cucumber with JUnit Framework to automate both:
> UI Tests

> API Tests

Two separate .features files have been created for UI & API tests. Path: src/test/resources/features

StepDefinition files are located at: src/test/java/stepDefinition

Test Runner is located at: src/test/java/runner

Code has been checked into GitHub in a public repository.

To Run the Tests please do the following:

- clone the repository:
    
  - git clone https://github.com/zahidsye/sogetiPOC.git


- Execute mvn test in IntelliJ terminal


- To run Tests From IntelliJ Run Configuration execute:
  - clean test -Dcucumber.filter.tags=@RegressionTest



