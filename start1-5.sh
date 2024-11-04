cp compose1-5.yaml compose.yaml
mvn clean && mvn install
sudo docker-compose up -d
