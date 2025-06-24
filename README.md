# hicru-assessment-backend

A RESTFul API for Create/Read/Update/Delete position from h2 database.

If you need to hook up a mysql database, you just need to change the string connection and the driver.

Local Development
=======

1. Install java 21.
2. [Optional] Install maven 3.9, this is necessary is you want to build and run the all manually.
3. [Optional] Install docker. The API run in a container using Docker for deploying proposes.
4. [Optional] Install IntelliJ Community Edition (Free edition). You can install your favorite IDE.
5. Clone repository https://github.com/JuanCarlosNH/hicru-assessment-backend
6. Export the project with your IDE
7. Run the app with your IDE. The entry point of the app is the AssessmentApplication class. Location src/main/java/com.hikru.assessment/AssessmentApplication.java

End points
========

Note: All end points require the `X-Api-Key` header, the value of this header is: `8qLP7pS52L36SwcykStk40WlJCfsEH4AHH9TlhnFVuw28AEWnYeCpoYO1ETLEFFJgZSltW9CENdey2vVWFEuDtBRHm5wWYyAqiWAYG7GIVL7MByUC4nQpj6rHMXcCH0U`

* PositionController

##### GET `/api/positions`

* Returns a list of positions

* Example response body

````
[
    {
        "title": "Software Engineer I",
        "description": "Master in Java and bankend technologies",
        "location": "Mexico city",
        "status": "open",
        "recruiterId": 1,
        "departmentId": 1,
        "budget": 90000.0,
        "closingDate": "2025-07-20T00:00:00.000+00:00",
        "id": 1
    },
    {
        "title": "Software Engineer II",
        "description": "Master in Java and bankend technologies",
        "location": "Mexico city",
        "status": "open",
        "recruiterId": 2,
        "departmentId": 2,
        "budget": 100000.0,
        "closingDate": "2025-07-20T00:00:00.000+00:00",
        "id": 2
    },
    {
        "title": "Software Engineer III",
        "description": "Master in Java and bankend technologies",
        "location": "Mexico city",
        "status": "open",
        "recruiterId": 3,
        "departmentId": 3,
        "budget": 110000.0,
        "closingDate": "2025-07-20T00:00:00.000+00:00",
        "id": 3
    }
]
````

##### GET `/api/positions/{id}`

* Returns a list of positions

* Path variables
** id: id of the position

* Example response body
````
{
    "department": {
        "id": 3,
        "name": "Human Resources"
    },
    "recruiter": {
        "id": 3,
        "name": "Gonzalo",
        "lastname": "Lira"
    }
}
````

##### PUT `/api/positions/{id}`

* Returns a list of positions

* Path variables
  ** id: id of the position

* Example response body
````
{
    "id": 4,
    "title": "title1",
    "description": "description1",
    "location": "location1",
    "status": "closed",
    "budget": 90000,
    "closingDate": "2025-06-21",
    "recruiterId": 3,
    "departmentId": 2
}
````

##### POST `/api/positions`

* Save a new position

* Example request body

````
{
    "title":"Software Engineer V",
    "description":"Master in Java and bankend technologies",
    "location":"Mexico City",
    "status":"open",
    "budget":140000.0,
    "closingDate":"2025-07-30",
    "recruiterId":"3",
    "departmentId":"3"
}
````

##### DELETE `/api/positions/{id}`

* Returns a list of positions

* Path variables
  ** id: id of the position