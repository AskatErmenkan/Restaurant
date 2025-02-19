package peaksoft.restaurant.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.restaurant.dto.request.stopList.StopListRequest;
import peaksoft.restaurant.dto.response.asimple.SimpleResponse;
import peaksoft.restaurant.service.StopListService;

@RestController
@RequestMapping("/api/stopList")
@RequiredArgsConstructor
public class StopListAPI {
   private final StopListService stopListService;

   @PostMapping("/{employeeId}")
    public SimpleResponse createStopList(@PathVariable Long employeeId, @RequestBody StopListRequest stopListRequest) {
      return stopListService.createStopList(employeeId, stopListRequest);
   }

}
