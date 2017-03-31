package com.duotin.redis;

/**
 * redis key
 * User: guoqi
 * Date: 15/9/1
 * Time: 上午10:52
 */
public final class RedisConst {

    public static final class ExpireTime{

        /**
         * 5分钟
         */
        public static final Integer FIVE_MINTES = 300;

        /**
         * 半小时
         */
        public static final Integer HALF_HOUR = 1800;

        /**
         * 一天
         */
        public static final Integer ONE_DAY = 24*3600;

    }


    /**
     * 投票相关
     */
    public static final class Vote {

        /**
         * 投票统计信息 hash
         */
        public static final String VOTE_VOTEID_SOURCE_STAT = "Vote:{voteId}:{source}:Stat";

        /**
         * 投票总数  String
         */
        public static final String VOTE_TOTAL_COUNT = "Vote:{voteId}:{source}:Count";
    }

    /**
     * 赞相关
     */
    public static final class Praise {
        /**
         * 赞次数
         */
        public static final String PRAISE_APPID_DESCID = "Praise:{appId}:{descId}:Count";
    }


    /**
     * 直播
     */
    public static final class Live {
        /**
         * 存放每天的新增节目集合
         */
        public static final String LIVE_DAY_CONTENT = "Live:{liveId}:{YYYYMMDD}";
        /**
         * 存放用户每天收听节目集合
         */
        public static final String LIVE_USER_LISTEN = "Live:User:{userKey}:{YYYYMMDD}";

        /**
         * 直播键集合
         */
        public static final String LIVE_KEY_SET = "Live:{liveId}:Key";

        /**
         * 用户收听键集合
         */
        public static final String LIVE_USER_LISTEN_KEY_SET = "Live:User:{userKey}:Key";

        /**
         * 伪直播当前播放位置
         */
        public static final String LIVE_CURRENT_POSITION="Live:{liveId}:Current";

        /**
         * 存放直播节目列表
         *
         * 当天有效
         *
         */
        public static final String LIVE_PROGRAM_List="Live:{liveId}:Program:List";

    }


    /**
     * 商城相关
     */
    public static final class Mall{
        /**
         * 订单生成器
         */
        public static final String ORDER_NUM_GENERATOR="order_num_generator";
    }


    /**
     * 签到
     */
    public static final class SignIn{
        /**
         * 保存用户的连续签到次数
         */
        public static final String SIGNIN_USERKEY_CONTINUOUS_DAYS="SignIn:{userKey}:{YYYYMMDD}:continuous:days";
    }

    /**
     * 第三方平台
     */
    public static final class OpenApp{

        /**
         * open app 的app信息
         */
        public static final String OPEN_APP_KEY = "OpenApp:{appKey}";
        /**
         * 分页获取专辑
         */
        public static final String OPEN_APP_ALBUM_APP_KEY = "OpenAppAlbums:{appId}:page:{page}";

        /**
         * 三方平台可以使用的类目接口
         */
        public static final String OPEN_APP_CATEGORY_APP_KEY = "OpenAppCats:{appId}";
    }

    /**
     * service里面用的album dto的缓存，这里需要重构，需要去和core里面进行合并
     */
    public static final class ServiceAlbumDto{

        /**
         * album和内容的分页
         */
        public static  final String ALBUM = "s.AlbumDto:{albumId}:page:{page}";
    }

    /**
     * 移动设备
     */
    public static final class Mobile
    {
        /**
         * 移动设备ID
         */
        public static final String MOBILE_ID = "v2:mb_id{deviceKey}{packageName}";


        /**
         * 根据SKU查询APPID
         */
        public static final String APP_ID = "v2:app_id:{sku}";
    }

    public static final class Token{

        public static final String TOKEN = "token";

    }

    public static final class Cache{

        /**
         * mobile缓存key
         */
        public static final String MOBILE_ID="mobileid:{deviceKey}:{platform}:{packageName}";

        /**
         * 汽车之家直播缓存
         */
        public static final String QiCheZhiJia="qichezhijia";

        /**
         * 专辑下的节目
         */
        public static final String Album_Content="album:{albumId}:{page}:{ordey}";
        /**
         * 专辑
         */
        public static final String ALBUM="album:{album_id}";

        /**
         * 主播下的节目
         */
        public static final String User_Content="user:{userId}:{page}:{ordey}";

        /**
         * 老版本专辑下的节目
         */
        public static final String Album_Content_Lower_Version="albumLowerVersion:{albumId}:{page}:{ordey}";

        /**
         * 首页
         */
        public static final String Home_Page="home:page:{type}:{platform}:{version}";

        /**
         * 拦截器用户信息
         */
        public static final String USER_KEY_KEY = "user:key:{userKey}";

        public static final String USER_ID_KEY = "user:id:{userId}";

