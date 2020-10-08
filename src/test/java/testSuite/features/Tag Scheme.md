Read this in conjunction with the TestRunner.

* Feature file naming
Whilst it is good practice to make all test independent, I have the standards and nav tests run first so that, should these and subsequent test fail, you could be given a clue as to whether the subsequent failures are worth investigating.

* @smoke
  * These are of use, as a one-off, if a big change has been made.
  * They are so simple, that it is fast
  * The things tested are (effectively) tested many times in the more extensive functional tests. So it is as well to exclude @smoke from the main testing effort.

* @standards
  * Always use this in formal testing (hence the separate tag)   