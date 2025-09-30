package ch.nicksohl.winslow.application.cqrs.command;

import ch.nicksohl.winslow.domain.value_object.OrderIndex;

public record ModuleCommand(String name, String description, OrderIndex orderIndex) {
}
