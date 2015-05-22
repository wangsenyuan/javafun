Feature: Nashore JS Engine
  Scenario: add a + b, will get c
    When add 10 and 11
    Then the sum should be 21

  Scenario: eval add_number.js
    When nashorn eval javascript file 'src/test/resources/java8/learn/chapter7/add_number.js'
    Then eval javascript file will get 70

  Scenario: try array example
    When nashorn eval javascript file 'src/test/resources/java8/learn/chapter7/array_example.js'
    Then eval javascript file will get 55

  Scenario: sort an array of integers by java Arrays::sort with lambda comparator
    When nashorn eval javascript file 'src/test/resources/java8/learn/chapter7/lambda_example.js'
    Then eval javascript file will get 10

  Scenario: iterate 10 times in javascript
    When nashorn eval javascript file 'src/test/resources/java8/learn/chapter7/extend_java_iterator.js'
    Then eval javascript file will get 10

  Scenario: extend java array as a Java.super(obj) example
    When nashorn eval javascript file 'src/test/resources/java8/learn/chapter7/extend_java_arraylist.js'
    Then eval javascript file will get 6

  Scenario: try string intention
    When nashorn eval javascript file 'src/test/resources/java8/learn/chapter7/test_string_intention.js'
    Then eval javascript file will get 28