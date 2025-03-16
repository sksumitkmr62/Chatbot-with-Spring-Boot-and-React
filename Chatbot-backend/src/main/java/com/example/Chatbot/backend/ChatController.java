package com.example.Chatbot.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.Chatbot.backend.ChatRequest;
import com.example.Chatbot.backend.ChatResponse;
import com.example.Chatbot.backend.GeminiService;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private GeminiService geminiService;

    @PostMapping
    public ChatResponse chat(@RequestBody ChatRequest request) {
        System.out.println("Received request: " + request.getMessage());
        String response;
        try {
            response = geminiService.callApi(request.getMessage(), "AIzaSyBMhZzLK3YaqD2E98YOMTN823DBZVslAg8");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error calling Gemini API", e);
        }
        return new ChatResponse(response);
    }

    @GetMapping("/test")
    public String test() {
        return "Test endpoint works!";
    }
}
