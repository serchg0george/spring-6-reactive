package com.springframework.spring6reactive.bootstrap;


import com.springframework.spring6reactive.domain.Beer;
import com.springframework.spring6reactive.domain.Customer;
import com.springframework.spring6reactive.repositories.BeerRepository;
import com.springframework.spring6reactive.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class BootStrapData implements CommandLineRunner {

    private final BeerRepository beerRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        loadBeerData();

        loadCustomerData();
        beerRepository.count().subscribe(count -> {
            System.out.println("Count is: " + count);
            System.out.println("Beer Count is: " + count);
        });

        customerRepository.count().subscribe(count -> {
            System.out.println("Customer Count is: " + count);
        });
    }

    private void loadCustomerData() {
        customerRepository.count().subscribe(count -> {
            if(count == 0){
                customerRepository.save(Customer.builder()
                                .customerName("Customer 1")
                                .build())
                        .subscribe();

                customerRepository.save(Customer.builder()
                                .customerName("Customer 2")
                                .build())
                        .subscribe();

                customerRepository.save(Customer.builder()
                                .customerName("Customer 3")
                                .build())
                        .subscribe();
            }
        });
    }

    private void loadBeerData() {
        beerRepository.count().subscribe(count -> {
            if (count == 0) {

                Beer beer1 = Beer.builder()
                        .beerName("Galaxy Cat")
                        .beerStyle("Pale ale")
                        .upc("12356")
                        .price(new BigDecimal("12.99"))
                        .quantityOnHand(122)
                        .createdDate(LocalDateTime.now())
                        .lastModified(LocalDateTime.now())
                        .build();

                Beer beer2 = Beer.builder()
                        .beerName("Starship")
                        .beerStyle("Pale ale")
                        .upc("13216")
                        .price(new BigDecimal("13.50"))
                        .quantityOnHand(25)
                        .createdDate(LocalDateTime.now())
                        .lastModified(LocalDateTime.now())
                        .build();

                Beer beer3 = Beer.builder()
                        .beerName("Moonlight")
                        .beerStyle("Pale ale")
                        .upc("13216")
                        .price(new BigDecimal("10.49"))
                        .quantityOnHand(40)
                        .createdDate(LocalDateTime.now())
                        .lastModified(LocalDateTime.now())
                        .build();

                beerRepository.save(beer1).subscribe();
                beerRepository.save(beer2).subscribe();
                beerRepository.save(beer3).subscribe();
            }
        });
    }
}
