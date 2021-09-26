Feature: Browser's location display
  As a user of the site
  So that I can bookmark and share specific pages
  I want the web site to have friendly URLs

  Scenario Outline: I visit all the known chapters and check the page title
  Note that this scenario does not use any SUT specific criteria, but does assume that the default has been set
    Given I navigate to the page "<testURL>"
    Then the page title is "<expectedTitle>"
    And the first heading contains "<heading>"
    Examples:
      | testURL         | expectedTitle         | heading                    |
      |                 | Swords and Clapboards | Welcome                    |
      | home            | Swords and Clapboards | Welcome                    |
      | about           | Swords and Clapboards | About                      |
      | on-release      | Swords and Clapboards | On Release                 |
      | comic-fantasy   | Swords and Clapboards | Comic Fantasy Features     |
      | period-brit-lit | Swords and Clapboards | Period Brit. Lit. Features |
      | contact         | Swords and Clapboards | Contact                    |

  Scenario Outline: I visit all known chapters and see that they appear to be complete
  Note: This scenario is very likely to make use of SUT-specific page details
    Given I navigate to the page "<testURL>"
    Then the page is fully drawn
    Examples:
      | testURL         |
      |                 |
      | about           |
      | on-release      |
      | comic-fantasy   |
      | period-brit-lit |
      | contact         |

  Scenario Outline: I visit all the pages and see that no images fail to appear
    Given I navigate to the page "<testURL>"
    Then all images are well formed
    Examples:
      | testURL         |
      |                 |
      | about           |
      | on-release      |
      | comic-fantasy   |
      | period-brit-lit |
      | contact         |