package io.github.kings1990.plugin.fastrequest.model;

import java.io.Serializable;

public class AiPromptConfig implements Serializable {
    public AiPromptConfig() {
    }

    public AiPromptConfig(String key, String summary, String preQuery, String preAnswer, String prompt) {
        this.key = key;
        this.summary = summary;
        this.preQuery = preQuery;
        this.preAnswer = preAnswer;
        this.prompt = prompt;
    }

    private String key;
    private String summary;
    private String preQuery;
    private String preAnswer;
    
    private String prompt;
    
    public String getKey() {
        return key;
    }
    
    public void setKey(String key) {
        this.key = key;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPreQuery() {
        return preQuery;
    }

    public void setPreQuery(String preQuery) {
        this.preQuery = preQuery;
    }

    public String getPreAnswer() {
        return preAnswer;
    }

    public void setPreAnswer(String preAnswer) {
        this.preAnswer = preAnswer;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}


