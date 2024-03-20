#!/bin/bash

# Definir el nombre de la imagen y el contenedor
IMAGE_NAME="takenotes:latest"
CONTAINER_NAME="takenotes-container"

# Parar y eliminar el contenedor si ya está en ejecución
docker stop $CONTAINER_NAME || true
docker rm $CONTAINER_NAME || true

# Construir la imagen Docker
docker build -t $IMAGE_NAME .

# Ejecutar el contenedor y vincular el puerto
docker run -d -p 8080:8080 --name $CONTAINER_NAME $IMAGE_NAME