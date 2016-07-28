Feature: Availability CRUD

  Scenario: Create a new Availability
    Given a new availability with title "new event" owned by user id "decla"
    When a request is made to create the availability
    Then http code status should be 200
    Then the payload is returned with an id greater than 0
