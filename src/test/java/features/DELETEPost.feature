Feature: DELETEPosts
Delete Scenario
  @smoke11
  Scenario: Verify Delete Operation after POST
    Given I ensure to perform POST operation for "/posts" with the body as
    |id   |title        |views     |
    | 100 | my 100 title    | 10000    |
    And I perform DELETE Operation for "/posts/{postid}/"
    |postid|
    |100   |
    And I perform GET Operation with path param for "/posts/{postid}/"
    |postid|
    |100   |
    Then I "should not" see the body with the title as "my 100 title"