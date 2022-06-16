import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Controller {

    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);//获取年份
        int month=cal.get(Calendar.MONTH);//获取月份
        int day=cal.get(Calendar.DATE);//获取日
        int hour=cal.get(Calendar.HOUR);//小时
        int minute=cal.get(Calendar.MINUTE);//分
        int second=cal.get(Calendar.SECOND);//秒
        int WeekOfYear = cal.get(Calendar.DAY_OF_WEEK);//一周的第几天
        System.out.println("现在的时间是："+year+"年"+month+"月"+day+"日"+hour+"时"+minute+"分"+second+"秒       星期"+(WeekOfYear-1));



        int []a = {1,2,3,4,5,6,7,8,9,10,11,12,13};
        Algorithm algorithm = new Algorithm();
        algorithm.biSearch(a,6);



        int [] b ={3,2,1,5,6,4};
        algorithm.findKthLargest(b,2);






    }


}


