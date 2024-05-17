Feature: using Complex Data Get using Lombok
  Verify Complex Data GET using Lombok

@usingLOMBOK
Scenario: Verify GET operation with bearer authentication token using Lombok
Given I perform authorization operation for "/auth/login" with body using Lombok
|email		|password	|
|alagu.rar@gmail.com	|haha123	|
When I perform GET operation for with token "/posts/1" using Lombok
Then I should see the title as "a title" using Lombok