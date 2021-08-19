@ci
Feature: Chapter: On Release
  This feature file assumes that you have run the @smoke tests

  Scenario: Check the page's framework
    Given I navigate to the page "on-release"
    Then the first heading is "On Release"

  Scenario: Check the page's images are well formed
    Given I navigate to the page "on-release"
    Then all images are well formed

  Scenario: Check page's links
    Given the w3c link checker reviews the file "on-release"
    Then the w3c link checker reports compliance

  Scenario: Review the contents of the page
  This feature is a 'list' with links to details, which open a page from another site (in another tab).
  It is is data-driven. So the risks are things like images and other content are not correctly specified, or drawn.
    Given I navigate to the page "on-release"
    Then there is more than 4 listed films
    And all thumbnails are shown
    And all titles are populated
    And all puff paragraphs are populated

# The links from these will all take the visitor away from this site. Assume that the link check ensures that this is not embarrassing