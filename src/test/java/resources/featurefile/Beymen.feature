@BeymenPage
Feature: Beymen Test
  As user I want to visit Beymen automation website

  @ShoppingPage
  Scenario Outline: User should verify website and do shopping
    Given I am on homepage
    Then I enter "<row1>", "<row2>" and "<column1>","<column2>" word to searchbox
    Then I pick a random item and add it to cart
    Then I add more item and empty the cart


    Examples:
      | row1 | column1 |  | row2 | column2 |
      | 0    | 0       |  | 1    | 0       |
