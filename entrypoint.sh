#!/bin/sh

./gradlew bootRun -Pargs=--ENV_PROFILE=${PROFILE},--SERVICE_PORT=${SERVICE_PORT}