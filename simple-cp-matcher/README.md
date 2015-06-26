# A Service Matchmaking Approach for Cloud Service Marketplaces #
Service requesters with limited technical knowledge should be able to compare services based
on their QoS requirements in business-oriented cloud service marketplaces. Existing service
matching approaches focus on QoS requirements as discrete numeric values and intervals,
while providing the results with varying matching degrees. This project develops a concept for a service matcher and the code is the prototype implementation of the concept.

This prototype uses JSR-331(Java Constraint Programming API) with Choco Solver 2 in the backend.

## JUnit Tests ##
An example for a service request with three service descriptions can be found in the tests.
The NumericFeatureIntegrationTest creates three discrete numeric constraints (establishmentYear, maintenanceWindow and numberOfUsers) and one feature list constraint (compatibleBrowsers).

The request looks like:

establishmentYear= 2010

maintenanceWindow= 20

numberOfUsers= 10

featureList=[0,1]

(in the same order as the request)
Service description \#1 : 2010, 20, 10, [0,1,4]

Service description \#2 : 2012, 30, _, [0,4]

Service description \#3 : 2013, 15, 5, [0,1]

You should see logs as:

matching service index no 0 :0

Evaluated Service Descriptions:

18:11:54.617 [main] DEBUG d.t.f.sm.cp.model.ServiceRepository -  property name: establishmentYear, property itself: 2010 ; property name: browsers, property itself: featurelistSpec [0, 1, 4] ranking: 3 matching degree: SUPER ; property name: maintenanceWindow, property itself: 20 ; property name: numberOfUsers, property itself: 10 ;scores: 9

18:11:54.617 [main] DEBUG d.t.f.sm.cp.model.ServiceRepository -  property name: establishmentYear, property itself: 2012 ; property name: browsers, property itself: featurelistSpec [0, 4] ranking: 1 matching degree: PARTIAL ; property name: maintenanceWindow, property itself: 30 ;scores: 1

18:11:54.617 [main] DEBUG d.t.f.sm.cp.model.ServiceRepository -  property name: establishmentYear, property itself: 2013 ; property name: browsers, property itself: featurelistSpec [0, 1] ranking: 2 matching degree: EXACT ; property name: maintenanceWindow, property itself: 15 ; property name: numberOfUsers, property itself: 5 ;scores: 2

Since the first service description has the highest number for scores, it is selected as the best match.

## Maven Build ##

Run
```bash
mvn clean install
```
