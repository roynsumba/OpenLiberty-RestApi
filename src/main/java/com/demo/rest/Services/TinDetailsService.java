package com.demo.rest.Services;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import com.demo.rest.Data.TaxDAO;
import com.demo.rest.Data.TaxInfo;
import com.demo.rest.Data.TinProfileDAO;
import com.demo.rest.Data.TinProfileData;
import com.demo.rest.Data.TinProfilePayloadEntity;
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

    @Inject
    @ConfigProperty(name="uName")
    private String cusername; 
    
    @Inject
    @ConfigProperty(name="password")
    private String cpassword; 

    @Inject
    @ConfigProperty(name="signature")
    private String csignature;

    @Inject
    private TaxDAO taxDAO;

    @Inject
    private TinProfileDAO tinProfileDAO;

    public TinDetailsService() {
        this.client = ClientBuilder.newClient();
    }


    @Transactional
    public void saveTinDetails(TinDetailsPayload payload) {
        TaxInfo event = new TaxInfo();
        event.setTin(payload.gettin());
        event.setMessage(payload.getmessage());
        event.setTaxPayerType(payload.gettaxPayerType());
        event.setTinStatus(payload.gettinStatus());
        event.settTaxPayerName(payload.gettaxPayerName());
        taxDAO.createEvent(event);
        
    }

    @Transactional
    public void updateTaxInfo(int id, TinDetailsPayload updatedInfo) {
        TaxInfo prevEvent = taxDAO.readEvent(id);
        if (prevEvent == null) {
            throw new NotFoundException("Event with id " + id + " not found");
        }
        prevEvent.setMessage(updatedInfo.getmessage());
        prevEvent.setTaxPayerType(updatedInfo.gettaxPayerType());
        prevEvent.setTinStatus(updatedInfo.gettinStatus());
        prevEvent.setTin(updatedInfo.gettin());
        prevEvent.settTaxPayerName(updatedInfo.gettaxPayerName());
        taxDAO.updateEvent(prevEvent);
    }

    public List<TaxInfo> getAllTaxInfo() {
        List<TaxInfo> entities = taxDAO.readAllEvents();
        if(entities != null) {
            return entities; 
            }
            return null;
        }

     public TaxInfo getTaxInfo(int eventId) {


        TaxInfo payloadEntity = taxDAO.readEvent(eventId);
        if (payloadEntity != null) {
            TaxInfo response = new TaxInfo();
            response.setId(payloadEntity.getId());
            response.setMessage(payloadEntity.getMessage());
            response.settTaxPayerName(payloadEntity.getTaxPayerName());
            response.setTaxPayerType(payloadEntity.getTaxPayerType());
            response.setTinStatus(payloadEntity.getTinStatus());
            response.setTin(payloadEntity.getTin());
            return response;
        }
        return null;
    } 

    @Transactional
    public void deleteTaxInfo(int id) {
        TaxInfo info = taxDAO.readEvent(id);
        if (info == null) {
            throw new NotFoundException("Event with id " + id + " not found");
        }
        taxDAO.deleteEvent(info);
    }


    public TinDetailsPayload FetchTinDetails(String tin) throws Exception {
        TinDetailsRequest requestPayload = new TinDetailsRequest();
        requestPayload.setUserName(cusername);
        requestPayload.setPassword(cpassword);
        requestPayload.setSignature(csignature);
        Response apiResponse = client.target(tinDetailsApiUrl + tin)
                                     .request(MediaType.APPLICATION_JSON)
                                     .post(Entity.json(requestPayload));
        return apiResponse.readEntity(TinDetailsPayload.class);
    }

    
    public void createTinProfile(TinProfilePayload payload) {
    TinProfilePayloadEntity entity = new TinProfilePayloadEntity();
    entity.setMessage(payload.getMessage());
    entity.setStatusCode(payload.getStatusCode());
    if (payload.getData() != null && !payload.getData().isEmpty()) {
        TinProfileData data = payload.getData().get(0);
        List<TinProfileData> dataArray = new ArrayList<>(); 
        TinProfileData list = new TinProfileData();
        list.setSurName(data.getSurName());
        list.setFirstName(data.getFirstName());
        list.setMiddleName(data.getMiddleName());
        list.setBirthDt(data.getBirthDt());
        list.setNin(data.getNin());
        list.setTaxPayerType(data.getTaxPayerType());
        list.setTinProfilePayload(entity);
        dataArray.add(list);
        entity.setData(dataArray);
    }
    tinProfileDAO.create(entity);
}
    
    public TinProfilePayloadEntity getTinProfile(Long id) {
        TinProfilePayloadEntity entity = tinProfileDAO.read(id);
        if (entity != null) {
            TinProfilePayloadEntity response = new TinProfilePayloadEntity();
            response.setId(entity.getId());
            response.setMessage(entity.getMessage());
            response.setStatusCode(entity.getStatusCode());
            List<TinProfileData> dataArray = new ArrayList<>(); 
            TinProfileData dataObj = new TinProfileData();
            for (TinProfileData data : entity.getData()) {

                dataObj.setSurName(data.getSurName()); 
                dataObj.setMiddleName(data.getMiddleName());
                dataObj.setFirstName(data.getFirstName());
                dataObj.setTaxPayerType(data.getTaxPayerType());
                dataObj.setNin(data.getNin());
                dataObj.setBirthDt(data.getBirthDt());
                dataObj.setId(data.getId());
            }
            dataArray.add(dataObj);
            response.setData(dataArray);
            return response;
        }
        return null;
    } 
        
        
        public List<TinProfilePayloadEntity> getTinProfiles() {     
            List<TinProfilePayloadEntity> entities = tinProfileDAO.findAll(); 
            List<TinProfilePayloadEntity> myList = new ArrayList<>();
            if(entities != null)
            {
                for(TinProfilePayloadEntity entity : entities){
                    TinProfilePayloadEntity response = new TinProfilePayloadEntity();
                    response.setId(entity.getId());
                    response.setMessage(entity.getMessage());
                    response.setStatusCode(entity.getStatusCode());
                    List<TinProfileData> dataArray = new ArrayList<>(); 
                    TinProfileData dataObj = new TinProfileData();
                    for (TinProfileData data : entity.getData()) {
                        dataObj.setSurName(data.getSurName()); 
                        dataObj.setMiddleName(data.getMiddleName());
                        dataObj.setFirstName(data.getFirstName());
                        dataObj.setTaxPayerType(data.getTaxPayerType());
                        dataObj.setNin(data.getNin());
                        dataObj.setBirthDt(data.getBirthDt());
                        dataObj.setId(data.getId());
                    }
                    dataArray.add(dataObj);
                    response.setData(dataArray);
                    myList.add(response);
                } 
                return myList;
            }
            return null;
        }


    public void updateTinProfile(Long id, TinProfilePayloadEntity payload){
        TinProfilePayloadEntity existingEntity = tinProfileDAO.read(id);
        List<TinProfileData> existingData = existingEntity.getData();
        if(existingEntity != null){
            existingEntity.setMessage(payload.getMessage());
            existingEntity.setStatusCode(payload.getStatusCode());
    if (payload.getData() != null && !payload.getData().isEmpty()) {
        List<TinProfileData> dataArray = new ArrayList<>(); 
        TinProfileData dataObj = new TinProfileData();
        for (TinProfileData data : payload.getData()) {
            dataObj.setSurName(data.getSurName()); 
            dataObj.setMiddleName(data.getMiddleName());
            dataObj.setFirstName(data.getFirstName());
            dataObj.setTaxPayerType(data.getTaxPayerType());
            dataObj.setNin(data.getNin());
            dataObj.setBirthDt(data.getBirthDt());
            dataObj.setId(existingData.get(0).getId());
            dataObj.setTinProfilePayload(existingEntity);
            dataArray.add(dataObj);
        }
        existingEntity.setData(dataArray);
    }
             tinProfileDAO.update(existingEntity);
        }
    }

    @Transactional
    public void deleteTinProfile(Long id) {
        TinProfilePayloadEntity entity = tinProfileDAO.read(id);
        if (entity != null) {
            tinProfileDAO.delete(entity); 
        }
    }

    public TinProfilePayload FetchTinProfile(String tin) throws Exception {
         // Create and populate the Authentication object
        TinDetailsRequest auth = new TinDetailsRequest();
        auth.setUserName(cusername);
        auth.setPassword(cpassword);
        auth.setSignature(csignature);

        // Populate ReqPayload
        TinProfileRequest reqPayload = new TinProfileRequest();
        reqPayload.setAuthentication(auth);
        reqPayload.setTinNo(tin);

        Response apiResponse = client.target(tinProfileApiUrl)
                                     .request(MediaType.APPLICATION_JSON)
                                     .post(Entity.json(reqPayload));

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(apiResponse.readEntity(String.class), TinProfilePayload.class);
    }
}
