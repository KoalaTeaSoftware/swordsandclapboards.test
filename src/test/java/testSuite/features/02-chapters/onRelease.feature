@ci
Feature: Chapter: On Release
  This feature is a 'list' with links to details, which open a page from another site (in another tab).
  It is is data-driven. So the risks are things like images and other content are not correctly specified, or drawn.
  It (currently) appears on the About page, so things like syntax, images, and links are checked there.
  This does, therefore, in a way, repeat that testing, but this is beneficial and more explicit.

  Scenario: Check the page's framework
    Given I navigate to the page "on-release"
    Then the page is fully drawn
    And the page title is ""
    And the first heading is "On Release"

  Scenario: Check page's HTML syntax
    Given the w3C HTML tester reviews the file "on-release"
    Then the w3c HTML tester reports compliance

  Scenario: Check the page's images are well formed
    Given I navigate to the page "on-release"
    Then all images are well formed

  Scenario: Check page's links
    Given the w3c link checker reviews the file "on-release"
    Then the w3c link checker reports compliance

  Scenario: Directly visit chapter
    Given I navigate to the page "on-release"
    Then the page is fully drawn
    And the first heading is "On Release"
    And there is more than 4 listed films
    And all thumbnails are shown
    And all titles are populated
    And all puff paragraphs are populated

#ToDo: The page should have a bit more testing, and the details pages need a whole feature file