Feature: Para bank login

  Background:
    Given User navigated to para bank application
    Then Validate login page title "ParaBank | Welcome | Online Banking"

  @Smoke
  Scenario: Verify valid login functionality
    When User login with valid username "marc.sandy" and password "Test@12"
    Then Validate account page title "ParaBank | Accounts Overview"
    Then User logs out of application

  @Smoke
  Scenario: Verify invalid login functionality
    When User login with valid username "marc.sandy" and password "Test"
    Then Validate authentication error "An internal error has occurred and has been logged."
    Then Validate authentication error page title "ParaBank | Error"