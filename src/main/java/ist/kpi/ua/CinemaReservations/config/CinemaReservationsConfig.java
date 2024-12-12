package ist.kpi.ua.CinemaReservations.config;

// import ist.kpi.ua.CinemaReservations.repository.jpa.BookingRepository;
// import ist.kpi.ua.CinemaReservations.repository.stub.BookingRepositoryStub;
import ist.kpi.ua.CinemaReservations.service.EmailService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

// This class demonstrates bean creation

@Configuration
public class CinemaReservationsConfig {

    // @Bean
    // public BookingRepository bookingRepositoryStub() {
    //     return new BookingRepositoryStub();
    // }

    @Bean
    @Scope("prototype")
    public EmailService emailService() {
        return new EmailService();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
