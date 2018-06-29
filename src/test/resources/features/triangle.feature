Feature: Triangle test
  Test the triangle with different inputs and verify maximum sum is calculated correctly

  Scenario: Verify maximum sum calculation of a small triangle
    Given Input data as below
      | 3       |
      | 7 4     |
      | 2 4 6   |
      | 8 5 9 3 |
    When build triangle with input data
    Then Maximum sum of the triangle should be 23
    And Highest path is "3 + 7 + 4 + 9"

  Scenario: Verify maximum sum calculation of a medium triangle
    Given Input data as below
      | 3                     |
      | 7  4                  |
      | 2  4   6              |
      | 8   5  9   3          |
      | 9  10  11  12 13      |
      | 13  14  15  16  17 18 |
    When build triangle with input data
    Then Maximum sum of the triangle should be 52
    And Highest path is "3 + 7 + 4 + 9 + 12 + 17"

  Scenario: Verify maximum sum calculation of a large triangle
    Given Input data as below
      | 75                                                         |
      | 95  64                                                     |
      | 17  47  82                                                 |
      | 18  35  87  10                                             |
      | 20  04  82  47  65                                         |
      | 19  01  23  75  03  34                                     |
      | 88  02  77  73  07  63  67                                 |
      | 99  65  04  28  06  16  70  92                             |
      | 41  41  26  56  83  40  80  70  33                         |
      | 41  48  72  33  47  32  37  16  94  29                     |
      | 53  71  44  65  25  43  91  52  97  51  14                 |
      | 70  11  33  28  77  73  17  78  39  68  17  57             |
      | 91  71  52  38  17  14  91  43  58  50  27  29  48         |
      | 63  66  04  68  89  53  67  30  73  16  69  87  40  31     |
      | 04  62  98  27  23  09  70  98  73  93  38  53  60  04  23 |
    When build triangle with input data
    Then Maximum sum of the triangle should be 1074
    And Highest path is "75 + 64 + 82 + 87 + 82 + 75 + 73 + 28 + 83 + 32 + 91 + 78 + 58 + 73 + 93"
