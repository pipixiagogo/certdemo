import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.File;
import java.io.FileInputStream;

public class Positions {
    private static final String QUERY_AREA_BY_LNG_AND_LAT = "{\n" +
            "\t\t$geoIntersects: {\n" +
            "        $geometry: {\n" +
            "                    \"type\" : 'Point',\n" +
            "                    \"coordinates\" : [%f,%f] }\n" +
            "        }\n" +
            "\t}";

    public static void main(String[] args)throws Exception {
        //4EE2 EE42 
       // B404 D041
       byte[]bytes=new byte[]{(byte)0x4E,(byte)0xE2,(byte)0xEE,(byte)0x42,(byte)0xB4,(byte)0x04,(byte)0xD0,(byte)0x41};
       byte[]time=new byte[]{(byte)0x5C,(byte)0x13,(byte)0x62,(byte)01};
//        FileInputStream fileInputStream = new FileInputStream(new File("D:/position.txt"));
//        fileInputStream.read()
        ByteBuf buf = Unpooled.wrappedBuffer(bytes);
//        float v = buf.readFloatLE();
        float v1 = buf.readFloatLE();
        boolean readable = buf.isReadable();
        System.out.println(readable);
        boolean writable = buf.isWritable();
        System.out.println(writable);
        ByteBuf buf1 = Unpooled.wrappedBuffer(time);
//        System.out.println(v);
//        System.out.println(v1);
        //1544774145
        long l = buf1.readInt();
        System.out.println(l);
  //      String format = String.format(QUERY_AREA_BY_LNG_AND_LAT, v, v1);
      //  System.out.println(format);
    }


}
