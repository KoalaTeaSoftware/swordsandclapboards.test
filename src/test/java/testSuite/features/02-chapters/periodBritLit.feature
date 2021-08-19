@ci
Feature: Chapter: Period Brit. Lit.
  This feature file assumes that you have run the @smoke tests
  Much of this page is a simple page, so the only real risks are things like syntax failures, or malformed images

  Scenario: Check the page's main heading
    Given I navigate to the page "period-brit-lit"
    Then the first heading is "Period Brit. Lit. Features"

  @slow
  Scenario: Check page's links
    Given the w3c link checker reviews the file "period-brit-lit"
    Then the w3c link checker reports compliance
