# Simple Super market application

This is a simple super market application which performs the checkout according to following rules

Requirements

1. a sales tax of 12% is added to all purchases, but insurance products are exempt
2. SIM cards are Buy One Get One Free (BOGOF)
3. insurance is discounted by 20% if you buy any type of earphone
4. the law prevents anyone buying more than 10 SIM cards in a single purchase

## Setup

Building this project requires maven. All you need to do is import project as maven in IDE by pointing it to pom.xml.

## Dependencies

No prior dependencies are required

## Assumptions

This project does not require a GUI or persistence layer. All the products will be stored in memory.

User will pass commandline arguments in double quotes. e.g. "SIM Card" "phone case" "phone insurance" "phone insurance" "wireless earphones" "wired earphones" 

##Improvements

Need to convert this project as SpringBoot and Rest application to fully operate as Rest service
