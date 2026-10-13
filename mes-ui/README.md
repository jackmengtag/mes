# MES 制造执行系统 — 管理端（mes-ui）

基于 **Vue 3 + Vite + Element Plus + vue-router 4 + pinia + axios** 搭建的 MES 管理端前端工程，
对接 RuoYi-Vue 3.9.2 架构的 `ruoyi-mes` 后端（Spring Boot，端口 `8080`，context-path 为 `/`）。

## 技术栈

| 依赖 | 版本 | 说明 |
| --- | --- | --- |
| Vue | ^3.5 | 组合式 API（`<script setup>`） |
| Vite | ^5.4 | 构建工具 |
| Element Plus | ^2.8 | UI 组件库（中文语言包） |
| vue-router | ^4.4 | 路由 |
| pinia | ^2.2 | 状态管理 |
| axios | ^1.7 | HTTP 请求 |

## 快速开始

```bash
# 1. 安装依赖
npm install

# 2. 启动开发服务（默认 http://localhost:80）
npm run dev

# 3. 打包生产产物（输出到 dist/）
npm run build

# 4. 本地预览打包结果
npm run preview
```

> 若 80 端口被占用，Vite 会自动切换到其它端口，请以控制台输出的地址为准。

## 后端地址说明

- 开发环境通过 Vite 代理转发请求：`/dev-api` → `http://localhost:8080`，并自动去掉 `/dev-api` 前缀。
- 代理配置见 `vite.config.js`；接口前缀由 `.env.development` 中的 `VITE_APP_BASE_API=/dev-api` 控制。
- 因此，**启动前端前请先启动 ruoyi-admin 后端（8080 端口）**。
- 若后端不在 `localhost:8080`，修改 `vite.config.js` 中 `proxy['/dev-api'].target` 即可。
- 生产打包使用 `.env.production` 中的 `VITE_APP_BASE_API=/prod-api`，部署时请由 Nginx 等网关将该前缀反向代理到后端。

## 默认账号

`admin / admin123`（若后端开启了验证码，登录页会自动加载并校验验证码）。

## 目录结构

```
mes-ui/
├── index.html
├── package.json
├── vite.config.js            # 别名、代理、构建配置
├── .env.development          # 开发环境变量（VITE_APP_BASE_API=/dev-api）
├── .env.production           # 生产环境变量
└── src/
    ├── main.js               # 应用入口：挂载 Element Plus / router / pinia
    ├── App.vue
    ├── router/index.js       # 路由（登录页 + 主布局 + 由资源配置自动生成的资源路由）
    ├── store/
    │   ├── index.js          # pinia 实例
    │   └── user.js           # 登录 / 获取用户信息 / 退出 / 权限判断
    ├── utils/
    │   ├── auth.js           # token 读写（localStorage）
    │   └── request.js        # axios 实例：token 注入、若依响应体处理、401 跳登录、blob 导出
    ├── config/
    │   └── resources.js      # ★ MES 资源配置（由后端 controller/domain 自动梳理）
    ├── layout/index.vue      # 左侧菜单 + 顶部栏 + 内容区
    └── views/
        ├── login/index.vue       # 登录页（账号/密码/验证码）
        ├── dashboard/index.vue   # 首页看板（生产/质量/设备/物料四类 KPI）
        ├── CrudPage.vue          # 通用 CRUD 组件（查询/表格/分页/新增/修改/删除/导出）
        └── ResourceView.vue      # 读取路由配置并渲染 CrudPage 的包装视图
```

## 接口约定

所有请求遵循若依规范：

- 统一响应体：列表接口返回 `{ "code":200, "rows":[...], "total":n }`；其余返回 `{ "code":200, "msg":"...", "data":... }`。
- 鉴权：`Authorization: Bearer <token>`（由请求拦截器自动注入）。
- 登录：`POST /login {username,password,code,uuid}`；验证码 `GET /captchaImage`；用户信息 `GET /getInfo`；退出 `POST /logout`。

