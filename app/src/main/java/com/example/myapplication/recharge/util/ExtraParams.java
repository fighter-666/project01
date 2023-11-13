/**
 * @Title: ExtraParams.java
 * @Package com.ct.client.promotion.comm
 *
 * @author linwen@ffcs.cn
 * @date 2015年6月30日 下午3:37:22
 * @version V1.0
 */
package com.example.myapplication.recharge.util;

import java.io.Serializable;

/**
 * @ClassName: ExtraParams
 *
 * @author linwen@ffcs.cn
 * @date 2015年6月30日 下午3:37:22
 *
 */
public class ExtraParams implements Serializable {

    /**
     * @Fields serialVersionUID :
     */
    private static final long serialVersionUID = 1L;
    /**
     * 说明：class name
     *
     * 使用 com.ct.client.xiaohao.activity.AliaActivityHelper.getInstance()中的方法
     *      跳转到对应的fragment时，
     *      属性data应该是fragment的全称,如：*.class.getName()
     *
     * @作者 linwen@ffcs.cn
     * @创建时间 2017/11/20 14:45
     * @版本
     * @------修改记录-------
     * @修改人
     * @版本
     * @修改内容
     */
    public String data;
    public int what;
    public Object object;

    /**
     * <p>Title: </p>
     * <p>Description: </p>
     */
    public ExtraParams() {

    }

}
