package com.baixiaowen.javaconcurrencyprogramming.threadcoreknowledge线程8大核心基础知识.aaaaaa;

import java.io.*;

public class QinYanKai {

    public static void main(String[] args) throws IOException {
        File file = new File("秦延凯.txt");

        file.createNewFile();

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        
        String str = "";

        for (int i = 0; i <= 1000000; i++) {
            str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Document><Msg><RequestID>"  + i + "</RequestID><OriginateOrgCode>42000000010001</OriginateOrgCode><OriginateUserCode>12</OriginateUserCode><Name>李冰心5172</Name><IDType>10</IDType><IDNum>李冰心5172</IDNum><QueryReason>01</QueryReason><ServiceCode>FW_TZBL_0001</ServiceCode><ProductDate>2019-12-31</ProductDate></Msg></Document>";
            writer.write(str);
            writer.write("\r\n");
        }

        writer.flush();

        writer.close();
        
    }
    
}
