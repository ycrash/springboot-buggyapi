**SpringBoot Buggy API Services**

The SpringBoot Buggy API Service, has endpoints designed to simulate Java performance problems for tool testing and analysis.

---

```
SSH: git clone git@github.com:ycrash/springboot-buggyapi.git
HTTP: git clone https://github.com/ycrash/springboot-buggyapi.git
```

** Pre-Requiste **

To run this, you need the following installed and configured in your path.

1. Java 1.8 or higher
2. Maven 3.0 or higher

---

** Build the application **
Please navigate to the folder and execute the following commands in your terminal.

1. cd springboot-buggyapi
2. mvn clean
3. mvn install
cd target 

OR 

** Download the application **
Download latest SpringBoot Buggy Api from [here](https://tier1app.com/dist/buggyapp/springboot-buggyapi-latest.zip).
unzip springboot-buggyapi-latest.zip
cd springboot-buggyapi-latest

** Run the application **
4. Change directory into folder where cd target 
5. java -jar  .jar
6. Open the application in the browser http://{your-host}:8090/swagger-ui.html to invoke the java performance problems using UI

or curl command example 
curl http://{your-host:8090}/v1/invoke/blocked-state

Note. You can change the port from default(8090) in the application properties.




---

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[Apache license 2.0](https://www.apache.org/licenses/LICENSE-2.0)

