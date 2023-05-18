Feature: TC0003_ClinicalSampleLogin_SampleRegistration

  Background:
    Given User navigates to CareDx Url
    And Close warning popup if exists


  Scenario Outline: TC 01 - Verify SampleRegistration
    Then Login to StarLIMS application
    And Select site "<Site>" and role "<Role>" details
    And Click ok button in site page
    And Click ok button in logon history screen
    And Click ClinicalSampleLoginlink in notifications section
    And Verify text ClinicalSampleLogin in Clinical Sample Login page
    And Click NewRequestType and select "<NewRequestType>"
    And Enter Random Accession Number
    And Click Sample Registration Tab
    And Clear Random Accession Number
    And Verify Warning Message For Clearing AccessionID
    And Enter Random Accession Number
    And Click Sample Registration Tab




    Examples:
      | Site              | Role          | NewRequestType      |
      | Brisbane CLIA Lab | Administrator | AlloHeme-Reference  |