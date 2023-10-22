Feature: Login page aplikasi demo
  Scenario: Success login
    Given Homepage login
    When Input username
    And Input password
    And click login button
    Then user in dashboard page

    Scenario: failed login pasuser kosong
      Given Homepage login
      And click login button
      Then user in message pasuser kosong

      Scenario: failed login usertypo
        Given Homepage login
        When Input tipousername
        And Input password
        And click login button
        Then user in message typo

        Scenario: failed userregister password kosong
          Given Homepage login
          When Input username
          And Input password kosong
          And click login button
          Then user in message userregist

          Scenario:  failed user locked_out
            Given Homepage login
            When Input username locked
            And Input password
            And click login button
            Then user in message locked
