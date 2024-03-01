package agency.amazon.test.model;

import java.util.List;

public record ApiError(String message, List<String> debugMessage) {}