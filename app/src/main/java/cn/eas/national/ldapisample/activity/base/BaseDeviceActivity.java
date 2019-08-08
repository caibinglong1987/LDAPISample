package cn.eas.national.ldapisample.activity.base;

import cn.eas.national.ldapisample.activity.interfaces.IDeviceView;

/**
 * Created by Czl on 2017/8/25.
 */

public abstract class BaseDeviceActivity extends BaseActivity implements IDeviceView {

    /**
     * All device operation result infomation will be displayed by this method.
     *
     * @param info
     */
    @Override
    public void displayInfo(final String info) {
        super.displayInfo(info);
    }

    @Override
    public void toast(String msg) {
        super.toast(msg);
    }

}
