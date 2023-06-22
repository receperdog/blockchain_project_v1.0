package com.example.demo.voting.service;

import com.example.demo.voting.model.Blockchain;
import com.example.demo.voting.model.VotingSystem;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Component
@AllArgsConstructor
public class VotingService {
    private VotingSystem votingSystem;
    private PeerService peerService;
    private PeerNetworkingService peerNetworkingService;

    public void castVote(String voterId, String candidate, PrivateKey privateKey, PublicKey publicKey) {
        votingSystem.castVote(voterId, candidate, privateKey, publicKey);
    }

    public Map<String, Integer> getVotingResults() {
        return votingSystem.calculateResults();
    }

    public List<String> getCandidates() {
        return votingSystem.getCandidates();
    }

    @Scheduled(fixedDelay = 10000) // 10 seconds
    public void synchronizeBlockchain() {
        // List of peers could be fetched from a config file or other source
        List<String> peerUrls = Arrays.asList("http://localhost:8081", "http://localhost:8082");

        for (String peerUrl : peerUrls) {
            try {
                System.out.println("SYNCING WITH PEER AT " + peerUrl);
                Blockchain peerBlockchain = peerNetworkingService.getBlockchainFromPeer(peerUrl);
                if (peerBlockchain != null && peerBlockchain.isValid() && peerBlockchain.getChain().size() > votingSystem.getChain().size()) {
                    peerService.setBlockchain(peerBlockchain);
                    votingSystem.setBlockchain(peerBlockchain);
                }
            } catch (Exception e) {
                System.out.println("Error synchronizing with peer at " + peerUrl + ": " + e.getMessage());
            }
        }
    }

}

