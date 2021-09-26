Feature: Site Navbar
  As any user
  So that I can navigate the site
  I want a working navbar

  Scenario Outline: Check the number of items in the nav bar
  This is a belt-and-braces test. If this fails then the go-to-places tests need to be reviewed, even if they pass
    When I navigate to the page "<location>"
    Then the number of nav items is 6
    Examples:
      | location |
      |          |
      | about    |

  Scenario Outline: Use the navbar inb the home page to go to another page
  This is a dual-purpose test, it both verifies that the set of expected nav-bar chapters is as large as expected and
  that they each go to the right place
    Given I navigate to the page "/"
    When I click on the nav links with text "<name>"
    Then the first heading becomes "<expectedTitle>"
    Examples:
      | name            | expectedTitle              |
      | Home            | Welcome                    |
      | About           | About                      |
      | On Release      | On Release                 |
      | Comic Fantasy   | Comic Fantasy Features     |
      | Period Brit Lit | Period Brit. Lit. Features |
      | Contact         | Contact                    |

  Scenario Outline: Use the navbar inb the about page to go to another page
  This is a repetition of the about at another starting point
  The assumption is that if it works in two places, then it will probably work on all pages
    Given I navigate to the page "/about"
    When I click on the nav links with text "<name>"
    Then the first heading becomes "<expectedTitle>"
    Examples:
      | name            | expectedTitle              |
      | Home            | Welcome                    |
      | About           | About                      |
      | On Release      | On Release                 |
      | Comic Fantasy   | Comic Fantasy Features     |
      | Period Brit Lit | Period Brit. Lit. Features |
      | Contact         | Contact                    |