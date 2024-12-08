*** Settings ***
Library         SeleniumLibrary

*** Variables ***
${Browser}      Chrome
${QuizName}		European Capital Cities
${QuizDescription}		3rd Grade Geography Quiz
${Question1}	What is the capital of Bulgaria?
${Question2}	What was the first capital city of Finland?

*** Keywords ***
Open Frontend Site
	Open Browser    http://localhost:5173       ${BROWSER}
	Page Should Contain     Quizzes

Open Backend Site
    Open Browser    http://localhost:8080       ${BROWSER}
    Page Should Contain     Quizzes

Go To Add Quiz Page
	Click Link		 link:Add a quiz
	Page Should Contain Element		xpath://h1[@class="mb-3"][contains(text(), "Add a quiz")]

Fill Form And Save
	Input Text		name:name	${QuizName}
	Input Text		name:description	${QuizDescription}
	Click Element	xpath://input[@value="Save"]
	Page Should Contain		${QuizName}

Add First Question
	Input Text		name:questionText	${Question1}
	Click Element	xpath://select[@name="difficulty"]
	Click Element	xpath://option[@value="Hard"]
	Click Element	xpath://input[@value="Save"]
	Page Should Contain		${Question1}

Add Second Question
	Click Link		link:Add a question
	Input Text		name:questionText	${Question2}
	Click Element	xpath://select[@name="difficulty"]
	Click Element	xpath://option[@value="Medium"]
	Click Element	xpath://input[@value="Save"]
	Page Should Contain		${Question2}

Add Answer Options To First Question
	Input Text		name:answerText		Budapest
	Click Element	xpath://select[@name="isRight"]
	Click Element	xpath://option[@value="false"]
	Click Element	xpath://input[@value="Save"]
	Input Text		name:answerText		Sofia
	Click Element	xpath://select[@name="isRight"]
	Click Element	xpath://option[@value="true"]
	Click Element	xpath://input[@value="Save"]
	Page Should Contain		Budapest
	Page Should Contain		Sofia

Add Answer Options To Second Question
	Input Text		name:answerText		Turku
	Click Element	xpath://select[@name="isRight"]
	Click Element	xpath://option[@value="true"]
	Click Element	xpath://input[@value="Save"]
	Input Text		name:answerText		Helsinki
	Click Element	xpath://select[@name="isRight"]
	Click Element	xpath://option[@value="false"]
	Click Element	xpath://input[@value="Save"]
	Page Should Contain		Turku
	Page Should Contain		Helsinki
	Click Link		link:Cancel
	Page Should Contain		Quizzes
