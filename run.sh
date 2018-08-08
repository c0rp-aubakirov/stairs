#!/usr/bin/env bash

./mvnw clean install

docker run -d --net=host c0rp/stairs:latest
