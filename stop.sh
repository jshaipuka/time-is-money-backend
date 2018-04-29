#!/usr/bin/env bash

PID_FILE=./application.pid

if [ -f "$PID_FILE" ]
then
    kill $(cat ${PID_FILE})
fi
