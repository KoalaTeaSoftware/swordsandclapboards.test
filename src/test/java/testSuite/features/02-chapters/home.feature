@functional
Feature: Home Chapter
  This is a simple page, so the only real risks are things like syntax failures, or malformed images

  Scenario Outline: Check the page's framework
    Given I navigate to the page "<relativeUrl>"
    Then the page is fully drawn
    And the page title is ""
    Examples:
      | relativeUrl | note                                                            |
      |             | This should give us the same result as actually asking for home |
      | home        |                                                                 |

  Scenario: Check page's HTML syntax
    Given the w3C HTML tester reviews the file "home"
    Then the w3c HTML tester reports compliance

  Scenario: Check the page's images are well formed
    Given I navigate to the page "home"
    Then all images are well formed

  Scenario: Check page's links
    Given the w3c link checker reviews the file "home"
    Then the w3c link checker reports compliance

