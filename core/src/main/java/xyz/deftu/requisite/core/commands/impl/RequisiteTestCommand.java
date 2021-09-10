package xyz.deftu.requisite.core.commands.impl;

import xyz.deftu.requisite.core.IRequisite;
import xyz.deftu.requisite.core.commands.annotations.Command;

@Command(
        name = "requisitetest",
        generateTabCompletions = true
)
public class RequisiteTestCommand {

    private final IRequisite requisite;

    public RequisiteTestCommand(IRequisite requisite) {
        this.requisite = requisite;
    }

    @Command.Argument(name = "1")
    public void one() {
        requisite.getNotifications().push("Test one", "This is the test one notification.");
    }

}