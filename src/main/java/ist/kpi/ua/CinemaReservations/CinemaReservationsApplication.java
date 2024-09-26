package ist.kpi.ua.CinemaReservations;

import ist.kpi.ua.CinemaReservations.service.EmailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CinemaReservationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaReservationsApplication.class, args);
	}

}
