package br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.request;

import br.com.scsoftware.afinese.domains.basicrecords.enums.StatusAgreement;
import br.com.scsoftware.afinese.infrastructure.common.serializer.CustomBigDecimalSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class UpdateAgreement {
    @NotNull
    @Positive
    private Long programId;
    @NotNull
    @Positive
    private Long groupId;
    @NotNull
    private StatusAgreement status;
    @NotNull
    @Positive
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal startingWeight;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    @Positive
    private BigDecimal goal;
    @NotNull
    private LocalDate hiringDate;
    @NotNull
    private LocalDate startDate;
    @PastOrPresent
    private LocalDate dateConclusion;
    @PastOrPresent
    private LocalDate cancellationDate;
    private String reasonCancellation;
    private String notes;

    @AssertFalse(message = "Cancellation date or reason for cancellation should only be when the status is cancellation")
    public boolean isCancellation() {
        return getStatus() != null && !getStatus().equals(StatusAgreement.CANCELED) &&
            (getDateConclusion() != null || getReasonCancellation() != null);
    }

    @AssertFalse(message = "The date and reason for cancellation must be informed when the status is cancellation")
    public boolean isCancellationRequired() {
        return getStatus() != null && getStatus().equals(StatusAgreement.CANCELED) &&
            (getDateConclusion() == null || getReasonCancellation() == null);
    }

    @AssertFalse(message = "Conclusion date should only be when the status is completed")
    public boolean isConclusion() {
        return getStatus() != null && !getStatus().equals(StatusAgreement.COMPLETED) && getDateConclusion() != null;
    }

    @AssertFalse(message = "The completion date must be entered when the status is completed")
    public boolean isConclusionRequired() {
        return getStatus() != null && getStatus().equals(StatusAgreement.COMPLETED) && getDateConclusion() == null;
    }
}
