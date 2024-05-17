Feature: ComplexDataGet
  Verify Complex Data GET

  @usingComplexPOJO
  Scenario: Verify GET operation complex data
    Given I perform authorization operation for "/auth/login" with body
      |email		|password	|
      |alagu.rar@gmail.com	|haha123	|
    And I perform GET operation with query parameter for address "/location/"
    |id|
    |1 |
    Then I should see the street name as "1st street" for the "primary" address

  @usingComplexPOJO_V2
  Scenario: Verify GET operation complex data using RestAssuredExtensionV2
    Given I perform authorization operation for "/auth/login" with body using RestAssuredExtensionV2
      |email		|password	|
      |alagu.rar@gmail.com	|haha123	|
    And I perform GET operation with query parameter for address "/location/" using RestAssuredExtensionV2
      |id|
      |1 |
    Then I should see the street name as "1st street" for the "primary" address using RestAssuredExtensionV2