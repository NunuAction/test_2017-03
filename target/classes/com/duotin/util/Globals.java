package com.duotin.util;

/**
 * Created with IntelliJ IDEA.
 * Author: wangyongxing(wangyongxing@duotin.com)
 * Date: 15-6-30 下午4:53
 *
 * @Description:
 */
public final class Globals {

    public static final Integer MAX_DISPLAY_ORDER = 100000;

    /**
     * 是否付费
     */
    public static final class IsFree {
        public static Byte FREE = 0;//正常
        public static Byte CHARGE = 1;//收费
    }


    public interface AlbumStatus {
        /**
         * 专辑状态（status）
         */
        public static Integer NOT_PUBLISHED = 0;//未发布

        public static Integer GROUND = 1;//上架

        public static Integer NOT_PASS = 2;//不通过

        public static Integer OFF_SHELF = 3;//下架

        public static Integer CHECK = 4;//上架审核

    }

    public static final class IsCopyRight {
        /**
         * 专辑版权（status）
         */
        public static Byte HAVE = 0;//有

        public static Byte NO_HAVE = 1;//没有

        public static Byte EXPIRE = 2;//过期


    }


    /**
     * 删除状态（is_del）
     */
    public static final class IsDel {

        public static Integer NOT_DELETE = 0;//未删除

        public static Integer DELETED = 1;//已删除
    }

    /**
     * 隐藏状态
     */
    public static final class IsShow {
        public static Integer NOT_SHOW = 0;//隐藏
        public static Integer SHOWED = 1;//显示
    }

    public interface InvalidStatus {
        Integer VALID = 1;//有效(上架)
        Integer INVALID = 0;//无效（下架）
        Integer UN_PUBLISH=2;//未发布
    }


    /**
     * CacheStatus
     */

    public static final class CacheStatus {

        public static Integer CACHE = 0;//缓存

        public static Integer DELETE = 1;//删除

        public static Integer PERSISTENCE = 2;//持久
    }


    /**
     * source
     */

    public final static class Source {

        public static Byte EDITOR = 0;//编辑

        public static Byte PODCAST = 1;//播客

        public static Byte SPIDER = 2;//爬虫
    }


    /**
     * '0-等待上传 1-已上传'
     */
    public interface UploadStatus {
        Boolean WAIT_UPLOAD = Boolean.FALSE;
        Boolean UPLOAD_SUCCESS = Boolean.TRUE;
    }

    /**
     * '0-新 1-转ing 2-完成 -1-源文件不存在 -2-转换失败'
     */
    public interface TranscodingStatus {
        Integer NEW = 0;
        Integer TRANSCODING = 1;
        Integer SUCCESS = 2;
        Integer SOURCE_FILE_NOT_EXISTS = -1;//暂时用来标志上传文件失败
        Integer TRANSCODE_FAILURE = -2;
    }


    /**
     * '0-未删除，1-已删除'
     */
    public interface DelStatus {
        Byte NOT_DELETE = 0;//未删除
        Byte DELETED = 1;//已删除
    }


    /**
     * '0-下架，1-上架，2-暂时不可用'
     */
    public interface ContentStatus {
        Integer UNDERCARRIAGE = 0;
        Integer GROUNDING = 1;
        Integer NOT_AVAILABLE = 2;
        Integer ALBUM_NOT_PUBLISHED = 3;
        Integer ALBUM_OFF_SHELF = 4;
    }

    /**
     * '0未审，1通过，2不通过'
     */
    public interface ContentPoolStatus {
        Integer NOT_VERIFY = 0;
        Integer PASS = 1;
        Integer NOT_PASS = 2;
    }

    /**
     * 否(false), 是(true);
     */
    public interface BooleanStatus {
        Boolean YES = Boolean.TRUE;
        Boolean NO = Boolean.FALSE;
    }

    /**
     * 目录显示
     */
    public interface CategoryGlobalShow {
        Boolean SHOW = Boolean.TRUE;
        Boolean HIDE = Boolean.FALSE;
    }

    /**
     * boolean删除状态
     */
    public interface BooleanIsDel {
        Boolean DELETE = Boolean.TRUE;
        Boolean NOT_DELETE = Boolean.FALSE;
    }

    /**
     * 加V申请状态
     * 申请状态:0 提交申请；1 审核中；2 成功；4 驳回
     */
    public interface ApplyVStatus {
        Byte COMMIT_TO_CHECKE = 0;
        Byte IN_CHECKE = 1;
        Byte SUCCESS_CHECKE = 2;
        Byte REJECT_CHECKE = 4;
    }

    public interface IntegerSource {

        Integer EDITOR = 0;//编辑

        Integer PODCAST = 1;//播客

        Integer SPIDER = 2;//爬虫
    }

    public interface IsHide {

        Integer hide = 1;

        Integer show = 0;

    }

    /**
     * 是否处理
     * 0未处理 1已处理
     */
    public interface IsFixed {
        Byte NOT_FIXED = 0; //0未处理
        Byte FIXED = 1; //1已处理
    }

    public interface Status {

        Integer CLOSE = 0;//未启用

        Integer OPEN = 1;//启用
    }

    public interface RecommendedPosition {
        Byte LIVE = 1;
    }

    public interface RecommendedStatus {
        Byte ONLINE = 1;
    }

    public interface LiveType {
        Integer REAL_LIVE = 1;
    }

    public interface PlatformType {
        Integer ALL = 0;
        Integer ANDROID = 1;
        Integer IOS = 2;
    }
}
