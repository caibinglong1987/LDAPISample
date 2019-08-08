package cn.eas.national.ldapisample;

import android.util.Log;

import org.junit.Test;

import java.util.Arrays;

import cn.eas.national.ldapisample.util.ByteUtil;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        //assertEquals(4, 2 + 2);

        System.out.println("ssss");
        System.out.println(Arrays.toString(ByteUtil.hexString2Bytes("b62307")));
    }
}