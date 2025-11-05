def call(String imageName, String imageTag, String containerName) {
    echo "Testing container: ${imageName}:${imageTag}"
    
    try {
        sh """
            echo "Testing frontend container..."
            docker rm -f ${containerName} || true
            docker run -d -p 5000:5000 --name ${containerName} ${imageName}:${imageTag}
            docker ps
            sleep 5
            echo "Testing frontend container..."
            curl -I http://\$(docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' ${containerName}):5000 || true
            echo "Cleaning up frontend container..."
            docker rm -f ${containerName}
            echo "Frontend test completed..."
        """
    } catch (Exception e) {
        echo "Container test failed: ${e.getMessage()}"
        // Clean up on failure
        sh "docker rm -f ${containerName} || true"
    }
}