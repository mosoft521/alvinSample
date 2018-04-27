package com.gmail.mosoft521.runtime;

public class Main {
    public static void main(String args[]) {
        try {
            String cmd = "cmd.exe /C fmpp -S D:\\tools\\fmpp\\docs\\examples\\qtour_step1\\src\\ -O D:\\tools\\fmpp\\docs\\examples\\qtour_step1\\out\\";

            // 重定向输出流和错误流
            LocalCommandExecutor errorGobbler = new LocalCommandExecutorImpl();

            ExecuteResult executeResult = errorGobbler.executeCommand(cmd, 1000);//1000ms=1s
            System.out.println("executeResult: " + executeResult.toString());
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
/*
2018-04-27 11:28:58,008 - INFO  - [main:LocalCommandExecutorImpl@33] - cmd.exe /C fmpp -S D:\tools\fmpp\docs\examples\qtour_step1\src\ -O D:\tools\fmpp\docs\examples\qtour_step1\out\
executeResult: ExecuteResult(exitCode=0, executeOut=- Copying: falcon.png
- Executing: index.html
- Executing: subdir\something.html

*** DONE ***

2 executed + 0 rendered + 1 copied = 3 successfully processed
0 failed, 0 warning(s)
Time elapsed: 0.063 seconds

)
 */