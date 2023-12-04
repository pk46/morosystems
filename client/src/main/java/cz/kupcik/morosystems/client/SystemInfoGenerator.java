package cz.kupcik.morosystems.client;

import cz.kupcik.morosystems.core.dto.MessageDto;

import java.time.LocalDateTime;

public class SystemInfoGenerator {

    private static String retrieveMemoryUsage() { return "memory"; }
    private static String retrieveCpuUsage() { return "cpu"; }
    private static String retrieveIpAddress() { return "ipAddress"; }

    public static MessageDto generate() {
        return new MessageDto(retrieveIpAddress(), retrieveMemoryUsage(), retrieveCpuUsage(), LocalDateTime.now());
    }
}
