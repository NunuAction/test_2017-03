package com.duotin.receivebroadcast;

import org.springframework.amqp.core.MessageListener;

/**
 * Created by qiguo on 16/12/7.
 */
public interface IMessageCallBack extends MessageListener {

    /**
     * exchange名字
     *
     * @return
     */
    String getName();

    /**
     * 专辑添加版权
     */
    String ALBUM_ADD_COPYRIGHT = "album_add_copyright_fanout";

    /**
     * 专辑删除版权
     */
    String ALBUM_DELETE_COPYRIGHT = "album_delete_copyright_fanout";

    /**
     * 专辑删除
     */
    String ALBUM_DELETE = "album_delete_fanout";

    /**
     * 专辑增加
     */
    String ALBUM_ADD = "album_add_fanout";

    /**
     * 节目添加
     */
    String CONTENT_ADD = "content_add_fanout";

    /**
     * 节目删除
     */
    String CONTENT_DELETE = "content_delete_fanout";
}
