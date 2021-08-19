@smoke
Feature: Page Title Verification
  The framework allows you to go to web pages and make various basic tests

  Background:
    When I navigate to the page "koalateasoftware.com"

  Scenario: Check the exact title of a page
    Then the page title is "Koala Tea Software"

  Scenario: Check that the title contains a keyphrase
    Then the page title contains "Koala Tea"

  Scenario: Check that the title does not contains a keyphrase
    Then the page title does not contain "Kaola Tea Software"

  @pot
  Scenario: Demonstrate that the exact title of a page test detects an error
    Then the page title is "Koala tea Software"

  @pot
  Scenario: Demonstrate that the title contains a keyphrase test detects an error
    Then the page title contains "The Smoking Gnu"

  @pot
  Scenario: Demonstrate that the title does not contains a keyphrase test detects an error
    Then the page title does not contain "Koala Tea Software"

