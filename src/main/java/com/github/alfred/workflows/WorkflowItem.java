package com.github.alfred.workflows;

import lombok.Builder;
import lombok.Data;

/**
 * WorkflowItem
 *
 * @author lupeng10 2023-07-14 14:08
 * @create 2023-07-14 14:08
 */
@Data
@Builder
public class WorkflowItem {

    private String title;
    private String subtitle;
    private String arg;
    private String icon;

    public String toXMLString() {
        String template = """
                <item valid="yes">
                    <title>%s</title>
                    <subtitle>%s</subtitle>
                    <arg>%s</arg>
                    <icon>%s</icon>
                </item>
                """.strip();
        return template.formatted(nullToEmpty(title), nullToEmpty(subtitle), nullToEmpty(arg), nullToEmpty(icon));
    }


    private String nullToEmpty(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }
}
