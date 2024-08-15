package com.example.ai.controller;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FunctionCallingController {
    private final ChatModel chatModel;
 
    
    @GetMapping("/nofunc")
    public String noFunc(String prompt) {
    	return chatModel.call(new Prompt(prompt)).getResult().getOutput().getContent();
    }

    @GetMapping("/func")
    public String func(String prompt) {
        return chatModel.call(new Prompt(prompt, 
				                         OpenAiChatOptions.builder()
				                         .withFunction("ProductSalesInfo")
				                         .withFunction("ProductDetailsInfo")
				                         .withFunction("CurrectDateTime")
				                         .build())
        		).getResult().getOutput().getContent();
    }
}
