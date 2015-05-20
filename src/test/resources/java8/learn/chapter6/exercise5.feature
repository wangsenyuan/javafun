Feature: Find contents in files
  read all files under certain directory, get a map of <string, set<file>>

  Scenario: work it with CocurrentMap::merge
    When read files in directory 'src/test/java/cucumber/learn/book'
    Then word 'library' should be in file 'BookSearchSteps.java'

  Scenario: work it with CocurrentMap::computeIfAbsent
    When work with CocurrentMap::computeIfAbsent, read files in directory 'src/test/java/cucumber/learn/book'
    Then word 'library' should be in file 'BookSearchSteps.java'