        /**
         * 分类下专辑
         */
        public static final String CATEGORY_SUBCATEGORY_ALBUM = "category:album:{categoryId}:{subCategoryId}:{sortType}:{copyRight}:{page}";

        /**
         * 开发平台分类下专辑
         */
        public static final String CATEGORY_SUBCATEGORY_ALBUM_OPEN = "category:album:{categoryId}:{subCategoryId}:{sortType}:{copyright}:{page}";

        /**
         * 主播下专辑
         */
        public static final String PODCASTER_ALBUMMAP = "pocaster:albumMap:{userId}";
        /**
         * 某个专辑的相关推荐
         */
        public static final String ALBUM_RELATED_ALBUMMAP = "album:related:albumMap:{albumId}";
        /**
         * 主播下所有专辑的节目
         */
        public static final String PODCASTER_ALBUM_CONTENT = "podcaster:album:content:{userId}";

        /**
         * 分类
         */
        public static final String CATEGORY_Id = "category:{categoryId}";

        public static final String STATISTICS_LIVE="live:{current_page}:{type}:{page_size}:{start_time}:{end_time}:{sources}:{dimension}:{duration}:{end_time}:{content_id}";
        public static final String STATISTICS_LIVE_TABLE="live_table:{current_page}:{type}:{page_size}:{start_time}:{end_time}:{sources}:{dimension}:{duration}:{end_time}:{content_id}";

        /**
         * 专辑信息
         */
        public static final String ALBUMMAP_ALBUMID = "albumMap:{albumId}";

        /**
         * 榜单-收听历史-节目分组，根据时间缓存
         */
        public static final String CONTENT_LISTEN_TIME_KEY = "content_listen_time_at:{start}:{end}:{limit}";
        /**
         * 榜单-收听历史-节目分组，根据月缓存
         */
        public static final String CONTENT_LISTEN_MONTH_KEY = "content_listen_month_at:{shardDate}:{limit}";
        /**
         * 主播2级标签
         */
        public static final String TAG = "tag:{tagId}:{pageIndex}:{pageSize}";
        /**
         * 主播电台，火热主播
         */
        public static final String HOT_ANCHOR_ALBUM_PAGE = "hot:anchor:album:{page}";
        /**
         * 主播电台，火热主播
         */
        public static final String NEW_ANCHOR_ALBUM = "new:anchor:album";
        /**
         * 榜单3，api
         */
        public static final String RANK_PERIOD_CODE_JSON_KEY = "rank:json:{rankId}:{period}";
        /**
         * 老版本的频道也首页
         */
        public static final String TEAM_CATEGORIES = "teamcategories";
        /**
         * 榜单3，节目收听历史
         */
        public static final String RANK3_CONTENT_LISTEN_TIME = "rank3:content:listen:{start}:{end}:{limit}";
        /**
         * 榜单3，节目收听历史
         */
        public static final String RANK3_CONTENT_LISTEN_MONTH = "rank3:content:listen:{month}:{limit}";
        /**
         * 榜单3，专辑收听历史
         */
        public static final String RANK3_ALBUM_LISTEN_TIME = "rank3:album:listen:{start}:{end}";
        /**
         * 榜单3，专辑收听历史
         */
        public static final String RANK3_ALBUM_LISTEN_MONTH = "rank3:album:listen:{month}:{limit}";
        /**
         * 榜单3，节目评论数
         */
        public static final String RANN3_COMMENT_TIME = "rank3:comment:time:{start}:{end}";
        /**
         * 榜单3，节目点赞数
         */
        public static final String RANN3_COLLECT_TIME = "rank3:collect:time:{start}:{end}";

        /**
         * 专辑基本信息
         */
        public static final String ALBUM_BASIC_INFO = "album_basic_info:{albumId}";
        /**
         * 专辑信息，包含更新至
         */
        public static final String ALBUM_INFO = "album_info:{albumId}";
        /**
         * 专辑详细信息，包含更新至，标签信息等
         */
        public static final String ALBUM_DETAIL_INFO = "album_detail_info:{albumId}";

    }


    /**
     *  视频推介
     */
    public static final class Recommendation{
        /**
         * mobile缓存key
         */
        public static final String MOBILE_ID="recommendation";
    }

    public static final class VerificationCode{

        public static final String CODE="mobile:{mobile}:code";
    }

    /**
     * 实名制认证
     */
    public static final class UserRealNameIdentityRedisKey{
        public static final String KEY="userRealNameIdentity:{userId}";
        public static final String DAYKEY="userRealNameIdentity:{userId}:day";
        public static final String DAYCOUNTKEY="userRealNameIdentity:{userId}:day_count";
    }

    /**
     * 用户收听历史相关的key
     */
    public static final class UserHistoryRedisKey {
        public static final String UHR_MOBILE_ID = "userHistory:{mobileId}";
    }

