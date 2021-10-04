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

import xyz.qalcyo.mango.Lists;
import xyz.qalcyo.mango.Objects;
import xyz.qalcyo.mango.collections.Pair;
import xyz.qalcyo.mango.collections.impl.ImmutablePair;
import xyz.qalcyo.requisite.core.RequisiteAPI;
import xyz.qalcyo.requisite.core.commands.annotations.Command;
import xyz.qalcyo.requisite.core.commands.exceptions.CommandException;
import xyz.qalcyo.requisite.core.commands.exceptions.IncorrectUsageException;
import xyz.qalcyo.requisite.core.commands.impl.RequisiteCommand;
import xyz.qalcyo.requisite.core.commands.impl.RequisiteTestCommand;
import xyz.qalcyo.requisite.core.util.ChatColour;
import xyz.qalcyo.requisite.core.events.SendChatMessageEvent;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class CommandRegistry {

    private final RequisiteAPI requisite;
    private final ICommandHelper commandHelper;
    private final List<ICommand> commands;

    private String[] cachedAutoCompletion;

    public CommandRegistry(ICommandHelper commandHelper) {
        this.requisite = RequisiteAPI.retrieveInstance();
        this.commandHelper = commandHelper;
        this.commands = Lists.newArrayList();

        requisite.getEventBus().register(SendChatMessageEvent.class, this::onChatMessageSent);

        register(new RequisiteCommand(requisite));
        register(new RequisiteTestCommand(requisite));
    }

    public void register(Object command) {
        Objects.notNull(command, "Command");

        Class<?> clazz = command.getClass();
        if (clazz.isAnnotationPresent(Command.class)) {
            Command annotation = clazz.getAnnotation(Command.class);

            List<Method> defaultExecutors = Lists.newLinkedList();
            List<Method> processors = Lists.newLinkedList();
            List<Pair<Method, Command.Argument>> arguments = Lists.newLinkedList();

            List<String> tabCompletions = Lists.newLinkedList();
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Command.Default.class)) {
                    defaultExecutors.add(method);
                }

                if (method.isAnnotationPresent(Command.Process.class)) {
                    processors.add(method);
                }

                if (method.isAnnotationPresent(Command.Argument.class)) {
                    Command.Argument argument = method.getAnnotation(Command.Argument.class);
                    arguments.add(new ImmutablePair<>(method, argument));
                    if (annotation.generateTabCompletions()) {
                        tabCompletions.add(argument.name());
                        tabCompletions.addAll(Arrays.asList(argument.tabCompleteOptions()));
                    }
                }
            }

            commands.add(new AnnotatedCommand(command, annotation, defaultExecutors, processors, arguments, tabCompletions));
        } else if (command instanceof ICommand) {
            commands.add((ICommand) command);
        } else {
            throw new IllegalArgumentException("Please provide a command to register.");
        }
    }

    public void unregister(String name) {
        commands.removeIf(command -> command.data().getName().equals(name));
    }

    public void unregister(ICommand command) {
        unregister(command.data().getName());
    }

    /**
     * Executes a command based on the input
     * provided. Returns a status code
     * depending on the status of the
     * command execution status.
     *
     * 0 - Successful
     * 1 - Failed
     *
     * @param input
     * @return
     */
    public int execute(String input) {
        input = input.trim();
        if (!input.startsWith("/")) {
            return 1;
        }

        input = input.substring(1);
        String[] split = input.split(" ");
        String[] args = new String[split.length - 1];
        String name = split[0];
        System.arraycopy(split, 1, args, 0, args.length);

        for (ICommand command : commands) {
            CommandData data = command.data();

            if (data.has(name)) {
                if (process(command, args)) {
                    return -1;
                }
            }
        }

        return 0;
    }

    private boolean process(ICommand command, String[] args) {
        try {
            command.execute(Arrays.asList(args));
            return true;
        } catch (IncorrectUsageException incorrectUsageException) {
            requisite.getChatHelper().send(incorrectUsageException.toString());
            incorrectUsageException.printStackTrace();
        } catch (CommandException genericException) {
            genericException.printStackTrace();
        }

        return false;
    }

    public void processAutoCompletion(String input) {
        cachedAutoCompletion = null;

        if (!input.startsWith("/")) {
            return;
        }

        input = input.substring(1);

        if (commandHelper.isInChat()) {
            List<String> options = retrieveTabCompletions(input);
            if (options != null && !options.isEmpty()) {
                if (input.indexOf(' ') == -1) {
                    for (int i = 0; i < options.size(); i++) {
                        options.set(i, ChatColour.RED + "/" + options.get(i) + ChatColour.RESET);
                    }
                } else {
                    for (int i = 0; i < options.size(); i++) {
                        options.set(i, ChatColour.RED + "/" + options.get(i) + ChatColour.RESET);
                    }
                }

                cachedAutoCompletion = options.toArray(new String[0]);
            }
        }
    }

    private List<String> retrieveTabCompletions(String input) {
        String[] split = input.split(" ", -1);
        String first = split[0];
        if (split.length == 1) {
            List<String> list = Lists.newArrayList();
            for (ICommand command : commands) {
                CommandData data = command.data();
                if (doesStringStartWith(first, data.getName())) {
                    list.add(data.getName());
                }

                if (data.getAliases() != null) {
                    for (String alias : data.getAliases()) {
                        if (alias != null) {
                            list.add(alias);
                        }
                    }
                }
            }
            return list;
        } else {
            return null;
        }
    }

    private boolean doesStringStartWith(String original, String region) {
        return region.regionMatches(true, 0, original, 0, original.length());
    }

    public String[] getCachedAutoCompletion() {
        return cachedAutoCompletion;
    }

    private void onChatMessageSent(SendChatMessageEvent event) {
        if (execute(event.message) == -1) {
            event.cancel();
        }
    }

}