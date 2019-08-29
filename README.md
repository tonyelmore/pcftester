# pcftester
Simple app to test pcf installed components

This is currently just a little service that allows for curl commands to be executed in order to test different aspects of a PCF foundation.

Initially I just needed a test utility for the credhub tile - so that's what it is.

* Clone the repo
* ./gradlew clean assemble
* cf push
