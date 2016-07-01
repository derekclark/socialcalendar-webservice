Feature: Get User By Id

  Scenario: Get User By Id
    Given user exists with email "decla", name "derek" and facebookId "1234"
    When a request is made to get user with email "decla"
    Then the payload is returned with email "decla", name "derek", facebookId "1234" and a http status of 200
