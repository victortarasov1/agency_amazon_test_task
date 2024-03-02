#!/bin/bash

docker pull mongodb/mongodb-community-server

docker run --name mongo -d -p 27017:27017 mongodb/mongodb-community-server:latest --bind_ip 0.0.0.0
