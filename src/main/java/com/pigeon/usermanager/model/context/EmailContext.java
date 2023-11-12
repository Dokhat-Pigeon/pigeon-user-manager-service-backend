package com.pigeon.usermanager.model.context;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class EmailContext {

    private String from;

    private String to;

    private String subject;

    private String email;

    private String attachment;

    private String fromDisplayName;

    private String emailLanguage;

    private String displayName;

    private String templateLocation;

    private Map<String, Object> context;
}
