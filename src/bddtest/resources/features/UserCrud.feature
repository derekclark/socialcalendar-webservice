Feature: User CRUD

  @user
  Scenario: Get User By Id
    Given user exists with email "decla", name "derek" and facebookId "555"
    When a request is made to get user with email "decla"
    Then a http code status of 200 is returned
    Then the payload is returned with email "decla", name "derek", facebookId "555"

  @user
  Scenario: Save User
    Given a request is made to save user with email "decla", name "derek" and facebookId "1234"
    Then a http code status of 200 is returned
    Then the payload is returned with email "decla", name "derek", facebookId "1234"

  @user
  Scenario: Delete a user
    Given user exists with email "decla", name "derek" and facebookId "1234"
    When a request is made to delete user with email "decla"
    Then a http code status of 200 is returned
    And the user with email "decla" does not exist
