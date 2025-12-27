# GDUE-SMS 学生管理系统

一个基于前后端分离架构的学生信息管理系统，采用 Spring Boot + Vue 3 技术栈开发。

## 项目简介

GDUE-SMS（学生管理系统）是一个现代化的Web应用，用于管理学生基本信息和专业信息。系统提供完整的CRUD功能，支持分页查询、条件筛选等特性。

## 技术栈

### 后端
- Java 25
- Spring Boot 3.5.9
- MyBatis 3.0.5
- MySQL 8.0+
- PageHelper 1.4.7（分页插件）
- Lombok

### 前端
- Vue 3.2.38
- Vue Router 4.1.5
- Element Plus 2.4.4
- Axios 1.7.2
- Vite 7.2.4

## 项目结构

```
GDUE-SMS/
├── SMSBackend/              # 后端项目
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/kinkou/
│   │   │   │   ├── controller/   # 控制器层
│   │   │   │   ├── service/      # 业务逻辑层
│   │   │   │   ├── mapper/       # 数据访问层
│   │   │   │   └── pojo/        # 实体类
│   │   │   └── resources/
│   │   │       └── application.yml
│   └── pom.xml
├── smsfrontend/             # 前端项目
│   ├── src/
│   │   ├── api/            # API接口封装
│   │   ├── views/          # 页面组件
│   │   ├── router/         # 路由配置
│   │   ├── utils/          # 工具类
│   │   └── main.js
│   └── package.json
├── 需求文档.md
├── 技术架构文档.md
└── 接口文档.md
```

## 功能特性

### 学生管理
- 学生信息增删改查
- 按姓名模糊查询
- 按专业筛选
- 分页展示
- 表单验证

### 专业管理
- 专业信息增删改查
- 按名称模糊查询
- 分页展示
- 表单验证

## 快速开始

### 环境要求
- JDK 25
- Node.js 20.19.0 或 22.12.0+
- MySQL 8.0+
- Maven 3.6+

### 数据库配置

1. 根据[GDUE-SMS.sql](./GDUE-SMS.sql)在您的MySQL建立数据库与表，并插入样本数据。

2. 修改数据库配置：
编辑 `SMSBackend/src/main/resources/application.yml` 文件，修改数据库连接信息，使用户名与密码信息与您主机的MySQL设定的的一致。

### 后端启动

```bash
cd SMSBackend
mvn spring-boot:run
```

后端服务将在 `http://localhost:8080` 启动。

### 前端启动

```bash
cd smsfrontend
npm install
npm run dev
```

前端服务将在 `http://localhost:5173` 启动。

## 接口文档

详细的API接口文档请查看 [接口文档.md](./接口文档.md)

## 技术文档

- [需求文档.md](./需求文档.md) - 项目需求说明
- [技术架构文档.md](./技术架构文档.md) - 技术架构设计
- [接口文档.md](./接口文档.md) - API接口文档

## 开发说明

### 后端开发
- Controller层：处理HTTP请求，参数校验
- Service层：业务逻辑处理
- Mapper层：数据库操作

### 前端开发
- 使用Vue 3 Composition API
- Element Plus组件库
- Axios进行HTTP请求
- Vue Router管理路由

## 许可证

MIT License

## 联系方式

如有问题，请提交Issue或联系开发团队。
