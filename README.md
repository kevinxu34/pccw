## Part 1.

### Prerequisites

1. The Chrome browser is installed.
2. The chromedriver is installed in `/usr/local/bin/chromedriver`. If it is installed in other directory, change the line 61 of `src/test/java/pccw/pccwtest/BaiduImageSearch.java`.

### How to use

``./gradlew clean test --info``

The screenshot is saved as `screenshot.png`. 

The image to be searched is `src/test/resources/book.jpg`.

The searched image is saved as `searched_image.jpg`.

The config file is `config.properties`

## Part 2 
The test case file is `Testcase.md`