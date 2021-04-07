/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.co.id.clientappmcc.services;

import java.util.List;
import mii.co.id.clientappmcc.config.RequestFormat;
import mii.co.id.clientappmcc.models.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author User
 */
@Service
public class ProvinceService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${api.url}/province")
    private String url;
    
    
//    public List<Province> getAll() {
//        ResponseEntity<List<Province>> response = restTemplate
//                .exchange(url, HttpMethod.GET, null,
//                        new ParameterizedTypeReference<List<Province>>() {
//                });
//
//        return response.getBody();
//    }
    
   public List<Province> getAll() {
        ResponseEntity<List<Province>> response = restTemplate
                .exchange(url, HttpMethod.GET, new HttpEntity(RequestFormat.createHeaders()),
                        new ParameterizedTypeReference<List<Province>>() {
                });

        return response.getBody();
    }
    
    public void create(Province province) {
        HttpEntity entity = new HttpEntity(province);
        ResponseEntity<String> res = restTemplate.exchange(url+ "/insert", HttpMethod.POST, entity,
                new ParameterizedTypeReference<String>(){});
    }
    
    public void delete(Integer id) {
//        ResponseEntity<District> res = restTemplate.exchange(url + "/delete/" + id, HttpMethod.DELETE, null, District.class);
        restTemplate.delete(url+"/delete/"+id,Province.class);
    }
    
     public Province getById(Integer id) {
        return restTemplate.getForEntity(url + "/" + id, Province.class).getBody();
    }
     
       public void update(Integer id, Province province) {
        HttpEntity entity = new HttpEntity(province);
        ResponseEntity<String> res = restTemplate.exchange(url + "/update/" +id, HttpMethod.PUT,
                entity,new ParameterizedTypeReference<String>() {
});
    }
}
