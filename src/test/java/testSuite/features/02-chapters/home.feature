@functional
Feature: Home Chapter
  This is a simple page, so the only real risks are things like syntax failures, or malformed images
  As (at the moment) there is no real way of telling that the home page and the default page are identical,
  repeat the test for both.

  Scenario Outline: Check the page's framework
    Given I navigate to the page "<relativeUrl>"
    Then the page is fully drawn
    And the page title is ""
    And the first heading is "Welcome"
    Examples:
      | relativeUrl | note                                                            |
      |             | This should give us the same result as actually asking for home |
      | home        |                                                                 |

  Scenario Outline: Check page's HTML syntax
    Given the w3C HTML tester reviews the file "<relativeUrl>"
    Then the w3c HTML tester reports compliance
    Examples:
      | relativeUrl | note                                                            |
      |             | This should give us the same result as actually asking for home |
      | home        |                                                                 |

  Scenario Outline: Check the page's images are well formed
    Given I navigate to the page ""
    Then all images are well formed
    Examples:
      | relativeUrl | note                                                            |
      |             | This should give us the same result as actually asking for home |
      | home        |                                                                 |

  Scenario Outline: Check page's links
    Given the w3c link checker reviews the file "<relativeUrl>"
    Then the w3c link checker reports compliance
    Examples:
      | relativeUrl | note                                                            |
      |             | This should give us the same result as actually asking for home |
      | home        |                                                                 |

