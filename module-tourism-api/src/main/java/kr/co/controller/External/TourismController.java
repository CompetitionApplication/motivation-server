package kr.co.controller.External;

import kr.co.service.External.TourismService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/External/tourism")
public class TourismController {

    private final TourismService tourismService;

    @PostMapping("/korea-version")
    public void saveKoreaTourism() {
        tourismService.saveKoreaTourism();
    }
}
