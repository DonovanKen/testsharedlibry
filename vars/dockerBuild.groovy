def call(String FRONTEND, String ORDERSERVICE, String IMAGE_TAG) {
    // Use double quotes for variable interpolation
    sh "docker build -t ${FRONTEND}:${IMAGE_TAG} frontend/"
    sh "docker build -t ${ORDERSERVICE}:${IMAGE_TAG} orderservice/"
}