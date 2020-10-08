@smoke
Feature: Visit Web Pages
  These scenarios smoke-test features of the test framework

  Scenario Outline: Visit a page
  This page is fairly fast, so it should give a quick test
    When I navigate to the page "<address>"
    Then the page title is "<title>"
    Examples:
      | address                              | title                 |
      | http://swordsandclapboards.com/      | Swords and Clapboards |
      | http://swordsandclapboards.com/about | Swords and Clapboards |


