package com.gmail.mosoft521.env;

import java.util.Iterator;
import java.util.Properties;

public class SystemProperty {
    public static void main(String[] args) {
//        System.out.println("java_vendor:" + System.getProperty("java.vendor"));
//        System.out.println("java_vendor_url:" + System.getProperty("java.vendor.url"));
//        System.out.println("java_home:" + System.getProperty("java.home"));
//        System.out.println("java_class_version:" + System.getProperty("java.class.version"));
//        System.out.println("java_class_path:" + System.getProperty("java.class.path"));
//        System.out.println("os_name:" + System.getProperty("os.name"));
//        System.out.println("os_arch:" + System.getProperty("os.arch"));
//        System.out.println("os_version:" + System.getProperty("os.version"));
//        System.out.println("user_name:" + System.getProperty("user.name"));
//        System.out.println("user_home:" + System.getProperty("user.home"));
//        System.out.println("user_dir:" + System.getProperty("user.dir"));
//        System.out.println("java_vm_specification_version:" + System.getProperty("java.vm.specification.version"));
//        System.out.println("java_vm_specification_vendor:" + System.getProperty("java.vm.specification.vendor"));
//        System.out.println("java_vm_specification_name:" + System.getProperty("java.vm.specification.name"));
//        System.out.println("java_vm_version:" + System.getProperty("java.vm.version"));
//        System.out.println("java_vm_vendor:" + System.getProperty("java.vm.vendor"));
//        System.out.println("java_vm_name:" + System.getProperty("java.vm.name"));
//        System.out.println("java_ext_dirs:" + System.getProperty("java.ext.dirs"));
//        System.out.println("file_separator:" + System.getProperty("file.separator"));
//        System.out.println("path_separator:" + System.getProperty("path.separator"));
//        System.out.println("line_separator:" + System.getProperty("line.separator"));
//        System.out.println("---------------------------------------------------------------------------");
        Properties props = System.getProperties();
        Iterator iter = props.keySet().iterator();
        while (iter.hasNext()) {
            String key = (String) iter.next();
            System.out.println(key + " = " + props.get(key));
        }
    }
}
/*
C:\tools\Java\jdk1.8.0_161\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2018.1.5\lib\idea_rt.jar=57317:C:\Program Files\JetBrains\IntelliJ IDEA 2018.1.5\bin" -Dfile.encoding=UTF-8 -classpath C:\tools\Java\jdk1.8.0_161\jre\lib\charsets.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\deploy.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\ext\access-bridge-64.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\ext\cldrdata.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\ext\dnsns.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\ext\jaccess.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\ext\jfxrt.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\ext\localedata.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\ext\nashorn.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\ext\sunec.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\ext\sunjce_provider.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\ext\sunmscapi.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\ext\sunpkcs11.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\ext\zipfs.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\javaws.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\jce.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\jfr.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\jfxswt.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\jsse.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\management-agent.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\plugin.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\resources.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\rt.jar;E:\ws_ij_alvin\alvinSample\target\classes;E:\java\repository\org\slf4j\slf4j-api\1.7.20\slf4j-api-1.7.20.jar;E:\java\repository\org\apache\zookeeper\zookeeper\3.4.8\zookeeper-3.4.8.jar;E:\java\repository\org\slf4j\slf4j-log4j12\1.6.1\slf4j-log4j12-1.6.1.jar;E:\java\repository\log4j\log4j\1.2.16\log4j-1.2.16.jar;E:\java\repository\jline\jline\0.9.94\jline-0.9.94.jar;E:\java\repository\io\netty\netty\3.7.0.Final\netty-3.7.0.Final.jar;E:\java\repository\org\apache\curator\curator-recipes\2.10.0\curator-recipes-2.10.0.jar;E:\java\repository\org\apache\curator\curator-framework\2.10.0\curator-framework-2.10.0.jar;E:\java\repository\org\apache\curator\curator-client\2.10.0\curator-client-2.10.0.jar;E:\java\repository\org\apache\curator\curator-test\2.10.0\curator-test-2.10.0.jar;E:\java\repository\org\javassist\javassist\3.18.1-GA\javassist-3.18.1-GA.jar;E:\java\repository\org\apache\commons\commons-math\2.2\commons-math-2.2.jar;E:\java\repository\com\google\guava\guava\16.0.1\guava-16.0.1.jar;E:\java\repository\org\apache\commons\commons-lang3\3.4\commons-lang3-3.4.jar;E:\java\repository\commons-io\commons-io\1.3.2\commons-io-1.3.2.jar;E:\java\repository\commons-net\commons-net\3.5\commons-net-3.5.jar;E:\java\repository\joda-time\joda-time\2.9.9\joda-time-2.9.9.jar;E:\java\repository\it\Unimi\dsi\fastutil\7.0.12\fastutil-7.0.12.jar;E:\java\repository\com\alibaba\fastjson\1.2.28\fastjson-1.2.28.jar;E:\java\repository\com\google\code\gson\gson\2.8.0\gson-2.8.0.jar;E:\java\repository\org\apache\poi\poi\3.16\poi-3.16.jar;E:\java\repository\commons-codec\commons-codec\1.10\commons-codec-1.10.jar;E:\java\repository\org\apache\commons\commons-collections4\4.1\commons-collections4-4.1.jar;E:\java\repository\org\apache\poi\poi-scratchpad\3.16\poi-scratchpad-3.16.jar;E:\java\repository\org\apache\poi\poi-ooxml\3.16\poi-ooxml-3.16.jar;E:\java\repository\org\apache\poi\poi-ooxml-schemas\3.16\poi-ooxml-schemas-3.16.jar;E:\java\repository\org\apache\xmlbeans\xmlbeans\2.6.0\xmlbeans-2.6.0.jar;E:\java\repository\stax\stax-api\1.0.1\stax-api-1.0.1.jar;E:\java\repository\com\github\virtuald\curvesapi\1.04\curvesapi-1.04.jar;E:\java\repository\net\sf\jasperreports\jasperreports\5.2.0\jasperreports-5.2.0.jar;E:\java\repository\commons-beanutils\commons-beanutils\1.8.0\commons-beanutils-1.8.0.jar;E:\java\repository\commons-collections\commons-collections\2.1\commons-collections-2.1.jar;E:\java\repository\commons-digester\commons-digester\2.1\commons-digester-2.1.jar;E:\java\repository\commons-logging\commons-logging\1.1.1\commons-logging-1.1.1.jar;E:\java\repository\jfree\jcommon\1.0.15\jcommon-1.0.15.jar;E:\java\repository\jfree\jfreechart\1.0.12\jfreechart-1.0.12.jar;E:\java\repository\xml-apis\xml-apis\1.3.02\xml-apis-1.3.02.jar;E:\java\repository\eclipse\jdtcore\3.1.0\jdtcore-3.1.0.jar;E:\java\repository\org\codehaus\castor\castor\1.2\castor-1.2.jar;E:\java\repository\com\fasterxml\jackson\core\jackson-core\2.0.5\jackson-core-2.0.5.jar;E:\java\repository\com\fasterxml\jackson\core\jackson-databind\2.0.5\jackson-databind-2.0.5.jar;E:\java\repository\com\fasterxml\jackson\core\jackson-annotations\2.0.5\jackson-annotations-2.0.5.jar;E:\java\repository\com\lowagie\itext\2.1.7\itext-2.1.7.jar;E:\java\repository\bouncycastle\bcmail-jdk14\138\bcmail-jdk14-138.jar;E:\java\repository\bouncycastle\bcprov-jdk14\138\bcprov-jdk14-138.jar;E:\java\repository\org\bouncycastle\bctsp-jdk14\1.38\bctsp-jdk14-1.38.jar;E:\java\repository\org\bouncycastle\bcprov-jdk14\1.38\bcprov-jdk14-1.38.jar;E:\java\repository\org\bouncycastle\bcmail-jdk14\1.38\bcmail-jdk14-1.38.jar;E:\java\repository\com\lowagie\itextasian\2.1.7\itextasian-2.1.7.jar;E:\java\repository\com\lowagie\itextasiancmaps\2.1.7\itextasiancmaps-2.1.7.jar;E:\java\repository\org\apache\httpcomponents\httpclient\4.3.6\httpclient-4.3.6.jar;E:\java\repository\org\apache\httpcomponents\httpcore\4.3.3\httpcore-4.3.3.jar;E:\java\repository\org\projectlombok\lombok\1.16.20\lombok-1.16.20.jar com.gmail.mosoft521.env.SystemProperty
java.runtime.name = Java(TM) SE Runtime Environment
sun.boot.library.path = C:\tools\Java\jdk1.8.0_161\jre\bin
java.vm.version = 25.161-b12
java.vm.vendor = Oracle Corporation
java.vendor.url = http://java.oracle.com/
path.separator = ;
java.vm.name = Java HotSpot(TM) 64-Bit Server VM
file.encoding.pkg = sun.io
user.country = CN
user.script =
sun.java.launcher = SUN_STANDARD
sun.os.patch.level =
java.vm.specification.name = Java Virtual Machine Specification
user.dir = E:\ws_ij_alvin\alvinSample
java.runtime.version = 1.8.0_161-b12
java.awt.graphicsenv = sun.awt.Win32GraphicsEnvironment
java.endorsed.dirs = C:\tools\Java\jdk1.8.0_161\jre\lib\endorsed
os.arch = amd64
java.io.tmpdir = C:\Users\Alvin\AppData\Local\Temp\
line.separator =

java.vm.specification.vendor = Oracle Corporation
user.variant =
os.name = Windows 10
sun.jnu.encoding = GBK
java.library.path = C:\tools\Java\jdk1.8.0_161\bin;C:\Windows\Sun\Java\bin;C:\Windows\system32;C:\Windows;C:\Program Files\Docker\Docker\Resources\bin;C:\ProgramData\Oracle\Java\javapath;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem;C:\Windows\System32\WindowsPowerShell\v1.0\;C:\tools\Java\jdk1.8.0_161\bin;C:\tools\Java\jdk1.8.0_161\jre\bin;C:\tools\maven\apache-maven-3.5.2\bin;D:\Program Files\TortoiseGit\bin;D:\Program Files\TortoiseSVN\bin;d:\tools\erl9.3\bin;D:\tools\RabbitMQServer\rabbitmq_server-3.7.5\sbin;C:\Users\Alvin\AppData\Local\Microsoft\WindowsApps;;.
java.specification.name = Java Platform API Specification
java.class.version = 52.0
sun.management.compiler = HotSpot 64-Bit Tiered Compilers
os.version = 10.0
user.home = C:\Users\Alvin
user.timezone =
java.awt.printerjob = sun.awt.windows.WPrinterJob
file.encoding = UTF-8
java.specification.version = 1.8
java.class.path = C:\tools\Java\jdk1.8.0_161\jre\lib\charsets.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\deploy.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\ext\access-bridge-64.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\ext\cldrdata.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\ext\dnsns.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\ext\jaccess.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\ext\jfxrt.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\ext\localedata.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\ext\nashorn.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\ext\sunec.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\ext\sunjce_provider.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\ext\sunmscapi.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\ext\sunpkcs11.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\ext\zipfs.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\javaws.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\jce.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\jfr.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\jfxswt.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\jsse.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\management-agent.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\plugin.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\resources.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\rt.jar;E:\ws_ij_alvin\alvinSample\target\classes;E:\java\repository\org\slf4j\slf4j-api\1.7.20\slf4j-api-1.7.20.jar;E:\java\repository\org\apache\zookeeper\zookeeper\3.4.8\zookeeper-3.4.8.jar;E:\java\repository\org\slf4j\slf4j-log4j12\1.6.1\slf4j-log4j12-1.6.1.jar;E:\java\repository\log4j\log4j\1.2.16\log4j-1.2.16.jar;E:\java\repository\jline\jline\0.9.94\jline-0.9.94.jar;E:\java\repository\io\netty\netty\3.7.0.Final\netty-3.7.0.Final.jar;E:\java\repository\org\apache\curator\curator-recipes\2.10.0\curator-recipes-2.10.0.jar;E:\java\repository\org\apache\curator\curator-framework\2.10.0\curator-framework-2.10.0.jar;E:\java\repository\org\apache\curator\curator-client\2.10.0\curator-client-2.10.0.jar;E:\java\repository\org\apache\curator\curator-test\2.10.0\curator-test-2.10.0.jar;E:\java\repository\org\javassist\javassist\3.18.1-GA\javassist-3.18.1-GA.jar;E:\java\repository\org\apache\commons\commons-math\2.2\commons-math-2.2.jar;E:\java\repository\com\google\guava\guava\16.0.1\guava-16.0.1.jar;E:\java\repository\org\apache\commons\commons-lang3\3.4\commons-lang3-3.4.jar;E:\java\repository\commons-io\commons-io\1.3.2\commons-io-1.3.2.jar;E:\java\repository\commons-net\commons-net\3.5\commons-net-3.5.jar;E:\java\repository\joda-time\joda-time\2.9.9\joda-time-2.9.9.jar;E:\java\repository\it\Unimi\dsi\fastutil\7.0.12\fastutil-7.0.12.jar;E:\java\repository\com\alibaba\fastjson\1.2.28\fastjson-1.2.28.jar;E:\java\repository\com\google\code\gson\gson\2.8.0\gson-2.8.0.jar;E:\java\repository\org\apache\poi\poi\3.16\poi-3.16.jar;E:\java\repository\commons-codec\commons-codec\1.10\commons-codec-1.10.jar;E:\java\repository\org\apache\commons\commons-collections4\4.1\commons-collections4-4.1.jar;E:\java\repository\org\apache\poi\poi-scratchpad\3.16\poi-scratchpad-3.16.jar;E:\java\repository\org\apache\poi\poi-ooxml\3.16\poi-ooxml-3.16.jar;E:\java\repository\org\apache\poi\poi-ooxml-schemas\3.16\poi-ooxml-schemas-3.16.jar;E:\java\repository\org\apache\xmlbeans\xmlbeans\2.6.0\xmlbeans-2.6.0.jar;E:\java\repository\stax\stax-api\1.0.1\stax-api-1.0.1.jar;E:\java\repository\com\github\virtuald\curvesapi\1.04\curvesapi-1.04.jar;E:\java\repository\net\sf\jasperreports\jasperreports\5.2.0\jasperreports-5.2.0.jar;E:\java\repository\commons-beanutils\commons-beanutils\1.8.0\commons-beanutils-1.8.0.jar;E:\java\repository\commons-collections\commons-collections\2.1\commons-collections-2.1.jar;E:\java\repository\commons-digester\commons-digester\2.1\commons-digester-2.1.jar;E:\java\repository\commons-logging\commons-logging\1.1.1\commons-logging-1.1.1.jar;E:\java\repository\jfree\jcommon\1.0.15\jcommon-1.0.15.jar;E:\java\repository\jfree\jfreechart\1.0.12\jfreechart-1.0.12.jar;E:\java\repository\xml-apis\xml-apis\1.3.02\xml-apis-1.3.02.jar;E:\java\repository\eclipse\jdtcore\3.1.0\jdtcore-3.1.0.jar;E:\java\repository\org\codehaus\castor\castor\1.2\castor-1.2.jar;E:\java\repository\com\fasterxml\jackson\core\jackson-core\2.0.5\jackson-core-2.0.5.jar;E:\java\repository\com\fasterxml\jackson\core\jackson-databind\2.0.5\jackson-databind-2.0.5.jar;E:\java\repository\com\fasterxml\jackson\core\jackson-annotations\2.0.5\jackson-annotations-2.0.5.jar;E:\java\repository\com\lowagie\itext\2.1.7\itext-2.1.7.jar;E:\java\repository\bouncycastle\bcmail-jdk14\138\bcmail-jdk14-138.jar;E:\java\repository\bouncycastle\bcprov-jdk14\138\bcprov-jdk14-138.jar;E:\java\repository\org\bouncycastle\bctsp-jdk14\1.38\bctsp-jdk14-1.38.jar;E:\java\repository\org\bouncycastle\bcprov-jdk14\1.38\bcprov-jdk14-1.38.jar;E:\java\repository\org\bouncycastle\bcmail-jdk14\1.38\bcmail-jdk14-1.38.jar;E:\java\repository\com\lowagie\itextasian\2.1.7\itextasian-2.1.7.jar;E:\java\repository\com\lowagie\itextasiancmaps\2.1.7\itextasiancmaps-2.1.7.jar;E:\java\repository\org\apache\httpcomponents\httpclient\4.3.6\httpclient-4.3.6.jar;E:\java\repository\org\apache\httpcomponents\httpcore\4.3.3\httpcore-4.3.3.jar;E:\java\repository\org\projectlombok\lombok\1.16.20\lombok-1.16.20.jar;C:\Program Files\JetBrains\IntelliJ IDEA 2018.1.5\lib\idea_rt.jar
user.name = Alvin
java.vm.specification.version = 1.8
sun.java.command = com.gmail.mosoft521.env.SystemProperty
java.home = C:\tools\Java\jdk1.8.0_161\jre
sun.arch.data.model = 64
user.language = zh
java.specification.vendor = Oracle Corporation
awt.toolkit = sun.awt.windows.WToolkit
java.vm.info = mixed mode
java.version = 1.8.0_161
java.ext.dirs = C:\tools\Java\jdk1.8.0_161\jre\lib\ext;C:\Windows\Sun\Java\lib\ext
sun.boot.class.path = C:\tools\Java\jdk1.8.0_161\jre\lib\resources.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\rt.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\sunrsasign.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\jsse.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\jce.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\charsets.jar;C:\tools\Java\jdk1.8.0_161\jre\lib\jfr.jar;C:\tools\Java\jdk1.8.0_161\jre\classes
java.vendor = Oracle Corporation
file.separator = \
java.vendor.url.bug = http://bugreport.sun.com/bugreport/
sun.io.unicode.encoding = UnicodeLittle
sun.cpu.endian = little
sun.desktop = windows
sun.cpu.isalist = amd64

Process finished with exit code 0

 */