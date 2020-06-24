package com.wondernect.elements.boot.application.event;

import org.springframework.context.ApplicationEvent;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: RoleTypeEvent
 * Author: chenxun
 * Date: 2020-06-23 14:08
 * Description: 角色事件
 */
public class WondernectBootEvent extends ApplicationEvent {

    private static final long serialVersionUID = -1917849828371385501L;

    private WondernectBootEventType wondernectBootEventType;

    public WondernectBootEvent(Object source, WondernectBootEventType wondernectBootEventType) {
        super(source);
        this.wondernectBootEventType = wondernectBootEventType;
    }

    @Override
    public Object getSource() {
        return super.getSource();
    }

    public WondernectBootEventType getWondernectBootEventType() {
        return wondernectBootEventType;
    }

    public void setWondernectBootEventType(WondernectBootEventType wondernectBootEventType) {
        this.wondernectBootEventType = wondernectBootEventType;
    }
}
