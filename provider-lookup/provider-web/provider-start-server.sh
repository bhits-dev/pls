#!/bin/bash
##
#Configure this location to run this tool in any location
Environment_Location=C:/\eclipse-workspace/\ds4p-workspace/\ds4p-prod/\FEiServices/\provider-services/\provider-lookup/\provider-web

cd $Environment_Location

loggerCheck() {
	mvn clean install -PloggerCheck
}

if [ ! -d $Environment_Location ];then
		echo -e "\e[1;31mEnvironment Location not found\e[0m"
		sleep 3
        exit 1
fi

loggerCheck


