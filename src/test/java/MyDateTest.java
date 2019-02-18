import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class MyDateTest {
    public static void main(String[] args) {
        //获取时间加一年或加一月或加一天
       Date data= new Date();
//        Calendar instance = Calendar.getInstance();
//        instance.setTime(data);
//        System.out.println(instance.getTime());
//
//        instance.add(Calendar.DATE,10);
//        instance.add(Calendar.YEAR,1);
//        System.out.println(instance.getTime());
//        Calendar c = Calendar.getInstance();//获取一个日历实例
//
//        c.set(2018, 12, 2);//设定日历的日期
//        data = c.getTime();
//        System.out.println(data);
//        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
//        String format = sf.format(data);
//        System.out.println(format);

        // 字符串转换日期格式
        // DateFormat fmtDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 接收传入参数
        // String strDate = args[1];
        // 得到日期格式对象
        // Date date = fmtDateTime.parse(strDate);

        // 完整显示今天日期时间
        String str = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
        System.out.println(str);

        // 创建 Calendar 对象
        Calendar calendar = Calendar.getInstance();

        try
        {
            // 对 calendar 设置时间的方法
            // 设置传入的时间格式
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
            // 指定一个日期
            Date date = dateFormat.parse("2018-12-2");
            // 对 calendar 设置为 date 所定的日期
            calendar.setTime(date);

            // 按特定格式显示刚设置的时间
            str = (new SimpleDateFormat("yyyy-MM-dd")).format(calendar.getTime());
            System.out.println(str);
        }catch (ParseException e){
            e.printStackTrace();
        }


    }


}
