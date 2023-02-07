# speaking-clock
This project converts 24hr time input to words / readable text

1. Configurations is simple, Application runs on available/configurable port using tomcat if default port is busy else run on defualt tomcat port
2. Postman Collection has been attached (see assets/postman_collection), please import it into your app and hit the server @base_url/speakingclock
3. Attachments of sample runs are added in assets/images
4. Custom Exception Handling has also bee added and implemented
5. This project uses Java 8, spring web, lombok libs (to reduce boilerplate code) , please see the dependencies in pom.xml
6. This project maintains the legacy of MVC Design Pattern
7. All the Response have to pass through the Base Controller for standard handling, It also follows the standard Response Structure
8. When input time is invalid in any form then only current time taken from system in words is set in response, input time in words is NOT set
9. TO run the project, please add it as a Maven project in your IDE and let the IDE download desired dependencies to run this application
10 I have tried to make to code readableby using inline comments and good coding practices
