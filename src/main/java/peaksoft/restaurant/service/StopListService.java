package peaksoft.restaurant.service;

import org.springframework.stereotype.Service;
import peaksoft.restaurant.dto.request.stopList.StopListRequest;
import peaksoft.restaurant.dto.response.asimple.SimpleResponse;

@Service
public interface StopListService {
    SimpleResponse createStopList(Long employeeId, StopListRequest stopListRequest);
}
