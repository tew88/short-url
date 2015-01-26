package io.tew88.shorturl.resources;

import io.tew88.shorturl.domain.UrlMapping;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class UrlResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response submitUrl(@QueryParam("url") String submittedUrl) {
        UrlMapping urlMapping = new UrlMapping(submittedUrl, "shortUrl");
        return Response.ok(urlMapping).build();
    }
}