@smoke
Feature: Visit Web Pages
  These scenarios smoke-test features of the test framework
  
  Scenario: Check the title of a page
    When I navigate to the page "koalateasoftware.com"
    Then the page title is "Koala Tea Software"


