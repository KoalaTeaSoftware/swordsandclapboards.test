@smoke
Feature: The pages and components of the site should all have a compliant syntax

  Scenario Outline: CSS Compliance with W3C standards
  This is intended for testing directly created components of the SUT
  For example, Bootstrap 4' css will generate a load of error messages from this tester
    Given the w3C CSS tester reviews the file "<file>"
    Then the w3c CSS tester reports compliance
    Examples:
      | file         |
      | /index.css   |
      | /theRest.css |

  Scenario Outline: I visit all the pages and see that they have good syntax
    Given the w3C HTML tester reviews the file "<testURL>"
    Then the w3c HTML tester reports compliance
    Examples:
      | testURL         |
      |                 |
      | home            |
      | about           |
      | on-release      |
      | comic-fantasy   |
      | period-brit-lit |
#    ToDo
#  Error: Element script must not have attribute async unless attribute src is also specified or unless attribute type is specified with value module.
#  From line 174, column 1; to line 174, column 14
#  m>↩</div>↩<script async>↩
#  | contact         |