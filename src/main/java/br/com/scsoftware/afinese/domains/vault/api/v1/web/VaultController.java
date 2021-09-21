package br.com.scsoftware.afinese.domains.vault.api.v1.web;

import br.com.scsoftware.afinese.domains.vault.api.v1.web.request.CreatePinRequest;
import br.com.scsoftware.afinese.domains.vault.api.v1.web.request.RecoveryPinChallengeRequest;
import br.com.scsoftware.afinese.domains.vault.api.v1.web.request.RecoveryPinConfirmationRequest;
import br.com.scsoftware.afinese.domains.vault.api.v1.web.request.VerifyPinRequest;
import br.com.scsoftware.afinese.domains.vault.api.v1.web.response.KeyPairResponse;
import br.com.scsoftware.afinese.domains.vault.api.v1.web.response.PublicKeyInfoResponse;
import br.com.scsoftware.afinese.domains.vault.api.v1.web.response.UserPinResponse;
import br.com.scsoftware.afinese.domains.vault.service.VaultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/vault/{platform}")
public class VaultController {

    private final VaultService vaultService;

    @GetMapping("/public-key")
    public ResponseEntity<PublicKeyInfoResponse> getPublicKey(@PathVariable String platform) {
        log.info("GET | getPublicKey | Requesting Public Key | Platform: {}", platform);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/generate-keys")
    public ResponseEntity<KeyPairResponse> getKeyPair(@PathVariable String platform) {
        log.info("GET | getKeyPair | Requesting  Key Pair | Platform: {}", platform);

        return ResponseEntity.ok(null);
    }

    @GetMapping("/pins/{document}")
    public ResponseEntity<UserPinResponse> getHasPin(@PathVariable String platform, @PathVariable String document) {
        log.info("GET | getHasPin | Requesting Has Pin | Platform: {} | Document: {}", platform, document);

        return ResponseEntity.ok(null);
    }

    @PostMapping(value = "/pins/{document}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postCreatePin(@Valid @RequestBody CreatePinRequest requestBody, @PathVariable String platform, @PathVariable String document) {
        log.info("GET | postCreatePin | Requesting Pin Creation | Platform: {} | Document: {}", platform, document);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/pins/{document}/verify")
    public ResponseEntity<?> postVerifyPin(@Valid @RequestBody VerifyPinRequest requestBody, @PathVariable String platform, @PathVariable String document) {
        log.info("GET | postCreatePin | Requesting Pin Creation | Platform: {} | Document: {}", platform, document);

        //TODO: Implement

        return ResponseEntity.ok().build();
    }

    @PostMapping("/pins/{document}/recovery")
    public ResponseEntity<?> postRecoveryPin(@Valid @RequestBody RecoveryPinChallengeRequest requestBody, @PathVariable String platform, @PathVariable String document) {
        log.info("GET | postCreatePin | Requesting Pin Recovery Challenge | Platform: {} | Document: {}", platform, document);

        //TODO: Implement

        return ResponseEntity.ok().build();
    }

    @PutMapping("/pins/{document}/recovery")
    public ResponseEntity<?> putRecoveryPinConfirm(@Valid @RequestBody RecoveryPinConfirmationRequest requestBody, @PathVariable String platform, @PathVariable String document) {
        log.info("GET | postCreatePin | Confirming Pin Recovery Challenge | Platform: {} | Document: {}", platform, document);

        //TODO: Implement

        return ResponseEntity.ok().build();
    }
}
