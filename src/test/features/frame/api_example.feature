@smoke
Feature: The framework allows you to test restful APIs
  As a test engineer,
  So that I can verify the behaviour of a restful API
  I want to be able to exercise an API, and examine its responses

  Scenario Outline: Good and bad gets from a fake API
    Given the request has the method "get"
    And the request has the url "<url>"
    When the request is sent
    Then the the response status is <response>
    Examples:
      | url                                                         | response |
      | https://my-json-server.typicode.com/typicode/demo/posts     | 200      |
      | https://my-json-server.typicode.com/typicode/demo/posts/1   | 200      |
      | https://my-json-server.typicode.com/typicode/demo/posts/100 | 404      |
      | https://my-json-server.typicode.com/typicode/demo/elephants | 404      |

  Scenario: Post to a fake API
  This example URL is not be at all intelligent about what it accepts, and will always give you 201, unless you give it a bad path
  However, you can see that posting in this manner does not crash that easily.
    Given the request has following simple JSON body elements
      | I     | This api calls this the title         |
      | donot | This would be in the body of the post |
      | care  | 100000                                |
    And the request has following header data
      | Content-type | application/json; charset=UTF-8 |
    And the request has the method "post"
    And the request has the url "https://jsonplaceholder.typicode.com/posts"
    When the request is sent
    Then the the response status is 201

