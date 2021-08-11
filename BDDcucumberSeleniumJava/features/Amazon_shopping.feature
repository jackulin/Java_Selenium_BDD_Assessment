Feature: 

  Scenario Outline: Signing in
    Given I am on Amazon Homepage '<Url>'
    When I am clicking on signin
    Then provide email '<Email>'
    And click continue
    And provide password '<pass_word>'
    And click signin

    Examples: 
      | Url                    | Email                   | pass_word       |
      | https://www.amazon.in/ | jackulinshanu@gmail.com | Jackulin1998@17 |

  Scenario: Selecting camera
    Given I am in signed in Homepage
    When Searching for camera 'Fujifilm Instax Mini 9 Instant Camera (Ice Blue)'
    Then Navigate to camera page
    And Select camera

  Scenario: Adding camera to cart
    Given I am in selected camera page
    When Click Add to cart
    Then click on refresh and cart symbol

  Scenario: validation of camara name and price
    Given I am in cart page
    When getting its name and price
    Then validating the camara name and price
