cp compose1-1.yaml compose.yaml
mvn clean && mvn install
sudo docker-compose up -d