package com.example.demo;

import com.example.demo.entities.VetEntity;
import com.example.demo.repositories.VetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.ZonedDateTime;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Component
	class SampleRunner implements CommandLineRunner {

		private final VetRepository vetRepository;

		SampleRunner(VetRepository vetRepository) {
			this.vetRepository = vetRepository;
		}


		@Override
		public void run(String... args){

			Clock clock = Clock.systemUTC();
			ZonedDateTime yesterday = clock.instant().atZone(clock.getZone()).minusDays(1);
			ZonedDateTime tenMinutesAgo = clock.instant().atZone(clock.getZone()).minusMinutes(10);
			ZonedDateTime killMe = clock.instant().atZone(clock.getZone()).plusHours(5);

			VetEntity erika = VetEntity.of("Erika", yesterday, tenMinutesAgo);
			VetEntity david = VetEntity.of("David", yesterday, killMe);

			vetRepository.save(erika);
			vetRepository.save(david);



			System.out.println(vetRepository.findByName("Erika"));
			System.out.println(vetRepository.findByName("Gloria"));
			System.out.println(vetRepository.findByIdAndName(1L, "Erika"));
			System.out.println(vetRepository.findByIdAndName(2L, "Erika"));
		}

	}

}
