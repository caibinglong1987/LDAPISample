package cn.eas.national.ldapisample.presenter;

/**
 * Created by Czl on 2017/7/23.
 */

public interface IAT1608CardPresenter {
    void cardPower();
    void cardHalt();
    void read();
    void write();
    void changePassword();
    void readCardStatus();
    void testIO();
}
