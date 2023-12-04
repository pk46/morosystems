package cz.kupcik.morosystems.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@ToString
public class MessageDto implements Serializable {
    private String ipAddress;
    private String memoryUsage;
    private String cpuUsage;
    private LocalDateTime timestamp;
}
