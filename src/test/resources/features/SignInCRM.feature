Feature: Sign in page CRM

  @Regression
  Scenario Outline: Sign in CRM with an email valid
    Given User navigate to url "<url>"
    When User login with valid username "<username>" and password "<password>"
    Then The user redirect to Dashboard page
    Examples:
      | url                                | username       | password |
      | https://rise.fairsketch.com/signin | admin@demo.com | riseDemo |

  @Smoke
  Scenario Outline: Sign in CRM with an email invalid
    Given User navigate to url "<url>"
    When User login with invalid username "<username>" and password "<password>"
    Then The error message is displayed
    Examples:
      | url                                | username          | password |
      | https://rise.fairsketch.com/signin | admin123@demo.com | riseDemo |


  @Regression
  Scenario Outline: Sign in CRM with an email valid
    Given User navigate to url "<url>"
    When User login with valid username "<username>" and password "<password>"
    Then The user redirect to Dashboard page
    Examples:
      | url                                | username       | password |
      | https://rise.fairsketch.com/signin | admin@demo.com | riseDemo |

  @Smoke
  Scenario Outline: Sign in CRM with an email invalid
    Given User navigate to url "<url>"
    When User login with invalid username "<username>" and password "<password>"
    Then The error message is displayed
    Examples:
      | url                                | username          | password |
      | https://rise.fairsketch.com/signin | admin123@demo.com | riseDemo |

  @Regression
  Scenario Outline: Sign in CRM with an email valid
    Given User navigate to url "<url>"
    When User login with valid username "<username>" and password "<password>"
    Then The user redirect to Dashboard page
    Examples:
      | url                                | username       | password |
      | https://rise.fairsketch.com/signin | admin@demo.com | riseDemo |

  @Smoke
  Scenario Outline: Sign in CRM with an email invalid
    Given User navigate to url "<url>"
    When User login with invalid username "<username>" and password "<password>"
    Then The error message is displayed
    Examples:
      | url                                | username          | password |
      | https://rise.fairsketch.com/signin | admin123@demo.com | riseDemo |