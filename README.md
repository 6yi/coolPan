## coolPan  V1.0
   自建私有云盘管理~~

  springBoot练手项目



# 启动前准备

1.准备好mysql以及Maven和java环境

2.根据自己的服务器环境修改配置文件

```properties
#端口
server.port=8088
#文件保存地址
file.SavePath=X:/Save/  
#单次上传文件最大值
spring.servlet.multipart.max-file-size=1600MB
spring.servlet.multipart.max-request-size=1600MB
#每个用户的初始空间大小(单位MB)
account.maxsize=5000

#是否开放注册0或1
account.signup=0    

#数据库
mybatis.mapper-locations=classpath*:mapper/*.xml
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/coolpan?serverTimezone=GMT
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=1234
mybatis.type-aliases-package=com.lzheng.coolpan.domain

#####激活邮件
spring.mail.properties.mail.smtp.auth=true
spring.mail.host=smtp.qq.com
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
spring.mail.username=XXXXX@foxmail.com
#这个密码为授权码
spring.mail.password=XXXXXXX
spring.mail.default-encoding=UTF-8
#发送者别名
mail.from=CoolPan
#邮件标题
mail.subject=激活链接
#邮件正文
mail.content=感谢您的注册,以下是您的激活链接:\n
#您服务器的域名或者公网IP
mail.address=http://X.X.X.X:10086
#####激活邮件
```



### 启动方式

```
用Maven打包成jar即可直接启动
java -jar xxx.jar
```

### Demo

http://59.110.173.180:8089

账户:demo123

密码:demo123











## 说明

1.0版本,Admin管理页面还没写,等期末考完试再说吧~









