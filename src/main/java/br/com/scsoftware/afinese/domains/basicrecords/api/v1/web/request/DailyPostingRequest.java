package br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class DailyPostingRequest {
    private LocalDate date;
    @NotNull
    @Valid
    private BalanceDailyPosting balance;
    @NotNull
    private Boolean breakfast;
    @NotNull
    private Boolean morningSnack;
    @NotNull
    private Boolean lunch;
    @NotNull
    private Boolean afternoonSnack;
    @NotNull
    private Boolean dinner;
    @NotNull
    private Boolean hiit;
    private String notes;
}
