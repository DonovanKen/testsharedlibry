def call(String FRONTEND, String ORDERSERVICE, String PRODUCTSERVICE, String IMAGE_TAG, String CONTAINER_FRONTEND, String CONTAINER_ORDERSERVICE, String CONTAINER_PRODUCTSERVICE) {
    // Test frontend
    sh """
        echo "Testing frontend container..."
        docker rm -f ${CONTAINER_FRONTEND} || true
        docker run -d -p 5000:5000 --name ${CONTAINER_FRONTEND} ${FRONTEND}:${IMAGE_TAG}
        docker ps
        sleep 5
        echo "Testing frontend container..."
        curl -I http://\$(docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' ${CONTAINER_FRONTEND}):5000 || true
        echo "Cleaning up frontend container..."
        docker rm -f ${CONTAINER_FRONTEND}
        echo "Frontend test completed..."
    """

    // Test orderservice
    sh """
        echo "Testing orderservice container..."
        docker rm -f ${CONTAINER_ORDERSERVICE} || true
        docker run -d -p 5003:5003 --name ${CONTAINER_ORDERSERVICE} ${ORDERSERVICE}:${IMAGE_TAG}
        docker ps
        sleep 5
        echo "Testing orderservice container..."
        curl -I http://\$(docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' ${CONTAINER_ORDERSERVICE}):5003 || true
        echo "Cleaning up orderservice container..."
        docker rm -f ${CONTAINER_ORDERSERVICE}
        echo "Orderservice test completed..."
    """

    // Test productservice
    sh """
        echo "Testing productservice container..."
        docker rm -f ${CONTAINER_PRODUCTSERVICE} || true
        docker run -d -p 5002:5002 --name ${CONTAINER_PRODUCTSERVICE} ${PRODUCTSERVICE}:${IMAGE_TAG}
        docker ps
        sleep 5
        echo "Testing productservice container..."
        curl -I http://\$(docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' ${CONTAINER_PRODUCTSERVICE}):5002 || true
        echo "Cleaning up productservice container..."
        docker rm -f ${CONTAINER_PRODUCTSERVICE}
        echo "Productservice test completed..."
    """
}