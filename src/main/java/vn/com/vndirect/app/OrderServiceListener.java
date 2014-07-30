package vn.com.vndirect.app;

import java.io.IOException;
import java.net.URI;

import org.apache.log4j.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.servlet.ServletRegistration;
import org.glassfish.grizzly.servlet.WebappContext;
import org.zk.ApplicationListener;
import org.zk.ApplicationState;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;

public class OrderServiceListener implements ApplicationListener {
	private static final Logger log = Logger.getLogger(OrderServiceListener.class);
	private HttpServer server;
	private URI baseUri;

	public OrderServiceListener(URI baseUri) {
		this.server = new HttpServer();
		this.baseUri = baseUri;
	}

	@Override
	public void onChange(ApplicationState state) {
		if (state.equals(ApplicationState.MASTER)) {
			System.out.println("------------MASTER-----------------");
			try {
				WebappContext webAppContext = new WebappContext("webAppContext", "/rest");
				final ServletRegistration reg = webAppContext.addServlet("spring", new SpringServlet());
				reg.addMapping("/rest");
				webAppContext.addContextInitParameter("contextConfigLocation", "classpath:configs/spring-context.xml");
				webAppContext.addListener("org.springframework.web.context.ContextLoaderListener");
				final ResourceConfig rc = new PackagesResourceConfig("vn.com.vndirect.resources");
				rc.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,true);
				server = GrizzlyServerFactory.createHttpServer(baseUri, rc);
				webAppContext.deploy(server);
				
				server.start();
				System.out.println("Press any key to stop server!");
				System.in.read();
				server.stop();
				System.out.println("Server stopped");

			} catch (Exception e) {
				log.error(e);
				server.stop();
			}
		} else {
			System.out.println("---------SLAVER-------------");
			try {
				server.stop();
				System.out.println("Press any key to stop server!");
				System.in.read();
			} catch (IOException e) {
				log.error(e);
			}
		}
	}

}
