package com.github.alfred.workflows;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * WorkflowItemList
 *
 * @author lupeng10
 * @create 2023-07-14 14:19
 */
@Data
public class WorkflowItemList {

    private List<WorkflowItem> workflowItems;

    private static volatile WorkflowItemList instance;

    public static WorkflowItemList getInstance() {
        if (instance == null) {
            synchronized (WorkflowItemList.class) {
                if (instance == null) {
                    instance = new WorkflowItemList();
                }
            }
        }
        return instance;
    }

    public void addItem(WorkflowItem item) {
        if (workflowItems == null) {
            workflowItems = new ArrayList<>();
        }
        workflowItems.add(item);
    }

    public String toXMLString() {
        StringBuilder builder = new StringBuilder("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        builder.append("<items>");
        workflowItems.stream().map(WorkflowItem::toXMLString).forEach(builder::append);
        builder.append("</items>");
        return builder.toString();
    }
}
