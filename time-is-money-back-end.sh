#!/bin/sh

export SPRING_DATA_MONGODB_HOST=91.92.128.214
export SPRING_DATA_MONGODB_PORT=27017

SERVICE_NAME=time-is-money-back-end
SERVICE_FOLDER=/app/time-is-money-backend
PID_PATH_NAME=${SERVICE_FOLDER}/target/application.pid
case $1 in
    start)
        echo "Starting $SERVICE_NAME ..."
        if [ -f ${PID_PATH_NAME} ]; then
            echo "$SERVICE_NAME is already running ..."
        else
            nohup java -jar /app/time-is-money-backend/target/timer-0.0.1-SNAPSHOT.jar >/dev/null 2>&1 &
            echo "$SERVICE_NAME started ..."
        fi
    ;;
    stop)
        if [ -f ${PID_PATH_NAME} ]; then
            PID=$(cat ${PID_PATH_NAME});
            echo "$SERVICE_NAME stopping ..."
            kill ${PID};
            echo "$SERVICE_NAME stopped ..."
            rm ${PID_PATH_NAME}
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;
    restart)
        if [ -f ${PID_PATH_NAME} ]; then
            PID=$(cat ${PID_PATH_NAME});
            echo "$SERVICE_NAME stopping ...";
            kill ${PID};
            echo "$SERVICE_NAME stopped ...";
            rm ${PID_PATH_NAME}
            echo "$SERVICE_NAME starting ..."
            nohup java -jar /app/time-is-money-backend/target/timer-0.0.1-SNAPSHOT.jar >/dev/null 2>&1 &
            echo "$SERVICE_NAME started ..."
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;
esac