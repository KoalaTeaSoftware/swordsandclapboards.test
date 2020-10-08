@functional
Feature: Friendly URLs & Site Navigation
  The site allows (and only really responds to) the use of friendly URLs. The Given steps exercise 'good' URLs
  This differs from the 'broken links' testing in that it demonstrates that the links in the nav bar take you
  to where you expect to be taken.

  Scenario Outline: Follow nav links on all pages
  It is worth looking at all pages, because relative links (if they are used) may work in some pages and not others
  This may not be the fastest, or most elegant way of doing this, but it is simple
    Given I navigate to the page "<url>"
    When I click on the nav link with text "<linkText>"
    Then the page title is "Swords and Clapboards"
    Examples:
      | url                                                 | linkText        |
      | http://stage.swordsandclapboards.com/               | Home            |
      | http://stage.swordsandclapboards.com/               | About           |
      | http://stage.swordsandclapboards.com/               | Comic Fantasy   |
      | http://stage.swordsandclapboards.com/               | Period Brit Lit |
      | http://stage.swordsandclapboards.com/               | On Release      |
      | http://stage.swordsandclapboards.com/               | Contact         |

      | http://stage.swordsandclapboards.com/home           | Home            |
      | http://stage.swordsandclapboards.com/home           | About           |
      | http://stage.swordsandclapboards.com/home           | Comic Fantasy   |
      | http://stage.swordsandclapboards.com/home           | Period Brit Lit |
      | http://stage.swordsandclapboards.com/home           | On Release      |
      | http://stage.swordsandclapboards.com/home           | Contact         |

      | http://stage.swordsandclapboards.com/about          | Home            |
      | http://stage.swordsandclapboards.com/about          | About           |
      | http://stage.swordsandclapboards.com/about          | Comic Fantasy   |
      | http://stage.swordsandclapboards.com/about          | Period Brit Lit |
      | http://stage.swordsandclapboards.com/about          | On Release      |
      | http://stage.swordsandclapboards.com/about          | Contact         |

      | http://stage.swordsandclapboards.com/comic-fantasy  | Home            |
      | http://stage.swordsandclapboards.com/comic-fantasy  | About           |
      | http://stage.swordsandclapboards.com/comic-fantasy  | Comic Fantasy   |
      | http://stage.swordsandclapboards.com/comic-fantasy  | Period Brit Lit |
      | http://stage.swordsandclapboards.com/comic-fantasy  | On Release      |
      | http://stage.swordsandclapboards.com/comic-fantasy  | Contact         |

      # see that the main nav continues to work if a silly URL is used
      | http://stage.swordsandclapboards.com/engine-trouble | Home            |
      | http://stage.swordsandclapboards.com/pigs/are/great | Home            |