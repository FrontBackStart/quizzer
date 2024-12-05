[Backlog](https://github.com/orgs/FrontBackStart/projects/1/views/1)

This is a web app made for Haaga-Helia UAS course [Ohjelmistoprojekti 1 (Software Development Project 1)](https://software-development-project-1.github.io/).

Deployment:

Backend (teacher dashboard): https://quizzer-zxp3.onrender.com

Frontend: https://quizzer-front.onrender.com/

# Quizzer

- **Create Quizzes**: Teachers can easily create quizzes with multiple choice, true/false, and short-answer questions.
- **Save Quizzes**: Save quizzes for future use, making it easy to reuse quizzes without having to recreate them.
- **Student Assignments**: Assign quizzes to individual students or groups for assessment.
- **Quiz Review**: Review students' answers and provide feedback.
- **Analytics**: Track student performance and identify areas where they might need additional support.

## How It Works

1. **Sign Up / Log In**: Teachers sign up or log in to their account.
2. **Create a Quiz**: Use the simple editor to create a quiz with questions of different types.
3. **Assign the Quiz**: Select the students or groups who will take the quiz.
4. **Track Progress**: After students take the quiz, teachers can review their answers and see performance metrics.
5. **Reuse Quizzes**: Save and reuse quizzes for future classes or assignments.

## REST API DOCUMENTATION

Type the below url to access Swagger documentation.

https://quizzer-zxp3.onrender.com/swagger-ui/index.html

## Getting Started

### Prerequisites

- A modern web browser (Google Chrome, Firefox, Safari, etc.)
- An active internet connection

# Developer Guide

### Prerequisites

- Java 17 or higher
- Git
- Internet connection

**Clone Repository.** Navigate in terminal/command line to a directory or a folder where you wish to place the source code. Use git to clone the repository

    $ git clone https://github.com/FrontBackStart/quizzer.git

**Start Development Server.** Navigate to a directory/folder named `quizzer`. Compile and run the application using Maven.

    $ ./mvnw spring-boot:run

**Open In Browser.** Type the below url to access app.

    localhost:8080
