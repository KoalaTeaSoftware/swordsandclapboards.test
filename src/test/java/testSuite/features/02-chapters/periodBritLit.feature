@ci
Feature: Chapter: Period Brit. Lit.
  Much of this page is a simple page, so the only real risks are things like syntax failures, or malformed images

  Scenario: Check the page's framework
    Given I navigate to the page "period-brit-lit"
    Then the page is fully drawn
    And the page title is "Swords and Clapboards"
    And the first heading is "Period Brit. Lit. Features"

  Scenario: Check page's HTML syntax
    Given the w3C HTML tester reviews the file "period-brit-lit"
    Then the w3c HTML tester reports compliance

  Scenario: Check the page's images are well formed
    Given I navigate to the page "period-brit-lit"
    Then all images are well formed

  Scenario: Check page's links
    Given the w3c link checker reviews the file "period-brit-lit"
    Then the w3c link checker reports compliance
