Feature: Availability CRUD

  Scenario: Create a new Availability
    Given a request is made to create an availability
    Then http code status should be 200
    Then the payload is returned for the availability
