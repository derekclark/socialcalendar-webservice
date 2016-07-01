Feature: Get User By Id

  Scenario: Save and Get User By Id
    Given user exists with email "decla", name "derek" and facebookId "1234"
    When a request is made to get user with email "decla"
    Then the payload is returned with email "decla", name "derek", facebookId "1234" and a http status of 200

  Scenario: Delete a user
    Given user exists with email "decla1", name "derek" and facebookId "1234"
    When a request is made to delete user with email "decla1"
    Then a http code status of 200 is returned
    And the user with email "decla1" does not exist
