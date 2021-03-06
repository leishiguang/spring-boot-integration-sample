

## 项目说明

- **spring boot 集成组件脚手架**


## 功能说明

**主要的功能：**
1. 配置加密，自动更新配置文件，将明文配置替换为加密后的配置；
2. 已内嵌数据库，无需部署、配置额外的数据库；
3. 已集成数据库版本升级工具，无需进行手动维护数据库版本；
4. 日志以每日的形式保存在 jar 所在目录，并自动压缩；


## 工程目录结构

- ops-web: 程序运行入口，包含 config、properties、yml、数据库默认数据等；
- ops-sys: 系统框架，如登录、用户管理、监控等，也包含 LayUI 和其它一些前端组件、静态资源；
- ops-core: 通用组件与核心组件，如 Util 工具包、异常类、中间件等；
- ops-work: 具体业务逻辑，如清理登簿、执行 sql 查询规则、导出数据等； 

注：
1. 每个包均有自己的数据库表结构，有自己的 mapper ，尽量避免不同包之间的 mapper 交叉；
2. 不同包之间尽量使用接口进行数据交互；

## 启动说明

可以按以下方式运行：

1、开发环境

- 从根目录 pom.xml 导入 Maven 包，通过 ops-web 子包中的 Application 即启动项目。

2、打包后运行在生产环境

- 从 maven root 工程执行 package 即可完成打包，将会在 ops-web 的 target 目录生成 operations-box-20190404-012820.jar 文件
- 使用 java -jar 命令启动，或者双击根目录脚本 starup.bat 启动本项目。

## 测试说明

从 com.supermap.ApplicationTest 可执行所有测试。各模块均有对应的模块测试类，执行这些类即可完成对当前模块的测试。

- 比如 ops-core 的 com.supermap.core.OpsCoreTest 类；
- 比如 ops-web 的 com.supermap.web.OpsWebTest 类；
- 比如 ops-sys 的 com.supermap.sys.OpsSysTest 类；

## 更新说明

2019年6月1日：项目已暂停
2019年4月1日：创建初始版本
