# ROBOT FRAMEWORK TESTS

*** Settings ***
Documentation	Test to check RF environment w/ SeleniumLibrary & ChromeDriver.
Resource	keywords.resource

*** Test Cases ***
Sites open correctly
    Open Frontend Site
    Open Backend Site
