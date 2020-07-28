Test with BDDMockito in a spring application.<br/>
This example uses a simple @Mock.<br/>
<br/>
compile & test :<br/>
mvn spring-boot:run<br/>
or<br/>
mvn test<br/>

<br/>
--HelloRepositoryImpl.java<br/>
public String get(String myString) {<br/>
&nbsp;&nbsp;return myString+"stringAddedByHelloRepositoryImpl";<br/>
<br/>
--HelloServiceImpl.java<br/>
@Autowired<br/>
HelloRepository helloRepository;<br/>
@Override<br/>
public String get(String myString) {<br/>
&nbsp;&nbsp;return helloRepository.get(myString + "stringAddedFromHelloServiceImpl");<br/>
<br/>
--HelloServiceMockTest.java<br/>
<b>@Mock</b>//the mocked class<br/>
private HelloRepository helloRepository;<br/>
<b>@InjectMocks</b>//the class we inject the mocked class within<br/>
private HelloService helloService = new HelloServiceImpl();<br/>
@Test<br/>
void testReturnedValueWithMockito() {<br/>
&nbsp;&nbsp;//Arrange<br/>
&nbsp;&nbsp;given(helloRepository.get("teststringAddedFromHelloServiceImpl")).willReturn("Hello from Mockito mocked repository");<br/>
&nbsp;&nbsp;//Act<br/>
&nbsp;&nbsp;String actualValue = helloService.get("test");<br/>
&nbsp;&nbsp;//Assert<br/>
&nbsp;&nbsp;String expectedValue = "Hello from Mockito mocked repository";<br/>
&nbsp;&nbsp;assertEquals(expectedValue, actualValue);<br/>
<br/>

@Test<br/>
void testServiceBeanUsesRepositoryBeanWithBDDMockito() {<br/>
&nbsp;&nbsp;//Arrange<br/>
&nbsp;&nbsp;// nothing to arrange...<br/>
&nbsp;&nbsp;//Act<br/>
&nbsp;&nbsp;helloService.get("test");<br/>
&nbsp;&nbsp;//Assert<br/>
&nbsp;&nbsp;then(helloRepository)<br/>
&nbsp;&nbsp;.should()<br/>
&nbsp;&nbsp;.get("teststringAddedFromHelloServiceImpl");<br/>
<br/>

@Test<br/>
void testNumberOfCallsWithMockito() {<br/>
&nbsp;&nbsp;//Arrange<br/>
&nbsp;&nbsp;// nothing to arrange...<br/>
&nbsp;&nbsp;//Act<br/>
&nbsp;&nbsp;helloService.get("test");<br/>
&nbsp;&nbsp;helloService.get("test");<br/>
&nbsp;&nbsp;//Assert<br/>
&nbsp;&nbsp;then(helloRepository)<br/>
&nbsp;&nbsp;.should(Mockito.times(2))<br/>
&nbsp;&nbsp;.get("teststringAddedFromHelloServiceImpl");<br/>
<br/>