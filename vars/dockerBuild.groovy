def call(Map params) {
    def imageName = params.imageName
    def imageTag = params.imageTag
    def contextPath = params.contextPath ?: "."
    def dockerfile = params.dockerfile ?: "Dockerfile"
    
    echo "Building Docker image: ${imageName}:${imageTag}"
    
    sh """
        docker build -t ${imageName}:${imageTag} -f ${dockerfile} ${contextPath}
    """
}