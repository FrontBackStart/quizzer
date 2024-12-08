*** Settings ***
Library         SeleniumLibrary

*** Variables ***
${Browser}      Chrome
${Sleep}	5

*** Keywords ***
Open Frontend Site
	Open Browser    http://localhost:5173       ${BROWSER}
	Page Should Contain     Quizzes
	Close Browser

Open Backend Site
    Open Browser    http://localhost:8080       ${BROWSER}
    Page Should Contain     Quizzes
	Close Browser
