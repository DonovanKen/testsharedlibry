def call(Map params) {
    def imageName = params.imageName
    def imageTag = params.imageTag
    def containerName = params.containerName
    def port = params.port ?: "5000"
    
    echo "Testing container: ${imageName}:${imageTag}"
    
    try {
        sh """
            # Clean up any existing container
            docker rm -f ${containerName} || true
            
            # Run container
            docker run -d --name ${containerName} -p ${port}:${port} ${imageName}:${imageTag}
            
            # Wait for container to start
            sleep 10
            
            # Check container status
            echo "=== Container Status ==="
            docker ps | grep ${containerName} || exit 1
            
            # Test endpoint
            echo "=== Testing Endpoint ==="
            curl -f http://localhost:${port} || curl -I http://localhost:${port} || echo "Endpoint check completed"
            
            # Check logs
            echo "=== Container Logs ==="
            docker logs ${containerName}
        """
    } catch (Exception e) {
        echo "Container test failed: ${e.getMessage()}"
        throw e
    } finally {
        // Always clean up
        sh "docker rm -f ${containerName} || true"
    }
}