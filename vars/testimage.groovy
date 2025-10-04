def call(String IMAGE_NAME, String IMAGE_TAG, String CONTAINER_NAME) {
    sh '''
                   echo "delete old container and create new..."
                   docker rm -f ${CONTAINER_NAME}
                   docker run -d -p 5000:5000 --name ${CONTAINER_NAME} ${IMAGE_NAME}:${IMAGE_TAG}
                   docker ps
                   sleep 5

                   echo "testing container..."
                   curl -I http://$(docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' ${CONTAINER_NAME}):5000

                   echo "delete tested container..."
                   docker rm -f ${CONTAINER_NAME}

                    echo "End..."
                '''
}