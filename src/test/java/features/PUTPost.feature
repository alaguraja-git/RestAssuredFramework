Feature: PUTPost
  Verify PUTPost operation

  @smoke11
  Scenario: Verify PUT Operation after POST
    Given I ensure to perform POST operation for "/posts" with the body as
      |id   |title        |views     |
      | 11 | my 11 title| 10000    |
    And I perform PUT Operation for "/posts/{postid}/"
      |id   |title        |views     |
      | 11 | my 11 | 10000    |
    And I perform GET Operation with path param for "/posts/{postid}/"
      |postid|
      |11     |
    Then I "should" see the body with the title as "my 11"
