package com.duotin.receivebroadcast.content;


import com.duotin.receivebroadcast.AbstractEvent;

/**
 *
 * 删除节目事件
 *
 * Created by qiguo on 16/12/7.
 */
public abstract class AbstractContentDelete extends AbstractEvent {

    @Override
    public String getName() {
        return CONTENT_DELETE;
    }

}
