package br.com.fiap.finclass_api.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ApiError(
        LocalDateTime timestamp,
        int status,
        String error,
        String path,
        List<String> details
) { }
