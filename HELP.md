# Spring Boot september 2024

<br/><br/>
<br/><br/>
<br/><br/>
<br/><br/>

## Json serialization (annotations in model classes)
* [https://www.baeldung.com/jackson-annotations](https://www.baeldung.com/jackson-annotations)

<br/><br/>
<br/><br/>
<br/><br/>
<br/><br/>

## Java Faker dependency
```xml
		<dependency>
			<groupId>com.github.javafaker</groupId>
			<artifactId>javafaker</artifactId>
			<version>1.0.2</version>
		</dependency>
```

```java
public class Util {

    private static Faker faker = new Faker();

    public static String getFakeFirstName(){

        return faker.name().firstName();

    }

    public static String getFakeFullName(){

        return faker.name().fullName();

    }
    public static String getFakeAddress(){

        return faker.address().fullAddress();

    }


    public static LocalDateTime getLocalDateTime(){

        DateAndTime dateAndTime = faker.date();

        LocalDateTime localDateTime = dateAndTime
                .birthday()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();


        return localDateTime;

    }


    public static int getFakeAge() {

        Random r = new Random();
        int low = 18;
        int high = 88;
        int age = r.nextInt(high - low) + low;
        return age;
    }

}
```

## Swagger annotations
* [https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Annotations](https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Annotations)

<br/><br/>

* @Tag

```java
@RestController
@RequestMapping("/api")
@Tag(name = "API for customer financial transactions",
     description = "This API offers all services needed to handle customer financial transactions")
public class TransactionController {
   ...
}
```

<br/><br/>

* @Operation
* @ApiResponses

```java
@Operation(summary = "Send transaction as JSON and store in database")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Message confirming transaction done"),
    @ApiResponse(responseCode = "500", description = "Invalid call", content = @Content),
})
@PutMapping(value = "/transaction/json", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Message> transactionAsJson(@RequestBody Transaction transaction) {

    logger.info( transaction.toString());
    transactionRepository.save( transaction);
    return new ResponseEntity<>(new Message("Transaction done"), HttpStatus.OK);
    }
```

<br/><br/>

## Spring Context

```java

        ApplicationContext applicationContext = SpringApplication.run(MolvenoLakeResortApplication.class, args);

        // Show what is autowired into ReservationController
        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
            if (beanDefinitionName.startsWith("??????")) {
                Object object = applicationContext.getBean(beanDefinitionName);
                System.out.println("Stop here");
            }
        }
```



