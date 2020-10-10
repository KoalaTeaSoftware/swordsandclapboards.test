@functional
Feature: On Release list of films
  Pages in this chapter are data-driven. So the risks are things like
  Images and other content are not correctly specified, or drawn.
  The chapter has a 'list page' with links to details, which open a page from another site (in another tab)

  Scenario: Directly visit chapter
    Given I navigate to the page "http://stage.swordsandclapboards.com/on-release/"
    Then the page is fully drawn
    And the first heading is "On Release"
    And there is more than 4 listed film
    And all thumbnails are shown
    And all titles are populated
    And all puff paragraphs are populated

  Scenario: HTML Syntax Compliance with W3C standards
  A syntactical failure may be indicative of a processing failure.
  It is worth making  special point of running this test here, in case the general syntax checks were omitted.
    Given the w3C HTML tester reviews the file "http://stage.swordsandclapboards.com/on-release/"
    Then the w3c HTML tester reports compliance

  Scenario: Check links on a page
  As for the syntax checks, it is worth making sure of this on this page
    The step 'all thumbnails are shown' checks the veracity of the src attributes for the images, but there are other links
    Given the w3c link checker reviews the file "http://stage.swordsandclapboards.com/on-release/"
    Then the w3c link checker reports compliance
