package io.tew88.shorturl.resources;

import java.net.URI;
import java.net.URISyntaxException;

import io.tew88.shorturl.domain.UrlMapping;
import io.tew88.shorturl.services.UrlService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.common.base.Optional;
import com.google.inject.Inject;

@Path("/")
public class UrlResource {
    
    private final UrlService urlService;
    
    @Inject
    public UrlResource(final UrlService urlService) {
        this.urlService = urlService;
    }
    
    @GET
    @Path("{shortUrlId}")
    public Response redirect(@PathParam("shortUrlId") String shortUrlId) throws URISyntaxException {
        
        Optional<UrlMapping> urlMapping = urlService.getUrl(shortUrlId);
        
        if (urlMapping.isPresent()) {
            return Response.seeOther(new URI(urlMapping.get().getUrl())).build();
        } else {
            
            return Response.status(Status.NOT_FOUND).build();
        }
    }
    
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response submitUrl(@QueryParam("url") String submittedUrl) {
        
        Optional<UrlMapping> urlMapping = urlService.saveUrl(submittedUrl);
        
        if (urlMapping.isPresent()) {
            return Response.ok(urlMapping.get()).build();
        } else {
            
            return Response.status(Status.BAD_REQUEST).build();
        }
    }
}