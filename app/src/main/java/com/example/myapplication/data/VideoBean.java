package com.example.myapplication.data;

import java.io.Serializable;

/**
 * 说明：直播流
 *
 * @作者 luohao
 * @创建时间 2022/11/15 10:12
 * @版本
 * @------修改记录-------
 * @修改人
 * @版本
 * @修改内容
 */
public class VideoBean implements Serializable {
    /**
     * title 标题
     */
    public String title = "";
    /**
     * imageRatio 图片区域默认宽高比
     */
    public String imageRatio = "";
    /**
     * imageUrl 图片url
     */
    public String imageUrl = "";
    /**
     * link 跳转链接
     */
    public String link = "";
    /**
     * linkType 跳转链接类型
     */
    public String linkType = "";
    /**
     * provinceCode 省份、集团编码
     */
    public String provinceCode = "";
    /**
     * recommender 插码推荐码
     */
    public String recommender = "";
    /**
     * sceneId 大数据场景ID
     */
    public String sceneId = "";
    /**
     * playType 播放类型（0.不直接播放、1.直接播放）
     */
    public String playType = "";
    /**
     * startTime 直播开始时间（时间格式：yyyyMMddHHmmss）
     */
    public String startTime = "";
    /**
     * endTime 直播结束时间（时间格式：yyyyMMddHHmmss）
     */
    public String endTime = "";
    /**
     * topImage 配置标签
     */
    public String topImage = "";
    /**
     * topImageBeforeLive 直播开始前标签（倒计时前标签）
     */
    public String topImageBeforeLive = "";
    /**
     * topImageLiving 直播中标签（倒计时中标签）
     */
    public String topImageLiving = "";
    /**
     * type 配置类型（1.图片、2.短视频、3.直播）
     */
    public String type = "";
    /**
     * videoCommerceId 直播间id
     */
    public String videoCommerceId = "";
    /**
     * videoDuration 短视频时长(单位秒)
     */
    public String videoDuration = "";
    /**
     * videoStreamUrl 视频流地址
     */
    public String videoStreamUrl = "";
    /**
     * videoStreamUrlType 直播推流地址类型(1:m3u8 2:flv 3:rtmp)
     */
    public String videoStreamUrlType = "";

    /**
     * 播放类型（0.不直接播放、1.直接播放）
     */
    public static class VIDEO_PLAY_TYPE {
        public static final String NO_AUTO_PALY = "0";
        public static final String AUTO_PALY = "1";
    }

    /**
     * 配置类型（1.图片、2.短视频、3.直播）
     */
    public static class VIDEO_TYPE {
        public static final String IMAGE = "1";
        public static final String VIDEO = "2";
        public static final String LIVE = "3";
    }

    /**
     * 直播推流地址类型 1:m3u8 2:flv 3:rtmp
     */
    public static class VIDEO_STREAM_URL_TYPE {
        public static final String M3U8 = "1";
        public static final String FLV = "2";
        public static final String RTMP = "3";
    }
}
