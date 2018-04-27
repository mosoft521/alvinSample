package com.gmail.mosoft521.runtime;

public interface LocalCommandExecutor {
    ExecuteResult executeCommand(String command, long timeout);
}