ARG CAMUNDA_IMAGE=camunda/camunda-bpm-platform:wildfly-7.14.0

FROM ${CAMUNDA_IMAGE} AS run

COPY book/target/book-apartment-1.0-SNAPSHOT.war /camunda/standalone/deployments
COPY register/target/register-apartment-1.0-SNAPSHOT.war /camunda/standalone/deployments