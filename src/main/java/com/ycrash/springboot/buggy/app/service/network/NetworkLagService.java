package com.ycrash.springboot.buggy.app.service.network;



import org.littleshoot.proxy.HttpFilters;
import org.littleshoot.proxy.HttpFiltersAdapter;
import org.littleshoot.proxy.HttpFiltersSourceAdapter;
import org.littleshoot.proxy.HttpProxyServer;
import org.littleshoot.proxy.impl.DefaultHttpProxyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ycrash.springboot.buggy.app.service.diskspace.DiskSpaceService;

import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;

@Service
public class NetworkLagService {
	
	public HttpProxyServer httpProxyServer;
	private static final Logger log = LoggerFactory.getLogger(NetworkLagService.class);


    public  void startNetworkLagProxy(Integer port, Integer delay) {
    	if( httpProxyServer != null ) {
    		httpProxyServer.stop();
    	}

        // Start LittleProxy server
        httpProxyServer = DefaultHttpProxyServer.bootstrap()
                .withPort(port)
                .withFiltersSource(new HttpFiltersSourceAdapter() {
                    @Override
                    public HttpFilters filterRequest(HttpRequest originalRequest) {
                        // Introduce a 1000 milliseconds (1 second) delay for each HTTP request
                        return new HttpFiltersAdapter(originalRequest) {
                            @Override
                            public HttpResponse clientToProxyRequest(HttpObject httpObject) {
                                try {
                                    Thread.sleep(delay);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                return null;
                            }
                        };
                    }
                })
                .start();
        
        log.debug(  "Proxy server started on port " + port+  " which introduces a network lag of "+delay+"ms");
        
    }
}