@smoke
Feature: The framework allows you to test restful APIs
  As a test engineer,
  So that I can verify the behaviour of a restful API
  I want to be able to exercise an API, and examine its responses

  Scenario Outline: Get an error message by using disallowed methods on the Swords and Clapboards mail handler
#    When I get from api at "https://stage.swordsandclapboards.com/chapters/contact/sendmail.php"
    When I "<method>" from api at "https://stage.swordsandclapboards.com/chapters/contact/sendmail.php"
    Then the the response status is 302
    And the response "Location" header contains "error"
    And the response "Location" header contains "Message not sent"
    Examples:
      | method |
      | get    |
      | put    |
      | delete |
