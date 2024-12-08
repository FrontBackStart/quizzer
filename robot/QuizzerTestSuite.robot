# ROBOT FRAMEWORK TESTS

*** Settings ***
Documentation	Test to check RF environment w/ SeleniumLibrary & ChromeDriver.
Resource	keywords.resource
Library         SeleniumLibrary

*** Test Cases ***
Sites open correctly
    Open Frontend Site
    Open Backend Site
    Close Browser

Adding new quiz works
	Open Backend Site
	Go To Add Quiz Page
	Fill Form And Save
	Add First Question
	Add Answer Options To First Question
	Add Second Question
	Add Answer Options To Second Question
