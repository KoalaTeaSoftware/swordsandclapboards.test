Feature: Collaborative Fundraising
  Much of this page is a simple page, so the only real risks are things like syntax failures, or malformed images

  Scenario: Check the page's framework
    Given I navigate to the page "collaborative-fundraising"
    Then the page is fully drawn
    And the page title is ""
    And the first heading is "Collaborative Fundraising"

  Scenario: Check page's HTML syntax
    Given the w3C HTML tester reviews the file "collaborative-fundraising"
    Then the w3c HTML tester reports compliance

  Scenario: Check the page's images are well formed
    Given I navigate to the page "collaborative-fundraising"
    Then all images are well formed

  Scenario: Check page's links
    Given the w3c link checker reviews the file "collaborative-fundraising"
    Then the w3c link checker reports compliance
