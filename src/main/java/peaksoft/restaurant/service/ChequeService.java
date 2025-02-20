package peaksoft.restaurant.service;

import org.springframework.stereotype.Service;
import peaksoft.restaurant.dto.request.cheque.ChequeRequest;
import peaksoft.restaurant.dto.response.asimple.SimpleResponse;
import peaksoft.restaurant.dto.response.cheque.ChequeResponse;
import peaksoft.restaurant.dto.response.cheque.ChequeResponseInfo;

@Service
public interface ChequeService {
    SimpleResponse createChequeWithEmployee(Long employeeId, ChequeRequest chequeRequest);

    ChequeResponseInfo getChequeInfo(Long chequeId);

}
