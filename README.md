# JavaWeb-Springboot
使用Springboot开发java Web  

### 一、环境

环境 `IDEA` `Spring Boot` `gradle` `mybatis` `JDK别忘了`<br>

[IDEA spring boot gradle mybatis环境搭建](https://github.com/DerekYanJ/JavaWeb-Springboot/raw/master/doc/%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA%E5%92%8C%E6%96%B0%E5%BB%BA%E4%B8%80%E4%B8%AASpringboot%2Bgradle%2Bmybatis%E9%A1%B9%E7%9B%AE.docx])  

JDK java运行环境 移步：[JDK](https://www.oracle.com/technetwork/java/javase/downloads/index.html) (英文)  
Spring Boot 移步：[Spring Boot](https://spring.io/projects/spring-boot/) (英文)  
Gradle 移步：[Gradle](https://gradle.org/) (英文)  
mybatis 移步： [mybatis](http://www.mybatis.org/mybatis-3/zh/index.html) (中文)  


### 二、工程目录结构
图片有点高凑活看吧<img src="https://github.com/DerekYanJ/JavaWeb-Springboot/blob/master/img/img1.png" width="200"/>  
* 工程包名 
* 基类 —— MVC的基类、`ApiResponse` (统一返回格式)
* 实体类
  * dao层 —— 通过 `mybatis` 注解连接数据库
* 控制层 —— url映射、请求参数处理、调用service
* 服务层 —— 业务逻辑处理、调用dao层
* springboot框架 —— 关于springboot框架的操作
  * 注解 —— 自定义的注解
  * 切面 —— 目前用于获取请求的详细数据和返回结果
  * 统一异常处理 —— 捕获的异常都会经过，然后做响应处理
  * 拦截器 —— 拦截请求，校验token
  * redis —— redis配置
  * token里只有一个注解类，应该放入第一个注解 `annotation` 包下
* 辅助类 —— 存放辅助类的包
* MainApplication —— springboot启动入口，自动生成，可以添加一些配置
* ServletInitializer —— spring web做启动初始化工作
* static 静态文件 —— 存放静态文件的地方
* 模板 —— 前端页面
* 配置文件 `application.properties`
* log配置 `logback-spring.xml`

* buld.gradle —— 整个工程的配置文件（引入插件、第三方库）
