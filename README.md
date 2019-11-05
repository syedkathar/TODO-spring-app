<b>TODO simple Spring boot application</b>

Introduction:

This application exposes below RESTful APIs for create/update/delete TODO task list

### Tasks

Method | HTTP Requests | Description | Returns 
------------ | ------------- |-------|----------
*create* | POST 'http://localhost:8080/todo/api/tasklist' | Create a tasklist along with taks | A tasklist with list of tasks 
*list* | GET 'http://localhost:8080/todo/api/tasklist`| List ALL task list along with list of associated tasks | An array of task objects
*list* | GET 'http://localhost:8080/todo/api/tasklist/{id}`| Get specific task list along with list of associated tasks | Task List with array of tasks |
*add/update* |PUT 'http://localhost:8080/todo/api/tasklist/{id}' | Add/Update tasks (ie., Mark task as completed, Tag item, Delete task, restore Task, Add reminder to task | A Task list with updated tasks

##Tools used:

<li>Spring Boot
<li>Hibernate
<li>Spring Boot Data
<li>Spring Boot Security
<li>H2 In-memory database
<li>MapStruct - Mapper API to convert entity to DTO and DTO to entity object.
<li>Mockito - For Integration testing
