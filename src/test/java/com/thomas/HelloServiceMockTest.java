package com.thomas;

import com.thomas.repository.HelloRepository;
import com.thomas.services.HelloService;
import com.thomas.services.HelloServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@SpringBootTest
public class HelloServiceMockTest {

    @Mock
    private HelloRepository helloRepository;

    @InjectMocks
    private HelloService helloService = new HelloServiceImpl();

    @Test
    void testReturnedValueWithMockito() {
    	//Arrange
    	given(helloRepository.get("teststringAddedFromHelloServiceImpl")).willReturn("Hello from Mockito mocked repository");
    	//Act
    	String actualValue = helloService.get("test");
    	//Assert
    	String expectedValue = "Hello from Mockito mocked repository";
        assertEquals(expectedValue, actualValue);
    }
    
    @Test
    void testServiceBeanUsesRepositoryBeanWithBDDMockito() {
    	//Arrange
    	// nothing to arrange...
    	//Act
    	helloService.get("test");
    	//Assert
    	then(helloRepository)
    	.should()
    	.get("teststringAddedFromHelloServiceImpl");
    }
    
    @Test
    void testNumberOfCallsWithMockito() {
    	//Arrange
    	// nothing to arrange...
    	//Act
    	helloService.get("test");
    	helloService.get("test");
    	//Assert
    	then(helloRepository)
    	  .should(Mockito.times(2))
    	  .get("teststringAddedFromHelloServiceImpl");
    }
}