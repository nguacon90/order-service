package vn.com.vndirect.app;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.zk.Application;

public class App {
	private static final int DEFAULT_PORT = 8080;
	private static final String DEFAULT_URI = "http://localhost";
	private static URI baseUri = UriBuilder.fromUri(DEFAULT_URI).port(DEFAULT_PORT).build();
	
	public static void main(String[] args) throws Exception {
		Application application = new Application();
		if (args.length > 0 && Integer.valueOf(args[0]) != null) {
			baseUri = UriBuilder.fromUri(DEFAULT_URI).port(Integer.valueOf(args[0])).build();
		}
		
		application.registListener(new OrderServiceListener(baseUri));
		application.start();
	}

}
