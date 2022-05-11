package com.example1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLOutput;

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
public class SpringAddressMappingApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringAddressMappingApplication.class, args);
    try {
      SpringAddressMappingApplication.checkHealth();
    } catch (Exception e) {
      System.out.println("Cron job is in the trouble");
    }
  }

  @Scheduled(cron = "5 * * * * ?")
  public static void checkHealth() {
    RestTemplate restTemplate = new RestTemplate();
    String result;
    try {
      result = (restTemplate.exchange("http://localhost:9191/checkhealth", HttpMethod.GET, null, String.class).getBody());
      result = result.substring(11,13);
      if(result.equalsIgnoreCase("up")){
        result="<--------------Application Health is Good------------>";
      }
    }catch (Exception exception){
      result = "Application is Down------------->";
    }
    System.out.println(result);
  }

}
