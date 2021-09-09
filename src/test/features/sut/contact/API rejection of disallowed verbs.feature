@ci
Feature: Handling of disallowed verbs
  As the product owner
  So that I can be confident that the mail handler is robust
  I want it to reject unauthorised kinds of request

  Scenario Outline: Get an error message by using disallowed methods on the Swords and Clapboards mail handler
  The obviously bad verbs should give an appropriate error message
    Given the request has the url "https://stage.swordsandclapboards.com/chapters/contact/sendmail.php"
    And the request has the method "<method>"
    When the request is sent
    Then the response status is 302
#    When I "<method>" from api at "https://stage.swordsandclapboards.com/chapters/contact/sendmail.php"
#    Then the the response status is 302
    And the response "Location" header contains "error"
    And the response "Location" header contains "postman"
    Examples:
      | method |
      | get    |
      | put    |
      | delete |
