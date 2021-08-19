@smoke
Feature: The framework allows for screen grabs when a scenario fails, and for extra messages to be placed in that output
  All of these a marked as @pot because you need to manually verify that you got a satisfactory result

  @pot
  Scenario: Fail a test and get a screen grab
    When I navigate to the page "https://swordsandclapboards.com/"
    And I write to the html report "The following step will cause the scenario to fail. "
    Then the page title is "qwertyuiop"
    And I write to the html report "This step should be skipped, and the message about skipping it will not be attached"

  @pot @wip
  Scenario: Arbitrarily force a screen grab
  This could be useful if you are struggling with a long test and the automatic capture is insufficient
  ToDo:For some reason, the image is a blank - perhaps it is a timing thing
    When I navigate to the page "https://swordsandclapboards.com/"
    And I write to the html report "This step should allow the browser to paint the page"
    And I force a screen grab to the html report "Arbitrarily forced screen grab"
    Then the page title is "I am going to fail now so you have to look at the result"

