package com.shivam.jsonassert;

import com.shivam.jsonassert.assertions.JsonUtils;
import com.shivam.model.Address;
import com.shivam.model.Employee;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.boot.test.context.SpringBootTest;

class JsonAssertApplicationTests {

  private static String directory = "/employee";

  @Test
  void testEmployee() {

    Employee employee =
        Employee.builder()
            .id(1)
            .name("Shivam")
            .address(
                Address.builder()
                    .line1("line 3")
                    .line2("line 2")
                    .city("Bengaluru")
                    .country("India")
                    .state("Karnataka")
                    .pincode(560037)
                    .build())
            .build();
    JsonUtils.assertEquals(
        directory + "/employeeExpectedResponse.json", employee, JSONCompareMode.LENIENT);
  }
}
