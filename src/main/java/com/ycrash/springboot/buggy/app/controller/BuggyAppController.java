
package com.ycrash.springboot.buggy.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import com.ycrash.springboot.buggy.app.service.blockedapp.BlockedAppDemoService;
import com.ycrash.springboot.buggy.app.service.cpuspike.CPUSpikeDemoService;
import com.ycrash.springboot.buggy.app.service.dbconnectionleak.DBConnectionLeakService;
import com.ycrash.springboot.buggy.app.service.deadlock.DeadLockDemoService;
import com.ycrash.springboot.buggy.app.service.fileconnectionleak.FileConnectionLeakService;
import com.ycrash.springboot.buggy.app.service.httpconnectionleak.HttpConnectionLeak;
import com.ycrash.springboot.buggy.app.service.memoryleak.MemoryLeakDemoService;
import com.ycrash.springboot.buggy.app.service.metaspaceleak.MetaspaceLeakService;
import com.ycrash.springboot.buggy.app.service.oomcrash.OOMCrashService;
import com.ycrash.springboot.buggy.app.service.oomcrash.OOMNoCrashService;
import com.ycrash.springboot.buggy.app.service.resttemplate.RestTemplateService;
import com.ycrash.springboot.buggy.app.service.stackoverflow.StackOverflowDemoService;
import com.ycrash.springboot.buggy.app.service.threadleak.ThreadLeakDemoService;
import com.ycrash.springboot.buggy.app.service.webclient.WebClientService;

import io.swagger.annotations.Api;

@Controller
@Api(tags = "Buggy App Service")
@RequestMapping("/v1/invoke")
public class BuggyAppController {
	
	
	private static final Logger log = LoggerFactory.getLogger(BuggyAppController.class);

	@Autowired
	private BlockedAppDemoService blockedAppDemoService;
	
	@Autowired
	private CPUSpikeDemoService cpuSpikeDemoService;
	
	@Autowired
	private DeadLockDemoService deadLockDemoService;
	
	@Autowired
	private MemoryLeakDemoService memoryLeakDemoService;
	
	@Autowired
	private MetaspaceLeakService metaspaceLeakService;
	
	@Autowired
	private OOMCrashService oomCrashService;
	
	@Autowired
	private OOMNoCrashService oomNoCrashService;
	
	@Autowired
	private StackOverflowDemoService stackOverflowDemoService;
	
	@Autowired
	private ThreadLeakDemoService threadLeakDemoService;
	
	protected NativeWebRequest request;
	
	@Autowired
	private  DBConnectionLeakService dbConnectionLeakService;
	
	@Autowired
	private HttpConnectionLeak httpConnectionLeak;
	
	@Autowired
	private FileConnectionLeakService fileConnectionLeakService;
	
	
	@Autowired
	private WebClientService webClientService;
	
	@Autowired
	private RestTemplateService restClientService;
	

	
	@Autowired
	public BuggyAppController(NativeWebRequest request) {
		this.request = request;
	}
	

	@RequestMapping(value = "blocked-state", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Void>  invokeBlockedState() {
		log.debug("Starting blocked app demo");
		blockedAppDemoService.start();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "cpu-spike", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Void>  invokeSpikeCpu() {
		log.debug("Starting cpu spike demo");
		cpuSpikeDemoService.start();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "deadlock", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Void>  invokeDeadLock() {
		log.debug("Starting dead lock demo");
		deadLockDemoService.start();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	

	@RequestMapping(value = "memory-leak", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Void>  invokeMemoryLeak() {
		log.debug("Memory leak demo");
		memoryLeakDemoService.start();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "stack-overflow", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Void>  invokeStackOverflow() {
		log.debug("Stack Overflow demo");
		stackOverflowDemoService.start();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "thread-leak", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Void>  invokeThreadLeak() {
		threadLeakDemoService.start();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "oom-crash", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Void>  invokeOOMCrash() {
		log.debug("OOM No Crash demo");
		oomCrashService.start();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "meta-space-leak", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Void>  invokeMetaSpaceLeak() throws Exception {
		log.debug("Memory leak demo");
		metaspaceLeakService.start();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "oom-no-crash", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Void>  invokeoomcrash() throws Exception {
		log.debug("OOM No Crash demo");
		oomNoCrashService.start();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "db-connections-leak", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Void>  leakSQLConnections(@RequestParam("datasource.url") String datasourceUrl,
			                                        @RequestParam("datasource.username") String datasourceUsername,
			                                        @RequestParam("datasource.password") String datasourcePassword,
			                                        @RequestParam("datasource.tablename") String tableName) throws Exception {
		log.debug("DB Connections Leak");
		dbConnectionLeakService.start(datasourceUrl,datasourceUsername,datasourcePassword,tableName);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "http-connections-leak", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Void>  leakHttpConnections() throws Exception {
		log.debug("HTTP Connections Leak");
		httpConnectionLeak.start();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "file-connections-leak", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Void>  leakFileConnections() throws Exception {
		log.debug("HTTP Connections Leak");
		fileConnectionLeakService.start();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "slow-endpoint", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Void>  slowEndPoint(@RequestParam("delayInSeconds") Integer delayInSeconds) throws Exception {
		log.debug("HTTP Connections Leak");
		Thread.sleep(delayInSeconds * 1000);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "image-upload", produces = { "application/json" }, method = RequestMethod.POST)
    public ResponseEntity<String> handleImageUpload(@RequestParam("file") MultipartFile file) throws InterruptedException {
        // Process the uploaded image (save it, perform operations, etc.)
        // You can access the image data using file.getBytes()
		Thread.sleep(3 * 1000);
        // For simplicity, this example just returns a success message
        return ResponseEntity.ok("Image uploaded successfully!");
    }
	

	
	@RequestMapping(value = "WebClient-nio-hugeupload-connections", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Void>  webClientHugeUploads(  @RequestParam("image.url") String imageUrl,
			                                             @RequestParam("rest.url") String restUrl,
														 @RequestParam("numberOfCalls") Integer numberOfCalls) throws Exception {
		log.debug("HTTP Connections Leak");
		webClientService.loadWebClientCalls(numberOfCalls, restUrl, imageUrl);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	

	
	@RequestMapping(value = "restClient-nio-hugeupload-connections", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Void>  restClientHugeUploads(  @RequestParam("image.url") String imageUrl,
			                                             @RequestParam("rest.url") String restUrl,
														 @RequestParam("numberOfCalls") Integer numberOfCalls) throws Exception {
		log.debug("HTTP Connections Leak");
		restClientService.loadRestClientCallsWithThreads(numberOfCalls, restUrl, imageUrl);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	

}
