mvn clean
mvn install
/Library/Java/JavaVirtualMachines/jdk-11.jdk/Contents/Home/bin/java -jar -Xloggc:spring-boot-buggy-api-gc.log  -Xmx250m  target/*.jar
