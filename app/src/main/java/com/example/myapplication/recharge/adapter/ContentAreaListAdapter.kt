package com.example.myapplication.recharge.adapter

import android.graphics.Color
import android.os.CountDownTimer
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication.R
import com.example.myapplication.databinding.AdapterRechargeContentAreaListBinding
import com.example.myapplication.recharge.data.GetFeedListData
import com.example.myapplication.util.DensityUtils
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ContentAreaListAdapter(
    @LayoutRes layoutResId: Int,
    data: MutableList<GetFeedListData.FeedListBean.ContentAreaListBean>,
) :
    BaseQuickAdapter<GetFeedListData.FeedListBean.ContentAreaListBean, BaseViewHolder>(
        layoutResId,
        data
    ) {
    private var tvPriceIntegerWidth: Int = 0
    private var tvPriceDecimalWidth: Int = 0
    private var tvOriginalPriceWidth: Int = 0
    private var tvIsShowPriceUnitWidth: Int = 0

    override fun convert(
        holder: BaseViewHolder,
        item: GetFeedListData.FeedListBean.ContentAreaListBean,
    ) {
        val binding = AdapterRechargeContentAreaListBinding.bind(holder.itemView)

        when (item.type) {
            "1" -> {
                //mainTitle : 主标题
                if (item.mainTitle.type == "1") {
                    binding.tvMainTitleTitle.maxLines = 1
                } else{
                    binding.tvMainTitleTitle.maxLines = 2
                }
                if (item.mainTitle.color != ""){
                    binding.tvMainTitleTitle.setTextColor(
                        Color.parseColor(item.mainTitle.color)
                    )
                }
                binding.tvMainTitleTitle.ellipsize =
                    TextUtils.TruncateAt.END
                binding.tvMainTitleTitle.text = item.mainTitle.title
                binding.tvMainTitleTitle.visibility = View.VISIBLE
            }

            //2：随销条、
            "2" -> {
                //创建适配器
                val myAdapter = SaleTipListAdapter(
                    R.layout.adapter_recharge_contentarealist_saletiplist,
                    item.saleTipList
                )

                //设置布局管理器和给 recyclerView设置适配器
                binding.rvSaleTipList.apply {
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    adapter = myAdapter
                }
                //saleTipList : 随销条
            }

               //3：价格
               "3" -> {
                   //price : 价格
                   if (item.price != null) {
                       //售价字体颜色通过priceColor控制，默认颜色为#ea5858
                       binding.tvPriceInteger.text = item.price.priceInteger
                       if (item.price.priceColor != "") {
                           // 获取颜色值
                           val color = item.price.priceColor
                           try {
                               binding.tvPriceInteger.setTextColor(Color.parseColor(color))
                           }catch (e:Exception){
                               e.printStackTrace()
                               binding.tvPriceInteger.setTextColor(context.resources.getColor(R.color.ea5858))
                           }
                           try {
                               binding.tvPriceDecimal.setTextColor(Color.parseColor(color))
                           }catch (e:Exception){
                               e.printStackTrace()
                               binding.tvPriceDecimal.setTextColor(context.resources.getColor(R.color.ea5858))
                           }
                           try {
                               binding.tvIsShowPriceUnit.setTextColor(Color.parseColor(color))
                           }catch (e:Exception){
                               e.printStackTrace()
                               binding.tvIsShowPriceUnit.setTextColor(context.resources.getColor(R.color.ea5858))
                           }

                       }

                       //售价字体颜色通过priceColor控制，默认颜色为#ea5858
                       binding.tvPriceDecimal.text = item.price.priceDecimal

                       //原价originalPrice字段控制（没有不显示），字体颜色通过originalPriceColor控制，默认为#999999
                       if (item.price.originalPriceColor != "") {
                           val color2 = item.price.originalPriceColor
                           try {
                               binding.tvOriginalPrice.setTextColor(Color.parseColor(color2))
                           }catch (e:Exception){
                               e.printStackTrace()
                               binding.tvOriginalPrice.setTextColor(context.resources.getColor(R.color.black_999999))
                           }
                       }

                       binding.tvPriceInteger.visibility = View.VISIBLE
                       binding.tvPriceDecimal.visibility = View.VISIBLE
                       binding.tvOriginalPrice.visibility = View.VISIBLE
                       // "售价是否显示人民币符号：0：否1：是",
                       if (item.price.isShowPriceUnit == "1") {
                           binding.tvIsShowPriceUnit.visibility = View.VISIBLE
                       }

                       //"isOriginalPriceLine": "原价是否划横线：0：否1：是"
                       if (item.price.isOriginalPriceLine == "1") {
                           //为文字设置删除线
                           val spannableString4 =
                               SpannableString(item.price.originalPrice)
                           val strikethroughSpan = StrikethroughSpan()
                           spannableString4.setSpan(
                               strikethroughSpan,
                               0,
                               spannableString4.length,
                               Spanned.SPAN_INCLUSIVE_EXCLUSIVE
                           )
                           binding.tvOriginalPrice.setText(spannableString4)
                       } else {
                           binding.tvOriginalPrice.text = item.price.originalPrice
                       }

                       binding.tvPriceInteger.post {
                           run() {
                               binding.tvPriceDecimal.post {
                                   run() {
                                       binding.tvOriginalPrice.post {
                                           run() {
                                               binding.tvIsShowPriceUnit.post {
                                                   run() {
                                                       tvIsShowPriceUnitWidth =
                                                           binding.tvIsShowPriceUnit.width
                                                       tvOriginalPriceWidth =
                                                           binding.tvOriginalPrice.width;
                                                       tvPriceDecimalWidth =
                                                           binding.tvPriceDecimal.width;
                                                       tvPriceIntegerWidth =
                                                           binding.tvPriceInteger.width;
                                                       //当售价和原价过长出现交叉时仅展示原价
                                                       if (item.price.priceInteger != "" && item.price.originalPrice != "") {
                                                           val totalWidth =
                                                               tvIsShowPriceUnitWidth + tvOriginalPriceWidth + tvPriceDecimalWidth + tvPriceIntegerWidth + DensityUtils.dpToPx(
                                                                   context,
                                                                   20f
                                                               )
                                                           if (totalWidth > recyclerView.measuredWidth) {
                                                               binding.tvOriginalPrice.visibility =
                                                                   View.GONE

                                                           }
                                                       } else {
                                                           binding.tvOriginalPrice.maxLines = 1
                                                           binding.tvOriginalPrice.maxWidth =
                                                               recyclerView.measuredWidth - DensityUtils.dpToPx(
                                                                   context,
                                                                   20f
                                                               )
                                                           binding.tvOriginalPrice.isSingleLine = true
                                                           binding.tvOriginalPrice.ellipsize =
                                                               TextUtils.TruncateAt.END
                                                       }


                                                       //当仅有原价或售价且超过一行宽度时右侧…展示
                                                       val totalWidth2 =
                                                           tvIsShowPriceUnitWidth + tvPriceDecimalWidth + tvPriceIntegerWidth + DensityUtils.dpToPx(
                                                               context,
                                                               20f
                                                           )
                                                       if (totalWidth2 >= recyclerView.measuredWidth) {
                                                           binding.tvPriceInteger.maxLines = 1
                                                           binding.tvPriceInteger.maxWidth =
                                                               recyclerView.measuredWidth - tvIsShowPriceUnitWidth - DensityUtils.dpToPx(
                                                                   context,
                                                                   20f
                                                               )
                                                           binding.tvPriceInteger.isSingleLine = true
                                                           binding.tvPriceInteger.ellipsize =
                                                               TextUtils.TruncateAt.END
                                                           binding.tvPriceDecimal.visibility =
                                                               View.GONE
                                                           binding.tvOriginalPrice.visibility =
                                                               View.GONE
                                                       }
                                                   }
                                               }
                                           }
                                       }
                                   }
                               }
                           }
                       }
                   }
               }

               //4：位置
            "4" -> {
                  binding.tvLocationTitle.text = item.location.title
                  binding.ivLocationIcon.visibility = View.VISIBLE
                  binding.tvLocationTitle.visibility = View.VISIBLE
                  binding.clLocation.visibility = View.VISIBLE
              }

              //5：倒计时
              "5" -> {
                  // 获取倒计时数据结构
                  val countDownBean = item.countDown // 假设从接口获取到倒计时数据结构

                  // 判断是显示距开始还是距结束
                  val isCountingDownToStart =
                      shouldDisplayCountdownToStart(countDownBean)

                  // 计算距离开始或结束的剩余时间
                  val remainingTimeInMillis =
                      getRemainingTimeInMillis(countDownBean, isCountingDownToStart)

                 // 显示倒计时信息
                  binding.tvCountDownBackground.visibility = View.VISIBLE
                  binding.tvCountDown.visibility = View.VISIBLE
                  //创建了一个CountDownTimer对象，并设置了倒计时的逻辑
                  val countDownTimer =
                      object : CountDownTimer(remainingTimeInMillis, 1000) {
                          //实现onTick方法：覆盖CountDownTimer类的onTick方法。在每个时间间隔（这里是1000毫秒）内，该方法会被调用一次
                          override fun onTick(millisUntilFinished: Long) {
                              //更新倒计时文本
                              var countdownText = formatCountdownText(
                                  millisUntilFinished,
                                  isCountingDownToStart
                              )
                              if (isCountingDownToStart) {
                                  countdownText = "距开始  $countdownText"
                                  binding.tvCountDown.setBackgroundResource(R.drawable.shape_recharge_count_down_start)
                                  binding.tvCountDownBackground.setBackgroundResource(
                                      R.drawable.shape_recharge_count_down_background_start
                                  )
                                  val colorSpan =
                                      ForegroundColorSpan(Color.parseColor("#f5a937"))
                                  //设置文字的时间颜色为橘黄色
                                  val spannableString = SpannableString(countdownText)
                                  spannableString.setSpan(
                                      colorSpan,
                                      3,
                                      spannableString.length,
                                      Spanned.SPAN_INCLUSIVE_EXCLUSIVE
                                  )
                                  //设置文字的前景色为白色色
                                  val colorSpan2 =
                                      ForegroundColorSpan(Color.parseColor("#ffffff"))
                                  spannableString.setSpan(
                                      colorSpan2,
                                      0,
                                      3,
                                      Spanned.SPAN_INCLUSIVE_EXCLUSIVE
                                  )
                                  binding.tvCountDown.setText(spannableString)
                              } else {
                                  countdownText = "距结束  $countdownText"
                                  binding.tvCountDown.setBackgroundResource(R.drawable.shape_recharge_count_down)
                                  //设置文字的前景色为白色
                                  val spannableString = SpannableString(countdownText)
                                  val colorSpan =
                                      ForegroundColorSpan(Color.parseColor("#ffffff"))
                                  spannableString.setSpan(
                                      colorSpan,
                                      0,
                                      3,
                                      Spanned.SPAN_INCLUSIVE_EXCLUSIVE
                                  )
                                  binding.tvCountDown.setText(spannableString)
                                  binding.tvCountDown.setText(spannableString)
                              }
                          }

                          override fun onFinish() {
                              // 倒计时结束
                              binding.tvCountDown.visibility = View.GONE
                              binding.tvCountDownBackground.visibility = View.GONE
                          }
                      }

                  // 启动倒计时
                  countDownTimer.start()
              }

              //6：人数
                "6" -> {
                  if (item.numText != null) {
                      /*val priceInTenThousand = item.numText.toFloat() / 10000.0f
                    String.format("%.1f", priceInTenThousand) + " 万"
                    binding.tvNumText.text = priceInTenThousand.toString()*/
                    binding.tvNumText.text = item.numText
                    binding.tvNumText.visibility = View.VISIBLE
                }
            }

            //7：配图：一行一个
            "7" -> {

                val rechargeAdapter = ContentAreaListPicListAdapter(
                    R.layout.adapter_recharge_contentarealist_piclist,
                    item.picList
                )

                //设置布局管理器和给recyclerView 设置设配器
                binding.rvPicList.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = rechargeAdapter
                }

            }

            //8：配图2：一行两个
            "8" -> {
                val myAdapter = ContentAreaListGridAdapter(
                    R.layout.adapter_recharge_contentarealist_piclist_double,
                    item.picList
                )

                //设置布局管理器和给recyclerView设置适配器
                binding.rvPicListDouble.apply {
                    layoutManager = GridLayoutManager(context, 2)
                    adapter = myAdapter
                }
            }

            //9：末尾卡片按钮列表
            "9" -> {
                binding.tvNullTitleFirst.text =
                    item.completionInfo.title
                binding.tvNullTitleFirst.visibility =
                    View.VISIBLE
            }

            else -> {
            }
        }
    }

    //// 判断是显示距开始还是距结束
    private fun shouldDisplayCountdownToStart(countDownBean: GetFeedListData.FeedListBean.ContentAreaListBean.CountDownBean): Boolean {
        //获取当前时间
        val currentTime = getCurrentTime()
        //验证开始时间和结束时间的有效性
        val isValidStartTime = isValidDateTime(countDownBean.startTime)
        val isValidEndTime = isValidDateTime(countDownBean.endTime)

        if (!isValidStartTime || !isValidEndTime) {
            return false // 默认按不显示倒计时区域处理
        }

        return currentTime < countDownBean.startTime
    }

    //获取当前时间并以指定的格式返回时间字符串。
    private fun getCurrentTime(): String {
        val dateFormat = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault())
        return dateFormat.format(Date())
    }

    //检查给定的日期时间字符串是否是有效的
    private fun isValidDateTime(dateTime: String): Boolean {
        val dateFormat = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault())
        //将isLenient属性设置为false，表示日期时间解析过程中严格按照指定的格式进行匹配
        dateFormat.isLenient = false
        return try {
            dateFormat.parse(dateTime)
            true
        } catch (e: Exception) {
            false
        }
    }

    //计算剩余时间（以毫秒为单位）
    private fun getRemainingTimeInMillis(
        countDownBean: GetFeedListData.FeedListBean.ContentAreaListBean.CountDownBean,
        isCountingDownToStart: Boolean,
    ): Long {
        //创建日期格式对象，使用默认的语言环境
        val dateFormat = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault())
        //获取当前时间
        val currentTime = getCurrentTime()
        //确定目标时间
        val targetTime =
            if (isCountingDownToStart) countDownBean.startTime else countDownBean.endTime
        //计算剩余时间：使用日期格式化对象将目标时间和当前时间解析为Date对象，并通过调用time方法获取它们的时间戳（以毫秒为单位）
        return Math.abs(dateFormat.parse(targetTime).time - dateFormat.parse(currentTime).time)
    }

    //格式化倒计时文本，将给定的剩余时间（以毫秒为单位）转换为可读的倒计时字符串
    private fun formatCountdownText(
        remainingTimeInMillis: Long,
        isCountingDownToStart: Boolean,
    ): String {
        //取整
        val days = remainingTimeInMillis / (24 * 60 * 60 * 1000)
        //取余完，再取整
        val hours = (remainingTimeInMillis % (24 * 60 * 60 * 1000)) / (60 * 60 * 1000)
        val minutes = (remainingTimeInMillis % (60 * 60 * 1000)) / (60 * 1000)
        val seconds = (remainingTimeInMillis % (60 * 1000)) / 1000

        //计算得到的天数，判断是否大于0
        val daysText = if (days > 0) "$days 天" else ""
        //使用String.format方法将小时、分钟和秒格式化为两位数的字符串，例如"01:05:30"
        val timeText = String.format("%02d:%02d:%02d", hours, minutes, seconds)

        //根据isCountingDownToStart参数的值，决定最终返回的倒计时文本
        return if (isCountingDownToStart) {
            "$daysText$timeText"
        } else {
            "$daysText$timeText"
        }
    }
}