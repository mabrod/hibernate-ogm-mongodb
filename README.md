## CI Build
[![Build Status](https://travis-ci.org/mabrod/hibernate-ogm-mongodb.svg?branch=master)](https://travis-ci.org/mabrod/hibernate-ogm-mongodb)
I use Travis CI to run CI build pipeline: https://travis-ci.org/

## Learning SpringBoot with Hibernate OGM and Hibernate Search

The code samples show how to set up and run backend services based on Hibernate OGM library in a stand-alone SpringBoot application.

## Installation

The code is build with Gradle. Wrapper properties file to start a build with specific Gradle version is provided.
You need to have Mongo DB: https://www.mongodb.com installed and running. Database name and collections are all configurable.
By default it starts with database named as "books" found in application-profile.properties under hibernate.ogm.mongodb.database property
Collections are defined using Table persistence annotation e.g in Author entity with following values publishing.public.authors.
Look up each entity class in domain package to see definitions for each collections.

## Usage

I have added feature flag functionality to determine which scenarios to run based on togglez feature flag project: https://www.togglz.org
To enable/disable which scenario to run, toggle the value for a specific scenario's setting in application.properties file.
By default all scenarios are disabled

## Tests

To test persistence layer I use Fongo library : https://github.com/fakemongo/fongo

## License

The code is free to use and modify.