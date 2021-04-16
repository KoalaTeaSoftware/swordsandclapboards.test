@ci
Feature: Chapter: Comic Fantasy
  Much of this page is a simple page, so the only real risks are things like syntax failures, or malformed images

  Scenario: Check the page's framework
    Given I navigate to the page "comic-fantasy"
    Then the page is fully drawn
    And the page title is "Swords and Clapboards"
    And the first heading is "Comic Fantasy Features"

  Scenario: Check page's HTML syntax
    Given the w3C HTML tester reviews the file "comic-fantasy"
    Then the w3c HTML tester reports compliance

  Scenario: Check the page's images are well formed
    Given I navigate to the page "comic-fantasy"
    Then all images are well formed

  Scenario: Check page's links
    Given the w3c link checker reviews the file "comic-fantasy"
    Then the w3c link checker reports compliance
