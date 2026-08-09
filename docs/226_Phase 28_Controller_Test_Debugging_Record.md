# AICIP -- Phase 28 Controller Layer Testing

## MockMvc Register User Test -- Debugging Record

### Status

-   Phase 28.1 -- Controller Test Setup: ✅ Complete
-   Phase 28.2 -- `registerUser()` Success Test: ✅ Complete
-   Phase 28.3 -- `registerUser()` Validation/Failure Test: ⏭️ Next

------------------------------------------------------------------------

# 1. The Problem

The test was:

``` text
UserControllerTest
└── registerUser_ShouldReturnCreated()
```

The expected result was:

``` text
HTTP 201 CREATED
```

but MockMvc returned:

``` text
HTTP 200 OK
```

The important part of the error was:

``` text
Handler:
    Type = null

MockHttpServletResponse:
    Status = 200
    Body =
```

The assertion then failed:

``` text
Status expected:<201> but was:<200>
```

This was the key diagnostic information.

------------------------------------------------------------------------

# 2. Why This Was Confusing

The actual `UserController` was already correct.

It contains:

``` java
@RequestMapping("/api/users")
```

and:

``` java
@PostMapping("/register")
```

and the registration method returns:

``` java
new ResponseEntity<>(response, HttpStatus.CREATED);
```

Therefore, if `UserController.registerUser()` is executed, the response
should be `201 CREATED`.

The trace instead showed:

``` text
Handler:
    Type = null
```

This means the MockMvc request was not being handled by
`UserController`.

The test was therefore failing before the controller method could
provide its `201` response.

------------------------------------------------------------------------

# 3. First Test Configuration

The test originally used:

``` java
@SpringBootTest
@AutoConfigureMockMvc
```

This starts the complete Spring Boot application context.

That means the controller test also became dependent on:

-   Spring Boot application configuration
-   Security configuration
-   JPA
-   Hibernate
-   MySQL
-   repositories
-   JWT components
-   mail configuration
-   other application beans

This caused additional unrelated problems while debugging the controller
test.

The trace confirmed that the test was bootstrapping the full
application:

``` text
SpringBootTestContextBootstrapper
Found @SpringBootConfiguration com.sankar.aicip.BackendApplication
```

------------------------------------------------------------------------

# 4. Spring Boot 4 / Jackson Issue Encountered

The project uses:

``` text
Spring Boot 4.1.0
```

The classpath contains Jackson 3 for Spring Boot's web stack:

``` text
tools.jackson.core:jackson-databind:3.1.4
```

The project also has Jackson 2 artifacts transitively through other
dependencies.

An earlier version of the controller test tried to inject:

``` java
com.fasterxml.jackson.databind.ObjectMapper
```

through Spring.

That produced an ObjectMapper bean problem because the Spring Boot 4 web
stack is based on Jackson 3.

This was another reason to avoid unnecessary Spring context dependencies
in this particular test.

------------------------------------------------------------------------

# 5. Final Solution

The successful solution was to use MockMvc's standalone setup.

Instead of asking Spring Boot to discover and start the complete
application, the test directly creates the controller and gives it a
mocked `UserService`.

The important setup is:

``` java
userService = mock(UserService.class);

UserController userController =
        new UserController(userService);

mockMvc = MockMvcBuilders
        .standaloneSetup(userController)
        .build();
```

This makes the test directly target:

``` text
UserController
```

and avoids unrelated application startup problems.

The test also creates its own ObjectMapper:

``` java
objectMapper = new ObjectMapper();
```

Therefore Spring does not need to provide an ObjectMapper bean for this
test.

------------------------------------------------------------------------

# 6. Why the Final Solution Works

The request:

``` text
POST /api/users/register
```

is now handled directly by the actual:

``` java
UserController
```

The controller contains:

``` java
@PostMapping("/register")
```

so MockMvc can resolve the request to the controller method.

The mocked service returns the expected `UserResponse`.

The controller then returns:

``` text
HTTP 201 CREATED
```

The test verifies:

``` text
id
fullName
email
phoneNumber
```

Therefore the test now passes.

------------------------------------------------------------------------

# 7. Final Test Architecture

The successful test has this flow:

``` text
JUnit 6
   │
   ▼
UserControllerTest
   │
   ├── MockMvc
   │
   ├── UserController
   │       │
   │       └── mocked UserService
   │
   └── ObjectMapper
```

It does NOT require:

``` text
MySQL
Hibernate
JPA
Repository
Full Spring Boot Application Context
```

for this controller test.

------------------------------------------------------------------------

# 8. Important Debugging Lesson

The most important clue was:

``` text
Handler:
    Type = null
```

When a MockMvc request returns `200` with an empty response and the
handler is `null`, do not immediately debug the controller's return
statement.

First verify:

``` text
Was the controller actually registered in MockMvc?
```

In this case, it was not being used by the failing test configuration.

------------------------------------------------------------------------

# 9. Verification

The final IntelliJ result showed:

``` text
UserControllerTest
    ✓ registerUser_ShouldReturnCreated()
```

Therefore:

``` text
Phase 28.2 – registerUser() Success Test
```

is confirmed as:

``` text
✅ PASSED
```

The successful result is visible in the uploaded IntelliJ screenshots.

------------------------------------------------------------------------

# 10. What We Should NOT Change

The successful test does not require changing:

``` text
UserController.java
SecurityConfig.java
application.properties
application-local.properties
MySQL configuration
Hibernate configuration
UserServiceImpl.java
```

The problem was isolated to the controller test setup.

------------------------------------------------------------------------

# 11. Current Phase Status

## Phase 28 -- Controller Layer Testing (MockMvc)

### 28.1 -- Controller Test Setup

✅ Complete

### 28.2 -- `registerUser()` Success Test

✅ Complete

Verified test:

``` text
registerUser_ShouldReturnCreated()
```

Result:

``` text
PASS
```

### 28.3 -- `registerUser()` Validation/Failure Test

⏭️ Next

------------------------------------------------------------------------

# 12. Phase 28.3 -- Next Step

The next test should verify that invalid registration data is rejected
by the controller's:

``` java
@Valid
@RequestBody
```

For example, we can test an invalid request such as an empty full name
or invalid email.

The goal is to verify:

``` text
Invalid request
      ↓
Bean Validation
      ↓
Controller rejects request
      ↓
Expected 4xx response
```

We should first inspect the existing `UserRegistrationRequest`
validation annotations and the project's exception/error handling before
writing the test.

This prevents us from guessing the expected status code.

------------------------------------------------------------------------

# Final Conclusion

The two-day controller test issue was caused by the test setup rather
than the registration controller itself.

The decisive evidence was:

``` text
Handler:
    Type = null
```

The reliable fix was:

``` java
MockMvcBuilders
        .standaloneSetup(userController)
        .build();
```

with a mocked:

``` java
UserService
```

The final test now passes.

**Phase 28.2 is complete.**

**Next: Phase 28.3 -- `registerUser()` Validation/Failure Test.**
