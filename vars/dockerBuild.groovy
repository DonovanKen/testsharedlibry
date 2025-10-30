def call(String FRONTEND, String IMAGE_TAG) {
    sh 'docker build -t ${FRONTEND}:${IMAGE_TAG} frontend/'
}