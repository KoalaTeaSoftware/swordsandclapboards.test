@functional @ci
Feature: Chapter: Home
  This is a simple page, so the only real risks are things like syntax failures, or malformed images
  As (at the moment) there is no real way of telling that the home page and the default page are identical,
  repeat the test for both.
  Should be able to get to this page either with no additional path, or with 'home'

  Scenario Outline: Check the page's framework
    Given I navigate to the page "<relativeUrl>"
    Then the page is fully drawn
    And the page title is ""
    And the first heading is "Welcome"
    Examples:
      | relativeUrl |
      |             |
      | home        |

  Scenario Outline: Check page's HTML syntax
    Given the w3C HTML tester reviews the file "<relativeUrl>"
    Then the w3c HTML tester reports compliance
    Examples:
      | relativeUrl |
      |             |
      | home        |

  Scenario Outline: Check the page's images are well formed
    Given I navigate to the page "<relativeUrl>"
    Then all images are well formed
    Examples:
      | relativeUrl |
      |             |
      | home        |

  Scenario Outline: Check page's links
    Given the w3c link checker reviews the file "<relativeUrl>"
    Then the w3c link checker reports compliance
    Examples:
      | relativeUrl |
      |             |
      | home        |

