package com.example.demo.voting.service;

import com.example.demo.voting.model.VotingSystem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class VotingService {
    private final VotingSystem votingSystem = new VotingSystem();
    private PeerService peerService;

    public void castVote(String voterId, String candidate) {
        votingSystem.setBlockchain(peerService.getBlockchain());
        votingSystem.castVote(voterId, candidate);
    }

    public Map<String, Integer> getVotingResults() {
        votingSystem.setBlockchain(peerService.getBlockchain());
        return votingSystem.calculateResults();
    }

    public List<String> getCandidates() {
        return votingSystem.getCandidates();
    }
}
