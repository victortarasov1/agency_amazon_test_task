### Test Task for Java Developer

The project contains a Spring Boot RESTful API that updates statistics in a MongoDB database and caches responses.

Key features implemented:

- User registration and authentication.
- CRUD operations on users.
- Retrieval of statistics for a specified date (or date range).
- Retrieval of statistics for a specified ASIN (or list of ASINs).
- Retrieval of aggregated statistics for all dates.
- Retrieval of aggregated statistics for all ASINs.
- Statistics are updated from the 'test_report.json' file every 10 minutes (you can edit the filename and path by modifying the batch.properties file in the batch module).


Project requirements fulfilled:
- Spring Security (JWT) for authentication (you can customize settings by editing the security.properties file in the security module).
- Spring Cache for caching.
- Database: MongoDB (you can configure access to your database by editing the properties file in the repository module).
- Authentication: Spring Security (JWT).
- Caching: Spring Cache.

### Dependencies

- Java 17
- Gradle
- MongoDB
- Postman (optional)
- Docker (optional)


### RUN

1. Navigate to the project directory.
2. Execute the command to build the project using Gradle:
    ```
    gradle build
    ```
3. After a successful build, run the application by executing the command and providing the path to the controller's JAR file:
    ```
    java -jar controller/build/libs/controller-0.0.1-SNAPSHOT.jar
    ```

### Additional Step (Optional)

If you don't have MongoDB installed locally, you can deploy it in a Docker container using the Bash script provided in the `docker` directory. Please follow these steps:

1. Navigate to the `docker` directory.
2. Execute the Bash script to start MongoDB in a Docker container:
    ```
    bash runMongo.sh
    ```



### API for Managing User Accounts

1. **Create Account**
    - `POST /account/add`: Creates a new user account.

2. **Get Account Information**
    - `GET /account/get`: Retrieves information about the current user's account. (Requires authorization)

3. **Update Account**
    - `PATCH /account/update`: Updates information about the user's account. (Requires authorization)

4. **Delete Account**
    - `DELETE /account/delete`: Deletes the current user's account. (Requires authorization)

### API for Retrieving Sales and Traffic Statistics

1. **Get Statistics by Identifier**
    - `POST /report/id`: Retrieves sales and traffic statistics based on the provided identifier, either by dates or by ASIN. (Requires authorization)

2. **Get Statistics by Date Range or ASIN**
    - `POST /report/range`: Retrieves a list of sales and traffic statistics for the specified date range or ASIN list. (Requires authorization)

3. **Get Statistics for All ASINs and All Dates**
    - `POST /report/all`: Retrieves a list of all available sales and traffic statistics for all ASINs or all dates. (Requires authorization)

### API for User Authentication

1. **Login**
   - `GET /login`: Authenticates the user and generates a JWT (access and refresh) tokens for authorization.

2. **Refresh Token**
   - `GET /refresh`: Refreshes the user's JWT access token for extended session validity. (Requires authorization)

### Postman Collection
A Postman collection for interacting with the API is available in the `postman` directory.

### Used Technologies

- **Spring Data MongoDB**
- **Spring Security**
- **Spring Web MVC**
- **Spring Cache**
- **Hibernate Validator**
- **Lombok**
- **Mockito Core**
- **AssertJ Core**
- **Java JWT (Auth0)**


### Server build:
- **Gradle**


