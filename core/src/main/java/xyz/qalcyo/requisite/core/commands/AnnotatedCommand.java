/*
 * Requisite - Minecraft library mod
 *  Copyright (C) 2021 Qalcyo
 *
 * Requisite is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * Requisite is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Requisite. If not, see <https://www.gnu.org/licenses/>.
 */

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
                processor.setAccessible(true);
                if (processor.getParameterCount() > 0 && processor.getParameterTypes()[0].isAssignableFrom(List.class)) {
                    processor.invoke(instance, args);
                } else {
                    processor.invoke(instance);
                }
            }

            if (args.isEmpty()) {
                for (Method defaultExecutor : defaultExecutors) {
                    defaultExecutor.setAccessible(true);
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

                    method.setAccessible(true);
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