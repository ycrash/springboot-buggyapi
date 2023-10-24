mvn clean
mvn install
java -jar -Xss512k -Xloggc:spring-boot-buggy-api-gc.log   target/*.jar
