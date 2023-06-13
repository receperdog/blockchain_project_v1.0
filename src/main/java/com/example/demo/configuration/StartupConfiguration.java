package com.example.demo.configuration;

import com.example.demo.voting.service.VotingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class StartupConfiguration {

    @Bean
    public CommandLineRunner commandLineRunner(VotingService votingService) {
        return args -> {
            Random random = new Random();
            for (int i = 0; i < 100000; i++) {
                String voterId = "Voter" + i;
                int candidateIndex = random.nextInt(votingService.getCandidates().size());
                String candidate = votingService.getCandidates().get(candidateIndex);
                votingService.castVote(voterId, candidate);
            }
        };
    }
}
