#!/bin/bash

REPOSITORY=/home/ec2-user/deploy
PROJECT_NAME=test

echo "START DEPLOYING: $PROJECT_NAME"

echo "CHECK RUNNING THIS APP..."

RUNNING_APP_PID=$(pgrep -fl test | grep jar | awk '{print $1}')

if [ -z "$RUNNING_APP_PID"]; then
  echo "NOT RUNNING DIARY APP"
else
  kill -15 $RUNNING_APP_PID
  sleep 5
fi

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

chmod +x $JAR_NAME

nohup java -jar $JAR_NAME > $REPOSITORY/log/nohup.out 2>&1 &