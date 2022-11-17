#!/bin/bash
#---------------------------------------------------

echo "Starting Database"
echo -e "\n"
sudo docker stop swe1db
sudo docker rm swe1db
sudo docker run --name swe1db -e POSTGRES_USER=swe1user -e POSTGRES_PASSWORD=swe1pw -p 5432:5432 postgres
read line
