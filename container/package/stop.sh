#!/bin/bash
SERVERPID=`ps -ef | grep application-0.0.1-SNAPSHOT.jar | grep -v grep |  awk '{print $2}'`
echo kill $SERVERPID ......
kill $SERVERPID
while ps -p $SERVERPID; 
do 
sleep 1;
echo kill $SERVERPID ......; 
done

