package br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.request;

import br.com.scsoftware.afinese.domains.basicrecords.enums.StatusAgreement;
import br.com.scsoftware.afinese.infrastructure.common.serializer.CustomBigDecimalSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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
    private LocalDate dateConclusion;
    private LocalDate cancellationDate;
    private String reasonCancellation;
    private String notes;

    @AssertFalse(message = "A data de cancelamento ou o motivo do cancelamento deve ser informado apenas quando o status for CANCELAMENTO")
    public boolean isCancellation() {
        return getStatus() != null && !getStatus().equals(StatusAgreement.CANCELED) &&
            (getCancellationDate() != null || getReasonCancellation() != null);
    }

    @AssertFalse(message = "A data e o motivo do cancelamento devem ser informados quando o status for CANCELAMENTO")
    public boolean isCancellationRequired() {
        return getStatus() != null && getStatus().equals(StatusAgreement.CANCELED) &&
            (getCancellationDate() == null || getReasonCancellation() == null);
    }

    @AssertFalse(message = "A data de conclusão deve ser informada apenas quando o status for CONCLUÍDO")
    public boolean isConclusion() {
        return getStatus() != null && !getStatus().equals(StatusAgreement.COMPLETED) && getDateConclusion() != null;
    }

    @AssertFalse(message = "A data de conclusão deve ser informada quando o status for CONCLUÍDO")
    public boolean isConclusionRequired() {
        return getStatus() != null && getStatus().equals(StatusAgreement.COMPLETED) && getDateConclusion() == null;
    }
}
