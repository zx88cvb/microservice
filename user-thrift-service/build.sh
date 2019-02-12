#!/usr/bin/env bash

mvn package

sudo docker build -t user-service:latest .