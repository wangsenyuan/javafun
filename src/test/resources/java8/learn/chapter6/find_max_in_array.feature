Feature: Find Max In Array
  find the max value in an array by using LongAccumulator

  Scenario: find max in [1, 2, 3, 4, 5] will return 5
    When find the max from the following nums:
      | 1 |
      | 2 |
      | 3 |
      | 4 |
      | 5 |
    Then get the 5 as the max

  Scenario: find max in [6, 7, 9, 3, 5] will return 9
    When find the max from the following nums:
      | 6 |
      | 7 |
      | 9 |
      | 3 |
      | 5 |
    Then get the 9 as the max