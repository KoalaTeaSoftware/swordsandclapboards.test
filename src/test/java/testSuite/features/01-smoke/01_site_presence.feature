@smoke
Feature: The site exists and its chapters are all present
  This is a quick set of tests that ensure that the configuration appears to be in order, and that the SUT is worth looking at

  Scenario: I use the configure defaults
    Given I navigate to the page ""
    Then the page title is ""

  Scenario Outline: I visit all the pages and see that they have the right title
    Given I navigate to the page "<testURL>"
    Then the page title is "<expectedTitle>"
    Examples:
      | testURL         | expectedTitle         |
      |                 | Swords and Clapboards |
      | home            | Swords and Clapboards |
      | about           | Swords and Clapboards |
      | on-release      | Swords and Clapboards |
      | comic-fantasy   | Swords and Clapboards |
      | period-brit-lit | Swords and Clapboards |
      | contact         | Swords and Clapboards |

  Scenario Outline: I visit all the pages and see that they appear to be complete
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