    /**
     * 榜单--订阅
     */
    public static final class RankSubscribe {
        public static final String RANK_SUBSCRIBE = "rank_subscribe:{userId}";
    }
    /**
     *统计相关
     */
    public static final class Statistics{
        public static final String STATISTICS_NEW_CONTENT_SUM="statistics_new_content_sum:{field}:{current_page}:{number}";

        public static final String STATISTICS_ALBUM="statistics_album:{type}:{param}:{currentPage}:{page_size}:{duration}:{start_time}:{end_time}:{album_id}:{category_id}:{copyrightStatus}";
        public static final String STATISTICS_ALBUM_TABLE="statistics_album_table:{type}:{param}:{currentPage}:{page_size}:{duration}:{start_time}:{end_time}:{album_id}:{category_id}:{copyrightStatus}";

        public static final String STATISTICS_ALBUM_REAL_CHART="statistics_album_real_chart:{type}:{param}:{currentPage}:{page_size}:{duration}:{start_time}:{end_time}:{album_id}:{copyrightStatus}:{source}:{categoryId}";
        public static final String STATISTICS_ALBUM_REAL_TABLE="statistics_album_real_table:{type}:{param}:{currentPage}:{page_size}:{duration}:{start_time}:{end_time}:{album_id}:{copyrightStatus}:{source}:{categoryId}";

        public static final String STATISTICS_CONTENT="statistics_content:{type}:{param}:{currentPage}:{page_size}:{duration}:{start_time}:{end_time}:{content_id}:{hour_id}:{copyrightStatus}";
        public static final String STATISTICS_CONTENT_TABLE="statistics_content_table:{type}:{param}:{currentPage}:{page_size}:{duration}:{start_time}:{end_time}:{content_id}:{hour_id}:{copyrightStatus}";

        public static final String STATISTICS_CATEGORY="statistics_content:{type}:{param}:{currentPage}:{page_size}:{duration}:{start_time}:{end_time}:{category_id}:{grade}";
        public static final String STATISTICS_CATEGORY_TABLE="statistics_content_table:{type}:{param}:{currentPage}:{page_size}:{duration}:{start_time}:{end_time}:{category_id}:{grade}";

        public static final String STATISTICS_CONTENT_REAL_CHART="statistics_content_real_chart:{type}:{param}:{currentPage}:{page_size}:{duration}:{start_time}:{end_time}:{content_id}:{hour_id}:{copyrightStatus}:{source}";
        public static final String STATISTICS_CONTENT_REAL_TABLE="statistics_content_real_table:{type}:{param}:{currentPage}:{page_size}:{duration}:{start_time}:{end_time}:{content_id}:{hour_id}:{copyrightStatus}:{source}";

        public static final String STATISTICS_CATEGORY_REAL_CHART="statistics_content_real_chart:{type}:{param}:{currentPage}:{page_size}:{duration}:{start_time}:{end_time}:{content_id}:{grade}";
        public static final String STATISTICS_CATEGORY_REAL_TABLE="statistics_content_real_table:{type}:{param}:{currentPage}:{page_size}:{duration}:{start_time}:{end_time}:{content_id}:{grade}";

        public static final String STATISTICS_PODCAST="statistics_podcast:{type}:{param}:{current_page}:{page_size}:{duration}:{start_time}:{end_time}:{podcaster_id}";
        public static final String STATISTICS_PODCAST_TABLE="statistics_podcast_table:{type}:{param}:{current_page}:{page_size}:{duration}:{start_time}:{end_time}:{podcaster_id}";

        public static final String STATISTICS_PODCAST_REAL_CHART="statistics_podcast_real_chart:{type}:{param}:{source}:{current_page}:{page_size}:{duration}:{start_time}:{end_time}:{podcaster_id}";
        public static final String STATISTICS_PODCAST_REAL_TABLE="statistics_podcast_real_table:{type}:{param}:{source}:{current_page}:{page_size}:{duration}:{start_time}:{end_time}:{podcaster_id}";
    }
    /**
     * 判断获取到的url是否有效
     */
    public static final class RenameAlbumKey {
        public static final String CHECK_CURRENT_PAGE = "checkCurrentPage";
    }

    public static final class DistributedLock{

        /**
         * 播放
         */
        public static final String PLAY_CONTENT = "distributed:lock:play:content";

        public static final String PLAY_ALBUM = "distributed:lock:play:album";

        public static final String DOWNLOAD_CONTENT = "distributed:lock:download:content";

        public static final String DOWNLOAD_ALBUM = "distributed:lock:download:album";

        public static final String COLLECT_CONTENT="distributed:lock:collect:content";

        public static final String COLLECT_ALBUM="distributed:lock:collect:album";

        public static final String COMMENT_CONTENT="distributed:lock:comment:content";

    }

}
