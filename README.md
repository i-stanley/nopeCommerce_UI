Run tests

Chrome
gradle clean test -Dbrowser=chrome

Firefox
gradle clean test -Dbrowser=firefox

Edge
gradle clean test -Dbrowser=edge

Headless mode
gradle clean test -Dbrowser=chrome -Dheadless=true

Remote execution (Selenoid)
gradle clean test -Dremote=http://localhost:4444/wd/hub -Dbrowser=chrome

📊 Generate Allure Report
allure serve build/allure-results 


Run one test class
gradle clean test --tests com.my.tests.LoginTest

Run one test method
gradle clean test --tests com.my.tests.LoginTest.myTestMethod

Run multiple tests
gradle clean test --tests com.my.tests.LoginTest --tests com.my.tests.CartTest

Run by pattern (regex-like)
Run all tests that contain “Cart”:
gradle clean test --tests *Cart*

Run with browser + remote + single test
Example:
gradle clean test \
--tests com.my.tests.CartTest \
-Dbrowser=firefox \
-Dremote=http://localhost:4444/wd/hub


One test method:
gradle clean test \
--tests com.my.tests.CartTest.onlyRemovesOneItem \
-Dbrowser=chrome