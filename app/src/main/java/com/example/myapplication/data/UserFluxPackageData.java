package com.example.myapplication.data;


import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.myapplication.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserFluxPackageData implements Serializable {

    /**
     * buttonInfo : 底部配置按钮 {"buttonImageUrl":"string","buttonLink":"string","buttonLinkType":"string","buttonTitle":"string"}
     * careButtonInfo : 适老版底部跳转按钮信息 9.1.0新增 {"careLink":"string","careLinkType":"string","careTitle":"string"}
     * mainProductOFFInfo : 主套餐信息实体 {"prodOFFNameLink":"string","prodOFFNameLinkType":"string","productOFFName":"string","shareLink":"string","shareLinkType":"string","shareTipDesc":"string","shareTitle":"string"}
     * productOFFRatable : 流量、短信、语音使用量集合 {"exceedingUsages":[{"excessNum":"string","excessNumTitle":"string","excessTypeTitle":"string","excessUnit":"string","link":"string","linkType":"string","title":"string"}],"link":"string","linkType":"string","ratableResourcePackages":[{"balanceAmount":"string","leftStructure":{"num":"string","redFlag":"string","title":"string","titleCornerMark":"string","unit":"string"},"link":"string","linkType":"string","productInfos":[{"balanceAmount":"string","infiniteTitle":"string","infiniteUnit":"string","infiniteValue":"string","isInfiniteAmount":"string","isInvalid":"string","leftHighlight":"string","leftIcon":"string","leftTitle":"string","link":"string","linkType":"string","outOfServiceTime":"string","progressBar":"string","ratableAmount":"string","rightCommon":"string","rightHighlight":"string","rightTitle":"string","title":"string","titleIcon":"string","usageAmount":"string"}],"ratableAmount":"string","rightStructure":{"num":"string","redFlag":"string","title":"string","titleCornerMark":"string","unit":"string"},"tips":"string","title":"string","usageAmount":"string"}]}
     * warnInfo : 提示信息 {"describe":"string","icon":"string","link":"string","linkType":"string","provinceCode":"string","recommender":"string","sceneId":"string","title":"string"}
     * queryFailInfo : 查询失败信息 9.1.0新增 （901001：弹窗类提示、901002：toast框类提示、901003：页面类提示、901004：倒计时提示）{"blueButtonInfo": {"link": "string","linkType": "string","title": "string"},"failureMessage": "string","iconUrl": "string","whiteButtonInfo": {"link": "string","linkType": "string","title": "string"}
     * tips : 温馨提示 string
     * voiceMessage : 9.5新增 语音播报信息 string
     */

    public ButtonInfoBean buttonInfo = new ButtonInfoBean();
    public CareButtonInfoBean careButtonInfo = new CareButtonInfoBean();
    public MainProductOFFInfoBean mainProductOFFInfo = new MainProductOFFInfoBean();
    public ProductOFFRatableBean productOFFRatable = new ProductOFFRatableBean();
    public WarnInfoBean warnInfo = new WarnInfoBean();
    public QueryFailInfoBean queryFailInfo = new QueryFailInfoBean();
    public String tips = "";
    public String voiceMessage = "";

    public static class MainProductOFFInfoBean {
        /**
         * prodOFFNameLink : string 主套餐名称链接
         * prodOFFNameLinkType : string 主套餐名称链接类型
         * productOFFName : string 主套餐名称
         * shareLink : string 共享链接
         * shareLinkType : string 共享链接类型
         * shareTipDesc : string 共享提示文案描述
         * shareTitle : string 共享文案标题
         */

        public String prodOFFNameLink = "";
        public String prodOFFNameLinkType = "";
        public String productOFFName = "";
        public String shareLink = "";
        public String shareLinkType = "";
        public String shareTipDesc = "";
        public String shareTitle = "";
    }

    public static class ButtonInfoBean implements Serializable {
        /**
         * buttonImageUrl : 按钮配图地址 string
         * buttonLink : 按钮链接 string
         * buttonLinkType : 按钮链接类型 string
         * buttonTitle : 按钮标题 string
         */

        public String buttonImageUrl = "";
        public String buttonLink = "";
        public String buttonLinkType = "";
        public String buttonTitle = "";
    }

    public static class CareButtonInfoBean implements Serializable {
        /**
         * careLink : 按钮链接 string
         * careLinkType : 按钮链接类型 string
         * careTitle : 按钮标题 string
         */

        public String careLink = "";
        public String careLinkType = "";
        public String careTitle = "";
    }

    public static class ProductOFFRatableBean implements Serializable {
        /**
         * exceedLink ：套外使用量列表提示跳转链接 9.1.0新增 string
         * exceedLinkType ：套外使用量列表提示跳转类型 9.1.0新增 string
         * exceedTitle ：套外使用量列表提示标题 9.1.0新增 string
         * exceedResourcePackages ：套餐外使用量集合 9.1.0新增 [{"balanceAmount":"string","leftStructure":{"num":"string","redFlag":"string","title":"string","titleCornerMark":"string","unit":"string"},"link":"string","linkType":"string","productInfos":[{"balanceAmount":"string","infiniteTitle":"string","infiniteUnit":"string","infiniteValue":"string","isInfiniteAmount":"string","isInvalid":"string","leftHighlight":"string","leftIcon":"string","leftTitle":"string","link":"string","linkType":"string","outOfServiceTime":"string","progressBar":"string","ratableAmount":"string","rightCommon":"string","rightHighlight":"string","rightTitle":"string","title":"string","titleIcon":"string","usageAmount":"string"}],"ratableAmount":"string","rightStructure":{"num":"string","redFlag":"string","title":"string","titleCornerMark":"string","unit":"string"},"tips":"string","title":"string","usageAmount":"string"}]
         * exceedingUsages : 套餐外使用量集合 9.0版本使用 [{"excessNum":"string","excessNumTitle":"string","excessTypeTitle":"string","excessUnit":"string","link":"string","linkType":"string","title":"string"}]
         * link : 套内使用量列表提示跳转链接 string
         * linkType : 套内使用量列表提示跳转类型 string
         * ratableResourcePackages : 套餐内使用量集合 [{"balanceAmount":"string","leftStructure":{"num":"string","redFlag":"string","title":"string","titleCornerMark":"string","unit":"string"},"link":"string","linkType":"string","productInfos":[{"balanceAmount":"string","infiniteTitle":"string","infiniteUnit":"string","infiniteValue":"string","isInfiniteAmount":"string","isInvalid":"string","leftHighlight":"string","leftIcon":"string","leftTitle":"string","link":"string","linkType":"string","outOfServiceTime":"string","progressBar":"string","ratableAmount":"string","rightCommon":"string","rightHighlight":"string","rightTitle":"string","title":"string","titleIcon":"string","usageAmount":"string"}],"ratableAmount":"string","rightStructure":{"num":"string","redFlag":"string","title":"string","titleCornerMark":"string","unit":"string"},"tips":"string","title":"string","usageAmount":"string"}]
         */

        public String exceedLink = "";
        public String exceedLinkType = "";
        public String exceedTitle = "";
        public String link = "";
        public String linkType = "";
        public List<ExceedingUsagesBean> exceedingUsages = new ArrayList<ExceedingUsagesBean>();
        public List<RatableResourcePackagesBean> ratableResourcePackages = new ArrayList<RatableResourcePackagesBean>();
        public List<RatableResourcePackagesBean> exceedResourcePackages = new ArrayList<RatableResourcePackagesBean>();

        public static class ExceedingUsagesBean implements Serializable {
            /**
             * excessNum : 超出数值 string
             * excessNumTitle : 超出数值标题 string
             * excessTypeTitle : 超出量类型标题 string
             * excessUnit : 超出数值单位 string
             * link : 右上角超出提示跳转链接 string
             * linkType : 右上角超出提示跳转类型 string
             * title : 右上角超出提示标题 string
             */

            public String excessNum = "";
            public String excessNumTitle = "";
            public String excessTypeTitle = "";
            public String excessUnit = "";
            public String link = "";
            public String linkType = "";
            public String title = "";
        }

        public static class RatableResourcePackagesBean extends AbstractExpandableItem<RatableResourcePackagesBean.ProductInfosBean> implements MultiItemEntity {
            /**
             * leftStructure : 左边结构 {"num":"string","redFlag":"string","title":"string","titleCornerMark":"string","unit":"string"}
             * link : 套内使用量类型标题提示跳转链接 string
             * linkType : 套内使用量类型标题提示跳转类型 string
             * productInfos : 套餐详情 [{"balanceAmount":"string","infiniteTitle":"string","infiniteUnit":"string","infiniteValue":"string","isInfiniteAmount":"string","isInvalid":"string","leftHighlight":"string","leftIcon":"string","leftTitle":"string","link":"string","linkType":"string","outOfServiceTime":"string","progressBar":"string","ratableAmount":"string","rightCommon":"string","rightHighlight":"string","rightTitle":"string","title":"string","titleIcon":"string","usageAmount":"string"}]
             * rightStructure : 右边结构 {"num":"string","redFlag":"string","title":"string","titleCornerMark":"string","unit":"string"}
             * title : 套内使用量类型标题 string
             */

            public StructureBean leftStructure = new StructureBean();
            public String link = "";
            public String linkType = "";
            public StructureBean rightStructure = new StructureBean();
            public String title = "";
            public boolean isChecked = false;
            public List<ProductInfosBean> productInfos = new ArrayList<ProductInfosBean>();

            @Override
            public int getLevel() {
                return 0;
            }

            @Override
            public int getItemType() {
                return R.layout.item_normal_meal;
            }

            public static class StructureBean {
                /**
                 * num : 数值 string
                 * redFlag : 是否变红(0:否、1:是) string
                 * title : 标题 string
                 * titleCornerMark : 标题角标 string
                 * unit : 数值单位 string
                 */

                public String num = "";
                public String redFlag = "";
                public String title = "";
                public String titleCornerMark = "";
                public String unit = "";
            }

            public static class ProductInfosBean implements MultiItemEntity{
                /**
                 * infiniteTitle : 不限量样式标题 string
                 * infiniteUnit : 不限量样式单位 string
                 * infiniteValue : 不限量样式数值 string
                 * isInfiniteAmount : 是否不限量样式(0:否、1:是) string
                 * isInvalid : 是否失效(0:否、1:是) string
                 * leftHighlight : 左下角标题高亮部分 string
                 * leftIcon : 左下角图标 string
                 * leftTitle : 左下角标题 string
                 * link : 标题提示跳转链接 string
                 * linkType : 标题提示跳转类型 string
                 * outOfServiceTime : 失效时间 string
                 * progressBar : 进度条百分比 string
                 * rightCommon : 右下角标题结尾非高亮部分 string
                 * rightHighlight : 右下角标题高亮部分 string
                 * rightTitle : 右下角标题 string
                 * title : 标题 string
                 * titleIcon : 标题右侧图标 string
                 */

                public String infiniteTitle = "";
                public String infiniteUnit = "";
                public String infiniteValue = "";
                public String isInfiniteAmount = "";
                public String isInvalid = "";
                public String leftHighlight = "";
                public String leftIcon = "";
                public String leftTitle = "";
                public String link = "";
                public String linkType = "";
                public String outOfServiceTime = "";
                public String progressBar = "";
                public String rightCommon = "";
                public String rightHighlight = "";
                public String rightTitle = "";
                public String title = "";
                public String titleIcon = "";
                public boolean isChecked = false;

                @Override
                public int getItemType() {
                    return R.layout.item_normal_meal_son;
                }
            }
        }
    }

    public static class WarnInfoBean implements Serializable {
        /**
         * describe : 提示描述 string
         * icon : 提示图片 string
         * link : 跳转链接 string
         * linkType : 跳转链接类型 string
         * provinceCode : 省编码 string
         * recommender : 插码推荐码 string
         * sceneId : 场景id string
         * title : 提示标题 string
         */

        public String describe = "";
        public String icon = "";
        public String link = "";
        public String linkType = "";
        public String provinceCode = "";
        public String recommender = "";
        public String sceneId = "";
        public String title = "";
    }

    public static class QueryFailInfoBean implements Serializable {
        /**
         * blueButtonInfo : 蓝底按钮信息
         * failureMessage : 失败提示语 string
         * iconUrl : 图标 string
         * whiteButtonInfo : 白底按钮信息
         */

        public ButtonInfo blueButtonInfo = new ButtonInfo();
        public String failureMessage = "";
        public String iconUrl = "";
        public ButtonInfo whiteButtonInfo = new ButtonInfo();

        public static class ButtonInfo implements Serializable {
            /**
             * link : 按钮跳转链接 string
             * linkType : 按钮跳转类型 string
             * title : 按钮标题 string
             */

            public String link = "";
            public String linkType = "";
            public String title = "";
        }
    }
}
