package com.example.demo.voting.controller;

import com.example.demo.voting.model.Block;
import com.example.demo.voting.model.Transaction;
import com.example.demo.voting.model.VotingSystem;
import com.example.demo.voting.service.VotingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vote")
public class VotingController {

    private final VotingService votingService;

    public VotingController(VotingService votingService) {
        this.votingService = votingService;
    }

    @PostMapping
    public ResponseEntity<String> castVote(@RequestParam String voterId, @RequestParam String candidate) {
        votingService.castVote(voterId, candidate);
        return ResponseEntity.ok("Vote has been cast successfully!");
    }

    @GetMapping("/results")
    public ResponseEntity<Map<String, Integer>> getVotingResults() {
        Map<String, Integer> results = votingService.getVotingResults();
        return ResponseEntity.ok(results);
    }

    // You can add more methods here to expose more operations via your API.
    // For example, a method to get the voting results might be useful.
}
