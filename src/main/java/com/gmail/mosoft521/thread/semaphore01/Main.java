package com.gmail.mosoft521.thread.semaphore01;

//15. 实现例子的main类，创建名为 Main的类并实现main()方法。
public class Main {
    public static void main(String args[]) {

        //16. 创建PrintQueue对象名为printQueue。
        PrintQueue printQueue = new PrintQueue();

        //17. 创建10个threads。每个线程会执行一个发送文档到print queue的Job对象。
        Thread thread[] = new Thread[10];
        for (int i = 0; i < 10; i++) {
            thread[i] = new Thread(new Job(printQueue), "Thread" + i);
        }

        //18. 最后，开始这10个线程们。
        for (int i = 0; i < 10; i++) {
            thread[i].start();
        }
    }
}
/*
Thread0: Going to print a job
Thread6: Going to print a job
Thread0: PrintQueue: Printing a Job during 2 seconds
Thread2: Going to print a job
Thread7: Going to print a job
Thread3: Going to print a job
Thread5: Going to print a job
Thread8: Going to print a job
Thread9: Going to print a job
Thread4: Going to print a job
Thread1: Going to print a job
Thread0: The document has been printed
Thread6: PrintQueue: Printing a Job during 7 seconds
Thread6: The document has been printed
Thread2: PrintQueue: Printing a Job during 9 seconds
Thread2: The document has been printed
Thread7: PrintQueue: Printing a Job during 1 seconds
Thread7: The document has been printed
Thread3: PrintQueue: Printing a Job during 7 seconds
Thread3: The document has been printed
Thread5: PrintQueue: Printing a Job during 8 seconds
Thread5: The document has been printed
Thread8: PrintQueue: Printing a Job during 3 seconds
Thread8: The document has been printed
Thread9: PrintQueue: Printing a Job during 5 seconds
Thread9: The document has been printed
Thread4: PrintQueue: Printing a Job during 7 seconds
Thread4: The document has been printed
Thread1: PrintQueue: Printing a Job during 1 seconds
Thread1: The document has been printed
-----------------------------
Thread0: Going to print a job
Thread9: Going to print a job
Thread7: Going to print a job
Thread8: Going to print a job
Thread6: Going to print a job
Thread5: Going to print a job
Thread3: Going to print a job
Thread4: Going to print a job
Thread1: Going to print a job
Thread2: Going to print a job
Thread0: PrintQueue: Printing a Job during 7 seconds
Thread0: The document has been printed
Thread9: PrintQueue: Printing a Job during 6 seconds
Thread9: The document has been printed
Thread7: PrintQueue: Printing a Job during 5 seconds
Thread7: The document has been printed
Thread8: PrintQueue: Printing a Job during 6 seconds
Thread8: The document has been printed
Thread6: PrintQueue: Printing a Job during 3 seconds
Thread6: The document has been printed
Thread5: PrintQueue: Printing a Job during 4 seconds
Thread5: The document has been printed
Thread3: PrintQueue: Printing a Job during 9 seconds
Thread3: The document has been printed
Thread4: PrintQueue: Printing a Job during 8 seconds
Thread4: The document has been printed
Thread1: PrintQueue: Printing a Job during 6 seconds
Thread1: The document has been printed
Thread2: PrintQueue: Printing a Job during 0 seconds
Thread2: The document has been printed
-----------------------------
Thread0: Going to print a job
Thread9: Going to print a job
Thread0: PrintQueue: Printing a Job during 1 seconds
Thread8: Going to print a job
Thread7: Going to print a job
Thread0: The document has been printed
Thread6: Going to print a job
Thread5: Going to print a job
Thread4: Going to print a job
Thread3: Going to print a job
Thread2: Going to print a job
Thread1: Going to print a job
Thread9: PrintQueue: Printing a Job during 9 seconds
Thread9: The document has been printed
Thread8: PrintQueue: Printing a Job during 3 seconds
Thread8: The document has been printed
Thread7: PrintQueue: Printing a Job during 8 seconds
Thread7: The document has been printed
Thread6: PrintQueue: Printing a Job during 9 seconds
Thread6: The document has been printed
Thread5: PrintQueue: Printing a Job during 0 seconds
Thread5: The document has been printed
Thread4: PrintQueue: Printing a Job during 5 seconds
Thread4: The document has been printed
Thread3: PrintQueue: Printing a Job during 3 seconds
Thread3: The document has been printed
Thread2: PrintQueue: Printing a Job during 1 seconds
Thread2: The document has been printed
Thread1: PrintQueue: Printing a Job during 0 seconds
Thread1: The document has been printed
 */