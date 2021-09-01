Feature: Mail Handler
  As the Product Owner
  So that users of the web site can contact me
  I want a facility that handles email messages

  This specific API is made to work as the fielder of a request from a web page, so it always returns a redirection along
  with parameters indicating success, or failure.
  The specific names of components of the post body are used here so that the code does not have to use a look-up table.
  Note that these do NOT prove that the message was actually sent, or not. This has to be manually verified.

  Scenario: Send a good request to the mail handler
  Note that this is NOT proving that the message was actually sent. This has to be manually verified.
  Having this as part of the set of tests gives some confidence that th errors that subsequent tests should raise are real
  Especially as its tests values are often reused.
    Given I post the following data to the api at "https://stage.swordsandclapboards.com/chapters/contact/sendmail.php"
      | name             | Teddy the Test            |
      | emailAddress     | a@b.com                   |
      | emailAddressConf | a@b.com                   |
      | subject          | This a testing subject    |
      | yourMessage      | This is a testing message |
    Then the the response status is 302
    And the response "Location" header does not contain "error"
    And the response "Location" header contains "msg"
    And the response "Location" header contains "sent"

  Scenario Outline: Send requests with missing elements to the mail handler
  Note that this is NOT proving that the message was actually not sent. This has to be manually verified.
    Given I post the following data to the api at "https://stage.swordsandclapboards.com/chapters/contact/sendmail.php"
      | name             | <name>    |
      | emailAddress     | <add1>    |
      | emailAddressConf | <add2>    |
      | subject          | <subject> |
      | yourMessage      | <message> |
    Then the the response status is 302
    And the response "Location" header contains "error"
    And the response "Location" header does not contain "sent"
    And the response "Location" header contains "<responseMsg>"
    Examples:
      | name       | add1    | add2    | subject                      | message                   | responseMsg  |
      | Testing Ed | a@b.com | a@b.com | Testing no message           |                           | rather short |
      | Testing Ed | a@b.com | a@b.com |                              | testing no subject        | rather short |
      | Testing Ed | a@b.com |         | Testing missing second email | This is a testing message | address      |
      | Testing Ed |         | a@b.com | Testing missing first email  | This is a testing message | address      |
      |            | a@b.com | a@b.com | Testing missing name         | This is a testing message | name         |

  Scenario: Send a unmatched email addresses to the mail handler
    Given I post the following data to the api at "https://stage.swordsandclapboards.com/chapters/contact/sendmail.php"
      | name             | gonzo                     |
      | emailAddress     | a@b.com                   |
      | emailAddressConf | b@a.com                   |
      | subject          | This a testing subject    |
      | yourMessage      | This is a testing message |
    Then the the response status is 302
    And the response "Location" header contains "error"
    And the response "Location" header does not contain "sent"
    And the response "Location" header contains "identical"

  Scenario Outline: Send badly sized requests to the mail handler
  Note that this is NOT proving that the message was actually not sent. This has to be manually verified.
    Given I post the following data to the api at "https://stage.swordsandclapboards.com/chapters/contact/sendmail.php"
      | name             | <name>    |
      | emailAddress     | <add1>    |
      | emailAddressConf | <add2>    |
      | subject          | <subject> |
      | yourMessage      | <message> |
    Then the the response status is 302
    And the response "Location" header contains "error"
    And the response "Location" header does not contain "sent"
    And the response "Location" header contains "<responseMsg>"
    Examples:
      | name                                                       | add1    | add2    | subject                | message                   | responseMsg |
      | wil                                                        | a@b.com | a@b.com | This a testing subject | This is a testing message | longer      |
      | Lorem ipsum dolor sit amet consectetur adipiscing elit est | a@b.com | a@b.com | This a testing subject | This is a testing message | shorter     |
