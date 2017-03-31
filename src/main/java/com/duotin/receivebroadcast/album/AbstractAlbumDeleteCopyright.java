package com.duotin.receivebroadcast.album;

import com.duotin.receivebroadcast.AbstractEvent;

/**
 * Created by qiguo on 16/12/14.
 */
public abstract class AbstractAlbumDeleteCopyright extends AbstractEvent {

    @Override
    public String getName() {
        return ALBUM_DELETE_COPYRIGHT;
    }

}
