package com.duotin.receivebroadcast.content;


import com.duotin.receivebroadcast.AbstractEvent;

/**
 *
 * 添加节目事件
 *
 * Created by qiguo on 16/12/7.
 */
public abstract class AbstractContentAdd extends AbstractEvent {

    @Override
    public String getName() {
        return CONTENT_ADD;
    }

}
