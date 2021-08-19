@ci
Feature: Chapter: Comic Fantasy
  This feature file assumes that you have run the @smoke tests
  Much of this page is a simple page, so the only real risks are things like syntax failures, or malformed images

  Scenario: Check the page's framework
    Given I navigate to the page "comic-fantasy"
    Then the first heading is "Comic Fantasy Features"

  @slow
  Scenario: Check page's links
    Given the w3c link checker reviews the file "comic-fantasy"
    Then the w3c link checker reports compliance
