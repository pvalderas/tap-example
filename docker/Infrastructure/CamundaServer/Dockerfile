FROM camunda/camunda-bpm-platform:run-7.18.0
RUN rm -r /camunda/internal/example
COPY bpcontrollercamundalibrary-0.2.0.jar /camunda/configuration/userlib/bpcontrollercamundalibrary-0.2.0.jar
COPY json-20090211.jar /camunda/configuration/userlib/json-20090211.jar
COPY amqp-client-5.2.0.jar /camunda/configuration/userlib/amqp-client-5.2.0.jar
