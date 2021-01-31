ARG CAMUNDA_IMAGE=camunda/camunda-bpm-platform:wildfly-7.14.0

FROM ${CAMUNDA_IMAGE} AS run

COPY target/online-shopping-1.0-SNAPSHOT.war /camunda/standalone/deployments