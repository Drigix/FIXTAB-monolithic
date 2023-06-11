# Repair Management System

## Description
The application allows for defining users in specific roles. The defined users have access to customer records, records of repairable items of specific types, and employee records (repair managers, task performers). Users, according to their assigned roles, handle the lifecycle of repairs and tasks. The system enables reporting repairs, planning repairs, canceling repairs, and carrying out repairs through a sequence of tasks.

## Technologies
The applied technologies and tools include Angular, Java, Spring Boot, Spring Data, JPA + PostgreSQL.

## Deployment
To run the application, the following software is required, at the specified or higher versions:
- JDK v17.0.7
- Node.js v14.17.3

### Server
1. Open a terminal.
2. Navigate to the server directory.
3. Run the following command to start the server on port 8080:

```
java -jar <server_path>/app.jar
```

4. Ensure that you have a .env file in the server directory with the following environment variables, specifying the name and user of the PostgreSQL database:

```
DB_USERNAME=...
DB_PASSWORD=...
```

### Client
1. Open a separate terminal.
2. Navigate to the client directory.
3. Run the following commands to install dependencies and start the client on port 4200:

```
cd <client_path>
npm i
npm start
```


Make sure to replace `<server_path>` and `<client_path>` with the actual paths to your server and client directories, respectively.

Feel free to add any additional insights or comments of your own.
