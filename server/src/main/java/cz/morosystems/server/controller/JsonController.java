package cz.morosystems.server.controller;

import cz.morosystems.server.model.SystemInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JsonController {

    @GetMapping(value="/info")
    public List<SystemInfo> getInfo() {
        return new ArrayList<>();
    }
}
