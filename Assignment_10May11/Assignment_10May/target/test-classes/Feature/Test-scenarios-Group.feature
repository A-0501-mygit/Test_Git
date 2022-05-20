Feature: get groups

  Scenario Outline: Anjana_Test_Suite
    Given Send a request to get the group details with <Name> and <Amps>
    Then Response should be ok
    Examples:
      | Name | Amps|
      | Anjana_Group_1| 100|

  Scenario Outline: Anjana_Test_Suite
    Given Send request to create Connector with <Name>,<Amps>,<StationName> and <ConnectorAnmps>
    Then Corresponding Connector, charge station and group is created
    Examples:
      | Name | Amps | StationName | ConnectorAnmps |
      |Anjana_Group_2|100.0|Station1|20.0       |