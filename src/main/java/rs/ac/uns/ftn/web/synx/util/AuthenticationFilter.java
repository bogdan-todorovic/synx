package rs.ac.uns.ftn.web.synx.util;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import rs.ac.uns.ftn.web.synx.model.User;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

	@Context
	private ResourceInfo resourceInfo;

	private static final String AUTHORIZATION_PROPERTY = "x-auth-token";

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		Method method = resourceInfo.getResourceMethod();

		if (!method.isAnnotationPresent(PermitAll.class)) {

			// Access denied for all
			if (method.isAnnotationPresent(DenyAll.class)) {
				requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).entity("Access blocked!!").build());
				return;
			}

			// Get request headers
			final MultivaluedMap<String, String> headers = requestContext.getHeaders();

			// Fetch authorization header
			final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

			// If no authorization information present -> block access
			if (authorization == null || authorization.isEmpty()) {
				requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
						.entity("You cannot access this resource").build());
				return;
			}

			// Check token
			String token = authorization.get(0);
			RolesAllowed rolesAllowed = method.getAnnotation(RolesAllowed.class);
			String[] roles = rolesAllowed.value();
			
			TokenGenerator tokenGenerator = new TokenGenerator();
			User user = tokenGenerator.parseUserFromToken(token);
			
			
			if (user != null) {
				// Check if user role is allowed to access
				boolean isAllowed = Arrays.asList(roles).contains(user.getRole().toString());
				if (!isAllowed) {
					requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
							.entity("You cannot access this resource with that role").build());
				}

			}

		}
	}

}
