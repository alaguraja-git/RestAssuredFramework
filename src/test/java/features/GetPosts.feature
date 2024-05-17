Feature:
  Verify different GET operation using Rest Assured

  Scenario: verify one title of the post
    Given I perform GET operation for "/posts"
    Then I should see the title as "a title"

  Scenario: verify collection of title of the post
    Given I perform GET operation
    Then I should see the title names

  Scenario: verify path parameter of GET
    Given I perform GET operation
    Then I should see verify GET parameter

  Scenario: verify query parameter of GET
    Given I perform GET operation
    Then I should see verify GET query parameter

  Scenario: verify Post operation
    Given I perform POST operation for "/posts"

  @smoke
  Scenario: Verify GET operation with bearer authentication token
    Given I perform authorization operation for "/auth/login" with body
      |email		|password	|
      |alagu.rar@gmail.com	|haha123	|
    When I perform GET operation for with token "/posts"
    Then I should see the author name as "a title"


  @usingPOJO
  Scenario: Verify GET operation with bearer authentication token using POJO
    Given I perform authorization operation for "/auth/login" with body
      |email		|password	|
      |alagu.rar@gmail.com	|haha123	|
    When I perform GET operation for with token "/posts/1"
    Then I should see the title as "a title"

  @usingSchemaValidation
  Scenario: Verify GET operation with bearer authentication token for json schema validation
    Given I perform authorization operation for "/auth/login" with body
      |email		|password	|
      |alagu.rar@gmail.com	|haha123	|
    When I perform GET operation for with token "/posts/1"
    Then I should see the title as "a title" with json validation