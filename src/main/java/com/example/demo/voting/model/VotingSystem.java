package com.example.demo.voting.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class VotingSystem {
    private Blockchain blockchain;
    private Map<String, Integer> candidateVotes;

    private List<String> candidates;

    public VotingSystem() {
        candidateVotes = new HashMap<>();
        candidates = Arrays.asList("Candidate1", "Candidate2", "Candidate3", "Candidate4");
    }

    public void setBlockchain(Blockchain blockchain) {
        this.blockchain = blockchain;
    }

    public void castVote(String voterId, String candidate) {
        if (hasVoted(voterId)) {
            System.out.println("You have already cast your vote.");
            return;
        }

        // Check if the candidate is valid
        if (!isValidCandidate(candidate)) {
            System.out.println("Invalid candidate.");
            return;
        }

        // Create a new transaction and add it to the blockchain
        Transaction transaction = new Transaction(voterId, candidate);
        Block newBlock = new Block(blockchain.getLatestBlock().getIndex() + 1,
                blockchain.getLatestBlock().getHash(), transaction);

        // Check if consensus is reached before adding the block
        if (ConsensusProtocol.reachConsensus(blockchain)) {
            blockchain.addBlock(newBlock);

            // Update the vote count for the candidate
            candidateVotes.put(candidate, candidateVotes.getOrDefault(candidate, 0) + 1);

            System.out.println("Vote cast successfully!");
        } else {
            System.out.println("Consensus not reached. Vote not cast.");
        }
    }

    public boolean hasVoted(String voterId) {
        for (Block block : blockchain.getChain()) {
            Transaction transaction = block.getData();
            if (transaction != null && transaction.getVoterId().equals(voterId)) {
                return true;
            }
        }
        return false;
    }

    private boolean verifyHash(String voter, String hashedData) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(voter.getBytes(StandardCharsets.UTF_8));
            String hashedVoter = bytesToHex(hashBytes);
            return hashedVoter.equals(hashedData);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

    public boolean isValidCandidate(String candidate) {
        // Add your own logic to validate the candidate, e.g., checking against a list of valid candidates
        return true;
    }

    public List<Block> getChain() {
        return blockchain.getChain();
    }

    public Map<String, Integer> calculateResults() {
        return candidateVotes;
    }

    public List<String> getCandidates() {
        return candidates;
    }

}
