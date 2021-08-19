@ci
Feature: Chapter: Home
  This feature file assumes that you have run the @smoke tests
  This is a simple page, so the only real risks are things like syntax failures, or malformed images

  Scenario Outline: Check the page's main heading
  This gives confidence that the different ways of getting to the home page all give us the same page
  As (at the moment) there is no real way of telling that the home page and the default page are identical,
  repeat the test for both.
    Given I navigate to the page "<relativeUrl>"
    Then the first heading is "Welcome"
    Examples:
      | relativeUrl |
      |             |
      |             |
      | home        |

  @slow
  Scenario: Check page's links
    Given the w3c link checker reviews the file ""
    Then the w3c link checker reports compliance

