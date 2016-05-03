package tempName.server;

import tempName.client.service.GreetingService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	@Override
	public String greetServer(String name) {
		return "Hello, " + name;
	}

}