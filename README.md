# Java Spring Boot Example

This is a Java Spring Boot example project that implements a Library service.

## Database setup

### Create local Microsoft SQLServer

For this project, a Microsoft SQLServer Database was used. The Express edition can be downloaded from [microsoft.com/sql-server/sql-server-downloads](https://www.microsoft.com/en-us/sql-server/sql-server-downloads). Additionally, to manage the database, the [SQL Server Management Studio (SSMS)](https://learn.microsoft.com/en-us/sql/ssms/download-sql-server-management-studio-ssms?view=sql-server-ver16&redirectedfrom=MSDN#download-ssms) can be installed.

You can start and stop the server using the program "Sql Server Configuration Manager". In the section `SQL Server Services` you can set the start mode also to `Manual` in `Server Properties > Service` for your server.


### Create database `library`

The database can be created with the script in [`data/database.sql`](data/database.sql).

### Create a user for the database

To later use a login/password to access the database, you can add a specific database user `library_user`. Make sure that under `Server Properties > Security` the option `SQL Server and Windows Authentication mode` is selected, so that subsequent connections to the server can be done with this user. Note that after changing this option, you will have to restart the server (which can be done using "Sql Server Configuration Manager"). Additionally, make sure that the port 1433 is available. This can be done via the "Sql Server Configuration Manager" under `SQL Server Network Configuration > TCP/IP > Properties > IP Addresses` and then go to `IPALL > TCP port` and add 1433. Click apply, make sure TCP/IP is enabled and then restart the server.


```sql
CREATE LOGIN library_user WITH PASSWORD = 'db_password';

USE library;
CREATE USER library_user FOR LOGIN library_user;
```


## Configuration

Add a properties file called `application-secrets.properties` in the root of the project with the user and password:

```
spring.datasource.username=library_user
spring.datasource.password=db_password
```
