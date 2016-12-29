Feature: Availability CRUD

  @availability
  Scenario: Create a new Availability
    Given a request is made to create an availability
    Then http code status should be 200
    Then the payload is returned for the availability

  @availability
  Scenario: Get Availability by Id
    Given an availability exists
    When a request is made to get that availability
    Then http code status should be 200
    Then the payload is returned for the availability

  @availability
  Scenario: Get all my Availabilities
    Given an availabilities exist for a user
    When a request is made to get all that user's availabilities
    Then http code status should be 200
    And the payload contains all the availabilities for that user
