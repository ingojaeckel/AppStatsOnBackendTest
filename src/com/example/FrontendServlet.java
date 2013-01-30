package com.example;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.backends.BackendService;
import com.google.appengine.api.backends.BackendServiceFactory;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

@SuppressWarnings("serial")
public class FrontendServlet extends HttpServlet {
	private static final MemcacheService memcache = MemcacheServiceFactory.getMemcacheService();
	private static final BackendService backendService = BackendServiceFactory.getBackendService();

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		memcache.put("needle", 1);
		resp.getWriter().println(String.format("FrontendServlet says: '%s'.", backendService.getBackendAddress("my-backend")));
	}
}
