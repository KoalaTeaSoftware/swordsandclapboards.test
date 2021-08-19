@smoke
Feature: Page Image Sniffing
  The framework allows you to go to web pages and make very basic assertions about the images that it detects

  Background:
    When I navigate to the page "koalateasoftware.com"

  Scenario: See that all images appear not to be a mess
    Then all images are well formed

  # ToDo: find a web page somewhere that does not have well-formed images
  # That way you can have a @pot scenario

