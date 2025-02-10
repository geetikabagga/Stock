# Big Tech Stocks App

This application ranks the top 5 big tech stocks - Apple, Meta, Microsoft, Amazon, Google - in the order of their previous day's trading volume and posts it on Reddit. It gets the stock data for the previous trading day using Polygon APIs.

## Requirements
* You need Java JDK installed in your machine above version 8
* You will need Maven to build and run this application

## How to Install Maven
I have used Maven as a package manager to organize my application and manage the dependencies in my app.
You can download maven from https://maven.apache.org/download.cgi

After downloading, extract the package as
```
tar -xvf apache-maven-3.6.3-bin.tar.gz
```

Set the environment variables like
```
export M2_HOME="/Users/<userName>/Downloads/apache-maven-3.6.3"
PATH="${M2_HOME}/bin:${PATH}"
export PATH
```

Verify installation by checking the version
```
mvn -version
```
## How to Build and Run the App
Build the app by running the following command in root
```
mvn clean install
```

Then run the jar like
```
java -jar target/stock-1.0-SNAPSHOT.jar
```
