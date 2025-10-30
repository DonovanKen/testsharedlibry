def call(String DOCKERHUB_USER, String FRONTEND, String ORDERSERVICE, String IMAGE_TAG) {
    sh "docker build -t ${DOCKERHUB_USER}/${FRONTEND}:${IMAGE_TAG} frontend/"
    sh "docker build -t ${DOCKERHUB_USER}/${ORDERSERVICE}:${IMAGE_TAG} orderservice/"
}