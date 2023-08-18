package br.com.scsoftware.afinese.domains.basicrecords.api.v1.web;

import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.request.DailyPostingRequest;
import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.response.DailyPostingResponse;
import br.com.scsoftware.afinese.domains.basicrecords.business.DailyPostingBO;
import br.com.scsoftware.afinese.domains.basicrecords.business.DailyWeightInformation;
import br.com.scsoftware.afinese.domains.basicrecords.converter.DailyPostingConverter;
import br.com.scsoftware.afinese.domains.basicrecords.entity.DailyPosting;
import br.com.scsoftware.afinese.domains.basicrecords.service.DailyPostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/")
@Transactional(rollbackFor = Exception.class)
public class DailyPostingController {

    private final DailyPostingService dailyPostingService;

    @GetMapping("{patientId}/agreements/{agreementId}/dailies")
    public ResponseEntity<Page<DailyPostingResponse>> getAll(@PathVariable final Long patientId, @PathVariable final Long agreementId,
                                                             @PageableDefault final Pageable page) {
        final Page<DailyPostingResponse> dailyPostingList = dailyPostingService.getAllRecords(patientId, agreementId, page)
                .map(DailyPostingConverter::toDTO);

        if (dailyPostingList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(dailyPostingList);
    }

    @GetMapping("{patientId}/agreements/{agreementId}/dailies/{id}")
    public ResponseEntity<DailyPostingResponse> getRecord(@PathVariable final Long patientId, @PathVariable final Long agreementId,
                                                          @PathVariable final Long id) {
        Optional<DailyPosting> dailyPosting = dailyPostingService.getRecord(patientId, agreementId, id);
        if (dailyPosting.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(DailyPostingConverter.toDTO(dailyPosting.get()));
    }

    @PostMapping("{patientId}/agreements/{agreementId}/dailies")
    public ResponseEntity postRecord(@PathVariable final Long patientId, @PathVariable final Long agreementId,
                                     @Valid @RequestBody DailyPostingRequest dailyPostingRequest) {
        final DailyPostingBO dailyPostingBO = dailyPostingService.create(DailyPostingConverter.toBO(dailyPostingRequest),
                patientId, agreementId);
        final URI uriLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dailyPostingBO.getId())
                .toUri();

        return ResponseEntity.created(uriLocation).build();
    }

    @GetMapping("dailies")
    public ResponseEntity<List<DailyPostingResponse>> getByGroupAndPatient(@RequestParam final Long groupId,
                                                                           @RequestParam(required = false) final Long patientId,
                                                                           @RequestParam final String date,
                                                                           @PageableDefault final Pageable page) {
        LocalDate localDate = LocalDate.parse(date);
        final List<DailyPostingResponse> dailyPostingList = dailyPostingService.getAllRecords(groupId, patientId, localDate, page)
                .stream()
                .map(DailyPostingConverter::toDTO)
                .collect(Collectors.toList());

        if (dailyPostingList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        ArrayList<Long> agreementsId = new ArrayList<>();

        dailyPostingList.forEach(dailyPosting -> {
            if (dailyPosting.getId() == null) {
                agreementsId.add(dailyPosting.getAgreementId());
            }
        });

        if (agreementsId.isEmpty()) {
            return ResponseEntity.ok(dailyPostingList);
        }

        final List<DailyWeightInformation> dailyWeightInformationList = dailyPostingService
                .getDailyWeightInformation(localDate, agreementsId);

        dailyPostingList.forEach(dailyPosting -> {
            if (dailyPosting.getId() == null) {
                var balance = dailyPosting.getBalance();

                final var dailyWeightInformation = dailyPostingService.getDailyWeightInformation(
                        localDate,
                        balance.getCurrentWeight(),
                        balance.getEvolution(),
                        dailyPosting.getAgreementId(),
                        dailyWeightInformationList,
                        dailyPosting.getAgreementStartingWeight()
                );

                dailyPosting.getBalance().setPreviousWeight(dailyWeightInformation.getPreviousWeight());
                dailyPosting.getBalance().setAccumulatedEvolution(dailyWeightInformation.getAccumulatedEvolution());
            }
        });

        return ResponseEntity.ok(dailyPostingList);
    }
}
