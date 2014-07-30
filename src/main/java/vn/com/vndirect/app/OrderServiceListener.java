package vn.com.vndirect.app;

import java.io.IOException;
import java.net.URI;

import org.apache.log4j.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.servlet.ServletRegistration;
import org.glassfish.grizzly.servlet.WebappContext;
import org.zk.ApplicationListener;
import org.zk.ApplicationState;

import vn.com.vndirect.utils.Constants;

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
			try {
				System.out.println("------------MASTER-----------------");
				WebappContext webAppContext = createWebAppContext();
				server = createGrizzlyServer(baseUri);
				webAppContext.deploy(server);
				server.start();
				System.in.read();
				server.stop();
			} catch (Exception e) {
				log.error(e);
				server.stop();
			}

		} else {
			try {
				System.out.println("---------SLAVER-------------");
				server.stop();
				System.in.read();
			} catch (IOException e) {
				log.error(e);
				server.stop();
			}
		}
	}

	private HttpServer createGrizzlyServer(URI baseUri) throws IOException {
		final ResourceConfig rc = new PackagesResourceConfig(Constants.PACKAGE_RESOURCES);
		rc.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, true);
		
		return GrizzlyServerFactory.createHttpServer(baseUri + Constants.ROOT_CONTEXT_PATH, rc);
	}

	private WebappContext createWebAppContext() throws IOException {
		WebappContext webAppContext = new WebappContext("webAppContext", Constants.ROOT_CONTEXT_PATH);
		final ServletRegistration reg = webAppContext.addServlet("spring", new SpringServlet());
		reg.addMapping(Constants.ROOT_CONTEXT_PATH);
		webAppContext.addContextInitParameter(Constants.CONTEXT_LOCATION_PARAM, "classpath:configs/spring-context.xml");
		webAppContext.addListener(Constants.SPRING_CONTEXT_LISTENER);
		
		return webAppContext;
	}

}
