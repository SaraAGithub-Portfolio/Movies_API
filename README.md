# Sara's Movie Application

## Table of Contents
- [Introduction](#introduction)
- [Userstories](#user-stories)
- [Getting Started](#getting-started)
    - [Installation](#installation)
    - [Usage](#usage)
- [Features](#features)
- [References](#references)


## Introduction

This project is a refined implementation of a `Java` and `Spring Boot`-based movie API, utilizing `MongoDB` as the database. It represents an evolution from a basic movie retrieval and review system to a more feature-rich and user-interactive platform. This enhancement stems from the application of knowledge and skills acquired during my time at Digital Futures, where I learned the intricacies of modern software development.

### Purpose and Technology

The primary goal of this project was to build a practical and engaging tool for movie enthusiasts. By leveraging `Java` and `Spring Boot's` robust framework, combined with `MongoDB's` flexibility, the application offers a seamless experience for users to explore, review, and interact with a diverse range of movies.

### Project Enhancements and Features

Significant upgrades have been made to the initial version of this project, with a focus on user engagement and data management:
- **Movie information** : View movie images and trailers when viewing all movies.
- **Advanced Filtering**: Users can now filter movies not just by genre and release date, but also by title, enhancing the searchability and user experience.
- **Comprehensive Reviews**: The platform enables users to view all reviews associated with a movie, accessible via IMDb ID or movie title.
- **User Authentication and Authorization**: A secure system for user registration and login has been implemented, associating reviews with individual usernames.
- **Unit and Integration Testing**: Rigorous testing, including both unit and integration tests, ensures the reliability and stability of the application.
- **Error Handling**: Robust error handling mechanisms are in place for a smoother user experience and easier debugging.

### Future Additions

Looking ahead, there are several exciting features planned for further enhancement:
- **Companion Frontend**: Create the frontend using React
- **Role-Based Access Control (RBAC)**: To introduce different user roles and permissions for a more controlled and secure user environment.
- **Akka Implementation**: For improved scalability and performance.
- **Social Features**: Including movie ratings, comment upvoting, and more interactive elements to foster a community feel.

### Approach and Challenges

The development of this project involved structuring a JSON file, which was then integrated into a MongoDB cluster to create collections for Movies, Reviews, and Users. This structure ensures efficient data retrieval and storage. One of the main challenges encountered was familiarizing myself with new Spring dependencies and utilizing Mockito for testing for the first time. These hurdles were met with a proactive learning approach and practical application of theoretical knowledge.

In terms of security, user passwords are encrypted using Spring's PasswordEncoder, reflecting a commitment to protecting user data and privacy. Learning how to properly implement a SecurityConfig in Spring and Java required a lot of research and time.

### Conclusion

This project not only served as a valuable learning experience but also stands as a testament to the practical application of theoretical concepts in software development. It highlights the importance of continuous learning and adaptation in the field of technology.

## User Stories

1. As a movie enthusiast, I want to view a list of all movies, so that I can explore the movies available in the database.
2. As a user, I want to be able to search for movies by title, so that I can find my favorite movies quickly.
3. As a registered user, I want to be able to write reviews for movies, so that I can share my opinions and experiences with others.
4. As a movie enthusiast, I want to view all reviews for a specific movie, so that I can see what others think about it.
5. As a user, I want to filter movies by genre and release date, so that I can find movies that fit my interests.
6. As a new visitor, I want to register an account, so that I can participate in writing reviews.


## Getting Started(Postman, MongoDb, IntelliJ, Java/Spring)

## Installation

Follow these steps to set up and run the project locally.

### Prerequisites

- [Java 20](https://www.oracle.com/java/technologies/javase/jdk20-archive-downloads.html) - Ensure you have Java 20 installed on your system.
- [Maven](https://maven.apache.org/download.cgi) - The project uses Maven for dependency management.
- [MongoDB](https://www.mongodb.com/try/download/community) - The application requires a MongoDB instance to store data.

### Setting Up the Project

Users have the option of creating their own MongoDb database.

**Clone the Repository**: First, clone the repository to your local machine

### Configure MongoDB Connection
1. **Set Up MongoDB**: Ensure you have MongoDB installed or use an online service like MongoDB Atlas.
2. **Configure Environment Variables**: Copy the `.env.example` file to a new file named `.env` and update the `MONGODB_URI` with your MongoDB connection string.

### Build and Run the Application

- Run `mvn clean install` to build the project.
- Start the application with `mvn spring-boot:run`. Or you can 'Run' the project from the 'MoviesApplication' file.
- The application should now be running on `http://localhost:8080`.
- Tests can be run with `mvn test`

## Using the API with Postman

You can view all movies and reviews based on title, ImdbId, genre or release date.

**However** if you want to post a review, you should register and create a review with `Postman`. 

Below is an  example of how to use the API with Postman for registering a user:

### 1. Register a User

- **Method**: POST
- **URL**: `http://localhost:8080/api/auth/register`
- **Body** (JSON):
  ```json
  {
    "username": "newuser",
    "password": "password123",
    "email": "newuser@example.com"
  }
Please refer to example images if you need further help. This is an example of what you'd see using Postman:
![Postman Register User](https://i.imgur.com/t1lDhQe.png)
![Postman Create Review](https://i.imgur.com/0k5h65l.png)
What you should see in MongoDB:
![MongoDB](https://i.imgur.com/d6iXIlP.png)

## Features

This section outlines the key functionalities of the Movie API, the technology stack used, the rationale behind selecting specific dependencies, and the utilization of Mockito for effective testing.

### Key Functionalities

- **Register**: A User can register with the API to leave a review associated with their name.
- **Movie Browsing**: Users can view a comprehensive list of movies available in the database.
- **Advanced Movie Filtering**: The API provides advanced filtering options, allowing users to filter movies by genre, release date, and title.
- **User Reviews**: Registered users can write and post reviews for movies. These reviews are linked to their usernames, showcasing user engagement and interaction.
- **Review Retrieval**: Users can view all reviews for a specific movie, either by IMDb ID or movie title, providing insights into other users' opinions and experiences.

### Technology Stack

- **Java & Spring Boot**: Leveraging the robustness of Java and the agility of Spring Boot to create a responsive and scalable back-end.
- **MongoDB**: Utilized for its flexibility and performance as a NoSQL database, allowing for efficient data management and retrieval.
- **Spring Security**: Integrated for managing authentication and authorization, ensuring user data protection and privacy.

### Dependency Choices

- **Spring Boot Starter Web**: Chosen for its ease of creating web applications, including RESTful services.
- **Spring Boot Starter Data MongoDB**: Simplifies the integration with MongoDB, providing seamless data handling and persistence.
- **Spring Boot Starter Security**: Essential for implementing secure authentication and authorization functionalities.
- **Lombok**: Used to reduce boilerplate code for model/data objects, improving code readability and efficiency.
- **Spring Boot DevTools**: Facilitates application development with features like automatic restart and live reload.

### Testing with Mockito

- **Why Mockito?**: Mockito is employed for its capabilities in creating mock objects and defining their behavior, enabling the isolation of unit tests from external dependencies.
- **Unit Testing**: Ensures individual components of the application function as expected.
- **Integration Testing**: Validates the integrated components and their interactions within the application.

Through this blend of technologies and methodologies, the Movie API delivers a secure, efficient, and user-friendly platform for movie enthusiasts to explore and engage with movie content.


## References(Documentation)

* Spring Initialzor: https://start.spring.io/
* Exception Handling in Spring: https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc
* Spring security filter chain: https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/annotation/web/builders/HttpSecurity.html
* Mockito: https://site.mockito.org/
* Request Matchers Usage: https://docs.spring.io/spring-security/reference/5.8/migration/servlet/config.html
* MongoDb: mongodb.com
* Postman: It may be best to install `Postman` onto your local machine to test the application : postman.com 
* Java Bean Validation Basics(@NotNull, @Size):https://www.baeldung.com/java-validation#:~:text=%40NotNull%20validates%20that%20the%20annotated,%2C%20Map%2C%20and%20array%20properties.
