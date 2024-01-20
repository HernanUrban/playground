# Playground API
This is a playground project to solve some infrastructure problems by showing the environment variables and properties that are raised and displayed via REST requests.
Please use at your discretion as it may expose sensitive information.
## Clone the project
```shell
git clone https://github.com/HernanUrban/playground.git
```

## How to run
## Build
```shell
./mvnw clean package
```
## Build Docker Image
```shell
docker build -t hurban/playground .
```

## Run local or run on Docker

### 1. Run local
```shell
java -jar target/playground-1.0.0.jar 
```
*_You might want to open a new terminal to follow with next steps._

### 2. Run from Docker
```shell
docker run -d -p 9292:9292 hurban/playground
```

## Test the app
### Get the environment variables that the app can see
```shell
curl --location 'http://localhost:9292/api/env' | json_pp
```

### Get the properties that the app can see
```shell
curl --location 'http://localhost:9292/api/properties' | json_pp
```

**_If run from local, once you've done close the app by running `ctrl+C` in the terminal that is running the app._ 
