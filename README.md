# Java Spring Boot Example

This is a Java Spring Boot example project that implements a Library service.

## Database setup

### Microsoft SQLServer

For this project, a Microsoft SQLServer Database was used. The Express edition can be downloaded from [microsoft.com/sql-server/sql-server-downloads](https://www.microsoft.com/en-us/sql-server/sql-server-downloads). Additionally, to manage the database, the [SQL Server Management Studio (SSMS)](https://learn.microsoft.com/en-us/sql/ssms/download-sql-server-management-studio-ssms?view=sql-server-ver16&redirectedfrom=MSDN#download-ssms) can be installed.

#### Create database `library`

The database can be created with the script in [`data/database.sql`](data/database.sql).

#### Create a user for the database

To later use a login/password to access the database, you can add a specific database user `library_user`.

```sql
USE library;

CREATE LOGIN library_user WITH PASSWORD = 'db_password';
CREATE USER library_user FOR LOGIN library_user;
```

## Configuration

Add a properties file called `application-secrets.properties` in the root of the project with the user and password:

```
spring.datasource.username=library_user
spring.datasource.password=db_password
```
