Feature: Sogeti API POC

  @RegressionTest
  Scenario: API Test Case 1

    Given I hit the Get API
    Then Verify that the response status code is 200 and content type is JSON
    And Verify that the response time is below 1s

  @RegressionTest
  Scenario: API Test Case 2
    Given I hit the Get API
    Then Verify in Response - That country is "Germany" and state is "Baden-Württemberg"
    And Verify in Response For Post Code "70597" the place name has "Stuttgart Degerloch"

  @RegressionTest
  Scenario Outline: API Test Case 3
    Given I hit the Get API with "<Country>" and "<Postal_Code>"
    Then Verify that the response status code is 200 and content type is JSON
    And Verify in Response - "<Place_Name>" for each input Country and Postal Code
    And Verify that the response time is below 1s

    Examples:
      | Country | Postal_Code | Place_Name    |
      | us      | 90210       | Beverly Hills |
      | us      | 12345       | Schenectady   |
      | ca      | B2R         | Waverley      |



