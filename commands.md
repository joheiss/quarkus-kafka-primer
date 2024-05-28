# build packages
mvn -f producer package
mvn -f processor package

# build native packages
mvn -f producer package -Dnative -Dquarkus.native.container-build=true
mvn -f processor package -Dnative -Dquarkus.native.container-build=true

# build docker images
cd processor
docker build -f src/main/docker/Dockerfile.jvm -t joheiss/quarkus-kafka-processor:1.0 .
cd producer
docker build -f src/main/docker/Dockerfile.jvm -t joheiss/quarkus-kafka-producer:1.0 .

# run the system
export QUARKUS_MODE=native
docker-compose up --build