Feature: Get User By Id

  Scenario: Get User By Id
    Given user 'decla' exists
    When a request is made to get user 'decla'
    Then the user 'decla' is returned and a http status of 200 is returned
