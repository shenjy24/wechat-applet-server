### 启动服务

1.执行`resources/sql/schema.sql`脚本，创建数据表；

2.数据表`wechat_secret`保存应用对应的密钥，从微信公众平台获取并插入相应数据；

3.启动`Application`服务类。