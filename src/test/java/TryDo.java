import java.io.*;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TryDo {
    public static void main(String[] args) throws Exception {

//        FileOutputStream fileOutputStream = new FileOutputStream(new File("D:/text.txt"));
//         BufferedOutputStream buf=new BufferedOutputStream(fileOutputStream);
        FileWriter fileWriter = new FileWriter("D:/text.txt");
        BufferedWriter buf=new BufferedWriter(fileWriter);
        // 对 calendar 设置时间的方法
        // 设置传入的时间格式
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
        // 指定一个日期
        Date date = dateFormat.parse("2018-12-3");
        // 对 calendar 设置为 date 所定的日期
        calendar.setTime(date);
       for(int y=0;y<2;y++){
           if(y==0){
               buf.write("而金开始时间:"+dateFormat.format(calendar.getTime()));
               buf.newLine();
               //buf.write(("而金开始轮时间:     "+dateFormat.format(calendar.getTime())).getBytes());
               System.out.println("而金开始时间:"+dateFormat.format(calendar.getTime()));
               calendar.add(Calendar.DATE,10);
               calendar.add(Calendar.DATE,-1);
               buf.write("而金完成时间:"+dateFormat.format(calendar.getTime()));
               System.out.println("而金完成时间:"+dateFormat.format(calendar.getTime()));
               System.out.println();
               buf.newLine();
               buf.newLine();
           }
           if(y==1){
               calendar.add(Calendar.DATE,1);
               //buf.write(("而元开始轮时间:"+dateFormat.format(calendar.getTime())).getBytes());
               System.out.println("而元开始时间:"+dateFormat.format(calendar.getTime()));
               buf.write("而元开始时间:"+dateFormat.format(calendar.getTime()));
               buf.newLine();
               calendar.add(Calendar.DATE,10);
               calendar.add(Calendar.DATE,-1);
               //buf.write(("而元轮10天完时间:"+dateFormat.format(calendar.getTime())).getBytes());
               System.out.println("而元完成时间:"+dateFormat.format(calendar.getTime()));
               buf.write("而元开始时间:"+dateFormat.format(calendar.getTime()));
               buf.newLine();
               buf.newLine();
               System.out.println();
           }
       }
      for(int z=0;z<4;z++){

          buf.write("第"+(z+1)+"轮开始");
          buf.newLine();
          System.out.println("第"+(z+1)+"轮开始");
           for(int i=0;i<5;i++){
               if(i==i*5){
                   calendar.add(Calendar.DATE,1);
                   buf.write("松生开始时间:"+dateFormat.format(calendar.getTime()));
                   buf.newLine();
                   System.out.println("松生开始时间:"+dateFormat.format(calendar.getTime()));
                   calendar.add(Calendar.DATE,10);
                   calendar.add(Calendar.DATE,-1);
                   buf.write("松生完成时间:"+dateFormat.format(calendar.getTime()));
                   System.out.println("松生完成时间:"+dateFormat.format(calendar.getTime()));
                   buf.newLine();
                   System.out.println();
               }
               if(i+1==i*5+1){
                   calendar.add(Calendar.DATE,1);
                   buf.write("春生开始时间:"+dateFormat.format(calendar.getTime()));
                   buf.newLine();
                   System.out.println("春生开始时间:"+dateFormat.format(calendar.getTime()));
                   calendar.add(Calendar.DATE,10);
                   calendar.add(Calendar.DATE,-1);
                   buf.write("春生完成时间:"+dateFormat.format(calendar.getTime()));
                   buf.newLine();
                   System.out.println("春生完成时间:"+dateFormat.format(calendar.getTime()));
                   System.out.println();

               }
               if(i+2==i*5+2){
                   calendar.add(Calendar.DATE,1);
                   buf.write("而龙开始时间:"+dateFormat.format(calendar.getTime()));
                   buf.newLine();
                   System.out.println("而龙开始时间:"+dateFormat.format(calendar.getTime()));
                   calendar.add(Calendar.DATE,10);
                   calendar.add(Calendar.DATE,-1);
                   buf.write("而龙完成时间:"+dateFormat.format(calendar.getTime()));
                   buf.newLine();
                   System.out.println("而龙完成时间:"+dateFormat.format(calendar.getTime()));
                   System.out.println();
               }
               if(i+3==i*5+3){
                   calendar.add(Calendar.DATE,1);
                   buf.write("而金开始时间:"+dateFormat.format(calendar.getTime()));
                   buf.newLine();
                   System.out.println("而金开始时间:"+dateFormat.format(calendar.getTime()));
                   calendar.add(Calendar.DATE,10);
                   calendar.add(Calendar.DATE,-1);
                   buf.write("而金开始时间:"+dateFormat.format(calendar.getTime()));
                   buf.newLine();
                   System.out.println("而金完成时间:"+dateFormat.format(calendar.getTime()));
                   System.out.println();
               }
               if(i+4==i*5+4){
                   calendar.add(Calendar.DATE,1);
                   buf.write("而元开始时间:"+dateFormat.format(calendar.getTime()));
                   buf.newLine();
                   System.out.println("而元开始时间:"+dateFormat.format(calendar.getTime()));
                   calendar.add(Calendar.DATE,10);
                   calendar.add(Calendar.DATE,-1);
                   buf.write("而元完成时间:"+dateFormat.format(calendar.getTime()));
                   System.out.println("而元完成时间:"+dateFormat.format(calendar.getTime()));
                   System.out.println();
                   buf.newLine();
               }
           }
          buf.newLine();
       }
        buf.write("第5轮开始");
        buf.newLine();
       for(int i=0;i<6;i++){
          switch (i){
              case 0:
                  calendar.add(Calendar.DATE,1);
                  buf.write("松生开始时间:"+dateFormat.format(calendar.getTime()));
                  buf.newLine();
                  calendar.add(Calendar.DATE,10);
                  calendar.add(Calendar.DATE,-1);
                  buf.write("松生完成时间:"+dateFormat.format(calendar.getTime()));
                  buf.newLine();
                  break;
              case 1:
                  calendar.add(Calendar.DATE,1);
                  buf.write("春生开始时间:"+dateFormat.format(calendar.getTime()));
                  buf.newLine();
                  calendar.add(Calendar.DATE,10);
                  calendar.add(Calendar.DATE,-1);
                  buf.write("春生完成时间:"+dateFormat.format(calendar.getTime()));
                  buf.newLine();
                  break;
              case 2:
                  calendar.add(Calendar.DATE,1);
                  buf.write("而龙开始时间:"+dateFormat.format(calendar.getTime()));
                  buf.newLine();
                  calendar.add(Calendar.DATE,10);
                  calendar.add(Calendar.DATE,-1);
                  buf.write("而龙完成时间:"+dateFormat.format(calendar.getTime()));
                  buf.newLine();
                  break;
              case 3:
                  calendar.add(Calendar.DATE,1);
                  buf.write("而兰开始时间:"+dateFormat.format(calendar.getTime()));
                  buf.newLine();
                  calendar.add(Calendar.DATE,10);
                  calendar.add(Calendar.DATE,-1);
                  buf.write("而兰完成时间:"+dateFormat.format(calendar.getTime()));
                  buf.newLine();
                  break;
              case 4:
                  calendar.add(Calendar.DATE,1);
                  buf.write("而金开始时间:"+dateFormat.format(calendar.getTime()));
                  buf.newLine();
                  calendar.add(Calendar.DATE,10);
                  calendar.add(Calendar.DATE,-1);
                  buf.write("而金完成时间:"+dateFormat.format(calendar.getTime()));
                  buf.newLine();
                  break;
              case 5:
                  calendar.add(Calendar.DATE,1);
                  buf.write("而元开始时间:"+dateFormat.format(calendar.getTime()));
                  buf.newLine();
                  calendar.add(Calendar.DATE,10);
                  calendar.add(Calendar.DATE,-1);
                  buf.write("而元完成时间:"+dateFormat.format(calendar.getTime()));
                  break;
          }
       }
       buf.flush();
        buf.close();
        fileWriter.close();


       // calendar.add(Calendar.YEAR,1);

    }
}
