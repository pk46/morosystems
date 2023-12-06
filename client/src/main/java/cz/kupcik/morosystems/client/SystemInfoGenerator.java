package cz.kupcik.morosystems.client;

import cz.kupcik.morosystems.core.dto.MessageDto;
import lombok.extern.slf4j.Slf4j;


import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
import java.net.InetAddress;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.util.Locale;

@Slf4j
public class SystemInfoGenerator {

    private static final OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    private static String retrieveMemoryUsage() {
        return String.valueOf((osBean.getTotalMemorySize() - osBean.getFreeMemorySize()) / (1024 * 1024));
    }

    private static String retrieveCpuUsage() {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.##", otherSymbols); // round to two decimals
        return String.valueOf(df.format(osBean.getCpuLoad() * 100)); // * 100 to get result in percentage
    }

    private static String retrieveIpAddress() {
        try {
            InetAddress myIP = InetAddress.getLocalHost();
            return myIP.getHostAddress();
        } catch (java.net.UnknownHostException e) {
            log.error("Nepodařilo se získat IP adresu: ", e);
            return "";
        }
    }

    public static MessageDto generate() {
        return new MessageDto(retrieveIpAddress(), retrieveMemoryUsage(), retrieveCpuUsage(), LocalDateTime.now());
    }
}
