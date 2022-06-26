package com.example.task.http;

public class WeChatInfo {
    private String path;
    private String query;
    private String env_version;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getEnv_version() {
        return env_version;
    }

    public void setEnv_version(String env_version) {
        this.env_version = env_version;
    }
}
