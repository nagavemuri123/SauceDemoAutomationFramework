Feature: Add To Cart the Highest Priced Product

  Background:
    Given User navigates to Swag Labs Url

  Scenario: TC 01 - Verify Highest Value Product and Add To Cart
    When User enters username and password in Swag login page
    And Click on Login button
    Then Verify Swag Labs Home page is displayed
    And Verify products listed in Home page
    Then Select the highest price product in Home page
    And Add the selected highest price product to the cart
    Then Verify product is added to shopping cart
