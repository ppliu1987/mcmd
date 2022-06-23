package com.mcmd.common.event;

import com.mcmd.common.pojo.AutopilotExceptionRecord;
import org.springframework.context.ApplicationEvent;

/**
 * @author ppliu
 * @version 1.0
 * @date 2022/3/4 15:52
 */
public class ExceptionRecordEvent extends ApplicationEvent {
    public AutopilotExceptionRecord record;

    /**
     * Create a new ApplicationEvent.
     *
     * @param record the object on which the event initially occurred (never {@code null})
     */
    public ExceptionRecordEvent(AutopilotExceptionRecord record) {
        super(record);
        this.record = record;
    }
}
