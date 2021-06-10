package io.github.kings1990.plugin.fastrequest.model;


public class HostGroup {

    private String env;
    private String url;

    public HostGroup() {
    }

    public HostGroup(String env, String url) {
        this.env = env;
        this.url = url;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
