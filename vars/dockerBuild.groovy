def call(string IMAGE_NAME, string IMAGE_TAG) {
    sh 'docker build -t ${IMAGE_NAME}:${IMAGE_TAG} frontend/'
}