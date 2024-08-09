package com.demo.rest.Services;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import com.demo.rest.Models.Authentication;
import com.demo.rest.Models.TinDetailsPayload;
import com.demo.rest.Models.TinDetailsRequest;
import com.demo.rest.Models.TinProfilePayload;
import com.demo.rest.Models.TinProfileRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@ApplicationScoped
public class TinDetailsService {

    private final Client client;

    @Inject
    @ConfigProperty(name = "tin.details.api.url")
    private String tinDetailsApiUrl;

    @Inject
    @ConfigProperty(name = "tin.profile.api.url")
    private String tinProfileApiUrl; 

    @Inject@ConfigProperty(name="tin.default.value")
    private String configTin; 

    @Inject
    @ConfigProperty(name="uName")
    private String cusername; 
    
    @Inject
    @ConfigProperty(name="password")
    private String cpassword; 

    @Inject
    @ConfigProperty(name="signature")
    private String csignature;

    public TinDetailsService() {
        this.client = ClientBuilder.newClient();
    }

    public TinDetailsPayload FetchTinDetails(String tin, TinDetailsRequest payload) throws Exception {

        TinDetailsRequest requestPayload = new TinDetailsRequest();
        requestPayload.setUserName(cusername);
        requestPayload.setPassword(cpassword);
        requestPayload.setSignature(csignature);

        System.out.println(cusername);
        System.out.println(cpassword);
        System.out.println(csignature);
        System.out.println(configTin);
        Response apiResponse = client.target(tinDetailsApiUrl + configTin)
                                     .request(MediaType.APPLICATION_JSON)
                                     .post(Entity.json(requestPayload));

        return apiResponse.readEntity(TinDetailsPayload.class);
    }

    public TinProfilePayload FetchTinProfile(TinProfileRequest payload) throws Exception {
         // Create and populate the Authentication object
        Authentication auth = new Authentication();
        auth.setUserName(cusername);
        auth.setPassword(cpassword);
        auth.setSignature(csignature);

        // Populate ReqPayload
        TinProfileRequest reqPayload = new TinProfileRequest();
        reqPayload.setAuthentication(auth);
        reqPayload.setTinNo(configTin);

        Response apiResponse = client.target(tinProfileApiUrl)
                                     .request(MediaType.APPLICATION_JSON)
                                     .post(Entity.json(reqPayload));

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(apiResponse.readEntity(String.class), TinProfilePayload.class);
    }
}
