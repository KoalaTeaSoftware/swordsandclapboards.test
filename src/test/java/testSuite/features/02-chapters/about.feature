@ci
Feature: Chapter: About
  This feature file assumes that you have run the @smoke tests
  Much of this page is a simple page, so the only real risks are things like syntax failures, or malformed images

  Scenario: The page's main header is as expected
    Given I navigate to the page "about"
    Then the first heading is "About"

  @slow
  Scenario: Check page's links
    Given the w3c link checker reviews the file "about"
    Then the w3c link checker reports compliance
