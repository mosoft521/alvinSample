package com.gmail.mosoft521.ujvmafbp.ch02;

public class SpinTest {
    void spin() {
        int i;
        for (i = 0; i < 100; i++) {
            ;//nop
        }
    }
}
/*
>javap -c SpinTest.class
Compiled from "SpinTest.java"
public class com.gmail.mosoft521.ujvmafbp.ch02.SpinTest {
  public com.gmail.mosoft521.ujvmafbp.ch02.SpinTest();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  void spin();
    Code:
       0: iconst_0              #将int类型0推送至栈顶
       1: istore_1              #将栈顶int类型数值存人第2个本地变量
       2: iload_1               #将第2个int类型本地变量推送至栈顶
       3: bipush        100     #将单字节的常量值(-128~127) 推送至栈顶
       5: if_icmpge     14      #比较栈顶两int类型数值大小，当前者大于等于后者时跳转
       8: iinc          1, 1    #将指定int类型变量增加指定值(i++，i--，i+=2)
      11: goto          2       #无条件跳转
      14: return                #从当前方法返回void
}
>javap -v -p SpinTest.class
  Last modified 2021-3-17; size 434 bytes
  MD5 checksum 796bf3285b4ae0ea503bb422dc428dc4
  Compiled from "SpinTest.java"
public class com.gmail.mosoft521.ujvmafbp.ch02.SpinTest
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #3.#17         // java/lang/Object."<init>":()V
   #2 = Class              #18            // com/gmail/mosoft521/ujvmafbp/ch02/SpinTest
   #3 = Class              #19            // java/lang/Object
   #4 = Utf8               <init>
   #5 = Utf8               ()V
   #6 = Utf8               Code
   #7 = Utf8               LineNumberTable
   #8 = Utf8               LocalVariableTable
   #9 = Utf8               this
  #10 = Utf8               Lcom/gmail/mosoft521/ujvmafbp/ch02/SpinTest;
  #11 = Utf8               spin
  #12 = Utf8               i
  #13 = Utf8               I
  #14 = Utf8               StackMapTable
  #15 = Utf8               SourceFile
  #16 = Utf8               SpinTest.java
  #17 = NameAndType        #4:#5          // "<init>":()V
  #18 = Utf8               com/gmail/mosoft521/ujvmafbp/ch02/SpinTest
  #19 = Utf8               java/lang/Object
{
  public com.gmail.mosoft521.ujvmafbp.ch02.SpinTest();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 3: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Lcom/gmail/mosoft521/ujvmafbp/ch02/SpinTest;

  void spin();
    descriptor: ()V
    flags:
    Code:
      stack=2, locals=2, args_size=1
         0: iconst_0
         1: istore_1
         2: iload_1
         3: bipush        100
         5: if_icmpge     14
         8: iinc          1, 1
        11: goto          2
        14: return
      LineNumberTable:
        line 6: 0
        line 9: 14
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      15     0  this   Lcom/gmail/mosoft521/ujvmafbp/ch02/SpinTest;
            2      13     1     i   I
      StackMapTable: number_of_entries = 2
        frame_type = 252 # append
          offset_delta = 2
                  locals = [ int ]
                  frame_type = 11 # same
}
 */