MES 业务实体接口（每个资源统一）：

| 功能 | 方法 | 路径 |
| --- | --- | --- |
| 列表 | GET | `/mes/{biz}/{entity}/list` |
| 详情 | GET | `/mes/{biz}/{entity}/{id}` |
| 新增 | POST | `/mes/{biz}/{entity}` |
| 修改 | PUT | `/mes/{biz}/{entity}` |
| 删除 | DELETE | `/mes/{biz}/{entity}/{ids}` |
| 导出 | POST | `/mes/{biz}/{entity}/export`（文件流，前端以 blob 下载） |

权限标识：`mes:{biz}:{entity}:{list|query|add|edit|remove|export}`。

看板接口：

- `GET /mes/bi/kpi/production`
- `GET /mes/bi/kpi/quality`
- `GET /mes/bi/kpi/equipment`
- `GET /mes/bi/kpi/material`

## 资源配置说明（src/config/resources.js）

`resources.js` 依据后端 `ruoyi-mes` 的 **55 个实体**（controller + domain）自动梳理生成，每项结构：

```js
{
  module: 'md',                 // 模块编码
  moduleName: '主数据管理',      // 模块名称
  path: '/mes/md/material',     // 接口与路由基础路径
  perm: 'mes:md:material',      // 权限标识前缀（由 path 自动推导）
  title: '物料主数据',           // 资源标题
  columns: [{ prop, label }],   // 表格列（label 取自 domain 的 @Excel 中文名）
  form: [{ prop, label, type, options }] // 表单字段
}
```

- `type` 支持 `input` / `select` / `textarea` / `number` / `date`。
- 状态类字段（`status`、各类 `*Flag`、`docStatus`、`kitStatus`、类型字段等）已结合后端
  `com.ruoyi.mes.common.enums.*` 枚举给出可选项。
- 主键 `id` 不放入表单；新增/修改弹窗提交时自动携带主键。
- 侧边菜单与路由均由该配置自动生成，新增实体只需在此追加一项即可。

### 覆盖的实体（共 55 个）

| 模块 | 数量 | 实体（路径） |
| --- | --- | --- |
| 主数据管理 `md` | 20 | material、product、productVersion、bom、bomLine、route、routeOperation、inspectionItem、inspectionPlan、inspectionPlanItem、workCenter、line、station、equipment、equipmentPoint、employee、team、shift、defectCode、versionApproval |
| 需求管理 `om` | 1 | demand |
| 计划排程 `ps` | 2 | plan、scheduleTask |
| 生产工单 `wo` | 3 | order、operationTask、reworkOrder |
| 生产执行 `ex` | 6 | report、serial、wip、workHour、processParam、exceptionReport |
| 物料管理 `mt` | 4 | requirement、consumption、returnMaterial、supplementMaterial |
| 质量管理 `qc` | 5 | inspection、inspectionValue、nonconformity、rework、release |
| 设备管理 `eq` | 7 | status、oee、maintenance、spotCheck、alarm、command、bind |
| 追溯管理 `tr` | 1 | index |
| 集成管理 `integration` | 6 | message、event、deadLetter、idempotent、reconcileTask、reconcileResult |

## 通用 CRUD 组件（CrudPage.vue）

`CrudPage` 完全由配置驱动，真正调用对应 REST 接口完成：

- 查询表单（顶部，字段来源于 `form`）
- 数据表格（列来源于 `columns`）+ 分页（`pageNum` / `pageSize`，适配后端 PageHelper）
- 新增 / 修改弹窗表单（按字段 `type` 渲染对应控件）
- 单条删除、批量删除（多选后拼接 ids）
- 导出（`POST` 请求，`responseType: 'blob'`，浏览器下载 xlsx）
- 按钮显示受用户权限控制（`mes:{biz}:{entity}:{add|edit|remove|export}`）
