package com.duotin.receivebroadcast.album;


import com.duotin.receivebroadcast.AbstractEvent;

/**
 *
 * 删除新增事件
 *
 * Created by qiguo on 16/12/7.
 */
public abstract class AbstractAlbumAdd extends AbstractEvent {

    @Override
    public String getName() {
        return ALBUM_ADD;
    }

}
