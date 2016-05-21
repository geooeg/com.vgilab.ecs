=== Requirements ===

* Apache Tomcat or any other EE7 compatible application server
* PostgreSQL or any other JPA compatible database


=== Database Creation ===
Create a new database for the ECS application. Here is an example for a PostgreSQL database defined in the persistence units.

== PostgreSQL ==
=== Linux ===
Login as the postgres user and start the "psql" tool:

postgres@DatabaseServer:~# psql

Then create the ecsdb:

postgres=# CREATE DATABASE ecsdb;
CREATE DATABASE 

Now we need to create a user with access to the database:

postgres=# CREATE USER ecs WITH PASSWORD 'YOUR_SECURE_PASSWORD'; 
CREATE USER 

and the user needs access to the database:

postgres=# GRANT ALL PRIVILEGES ON DATABASE ecsdb TO ecs; 
GRANT


=== REST API ===

The REST service allows for posting a batch of positions, depending on the configuration it is reachable under:

http://<server_ip>:8080/ecs/positions

The JSON schema to post as a content for the positions is structured like this:

{
  "$schema": "http://json-schema.org/draft-04/schema#",
  "id": "/",
  "type": "object",
  "properties": {
    "maker": {
      "id": "maker",
      "type": "string"
    },
    "model": {
      "id": "model",
      "type": "string"
    },
    "software": {
      "id": "software",
      "type": "string"
    },
    "positions": {
      "id": "positions",
      "type": "array",
      "items": [
        {
          "id": "0",
          "type": "object",
          "properties": {
            "longitude": {
              "id": "longitude",
              "type": "number"
            },
            "latitude": {
              "id": "latitude",
              "type": "number"
            },
            "altitude": {
              "id": "altitude",
              "type": "number"
            },
            "trackedOn": {
              "id": "trackedOn",
              "type": "integer"
            }
          },
          "additionalProperties": false
        },
        {
          "id": "1",
          "type": "object",
          "properties": {
            "longitude": {
              "id": "longitude",
              "type": "number"
            },
            "latitude": {
              "id": "latitude",
              "type": "number"
            },
            "altitude": {
              "id": "altitude",
              "type": "number"
            },
            "trackedOn": {
              "id": "trackedOn",
              "type": "integer"
            }
          },
          "additionalProperties": false
        }
      ],
      "additionalItems": false
    }
  },
  "additionalProperties": false
}

As an example the data for a postion batch would look like this:

{"maker":"Apple","model":"iPhone 5s","software":"iOS 9.1","positions":[{"longitude":9.73322,"latitude":52.37052,"altitude":1223.1212,"trackedOn":1457819036},{"longitude":9.73322,"latitude":52.37052,"altitude":1223.1212,"trackedOn":1457819036}]}