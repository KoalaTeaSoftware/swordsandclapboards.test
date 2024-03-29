Feature: Mail Handler
  As the Product Owner
  So that users of the web site can contact me
  I want a facility that handles email messages, and rejects many sorts of hacking attacks

  This specific API is made to work as the fielder of a request from a web page, so it always returns a redirection along
  with parameters indicating success, or failure.
  The specific names of components of the post body are used here so that the code does not have to use a look-up table.
  Note that these do NOT prove that the message was actually sent, or not. This has to be manually verified.

  Background:
    Given the request has the url "https://stage.swordsandclapboards.com/chapters/contact/sendmail.php"
    And the request has the method "post"
    And the request has following header data
      | content-type | application/x-www-form-urlencoded |
      | Accept       | application/json                  |

  @expect_side_effect
  Scenario: Send a good request to the mail handler
  This will (if successful) have the effect of actually sending an email to the S&C address (configured in the mail constraints).
  Note that this is NOT proving that the message was actually sent. That has to be manually verified.
  Having this as part of the set of tests gives some confidence that the errors that subsequent tests should raise are real
  Especially as its tests values are often reused.
    When the request has following simple JSON body elements
      | name             | Teddy the Test            |
      | emailAddress     | a@b.com                   |
      | emailAddressConf | a@b.com                   |
      | subject          | This a testing subject    |
      | yourMessage      | This is a testing message |
    And the request is sent
    Then the response status is 302
    And the response "Location" header does not contain "error"
    And the response "Location" header contains "msg"
    And the response "Location" header contains "sent"

  Scenario Outline: Send requests with missing elements to the mail handler
  Note that this is NOT proving that the message was actually not sent. This has to be manually verified.
    When the request has following simple JSON body elements
      | name             | <name>    |
      | emailAddress     | <add1>    |
      | emailAddressConf | <add2>    |
      | subject          | <subject> |
      | yourMessage      | <message> |
    And the request is sent
    Then the response status is 302
    And the response "Location" header contains "error"
    And the response "Location" header contains "vital fields"
    And the response "Location" header does not contain "sent"
    Examples:
      | name       | add1    | add2    | subject                      | message                   |
      | Testing Ed | a@b.com | a@b.com | Testing no message           |                           |
      | Testing Ed | a@b.com | a@b.com |                              | testing no subject        |
      | Testing Ed | a@b.com |         | Testing missing second email | This is a testing message |
      | Testing Ed |         | a@b.com | Testing missing first email  | This is a testing message |
      |            | a@b.com | a@b.com | Testing missing name         | This is a testing message |

  Scenario: Send a unmatched email addresses to the mail handler
    When the request has following simple JSON body elements
      | name             | gonzo                     |
      | emailAddress     | a@b.com                   |
      | emailAddressConf | b@a.com                   |
      | subject          | This a testing subject    |
      | yourMessage      | This is a testing message |
    And the request is sent
    Then the response status is 302
    And the response "Location" header contains "error"
    And the response "Location" header does not contain "sent"
    And the response "Location" header contains "identical"

  Scenario Outline: Send badly sized names in requests to the mail handler
  Note that this is NOT proving that the message was actually not sent. This has to be manually verified.
    When the request has following simple JSON body elements
      | name             | <name>                    |
      | emailAddress     | a@b.com                   |
      | emailAddressConf | a@b.com                   |
      | subject          | This a testing subject    |
      | yourMessage      | This is a testing message |
    And the request is sent
    Then the response status is 302
    And the response "Location" header contains "error"
    And the response "Location" header does not contain "sent"
    And the response "Location" header contains "<responseMsg>"
    Examples:
      | name                                                       | responseMsg  |
      | wil                                                        | longer name  |
      | Lorem ipsum dolor sit amet consectetur adipiscing elit est | shorter name |

  Scenario Outline: Send badly sized subjects in requests to the mail handler
  Note that this is NOT proving that the message was actually not sent. This has to be manually verified.
    When the request has following simple JSON body elements
      | name             | Gonzo The Great           |
      | emailAddress     | a@b.com                   |
      | emailAddressConf | a@b.com                   |
      | subject          | <subject>                 |
      | yourMessage      | This is a testing message |
    And the request is sent
    Then the response status is 302
    And the response "Location" header contains "error"
    And the response "Location" header does not contain "sent"
    And the response "Location" header contains "<responseMsg>"
    Examples:
      | subject                                                    | responseMsg     |
      | neg                                                        | longer subject  |
      | Lorem ipsum dolor sit amet consectetur adipiscing elit est | shorter subject |


  Scenario Outline: Send badly messages names in requests to the mail handler
  Note that this is NOT proving that the message was actually not sent. This has to be manually verified.
    When the request has following simple JSON body elements
      | name             | Gonzo The Great |
      | emailAddress     | a@b.com         |
      | emailAddressConf | a@b.com         |
      | subject          | <subject>       |
      | yourMessage      | TBR             |
    And the "yourMessage" JSON body element contains a randomly generated string <length> characters long
    And the request is sent
    Then the response status is 302
    And the response "Location" header contains "error"
    And the response "Location" header does not contain "sent"
    And the response "Location" header contains "<responseMsg>"
    Examples:
      | length | responseMsg     |
      | 9      | longer message  |
      | 5001   | shorter message |


  Scenario Outline: Get an error message by using disallowed methods on the Swords and Clapboards mail handler
  The obviously bad verbs should give an appropriate error message
  The following step overrides the similar stp in the background (setup)
    When the request has the method "<method>"
    And the request is sent
    Then the response status is 302
    And the response "Location" header contains "error"
    And the response "Location" header contains "postman"
    Examples:
      | method |
      | get    |
      | put    |
      | delete |