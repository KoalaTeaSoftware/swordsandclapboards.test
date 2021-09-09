@smoke
Feature: Default Resource Locations
  As a test engineer,
  So that I do not have to repeatedly write them in the tests,
  And so that I can easily redirect the test suite at, say, the staging environment,
  I want to be able to specify default resource locations

  Note that this is not really an exhaustive test, you may want to make your own, but it is OK for a smoke as it will fall over
  if the defaults have not been correctly defined.

  Scenario: Check the exact title of a page
    When I navigate to the page ""
    Then the page title is ""
