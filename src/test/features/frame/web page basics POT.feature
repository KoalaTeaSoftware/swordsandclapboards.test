@smoke @pot
Feature: Web Page Basics - Proof of Tests
  As a test engineer, so that I can have confidence in the test framework, I want to see deliberate failures reported

  Background:
    When I write to the report "Expect this scenario to fail"
    And I navigate to the page "koalateasoftware.com"

  Scenario: Demonstrate that the :exact title of a page: test detects an error
    Then the page title is "Koala tea Software"

  Scenario: Demonstrate that the :title contains a keyphrase: test detects an error
    Then the page title contains "koala Tea"

  Scenario: Demonstrate that the :title does not contains a keyphrase: test detects an error
    Then the page title does not contain "Koala Tea"

