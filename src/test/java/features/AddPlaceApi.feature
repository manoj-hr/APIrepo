Feature: Validating the Add Place API
  @AddPlace @Regression
  Scenario Outline: verify if place is been successfully added using AddPlaceApi
    Given add Place payload "<name>" "<phone_number>" "<address>" "<language>"
    When user calls "addPlaceApi" using "Post" http method
    Then API call got success with status code 200
    And "status" in response body is "OK"
    And verify the place_id created for "<name>" using "getPlaceApi"

    Examples:
    |name    |phone_number     |address        |language   |
    |API     |+(91) 127 234 387|Bengaluru      |Kannada-English   |

  @DeletePlace @Regression
  Scenario: verify if delete place Api functionality is working
    Given deletePLace payload
    When user calls "deletePlaceAPi" using "delete" http method
    Then API call got success with status code 200
    And "status" in response body is "OK"
