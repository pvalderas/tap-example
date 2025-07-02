# Create Docker containers:

```
docker compose up
```

**Please note** that Camunda port 8080 is mapped to the same port on the local machine, so please check that it is not being used by another application..

Once containers are executed, you can check the Elder People Tap is deployed in the Camunda server by connecting with the user/pass (demo/demo) to:

```
http://localhost:8080/camunda/app/cockpit/default/#/login
```

# Executing a process

After containers are running you can trigger the Welcome process by injecting the 'Presence Detected' event throght the Camunda REST API. You can use the curl tool from the Camunda docker container terminal or use any REST client from the local machine.

```
 curl -X POST -d '{"messageName" : "PresenceDetected", "processVariables" : {"user" : {"value" : "pvalderas", "type": "String"}}} ' -H "Content-Type: application/json" http://localhost:8080/engine-rest/message
 ```

 **Please note that:**

* Only the user pvalderas has defined usage policies
* The first time the process is run, both the lamp and the ceiling light are turned on. In subsequent runs, only the ceiling light is used, as pvalderas can only use the Lamp's Turns On operation once.
* In each process run, the 'Talk to Patient' task must be completed from the Camunda dashboard using the following URL: 
```
http://localhost:8080/camunda/app/tasklist/default/#/login
```