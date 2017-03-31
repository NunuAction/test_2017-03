package com.duotin.receivebroadcast.album;

import com.duotin.receivebroadcast.AbstractEvent;

/**
 * 专辑添加版权
 * <p>
 * 广播数据结构
 * <p>
 * Created by qiguo on 16/12/14.
 */
public abstract class AbstractAlbumAddCopyright extends AbstractEvent {

    @Override
    public String getName() {
        return ALBUM_ADD_COPYRIGHT;
    }

}
