package com.example.myapplication.feed.data


class FeedTabData {
    internal val tabList: List<Tab> = ArrayList()

    private val jumpGuideBar: JumpGuideBar? = null

    private val isShowSubTitle: String? = null // Getters and Setters
}

internal class Tab {
    val tabName: String? = null

    val tabIcon: String? = null

    val redFlag: String? = null

     val timestamp: String? = null

     val tabType: String? = null

     val order: String? = null

     val link: String? = null

     val linkType: String? = null

     val type: String? = null

     val isDefault: String? = null

     val subTitle: String? = null // Getters and Setters
}

internal class JumpGuideBar {
    private val title: String? = null

    private val iconUrl: String? = null

    private val provinceCode: String? = null

    private val recommender: String? = null

    private val sceneId: String? = null // Getters and Setters
}