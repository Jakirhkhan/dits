package dits.gov.bd.externalapi;

import dits.gov.bd.auth.entity.User;
import org.springframework.web.client.RestTemplate;

public class ExternalApiCall {
    public static Object getRemoveObject(String uri){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, User.class);
    }
}