def call(String FRONTEND, String ORDERSERVICE, String IMAGE_TAG, String DOCKERHUB_USER) {
    // Build and tag for local use
    sh "docker build -t ${FRONTEND}:${IMAGE_TAG} frontend/"
    sh "docker build -t ${ORDERSERVICE}:${IMAGE_TAG} order-service/"
    
    // Tag for Docker Hub
    sh """
        docker tag ${FRONTEND}:${IMAGE_TAG} ${DOCKERHUB_USER}/${FRONTEND}:${IMAGE_TAG}
        docker tag ${ORDERSERVICE}:${IMAGE_TAG} ${DOCKERHUB_USER}/${ORDERSERVICE}:${IMAGE_TAG}
    """
}