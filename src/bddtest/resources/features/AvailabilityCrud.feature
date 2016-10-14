Feature: Availability CRUD

  @availability
  Scenario: Create a new Availability
    Given a request is made to create an availability
    Then http code status should be 200
    Then the payload is returned for the availability

  @wip
  Scenario: Get Availability by Id
    Given an availability exists
    When a request is made to get that availability
    Then http code status should be 200
    Then the payload is returned for the availability
