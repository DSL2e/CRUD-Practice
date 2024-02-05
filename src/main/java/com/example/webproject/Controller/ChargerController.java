package com.example.webproject.Controller;
import com.example.webproject.Dto.ChargerForm;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.webproject.domain.Charger;
import com.example.webproject.service.ChargerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ChargerController {
    private final ChargerService chargerService;

    /***
     *
     * 충전기 전체 조회
     */
    @GetMapping("/charger")
    public ResponseEntity<List<ChargerForm>> createForm(){
        List<Charger> chargers = chargerService.findItems();

        List<ChargerForm> chargerForms = chargers.stream()
                .map(ChargerForm::new)
                .toList();

        return ResponseEntity.ok(chargerForms);
    }


    /***
     *
     * 새로운 충전기 등록
     */
    @PostMapping("/charger/new")
    public ResponseEntity<Map<String, String>> createCharger(@RequestBody ChargerForm form) throws URISyntaxException {
        Charger charger = new Charger(form);
        chargerService.saveCharger(charger);

        // 생성된 Charger의 ID를 이용하여 URI 생성
        URI location = new URI("/charger/" + charger.getId());

        Map<String, String> response = new HashMap<>();
        response.put("message", "Charger created successfully");
        response.put("redirectUrl", location.toString());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return ResponseEntity.status(HttpStatus.CREATED)
                .headers(headers)
                .body(response);
    }

    /***
     *
     * 한 충전기 상세 조회
     */
    @GetMapping("/charger/{chargerId}")
    public ResponseEntity<ChargerForm> detailCharger(@PathVariable(name = "chargerId") Long chargerId) {
        Charger charger = chargerService.findOne(chargerId);

        if (charger != null) {
            ChargerForm chargerForm = new ChargerForm(charger);
            return ResponseEntity.ok(chargerForm);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    /***
     *
     * 한 충전기 수정
     */
    @PatchMapping("/charger/{chargerId}/edit")
    public ResponseEntity<ChargerForm> updateCharger(@RequestBody ChargerForm form, @PathVariable(name = "chargerId") Long chargerId) {
        Charger existingCharger = chargerService.findOne(chargerId);

        if (existingCharger != null) {
            existingCharger.updateCharger(form);

            ChargerForm chargerForm = new ChargerForm(existingCharger);
            return ResponseEntity.ok(chargerForm);
            // 리다이렉션 응답 반환
        } else {
            // 해당 ID가 없으면, 404 Not Found 반환
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/charger/{chargerId}")
    public ResponseEntity<Void> deleteCharger(@PathVariable(name = "chargerId") Long chargerId,@RequestParam(name = "del_status") boolean delStatus) {
        Charger existingCharger = chargerService.findOne(chargerId);

        if (existingCharger != null) {
            existingCharger.updateDelStatus(delStatus);

            // 리다이렉션 경로 지정
            URI location = URI.create("/charger");
            chargerService.saveCharger(existingCharger);

            return ResponseEntity.noContent().build();
        } else {
            // Charger 엔티티가 존재하지 않을 때 404 Not Found 반환
            return ResponseEntity.notFound().build();
        }
    }

}
