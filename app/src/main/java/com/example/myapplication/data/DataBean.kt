package com.example.myapplication.data

import com.example.myapplication.R
import java.util.Locale
import java.util.Random


class DataBean {
    var imageRes: Int? = null
    var imageUrl: String? = null
    var title: String?
    var viewType: Int

    constructor(imageRes: Int?, title: String?, viewType: Int) {
        this.imageRes = imageRes
        this.title = title
        this.viewType = viewType
    }

    constructor(imageUrl: String?, title: String?, viewType: Int) {
        this.imageUrl = imageUrl
        this.title = title
        this.viewType = viewType
    }

    companion object {
        val testData: List<DataBean>
            get() {
                val list: MutableList<DataBean> = ArrayList()
                list.add(DataBean(R.drawable.tengxun,"相信自己,你努力的样子真的很美", 1))
                list.add(DataBean(R.drawable.tengxun, "极致简约,梦幻小屋", 3))
                list.add(DataBean(R.drawable.tengxun, "超级卖梦人", 3))
                list.add(DataBean(R.drawable.tengxun,"夏季新搭配", 1))
                list.add(DataBean(R.drawable.tengxun,"绝美风格搭配", 1))
                list.add(DataBean(R.drawable.tengxun,"微微一笑 很倾城", 3))
                return list
            }
        val testData2: List<DataBean>
            get() {
                val list: MutableList<DataBean> = ArrayList()
                list.add(DataBean(R.drawable.youku,"听风.赏雨", 3))
                list.add(DataBean(R.drawable.tengxun,"迪丽热巴.迪力木拉提", 1))
                list.add(DataBean(R.drawable.bean,"爱美.人间有之", 3))
                list.add(DataBean(R.drawable.scratch1, "洋洋洋.气质篇", 1))
                list.add(DataBean(R.drawable.prise, "生活的态度", 3))
                return list
            }

        /**
         * 仿淘宝商品详情第一个是视频
         *
         * @return
         */
        val testDataVideo: List<DataBean>
            get() {
                val list: MutableList<DataBean> = ArrayList()
                list.add(
                    DataBean(
                        "http://vfx.mtime.cn/Video/2019/03/09/mp4/190309153658147087.mp4",
                        "第一个放视频",
                        2
                    )
                )
                list.add(DataBean(R.drawable.batman,"听风.赏雨", 1))
                list.add(DataBean(R.drawable.batman,"迪丽热巴.迪力木拉提", 1))
                list.add(DataBean(R.drawable.batman,"爱美.人间有之", 1))
                list.add(DataBean(R.drawable.batman, "洋洋洋.气质篇", 1))
                list.add(DataBean(R.drawable.batman, "生活的态度", 1))
                return list
            }
        val testData3: List<DataBean>
            get() {
                val list: MutableList<DataBean> = ArrayList()
                list.add(
                    DataBean(
                        "https://w.189.cn/bigdata/2023/2/13/111676275354612298.png",
                        null,
                        1
                    )
                )
                list.add(
                    DataBean(
                        "https://w.189.cn/bigdata/2023/2/13/111676275354612298.png",
                        null,
                        1
                    )
                )
                list.add(
                    DataBean(
                        "https://w.189.cn/bigdata/2023/2/13/111676275354612298.png",
                        null,
                        1
                    )
                )
                list.add(
                    DataBean(
                        "https://w.189.cn/bigdata/2023/3/17/111679019573649550.jpg",
                        null,
                        1
                    )
                )
                list.add(
                    DataBean(
                        "https://w.189.cn/bigdata/2023/2/13/111676275354612298.png",
                        null,
                        1
                    )
                )
                return list
            }
        val videos: List<DataBean>
            get() {
                val list: MutableList<DataBean> = ArrayList()
                list.add(
                    DataBean(
                        "http://vfx.mtime.cn/Video/2019/03/21/mp4/190321153853126488.mp4",
                        null,
                        0
                    )
                )
                list.add(
                    DataBean(
                        "http://vfx.mtime.cn/Video/2019/03/18/mp4/190318231014076505.mp4",
                        null,
                        0
                    )
                )
                list.add(
                    DataBean(
                        "http://vfx.mtime.cn/Video/2019/03/18/mp4/190318214226685784.mp4",
                        null,
                        0
                    )
                )
                list.add(
                    DataBean(
                        "http://vfx.mtime.cn/Video/2019/03/19/mp4/190319125415785691.mp4",
                        null,
                        0
                    )
                )
                list.add(
                    DataBean(
                        "http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4",
                        null,
                        0
                    )
                )
                list.add(
                    DataBean(
                        "http://vfx.mtime.cn/Video/2019/03/14/mp4/190314102306987969.mp4",
                        null,
                        0
                    )
                )
                return list
            }

        fun getColors(size: Int): List<String> {
            val list: MutableList<String> = ArrayList()
            for (i in 0 until size) {
                list.add(randColor)
            }
            return list
        }

        /**
         * 获取十六进制的颜色代码.例如  "#5A6677"
         * 分别取R、G、B的随机值，然后加起来即可
         *
         * @return String
         */
        val randColor: String
            get() {
                var R: String
                var G: String
                var B: String
                val random = Random()
                R = Integer.toHexString(random.nextInt(256)).uppercase(Locale.getDefault())
                G = Integer.toHexString(random.nextInt(256)).uppercase(Locale.getDefault())
                B = Integer.toHexString(random.nextInt(256)).uppercase(Locale.getDefault())
                R = if (R.length == 1) "0$R" else R
                G = if (G.length == 1) "0$G" else G
                B = if (B.length == 1) "0$B" else B
                return "#$R$G$B"
            }
    }
}