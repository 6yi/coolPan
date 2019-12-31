## coolPan  V1.1
   自建私有云盘管理~~

  springBoot练手项目



# 启动前准备

1.准备好mysql以及Maven和java环境

先创建好数据库

```mysql
CREATE DATABASE coolpan;
```

使用springboot的schema建表(推荐)  ,修改项目内的schema.sql

或者自行建表

```mysql
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `savefilename` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `maxsize` int(11) DEFAULT NULL,
  `nowsize` int(11) DEFAULT NULL,
  `emial` varchar(100) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8

CREATE TABLE `files` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountid` int(11) DEFAULT NULL,
  `filename` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `filepath` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `filetype` int(11) DEFAULT NULL,
  `size` varchar(40) DEFAULT NULL,
  `time` varchar(100) DEFAULT NULL,
  `ispublic` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_id_type` (`accountid`,`filetype`),
  KEY `idx_ispublic_id_type` (`accountid`,`ispublic`,`filetype`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8
```



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



# 启动方式

### Docker启动

#### 一.maven的docker插件启动

填写pom文件

```xml
 <plugin>
     <groupId>com.spotify</groupId>
     <artifactId>docker-maven-plugin</artifactId>
     <version>1.2.0</version>
     <configuration>
         <imageName>lzheng/${project.artifactId}</imageName>
         <baseImage>java:8</baseImage>
         <volumes>宿主机储存地址:配置信息所填地址</volumes>
         <entryPoint>["java", "-jar", "/${project.build.finalName}.jar"]</entryPoint>
         <resources>
             <resource>
                 <targetPath>/</targetPath>
                 <directory>${project.build.directory}</directory>
                 <include>${project.build.finalName}.jar</include>
             </resource>
         </resources>
     </configuration>
  </plugin>
```

把整个项目放到服务器内,进入项目目录

```shell
#构建镜像
mvn clean package docker:build -Dmaven.test.skip=true
#启动容器
docker run -itd  -p 需要暴露的端口:配置信息所填的端口 镜像名
```



#### 二.dockerfile文件启动

使用dockefile文件启动,将dockerfile文件于maven打包好的jar包放一起

```
FROM java:8
VOLUME 宿主机保存路径:配置文件所填的保存路径
COPY coolpan-1.1-SNAPSHOT.jar app.jar
ENTRYPOINT ['java','-jar','app.jar']
```

```
#打包镜像
docker build -t coolpan:1.0 .
#启动
docker run -d -p 你要暴露的端口:配置信息所填的端口 镜像id
```



### 直接用Maven启动

```
用Maven打包成jar即可直接启动,也可以把配置文件放到jar同文件下
java -jar xxx.jar
```

### Demo

http://59.110.173.180:8089

账户:demo123

密码:demo123











## 说明

1.0版本,Admin管理页面还没写,等期末考完试再说吧~





## LICENSE
MIT license.



