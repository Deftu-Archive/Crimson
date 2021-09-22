package xyz.qalcyo.requisite.core.commands;

import xyz.qalcyo.mango.collections.Pair;
import xyz.qalcyo.requisite.core.commands.annotations.Command;
import xyz.qalcyo.requisite.core.commands.exceptions.CommandException;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

class AnnotatedCommand implements ICommand {

    private final Object instance;

    private final Command command;
    private final List<Method> defaultExecutors, processors;
    private final List<Pair<Method, Command.Argument>> arguments;

    private final List<String> tabCompletions;

    public AnnotatedCommand(Object instance, Command command, List<Method> defaultExecutors, List<Method> processors, List<Pair<Method, Command.Argument>> arguments, List<String> tabCompletions) {
        this.instance = instance;
        this.command = command;
        this.defaultExecutors = defaultExecutors;
        this.processors = processors;
        this.arguments = arguments;

        this.tabCompletions = tabCompletions;
    }

    public CommandData data() {
        return CommandData.from(command.name(), Arrays.asList(command.aliases()), tabCompletions);
    }

    public void execute(List<String> args) throws CommandException {
        try {
            for (Method processor : processors) {
                if (processor.getParameterCount() > 0 && processor.getParameterTypes()[0].isAssignableFrom(List.class)) {
                    processor.invoke(instance, args);
                } else {
                    processor.invoke(instance);
                }
            }

            if (args.isEmpty()) {
                for (Method defaultExecutor : defaultExecutors) {
                    if (defaultExecutor.getParameterCount() > 0 && defaultExecutor.getParameterTypes()[0].isAssignableFrom(List.class)) {
                        defaultExecutor.invoke(instance, args);
                    } else {
                        defaultExecutor.invoke(instance);
                    }
                }
            } else {
                for (Pair<Method, Command.Argument> argument : arguments) {
                    Method method = argument.first();
                    Command.Argument arg = argument.second();

                    if (args.size() >= arg.index()) {
                        String name = args.get(arg.index());
                        List<String> argAliases = Arrays.asList(arg.aliases());
                        if (name.equalsIgnoreCase(arg.name()) || argAliases.stream().anyMatch(name::equalsIgnoreCase)) {
                            if (method.getParameterCount() > 0 && method.getParameterTypes()[0].isAssignableFrom(List.class)) {
                                method.invoke(instance, args);
                            } else {
                                method.invoke(instance);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}