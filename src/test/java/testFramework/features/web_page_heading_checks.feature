@smoke
Feature: Page Heading Verification
  The framework allows you to go to web pages and make various basic assertions about the first H1 tag it encounters

  Background:
    When I navigate to the page "koalateasoftware.com"

  Scenario: Check the exact first heading on a page
    Then the first heading is "Koala Tea Software"

  Scenario: Check that the first heading contains a keyphrase
    Then the first heading contains "Koala Tea"

  Scenario: Check that the first heading does not contains a keyphrase
    Then the first heading does not contain "Kaola Tea Software"

  @pot
  Scenario: Demonstrate that the exact first heading of a page test detects an error
    Then the first heading is "Koala tea Software"

  @pot
  Scenario: Demonstrate that the first heading contains a keyphrase test detects an error
    Then the first heading contains "The Smoking Gnu"

  @pot
  Scenario: Demonstrate that the first heading does not contains a keyphrase test detects an error
    Then the first heading does not contain "Koala Tea Software"

