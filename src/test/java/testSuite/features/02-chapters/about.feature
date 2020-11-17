Feature: About
  Much of this page is a simple page, so the only real risks are things like syntax failures, or malformed images
  The section about Rose finishes with here On Release, but this is treated as a separate feature

  Scenario: Check the page's framework
    Given I navigate to the page "about"
    Then the page is fully drawn
    And the page title is ""
    And the first heading is "About"

  Scenario: Check page's HTML syntax
    Given the w3C HTML tester reviews the file "about"
    Then the w3c HTML tester reports compliance

  Scenario: Check the page's images are well formed
    Given I navigate to the page "about"
    Then all images are well formed

  Scenario: Check page's links
    Given the w3c link checker reviews the file "about"
    Then the w3c link checker reports compliance
