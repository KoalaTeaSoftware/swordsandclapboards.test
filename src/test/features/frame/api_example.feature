@smoke
Feature: The framework allows you to test restful APIs
  As a test engineer,
  So that I can verify the behaviour of a restful API
  I want to be able to exercise an API, and examine its responses

  Scenario Outline: Get from a fake API
    Given the request has the method "get"
    And the request has the url "<url>"
    When the request is sent
    Then the response status is <response>
    Examples:
      | url                                                         | response |
      | https://my-json-server.typicode.com/typicode/demo/posts     | 200      |
      | https://my-json-server.typicode.com/typicode/demo/posts/1   | 200      |
      | https://my-json-server.typicode.com/typicode/demo/posts/100 | 404      |
      | https://my-json-server.typicode.com/typicode/demo/elephants | 404      |

  Scenario: Post to a fake API
    This fake API does not seem to care about the data that you give it, but does want some data
    Given the request has the url "https://jsonplaceholder.typicode.com/posts"
    And the request has the method "post"
    And the request has following header data
#      | content-type | application/json; charset=UTF-8 | - This WILL NOT result in the data getting sent out
      | content-type | application/x-www-form-urlencoded |
      | Accept       | application/json                  |
    And the request has following simple JSON body elements
      | I     | This api calls this the title         |
      | donot | This would be in the body of the post |
      | care  | 100000                                |
    When the request is sent
    Then the response status is 201

  Scenario: Omit content-type of "application/x-www-form-urlencoded"
  Without this header, the framework simply omits to send the parameters.
  This uses (what may seem to be a sensible type of content), just to demonstrate this
    Given the request has the url "https://jsonplaceholder.typicode.com/posts"
    And the request has the method "post"
    And the request has following header data
      | content-type | application/json |
      | Accept       | application/json |
    And the request has following simple JSON body elements
      | I     | This api calls this the title         |
      | donot | This would be in the body of the post |
      | care  | 100000                                |
    When the request is sent
    Then the response status is 500