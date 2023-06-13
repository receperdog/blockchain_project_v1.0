package com.example.demo.voting.service;

import com.example.demo.voting.model.VotingSystem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VotingService {
    private final VotingSystem votingSystem = new VotingSystem();

    public void castVote(String voterId, String candidate) {
        votingSystem.castVote(voterId, candidate);
    }

    public Map<String, Integer> getVotingResults() {
        return votingSystem.calculateResults();
    }

    public List<String> getCandidates() {
        return votingSystem.getCandidates();
    }
}
