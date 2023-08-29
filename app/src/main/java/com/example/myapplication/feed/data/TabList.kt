package com.example.myapplication.feed.data

import com.google.gson.Gson

data class TabList(val tabList: List<FeedTabData>)

fun main() {
    val json = """
        {
            "tabList": [
                {
                    "tabName": "推荐",
                    "tabIcon": "",
                    "redFlag": "",
                    "timestamp": "20230427152925",
                    "tabType": "1",
                    "order": "1",
                    "link": "",
                    "linkType": "",
                    "type": "1",
                    "isDefault": "1",
                    "subTitle": "大家在看"
                },
                {
                    "tabName": "百度",
                    "tabIcon": "",
                    "redFlag": "",
                    "timestamp": "20230427152943",
                    "tabType": "1",
                    "order": "2",
                    "link": "https://www.baidu.com/",
                    "linkType": "",
                    "type": "2",
                    "isDefault": "",
                    "subTitle": "最新动态"
                },
                {
                    "tabName": "商城",
                    "tabIcon": "",
                    "redFlag": "",
                    "timestamp": "20230427153051",
                    "tabType": "1",
                    "order": "4",
                    "link": "",
                    "linkType": "",
                    "type": "1",
                    "isDefault": "0",
                    "subTitle": "3C数码"
                },
                {
                    "tabName": "视频",
                    "tabIcon": "",
                    "redFlag": "0",
                    "timestamp": "20230427153129",
                    "tabType": "1",
                    "order": "5",
                    "link": "",
                    "linkType": "",
                    "type": "1",
                    "isDefault": "0",
                    "subTitle": "高清影视"
                },
                {
                    "tabName": "彩铃",
                    "tabIcon": "",
                    "redFlag": "0",
                    "timestamp": "20230510142539",
                    "tabType": "1",
                    "order": "6",
                    "link": "",
                    "linkType": "",
                    "type": "1",
                    "isDefault": "0",
                    "subTitle": "视频彩铃"
                },
                {
                    "tabName": "直播",
                    "tabIcon": "",
                    "redFlag": "0",
                    "timestamp": "20230711142241",
                    "tabType": "1",
                    "order": "8",
                    "link": "",
                    "linkType": "",
                    "type": "1",
                    "isDefault": "0",
                    "subTitle": "对不起，作为一个基于文本的AI模型，我无法访问或与外部URL进行交互。然而，如果您有任何具体问题或需要关于该URL相关内容的帮助，请告诉我，我会尽力帮助您。"
                }
            ]
        }
    """.trimIndent()

    val gson = Gson()
    val tabList = gson.fromJson(json, TabList::class.java)

    // 访问解析后的数据
   /* for (tab in tabList.tabList) {
        println("标签名称: ${tab.tabName}")
        println("标签图标: ${tab.tabIcon}")
        println("红旗: ${tab.redFlag}")
        println("时间戳: ${tab.timestamp}")
        println("标签类型: ${tab.tabType}")
        println("顺序: ${tab.order}")
        println("链接: ${tab.link}")
        println("链接类型: ${tab.linkType}")
        println("类型: ${tab.type}")
        println("是否默认: ${tab.isDefault}")
        println("子标题: ${tab.subTitle}")
        println()
    }*/
}