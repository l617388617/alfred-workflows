package com.github.alfred.workflows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Timestamp2DateMain
 *
 * @author lupeng10
 * @create 2023-07-14 14:25
 */
public class TimestampConvertDateMain {

    public static void main(String[] args) throws ParseException {
        if (args == null || args.length == 0) {
            return;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String arg = StringUtils.trim(args[0]);
        WorkflowItemList workflowItemList = WorkflowItemList.getInstance();
        WorkflowItem.WorkflowItemBuilder builder = WorkflowItem.builder()
                .icon("/System/Library/CoreServices/CoreTypes.bundle/Contents/Resources/Clock.icns");

        // 当前时间戳
        if (StringUtils.equals(arg, "n")) {
            long now = System.currentTimeMillis();
            builder.title(String.valueOf(now))
                    .subtitle(String.valueOf(arg))
                    .arg(String.valueOf(now))
                    .build();
            workflowItemList.addItem(builder.build());
            System.out.println(workflowItemList.toXMLString());
            return;
        }

        // 当前时间
        if (StringUtils.equals(arg, "nn")) {
            String now = format.format(new Date());
            workflowItemList.addItem(builder.title(now).subtitle(arg).arg(now).build());
            System.out.println(workflowItemList.toXMLString());
            return;
        }

        // 时间戳转换成时间
        if (StringUtils.isNumeric(arg)) {
            long timestamp = NumberUtils.toLong(arg);
            String dateStr = format.format(new Date(timestamp));
            workflowItemList.addItem(builder.title(dateStr).subtitle(String.valueOf(timestamp)).arg(dateStr).build());
            System.out.println(workflowItemList.toXMLString());
            return;
        }

        // 时间转换成时间戳
        String timestamp = String.valueOf(format.parse(arg).getTime());
        workflowItemList.addItem(builder.title(timestamp).subtitle(arg).arg(timestamp).build());
        System.out.println(workflowItemList.toXMLString());
    }
}
