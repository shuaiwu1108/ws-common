# ws-common
> Spring boot Web开发自用组件  
> 避免重复造轮子，避免重复依赖的管理

## 集成的组件
- 使用AOP，实现controller请求、返回日志的记录
- 定义了全局异常处理
- 集成SpringDoc
- 集成Redis
- 集成MybatisPlus

## 工具类
- HttpUtil，基于java.net包实现的HTTP请求工具，支持GET、POST、SSL、非SSL请求
- JsoupUtil，比较主流的html、xml解析工具
- MinioUtil，进一步封装了minioAPI接口，简化操作流程
- RedisUtil，基于Springboot的redisTemplate类的封装，简化redis操作流程