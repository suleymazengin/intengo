package com.intengo.intengo.utilty;

import java.util.Arrays;
import java.util.List;

public class Utility {

    public static final List<String> contentTypes = Arrays.asList("image/png", "image/jpeg",
            "application/pdf", "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5 * 60 * 60;
    public static final String SIGNING_KEY = "NTVmZmY3YjU1NjY5Nzk0NTk0MTc2MGQ5M2E4MDRhNjNkMmNmZDI5N2Q4YTQ4ZDIxYTAxMWNiZWM5MDgwOTUzZWJiZGIyMjRjZGQwNDQwODA3YjkxYmM1ZWFhM2VhM2ZkMGUwOGJiMzNmZGQ5MmU4YzU0MDA4NGM0NDAwNmQyNmQ=";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTHORITIES_KEY = "scopes";


}
