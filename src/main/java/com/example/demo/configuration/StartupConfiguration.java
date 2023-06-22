package com.example.demo.configuration;

import com.example.demo.voting.service.VotingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Configuration
public class StartupConfiguration {

    @Bean
    public CommandLineRunner commandLineRunner(VotingService votingService) {
        return args -> {
            Random random = new Random();
            for (int i = 0; i < 10; i++) {
                String voterId = "Voter" + i;
                int candidateIndex = random.nextInt(votingService.getCandidates().size());
                String candidate = votingService.getCandidates().get(candidateIndex);
                KeyPair keyPair = generateKeyPair();
                votingService.castVote(voterId, candidate, keyPair.getPrivate(), keyPair.getPublic());
            }
        };
    }

    private KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            return keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

