docker pull releases-docker.jfrog.io/jfrog/artifactory-pro:latest
docker run --name artifactory -d -p 8082:8082 releases-docker.jfrog.io/jfrog/artifactory-pro:latest