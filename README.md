# TableSync

MySQL 单表跨库同步工具 + 批量随机数据生成器。

## 功能特性
- **数据同步**：MySQL 单表跨库同步（目标库需提前建表）。
- **BatchInsert**：批量插入随机测试数据到 MySQL。
- **RandomValue**：生成随机个人信息（姓名、性别、生日、手机号、地址、邮箱等）。

## 技术栈
- Java + MySQL JDBC

## 使用方法
1. 克隆仓库并导入 IDE。
2. 确保目标 MySQL 数据库已建表。
3. 修改数据库连接信息（**注意：请勿提交真实凭证**，建议使用环境变量）。
4. 运行对应 main 方法。

## 项目结构
```
TableSync/
├── src/
│   ├── BatchInsert.java
│   ├── DataTransport.java
│   └── RandomValue.java
├── lib/ (MySQL Connector)
└── README.md
```

## 注意事项
- **安全提醒**：请移除硬编码密码，使用环境变量。
- 适合测试数据生成和简单数据迁移场景。

欢迎反馈改进建议！