package peaksoft.restaurant.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import peaksoft.restaurant.dto.request.cheque.ChequeRequest;
import peaksoft.restaurant.dto.response.asimple.SimpleResponse;
import peaksoft.restaurant.dto.response.cheque.ChequeResponse;
import peaksoft.restaurant.dto.response.cheque.ChequeResponseInfo;
import peaksoft.restaurant.service.ChequeService;

@RestController
@RequestMapping("/api/cheque")
@RequiredArgsConstructor
public class ChequeAPI {
    private final ChequeService chequeService;

//    @PostMapping("/create/{id}")
//    public SimpleResponse createCheque(@PathVariable Long employeeId, @RequestBody ChequeRequest chequeRequest) {
//           return chequeService.createChequeWithEmployee(employeeId, chequeRequest);
//    }

//    @GetMapping("/{chequeId}")
//    public ChequeResponseInfo getCheque(@PathVariable Long chequeId) {
//      return chequeService.getChequeInfo(chequeId);
//    }




}
