Feature: PostProfile
  Test Post operation using REST-assured
@smoke11
Scenario: verify Post operation for the Profile
    Given I perform POST operation for "/posts/{profileNo}/profile" with body
      |name|profileNo|
      |sams|2 |
    Then I should see the body has title as "sams"
