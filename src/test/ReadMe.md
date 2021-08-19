# Koala Tea Software Cucumber Frame

## Installation and Use

It should be easy to take a copy of the whole thing, or just clone from GitHub the most recent version of it.

The framework comprises these main parts:

1. testFramework - You should not need to alter the files in here a lot, and they should be interchangeable as the
   framework evolves
2. testSuite - This is where you build the tests that you want for the system under test
3. configuration - You will want alter the details in here. See the ReadMe.ms file in the ~/src/test/java/configuration
   directory

To prove tou yourself that the framework is operating as expected, run src/test/java/testFramework/FrameworkRunner. This
should generate a target folder and test reports.

> Take note that these smoke tests for the framework can generate false negatives (see the tagging conventions) so as to demonstrate to you that the tests do actually work

## Overview Of The Framework

## Tagging Convention

* @wip - any test that you want to be taken out of service (depending on your runner's parameters)
* @smoke
    * Quick tests that demonstrate the operation of the SUT (NB: in the features at ~
      /src/java/test/testFramework/features treat the framework itself as a SUT).
    * These should all pass, always.
* @pot - Proof of Test
    * These should all result in **false failures**, however, they give confidence that the respective tests are useful
    * Therefore, you'll not want them as part of an automated gateway, but you may want them as part of a smoke test.
* @sniff - Some tests that are known to occasionally produce  **false failures**, but can still be very useful.
    * An example is the automated web-page syntax checker. Sometimes, the page simply cn not be strictly correct by the
      standards applied by the W3C, but is still entirely acceptable. So the page syntax checker can be useful in
      determining the acceptability of a check-in candidate:
        * run @sniff tests as part of the pre-check-in review / regression suite.
        * manually verify any failures,
        * if they are not _real_ failures, then disregard them and complete the check-in.
* @ci - relevant to Continuous Integration
    * You can use these to control an automated CI gateway, provided you exclude any @sniff tests
* @cd - relevant to a continuous deployment gateway 
  * It seems normal to consider ci & cd as a single operation, so it would be normal not to distinguish
  * It would be normal to either use a different branch, or to @wip tests that would prevent deployment (depending ...)
* @slow - this is for your information
  * An example is the W3C link validator
  * It is just there so that you can decide to cut things a little if the tests is taking too long

### Actor

Actors are the thing that establish the connection with the driver (e.g. the Chrome Selenium driver).

## Context

1. Has a handle on the default actor (specified in the test configuration).
1. Provides a place where data can be shared between steps (it is refreshed for each scenario). So you will probably add
   data members to it.

## Steps

* This provides some useful basic steps (that can be used in your SUT tests)

## Object

* This provides some useful basic objects