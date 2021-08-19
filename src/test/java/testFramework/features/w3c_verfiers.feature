@smoke
Feature: The test framework provides access to some W3C verifiers
  Note that some are marked  as @pot as they can all give false negatives and probably should not be part of an automated gateway

  Scenario: A clean CSS file passes this verification
    Given the w3C CSS tester reviews the file "https://swordsandclapboards.com/index.css"
    Then the w3c CSS tester reports compliance

  Scenario: A page with clean HTML passes this verification
    Given the w3C HTML tester reviews the file "https://swordsandclapboards.com"
    Then the w3c HTML tester reports compliance

  @slow
  Scenario: A page with clean links passes this verification
    Given the w3c link checker reviews the file "https://swordsandclapboards.com"
    Then the w3c link checker reports compliance

  @pot
  Scenario: A non-compliant css file causes errors
    Given the w3C CSS tester reviews the file "https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
    Then the w3c CSS tester reports compliance

  @pot @wip
  Scenario: A page with non-compliant HTML passes this verification
  ToDo: find a page that has non-compliant HTML and put it in here
    Given the w3C HTML tester reviews the file "https://nowhere.com"
    Then the w3c HTML tester reports compliance

  @pot @wip
  Scenario: Check page's links
  ToDo: find a page that has broken links and put it here
    Given the w3c link checker reviews the file "https://nowhere.com"
    Then the w3c link checker reports compliance

