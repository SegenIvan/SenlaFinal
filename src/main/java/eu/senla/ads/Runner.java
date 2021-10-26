package eu.senla.ads;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Runner {
    public static void main(String[] args) {
        SpringApplication.run(Runner.class,args);
        //валидацию сделать, javax validation
        //написать запрос для создания объявления с рейтингом продавца
        //почитать про transactional
        //возвращать id объекта при создании на бэк, создать responceIdDto
        //почитать как маппитть разные энитити моделмаппером
    }
}
