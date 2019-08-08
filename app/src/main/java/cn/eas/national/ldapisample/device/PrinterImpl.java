package cn.eas.national.ldapisample.device;

import android.content.Context;

import com.landicorp.android.eptapi.device.Printer;
import com.landicorp.android.eptapi.exception.RequestException;
import com.landicorp.android.eptapi.utils.QrCode;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cn.eas.national.ldapisample.data.PrinterError;

import static com.landicorp.android.eptapi.utils.QrCode.ECLEVEL_Q;

/**
 * 针对无打印机终端需使用外接打印机，如蓝牙打印机等。该示例针对内置打印机。
 */

public abstract class PrinterImpl extends BaseDevice {
    private Printer.Progress progress;
    private List<Printer.Step> stepList;
    private Context context;

    public PrinterImpl(Context context) {
        this.context = context;
    }

    public int getPrinterStatus() {
        try {
            int status = Printer.getInstance().getStatus();
            return status;
        } catch (RequestException e) {
            e.printStackTrace();
        }
        return PrinterError.FAIL;
    }

    public void init() {
        stepList = new ArrayList<Printer.Step>();
    }

    public boolean addText() {
        if (stepList == null) {
            displayInfo("printer has not inited!");
            return false;
        }
        stepList.add(new Printer.Step() {
            @Override
            public void doPrint(Printer printer) throws Exception {
                printer.setAutoTrunc(false);
                Printer.Format format = new Printer.Format();
                format.setAscScale(Printer.Format.ASC_SC1x1);
                format.setAscSize(Printer.Format.ASC_DOT24x12);
                format.setHzScale(Printer.Format.HZ_SC1x1);
                format.setHzSize(format.HZ_DOT24x24);
                printer.setFormat(format);
                printer.printMid("福建联迪商用设备有限公司\n");
                format.setAscScale(Printer.Format.ASC_SC1x1);
                format.setAscSize(format.ASC_DOT16x8);
                format.setHzScale(format.HZ_SC1x1);
                format.setHzSize(format.HZ_DOT16x16);
                printer.setFormat(format);
                Printer.Alignment alignment = Printer.Alignment.LEFT;
                printer.printText(alignment, "福建联迪商用设备有限公司\n");
                printer.printText(alignment, "www.landicorp.com\n");
                format.setAscScale(format.ASC_SC1x1);
                format.setAscSize(format.ASC_DOT24x12);
                format.setHzScale(format.HZ_SC1x1);
                format.setHzSize(format.HZ_DOT24x24);
                printer.setFormat(format);
                alignment = Printer.Alignment.CENTER;
                printer.printText(alignment, "福建联迪商用设备有限公司\n");
                printer.printText(alignment, "www.landicorp.com\n");
                format.setAscScale(format.ASC_SC2x2);
                format.setAscSize(format.ASC_DOT24x12);
                format.setHzScale(format.HZ_SC2x2);
                format.setHzSize(format.HZ_DOT24x24);
                printer.setFormat(format);
                alignment = Printer.Alignment.RIGHT;
                printer.printText(alignment, "福建联迪\n");
                printer.printText(alignment, "landicorp\n");
                format.setAscScale(format.ASC_SC1x1);
                format.setAscSize(format.ASC_DOT16x8);
                format.setHzScale(format.HZ_SC1x1);
                format.setHzSize(format.HZ_DOT16x16);
                printer.printMixText(format, "有电子支付的");
                format.setAscScale(format.ASC_SC1x1);
                format.setAscSize(format.ASC_DOT24x12);
                format.setHzScale(format.HZ_SC1x1);
                format.setHzSize(format.HZ_DOT24x24);
                printer.printMixText(format, "地方就有");
                format.setAscScale(format.ASC_SC2x2);
                format.setAscSize(format.ASC_DOT24x12);
                format.setHzScale(format.HZ_SC2x2);
                format.setHzSize(format.HZ_DOT24x24);
                printer.printMixText(format, "联迪商用\n");
//                for (byte[] x :BitImg.ByteImg){
//                    Printer.Format imgFormat = new Printer.Format();
//                    imgFormat.setPicScale(Printer.Format.PIC_SC3x3);
//                    printer.setFormat(imgFormat);
//                    printer.printImage(0,32,32,x);
//                }

                printer.println(0, "test");
                printer.println(50, "test");
                printer.printText(Printer.Alignment.RIGHT, "test\n");

                printer.printBarCode(2, 50, 0, -1, "1234567890");
                printer.printBarCode(2, 50, 50, -1, "1234567890");
                printer.printBarCode(Printer.Alignment.RIGHT, 2, 50, "1234567890");
            }
        });
        return true;
    }

