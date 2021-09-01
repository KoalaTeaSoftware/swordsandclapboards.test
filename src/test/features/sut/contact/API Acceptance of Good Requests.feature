Feature: Good requests to the API give positive feedback
  As the Product Owner
  So that users of the web site can contact me
  I want good requests to the mail handler to appear to succeed

  This specific API always returns a redirection along with parameters indicating success, or failure

  Scenario: Send a good request to the mail handler
  Note that this is NOT proving that the message was actually sent. Currently, this has to be manually verified.
  The specific names of components of the post body are used so that a look-up table is not needed
    Given I post the following data to the api at "https://stage.swordsandclapboards.com/chapters/contact/sendmail.php"
      | name             | gonzo                  |
      | emailAddress     | a@b.com                |
      | emailAddressConf | a@b.com                |
      | subject          | this is a test subject |
      | yourMessage      |  |
    Then the the response status is 302
    And the response "Location" header contains "error"
    And the response "Location" header contains "not given data"
