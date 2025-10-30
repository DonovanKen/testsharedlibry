def call(String FRONTEND, String ORDERSERVICE, String IMAGE_TAG, String CONTAINER_FRONTEND, String CONTAINER_ORDERSERVICE) {
    sh '''
                   echo "delete old container and create new..."
                   docker rm -f ${CONTAINER_FRONTEND}
                   docker run -d -p 5000:5000 --name ${CONTAINER_FRONTEND} ${FRONTEND}:${IMAGE_TAG}
                   docker ps
                   sleep 5

                   echo "testing container..."
                   curl -I http://$(docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' ${CONTAINER_FRONTEND}):5000

                   echo "delete tested container..."
                   docker rm -f ${CONTAINER_FRONTEND}

                    echo "End..."
                '''


    sh '''
                   echo "delete old container and create new..."
                   docker rm -f ${CONTAINER_ORDERSERVICE}
                   docker run -d -p 5000:5000 --name ${CONTAINER_ORDERSERVICE} ${ORDERSERVICE}:${IMAGE_TAG}
                   docker ps
                   sleep 5

                   echo "testing container..."
                   curl -I http://$(docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' ${CONTAINER_ORDERSERVICE}):5003

                   echo "delete tested container..."
                   docker rm -f ${CONTAINER_ORDERSERVICE}

                    echo "End..."
                '''
}