    public boolean addBitmap() {
        if (stepList == null) {
            displayInfo("printer has not inited!");
            return false;
        }
        stepList.add(new Printer.Step() {
            @Override
            public void doPrint(Printer printer) throws Exception {
                InputStream inputStream = context.getAssets().open("pay.bmp");
                printer.printImage(Printer.Alignment.LEFT, inputStream);
            }
        });
        return true;
    }

    public boolean addBarcode() {
        if (stepList == null) {
            displayInfo("printer has not inited!");
            return false;
        }
        stepList.add(new Printer.Step() {
            @Override
            public void doPrint(Printer printer) throws Exception {
                printer.printBarCode("1234567890");
            }
        });
        return true;
    }

    public boolean addQRcode() {
        if (stepList == null) {
            displayInfo("printer has not inited!");
            return false;
        }
        stepList.add(new Printer.Step() {
            @Override
            public void doPrint(Printer printer) throws Exception {
                printer.printQrCode(Printer.Alignment.CENTER,
                        new QrCode("福建联迪商用设备有限公司", ECLEVEL_Q),
                        200);
            }
        });
        return true;
    }

    public boolean feedLine(final int line) {
        if (stepList == null) {
            displayInfo("printer has not inited!");
            return false;
        }
        stepList.add(new Printer.Step() {
            @Override
            public void doPrint(Printer printer) throws Exception {
                printer.feedLine(line);
            }
        });
        return true;
    }

    public boolean cutPaper() {
        if (stepList == null) {
            displayInfo("printer has not inited!");
            return false;
        }
        stepList.add(new Printer.Step() {
            @Override
            public void doPrint(Printer printer) throws Exception {
                printer.cutPaper();
            }
        });
        return true;
    }

    public void startPrint() {
        if (stepList == null) {
            displayInfo("printer has not inited!");
            return;
        }
        progress = new Printer.Progress() {
            @Override
            public void doPrint(Printer printer) throws Exception {
                // never call
            }

            @Override
            public void onFinish(int error) {
                stepList.clear();
                if (error == Printer.ERROR_NONE) {
                    displayInfo("print success");
                } else {
                    String errorDes = getDescribe(error);
                    displayInfo("打印出错：" + errorDes);
                }
            }

            @Override
            public void onCrash() {
                stepList.clear();
                onDeviceServiceCrash();
            }
        };
        for (Printer.Step step : stepList) {
            progress.addStep(step);
        }
        try {
            progress.start();
        } catch (RequestException e) {
            e.printStackTrace();
        }
    }

    public String getDescribe(int error) {
        switch (error) {
        case Printer.ERROR_BMBLACK:
            return "黑标探测器检测到黑色信号";
        case Printer.ERROR_BUFOVERFLOW:
            return "缓冲模式下所操作的位置超出范围";
        case Printer.ERROR_BUSY:
            return "打印机处于忙状态";
        case Printer.ERROR_COMMERR:
            return "手座机状态正常，但通讯失败 (520针打特有返回值)";
        case Printer.ERROR_CUTPOSITIONERR:
            return "切纸刀不在原位 (自助热敏打印机特有返回值)";
        case Printer.ERROR_HARDERR:
            return "硬件错误";
        case Printer.ERROR_LIFTHEAD:
            return "打印头抬起 (自助热敏打印机特有返回值)";
        case Printer.ERROR_LOWTEMP:
            return "低温保护或AD出错 (自助热敏打印机特有返回值)";
        case Printer.ERROR_LOWVOL:
            return "低压保护";
        case Printer.ERROR_MOTORERR:
            return "打印机芯故障(过快或者过慢)";
        case Printer.ERROR_NOBM:
            return "没有找到黑标";
        case Printer.ERROR_NONE:
            return "正常状态，无错误";
        case Printer.ERROR_OVERHEAT:
            return "打印头过热";
        case Printer.ERROR_PAPERENDED:
            return "缺纸，不能打印";
        case Printer.ERROR_PAPERENDING:
            return "纸张将要用尽，还允许打印 (单步进针打特有返回值)";
        case Printer.ERROR_PAPERJAM:
            return "卡纸";
        case Printer.ERROR_PENOFOUND:
            return "自动定位没有找到对齐位置,纸张回到原来位置";
        case Printer.ERROR_WORKON:
            return "打印机电源处于打开状态";
        case Printer.ERROR_CUTCLEAN:
            return "纸仓堵纸，需清理纸仓";
        case Printer.ERROR_CUTERROR:
            return "切纸刀卡刀";
        case Printer.ERROR_CUTFAULT:
            return "切纸刀故障";
        case Printer.ERROR_OPENCOVER:
            return "纸仓被打开";
        default:
            return "未知错误";
        }
    }
}
