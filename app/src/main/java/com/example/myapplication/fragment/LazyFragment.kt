package com.example.myapplication.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
/*import com.ct.client.promotion.comm.ExtraParams
import com.ct.base.widget.MyToastD
import com.ct.base.common.Log;*/
abstract class LazyFragment : Fragment() {

    var isLoaded = false
    var isLazy = true
   /* @JvmField
    var mExtraParams: ExtraParams? = null
*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!isLazy) {
            Log.d("hssTAG","onViewCreated lazy")
            lazyInit()
            isLoaded = true
        }
    }

    override fun onResume() {
        super.onResume()
        if (!isLoaded && isLazy) {
            lazyInit()
            Log.d("hssTAG", "onResume lazyInit:!!!!!!!")
            isLoaded = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isLoaded = false
    }

    abstract fun lazyInit()

    open fun showToast(text: String) {
        //MyToastD.show(text)
    }

    /**
     * 通过Class跳转界面
     *
     * @作者 huangssh
     * @创建时间 2015-8-6 上午9:19:29
     * @param cls 跳转到的class
     */
    protected open fun startActivity(cls: Class<*>) {
        startActivity(cls, null)
    }

    /**
     * 含有Bundle通过Class跳转界面
     *
     * @作者 huangssh
     * @创建时间 2015-8-6 上午9:21:52
     * @param cls 跳转到的class
     * @param bundle
     */
    protected open fun startActivity(cls: Class<*>, bundle: Bundle?) {
        val intent = Intent()
        intent.setClass(requireContext(), cls)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }
}


