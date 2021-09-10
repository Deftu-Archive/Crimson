package xyz.deftu.requisite.core.commands.impl;

import xyz.deftu.requisite.core.IRequisite;
import xyz.deftu.requisite.core.commands.annotations.Command;
import xyz.deftu.requisite.core.commands.annotations.Command.Process;

@Command(
        name = "requisite",
        generateTabCompletions = true
)
public class RequisiteCommand {

    private final IRequisite requisite;

    public RequisiteCommand(IRequisite requisite) {
        this.requisite = requisite;
    }

    @Process
    public void execute() {
        requisite.openMenu();
    }

}