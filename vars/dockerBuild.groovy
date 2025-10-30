def call(String FRONTEND, String IMAGE_TAG) {
    sh 'docker build -t ${FRONTEND}:${IMAGE_TAG} frontend/'
}

def call(String ORDERSERVICE, String IMAGE_TAG) {
    sh 'docker build -t ${ORDERSERVICE}:${IMAGE_TAG} frontend/'
}