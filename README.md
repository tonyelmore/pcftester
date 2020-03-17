# pcftester
Simple app to test pcf installed components

This is currently just a little service that allows for curl commands to be executed in order to test different aspects of a PCF foundation.

Initially I just needed a test utility for the credhub tile - so that's what it is.

* Clone the repo
* ./gradlew clean assemble
* cf push

Since adding the ElasticSearch example, the configuration test ran in the gradle build fails because it can not reach the server.
Also, the index name for the Article class in the ElasticSearch example is hard-coded and it changes every time you bind the service - so there will be errors there as well
