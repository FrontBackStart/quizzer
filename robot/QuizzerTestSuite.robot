# ROBOT FRAMEWORK TESTS

*** Settings ***
Documentation           Test to check RF environment w/ SeleniumLibrary & ChromeDriver.
Library         SeleniumLibrary

*** Variables ***
${Browser}      Chrome
${Sleep}	5

*** Test Cases ***
Front site opens
    Open Browser    http://localhost:5173       ${BROWSER}
    Page Should Contain     Quizzes
	Close Browser
