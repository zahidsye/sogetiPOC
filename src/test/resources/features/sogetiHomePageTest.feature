Feature: Sogeti UI POC


  @RegressionTest
  Scenario: Test Case 1
    Given user launches the application under test
    When user accepts Allow All cookies
    And Hover over Services Link and then Click Automation link.
    Then Verify that Automation Screen is displayed and "Automation" text is visible in Page
    Then Hover again over Services Link and Verify that the Services and Automation are selected

  @RegressionTest
  Scenario: Test Case 2
    Given user launches the application under test
    When user accepts Allow All cookies
    And Hover over Services Link and then Click Automation link.
    And On Automation Page scroll down to the Contact us Form
    And enter contact information data
    And Check the I agree checkbox
    And Click SUBMIT button
    Then After clicking SUBMIT button the form is submitted and Thank you message is displayed. Assert the Thank you message.

  @RegressionTest
  Scenario: Test Case 3
    Given user launches the application under test
    When user accepts Allow All cookies
    And Click the Worldwide Dropdown link in Page Header
    Then Assert that all the Country specific Sogeti links are working

