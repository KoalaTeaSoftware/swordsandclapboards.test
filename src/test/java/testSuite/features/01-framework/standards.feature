@standards
Feature: Standards Compliance
  Syntax errors could be a reason for functional failure, so it is worth regarding this as a smoke test as well.

  Scenario Outline: CSS Compliance with W3C standards
  This is intended for testing directly created components of the SUT
  For example, Bootstrap 4' css will generate a load of error messages from this tester
    Given the w3C CSS tester reviews the file "<file>"
    Then the w3c CSS tester reports compliance
    Examples:
      | file                                           |
      | http://stage.swordsandclapboards.com/index.css |

#  Scenario Outline: HTML Compliance with W3C standards
#    Given the w3C HTML tester reviews the file "<url>"
#    Then the w3c HTML tester reports compliance
#    Examples:
#      | url                                                  |
#      | http://stage.swordsandclapboards.com                 |
#      | http://stage.swordsandclapboards.com/home            |
#      | http://stage.swordsandclapboards.com/about           |
#      | http://stage.swordsandclapboards.com/comic-fantasy   |
#      | http://stage.swordsandclapboards.com/period-brit-lit |
#      | http://stage.swordsandclapboards.com/on-release      |
#      | http://stage.swordsandclapboards.com/contact         |
#
#  Scenario Outline: Check links on a page
#    Given the w3c link checker reviews the file "<url>"
#    Then the w3c link checker reports compliance
#    Examples:
#      | url                                                  |
#      | http://stage.swordsandclapboards.com                 |
#      | http://stage.swordsandclapboards.com/home            |
#      | http://stage.swordsandclapboards.com/about           |
#      | http://stage.swordsandclapboards.com/comic-fantasy   |
#      | http://stage.swordsandclapboards.com/period-brit-lit |
#      | http://stage.swordsandclapboards.com/on-release      |
#      | http://stage.swordsandclapboards.com/contact         |
