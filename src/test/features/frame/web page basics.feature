@smoke
Feature: Web Page Basics
  As a test engineer, I want to see that some basic web page tests will give expected results

  The framework allows you to go to web pages and make various basic tests
  Note that the tests are case sensitive

  Background:
    When I navigate to the page "koalateasoftware.com"

  Scenario: Check the exact title of a page
    Then the page title is "Koala Tea Software"

  Scenario: Check that the title contains a keyphrase
    Then the page title contains "Koala Tea"

  Scenario: Check that the title does not contains a keyphrase
    Then the page title does not contain "Kaola Tea Software"