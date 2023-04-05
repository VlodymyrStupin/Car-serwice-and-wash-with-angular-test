# Stage 1
FROM node:18.10 as node
WORKDIR /app
#COPY . .
#RUN npm install
#RUN npm run build --prod
COPY package.json ./
RUN npm install
COPY . .
RUN npm run build

# Stage 2
FROM nginx
COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=node /app/dist/frontend /usr/share/nginx/html
EXPOSE 80

#### STAGE 1: Build ###
#FROM node:latest AS build
#WORKDIR /usr/src/app
#COPY . .
#RUN npm install
#COPY . .
#RUN npm run build --prod
#
#### STAGE 2: Run ###
#FROM nginx:latest
#COPY nginx.conf /etc/nginx/conf.d/nginx.conf
#COPY --from=build /usr/src/app/dist/frontend /usr/share/nginx/html
#EXPOSE 80

#### STAGE 1:BUILD ###
## Defining a node image to be used as giving it an alias of "build"
## Which version of Node image to use depends on project dependencies
## This is needed to build and compile our code
## while generating the docker image
#FROM node:latest AS build
## Create a Virtual directory inside the docker image
#WORKDIR /dist/src/app
## Copy files to virtual directory
## COPY package.json package-lock.json ./
## Run command in Virtual directory
#RUN npm cache clean --force
## Copy files from local machine to virtual directory in docker image
#COPY . .
#RUN npm install
#RUN npm run build --prod
#
#
#### STAGE 2:RUN ###
## Defining nginx image to be used
#FROM nginx:latest AS ngi
## Copying compiled code and nginx config to different folder
## NOTE: This path may change according to your project's output folder
#COPY --from=build /dist/src/app/dist/frontend /usr/share/nginx/html
#COPY /nginx.conf  /etc/nginx/conf.d/default.conf
## Exposing a port, here it means that inside the container
## the app will be using Port 80 while running
#EXPOSE 80


## Stage 1 - build
#FROM node:latest as node
#WORKDIR /app
#COPY . .
#RUN npm install
#RUN npm run build --prod
#
## Stage 2 - run
#FROM nginx:alpine
#COPY --from=node /app/dist/frontend-jwt /usr/share/nginx/html
#COPY nginx.conf /etc/nginx/nginx.conf
