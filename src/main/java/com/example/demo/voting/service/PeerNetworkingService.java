package com.example.demo.voting.service;

import com.example.demo.voting.model.Blockchain;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class PeerNetworkingService {
    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();

    public Blockchain getBlockchainFromPeer(String peerUrl) {
        try {
            String response = restTemplate.getForObject(peerUrl + "/blockchain", String.class);
            return objectMapper.readValue(response, Blockchain.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sendBlockchainToPeer(String peerUrl, Blockchain blockchain) {
        try {
            String blockchainJson = objectMapper.writeValueAsString(blockchain);
            restTemplate.postForObject(peerUrl + "/blockchain", blockchainJson, String.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}