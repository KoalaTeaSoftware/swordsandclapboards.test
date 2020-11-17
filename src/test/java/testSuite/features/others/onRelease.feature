@functional
Feature: On Release list of films
  This feature is a 'list' with links to details, which open a page from another site (in another tab).
  It is is data-driven. So the risks are things like images and other content are not correctly specified, or drawn.
  It (currently) appears on the About page, so things like syntax, images, and links are checked there.
  This does, therefore, in a way, repeat that testing, but this is beneficial and more explicit.

  Scenario: Directly visit chapter
    Given I navigate to the page "about"
    Then the page is fully drawn
    And the first heading is "About"
    And there is more than 4 listed films
    And all thumbnails are shown
    And all titles are populated
    And all puff paragraphs are populated

    #ToDo follow links and see that the derived page is as expected