package com.example.myapplication.components

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.RechargePageBinding
import com.example.myapplication.recharge.ScrollImageView
import com.example.myapplication.recharge.ScrollTextView
import com.example.myapplication.recharge.ScrrollTextViewBackground
import com.example.recharge.Cards
import com.example.recharge.FirstAdapter
import com.example.recharge.FourthAdapter
import com.example.recharge.Piggy
import com.example.recharge.Second
import com.example.recharge.SecondAdapter
import com.gyf.immersionbar.ImmersionBar


class RechargePage : ComponentActivity() {

    private lateinit var binding: RechargePageBinding
    private lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = RechargePageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //沉浸式
        ImmersionBar.with(this)
            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .titleBar(binding.tvTv1)    //解决状态栏和布局重叠问题，任选其一
            .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
            .init();



        //消息条
        //右边textview跑马灯
        val marqueeText2: ScrrollTextViewBackground = binding.tv2Tv3

        val demographicsList2: MutableList<String> = ArrayList()

        demographicsList2.add("股票")
        demographicsList2.add("药业")
        demographicsList2.add("上市")


        marqueeText2.setList(demographicsList2)
        marqueeText2.startScroll()

        //imageview跑马灯
        val marqueeText3: ScrollImageView = binding.im1Im

        val demographicsList3: MutableList<Int> = ArrayList()

        demographicsList3.add(R.drawable.card3)
        demographicsList3.add(R.drawable.beans)
        demographicsList3.add(R.drawable.card2)


        marqueeText3.setList(demographicsList3)
        marqueeText3.startScroll()

        //中间textview跑马灯
        val marqueeText: ScrollTextView = binding.tv2Tv2

        val demographicsList: MutableList<String> = ArrayList()

        demographicsList.add("今日测试股票 上市")
        demographicsList.add("今日科伦药业 中国人保 可申购今日科伦药业 中国人保 可申购今日科伦药业 中国人保 可申购今日科伦药业 中国人保 可申购今日科伦药业 中国人保 可申购今日科伦药业 中国人保 可申购")
        demographicsList.add("今日中国平安 上市")

        marqueeText.setList(demographicsList)
        marqueeText.startScroll()






        val piggies = mutableListOf<Piggy>()
        piggies.add(Piggy(R.drawable.image1, "充流量", "流量告急速订购"))
        piggies.add(Piggy(R.drawable.image2, "开通自动充", "专治忘充值"))
        piggies.add(Piggy(R.drawable.image3, "电子发票", "批量开票不排队"))


        //创建适配器
        val myAdapter = FirstAdapter(R.layout.first, piggies)

        //设置布局管理器
        binding.recyclerView.setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false))

        //给RecycleView设置适配器
       binding.recyclerView.setAdapter(myAdapter)

        val piggiesCopy = mutableListOf<Piggy>()
        piggiesCopy.add(Piggy(R.drawable.image1, "充流量", "流量告急速订购"))
        piggiesCopy.add(Piggy(R.drawable.image2, "开通自动充", "专治忘充值"))
        piggiesCopy.add(Piggy(R.drawable.image3, "电子发票", "批量开票不排队"))
        piggiesCopy.add(Piggy(R.drawable.image4, "充值记录", "可查全网记录"))
        piggiesCopy.add(Piggy(R.drawable.image4, "充值记录", "可查全网记录"))
        piggiesCopy.add(Piggy(R.drawable.image4, "充值记录", "可查全网记录"))
        piggiesCopy.add(Piggy(R.drawable.image4, "充值记录", "可查全网记录"))


        //创建适配器
        val myAdapterCopy = FirstAdapter(R.layout.first, piggiesCopy)

        //设置布局管理器
        binding.recyclerViewCopy.setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false))

        //给RecycleView设置适配器
        binding.recyclerViewCopy.setAdapter(myAdapterCopy)


        //第二个
        val piggies2 = mutableListOf<Second>()
        piggies2.add(
            Second(R.drawable.tengxun, "腾讯视频会员\n" +
                "周卡", "1000金豆",0)
        )
        piggies2.add(
            Second(R.drawable.youku, "优酷视频会员\n" +
                "周卡", "1500金豆",R.drawable.shape_rectangle16)
        )
        piggies2.add(
            Second(R.drawable.youku, "腾讯视频会员\n" +
                "周卡", "1000金豆",0)
        )
        piggies2.add(
            Second(R.drawable.youku, "腾讯视频会员\n" +
                "周卡", "1000金豆",0)
        )
        piggies2.add(
            Second(R.drawable.tengxun, "腾讯视频会员\n" +
                "周卡", "1000金豆",0)
        )
        piggies2.add(
            Second(R.drawable.tengxun, "腾讯视频会员\n" +
                "周卡", "1000金豆",0)
        )

        //创建适配器
        val secondAdapter = SecondAdapter(R.layout.second, piggies2)

        //设置布局管理器
        binding.recyclerView2.setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false))

        //给RecycleView设置适配器
        binding.recyclerView2.setAdapter(secondAdapter)


        /*//第三个
        val piggies3 = mutableListOf<MultipleItem>()
        piggies3.add(MultipleItem(1,))
        piggies3.add(MultipleItem(2,))
        piggies3.add(MultipleItem(3,))

        //创建适配器
        val thirdAdapter = MultipleItemQuickAdapter( piggies3)

        //设置布局管理器
       binding.recyclerView3.setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false))

        //给RecycleView设置适配器
       binding.recyclerView3.setAdapter(thirdAdapter)*/


        //第三个
        val piggies4 = mutableListOf<Cards>()
        piggies4.add(Cards(0,0,R.drawable.falls1, "电信关爱版-为老年人架桥", "0","0","0","0","0", 0, 0))
        piggies4.add(Cards(R.drawable.shape_rectangle18,R.drawable.shape_rectangle18,R.drawable.falls8,  "加装【副卡】，一份套餐全家用 ", "赠新人礼包","赠美团神券","￥","10","/月", 0, 0))
        piggies4.add(Cards(R.drawable.shape_rectangle18,R.drawable.shape_rectangle18,R.drawable.fall,  "iPhone12 128GB 红色 双卡双待", "免运费","送配件","0","0","0", 0, 0))
        piggies4.add(Cards(R.drawable.shape_rectangle18,0,R.drawable.falls4,  "15GB定向流量+腾讯视频月会员卡", "0","0","0","0","0", 0, 0))
        //piggies4.add(Cards(R.drawable.falls3, "iPhone12 128GB 红色 双卡双待", "免运费","0","0","0","0", 0, 0))
        //piggies4.add(Cards(R.drawable.falls4, "15GB定向流量+腾讯视频月会员卡", "可查全网记录","0","0","0","0", 0, 0))

        //创建适配器
        val fourthAdapter = FourthAdapter(R.layout.fourth, piggies4)

        //设置布局管理器
        binding.recyclerView4.setLayoutManager(StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL))

        //给RecycleView设置适配器
        binding.recyclerView4.setAdapter(fourthAdapter)
    }


}

