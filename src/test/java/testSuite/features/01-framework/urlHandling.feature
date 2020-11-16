@functional
Feature: Friendly URLs
  The site allows (and only really responds to) the use of friendly URLs.
  'Good' urls are tested on each of the pages.
  This set sees that bad urls don't cause a problem

  Scenario Outline: Friendly treatment of rubbish urls
    Given I navigate to the page "<url>"
    Then the page title is "Swords and Clapboards"
    Examples:
      | url                                                 |
      | http://stage.swordsandclapboards.com/engine-trouble |
      | http://stage.swordsandclapboards.com/pigs/are/great |