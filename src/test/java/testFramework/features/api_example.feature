Feature: The framework allows you to test restful APIs

  Scenario Outline: Get an error message by using disallowed methods on the Swords and Clapboards mail handler
#    When I get from api at "https://stage.swordsandclapboards.com/chapters/contact/sendmail.php"
    When I "<method>" from api at "https://stage.swordsandclapboards.com/chapters/contact/sendmail.php"
    Then the the response status is 302
    And the user is sent back the the contact page
    And there is an error message containing "Message not sent"
    Examples:
      | method |
      | get    |
      | put    |
      | delete |
