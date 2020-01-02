package common.util;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Arrays;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;

/** 
* @date 2019-11-07 17:07:59
* @author LEI
* TODO
*/

public class EmailUtil {
	
	public static void main(String[] args) {
		MailAccount mailAccount = new MailAccount();
		mailAccount.setHost("smtp.exmail.qq.com");
		mailAccount.setPort(25);
//		mailAccount.setFrom("leijie@repeatlink.cn");
//		mailAccount.setUser("leijie@repeatlink.cn");
//		mailAccount.setPass("Lj46468546");
//		mailAccount.setFrom("test1@repeatlink.cn");
//		mailAccount.setUser("test1@repeatlink.cn");
//		mailAccount.setPass("REpeatlink1");
		mailAccount.setFrom("leijievip0@163.com");
		mailAccount.setUser("leijievip0@163.com");
		mailAccount.setPass("l46468546");
		mailAccount.setAuth(true);
		mailAccount.setCharset(Charset.forName("utf-8"));
		MailUtil.send(mailAccount, Arrays.asList("leijie@repeatlink.cn"), null, null, "标题", "内容", true, new File("d:/Copy.jpg"));
	}

}
