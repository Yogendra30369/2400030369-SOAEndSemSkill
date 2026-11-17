# Simple User Service – Controller Unit Tests

A beginner-friendly Spring Boot project for testing only the controller layer. The `UserService` dependency is mocked in `UserControllerTest`, so the real service logic is not executed during these tests.

## Requirements
- Java 17
- Spring Tool Suite (STS) / Eclipse with Spring support
- Maven (STS can use the project's Maven configuration)

## Import into Spring Tool Suite
1. Extract `user-service-controller-test.zip`.
2. Open Spring Tool Suite.
3. Select **File → Import → Maven → Existing Maven Projects**.
4. Browse to the extracted `user-service-controller-test` folder.
5. Select `pom.xml`, then click **Finish**.
6. Wait for Maven dependencies to download.

## Run the tests
- Right-click `UserControllerTest.java` → **Run As → JUnit Test**.
- Or right-click the project → **Run As → Maven test**.

## Mocking pattern used
1. `@WebMvcTest(UserController.class)` loads the controller and MVC test support.
2. `@MockBean UserService userService` creates a mock service dependency.
3. `when(...).thenReturn(...)` defines what the mock returns.
4. `mockMvc.perform(...)` calls the controller endpoint.
5. `andExpect(...)` checks the HTTP response and JSON fields.
6. `verify(...)` checks that the controller called the service.

## Endpoints
- `GET /api/users` – get all users
- `GET /api/users/1` – get a user by ID
- `POST /api/users` – add a user; JSON example: `{"name":"Kiran","task":"Design"}`

The `User` class contains only `id`, `name`, and `task`. The service uses an in-memory map so the project can run without a database.
