def call(String FRONTEND, String ORDERSERVICE, String PRODUCTSERVICE, String USERSERVICE, String IMAGE_TAG) {
    sh "docker build -t ${FRONTEND}:${IMAGE_TAG} frontend/"
    sh "docker build -t ${ORDERSERVICE}:${IMAGE_TAG} order-service/"
    sh "docker build -t ${PRODUCTSERVICE}:${IMAGE_TAG} product-service/"
    sh "docker build -t ${USERSERVICE}:${IMAGE_TAG} user-service/"
}