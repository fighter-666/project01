package com.example.myapplication.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GetHotListData implements Serializable {

    /**
     * title 标题
     */
    public String title = "";
    /**
     * hotList 热门推荐列表
     */
    public List<HotListBean> hotList = new ArrayList<HotListBean>();

    public static class HotListBean implements Serializable, Comparable<HotListBean>{
        /**
         * advertisingList 产品卡片列表
         */
        public List<AdvertisingListBean> advertisingList = new ArrayList<AdvertisingListBean>();
        /**
         * id 卡片id
         */
        public String id = "";
        /**
         * imageBean 纯图卡片
         */
        public ImageBean imageBean = new ImageBean();
        /**
         * isShowOnHomepage 是否显示在首页（1：是 0：否）
         */
        public String isShowOnHomepage = "";
        /**
         * isTop 是否置顶（1:是 0：否）
         */
        public String isTop = "";
        /**
         * order 排序
         */
        public String order = "";
        /**
         * topTitle 顶部标题
         */
        public TopTitleBean topTitle = new TopTitleBean();
        /**
         * type 类型（1：星播客卡片 2：产品卡片 3：纯图卡片）
         */
        public String type = "";
        /**
         * videoBean 直播卡片
         */
        public VideoBean videoBean = new VideoBean();

        @Override
        public int compareTo(HotListBean o) {
            return Integer.valueOf(this.order).compareTo(Integer.valueOf(o.order));

        }

        public static class ImageBean implements Serializable {
            /**
             * iconUrl 图片地址
             */
            public String iconUrl = "";
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
             * title 标题
             */
            public String title = "";
        }

        public static class TopTitleBean implements Serializable {
            /**
             * link 跳转链接
             */
            public String link = "";
            /**
             * linkType 跳转类型
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
             * title 标题
             */
            public String title = "";
        }

        public static class AdvertisingListBean implements Serializable {
            /**
             * iconUrl 图片地址
             */
            public String iconUrl = "";
            /**
             * link 跳转链接
             */
            public String link = "";
            /**
             * linkType 跳转类型
             */
            public String linkType = "";
            /**
             * priceTitle 价格标题
             */
            public String priceTitle = "";
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
             * subTitle 副标题
             */
            public String subTitle = "";
            /**
             * title 标题
             */
            public String title = "";
        }

        /**
         * 是否显示在首页（1：是 0：否）
         */
        public static class IS_SHOW_ON_HOMEPAGE {
            public static final String NO = "0";
            public static final String YES = "1";
        }

        /**
         * 是否置顶（1:是 0：否）
         */
        public static class IS_TOP {
            public static final String NO = "0";
            public static final String YES = "1";
        }

        /**
         * 类型（1：星播客卡片 2：产品卡片 3：纯图卡片）
         */
        public static class HOT_LIST_ITEM_TYPE {
            public static final String LIVE = "1";
            public static final String PRODUCT = "2";
            public static final String IMAGE = "3";
        }
    }
}
