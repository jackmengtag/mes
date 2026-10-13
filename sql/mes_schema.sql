-- MES 业务表结构（由生成器产出）
-- 生成日期：2026-10-13

-- ----------------------------
-- 物料主数据
-- ----------------------------
drop table if exists md_material;
create table md_material (
  id                bigint(20)      not null auto_increment    comment '主键',
  material_code     varchar(64)                              comment '物料编码',
  material_name     varchar(64)                              comment '物料名称',
  unit              varchar(64)                              comment '计量单位',
  material_type     varchar(64)                              comment '物料类型',
  batch_flag        varchar(64)                              comment '批次管理标识',
  serial_flag       varchar(64)                              comment '序列号管理标识',
  shelf_life        int(11)                                  comment '保质期(天)',
  key_flag          varchar(64)                              comment '是否关键物料',
  inspect_flag      varchar(64)                              comment '是否需要检验',
  substitute_flag   varchar(64)                              comment '是否允许替代',
  status            varchar(64)                              comment '状态',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
  ,unique key uk_md_material (material_code)
) engine=innodb auto_increment=1 comment = '物料主数据';

-- ----------------------------
-- 产品主数据
-- ----------------------------
drop table if exists md_product;
create table md_product (
  id                bigint(20)      not null auto_increment    comment '主键',
  product_code      varchar(64)                              comment '产品编码',
  product_name      varchar(64)                              comment '产品名称',
  product_type      varchar(64)                              comment '产品类型',
  serial_flag       varchar(64)                              comment '序列号管理标识',
  default_bom_version_id bigint(20)                               comment '默认BOM版本',
  default_route_version_id bigint(20)                               comment '默认工艺版本',
  status            varchar(64)                              comment '状态',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
  ,unique key uk_md_product (product_code)
) engine=innodb auto_increment=1 comment = '产品主数据';

-- ----------------------------
-- 产品版本
-- ----------------------------
drop table if exists md_product_version;
create table md_product_version (
  id                bigint(20)      not null auto_increment    comment '主键',
  product_id        bigint(20)                               comment '产品ID',
  product_code      varchar(64)                              comment '产品编码',
  version_no        varchar(64)                              comment '版本号',
  bom_version_id    bigint(20)                               comment 'BOM版本',
  route_version_id  bigint(20)                               comment '工艺版本',
  effective_time    datetime                                 comment '生效时间',
  expire_time       datetime                                 comment '失效时间',
  doc_status        varchar(64)                              comment '版本状态',
  status            varchar(64)                              comment '状态',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
  ,unique key uk_md_product_version (version_no)
) engine=innodb auto_increment=1 comment = '产品版本';

-- ----------------------------
-- BOM主数据
-- ----------------------------
drop table if exists md_bom;
create table md_bom (
  id                bigint(20)      not null auto_increment    comment '主键',
  bom_code          varchar(64)                              comment 'BOM编码',
  product_id        bigint(20)                               comment '产品ID',
  product_code      varchar(64)                              comment '产品编码',
  version_no        varchar(64)                              comment '版本号',
  doc_status        varchar(64)                              comment '版本状态',
  status            varchar(64)                              comment '状态',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
  ,unique key uk_md_bom (bom_code)
) engine=innodb auto_increment=1 comment = 'BOM主数据';

-- ----------------------------
-- BOM明细
-- ----------------------------
drop table if exists md_bom_line;
create table md_bom_line (
  id                bigint(20)      not null auto_increment    comment '主键',
  bom_id            bigint(20)                               comment 'BOM ID',
  material_id       bigint(20)                               comment '物料ID',
  material_code     varchar(64)                              comment '物料编码',
  material_name     varchar(64)                              comment '物料名称',
  quantity          decimal(18,4)                            comment '用量',
  loss_rate         decimal(18,4)                            comment '损耗率',
  feeding_operation_code varchar(64)                              comment '投料工序',
  key_flag          varchar(64)                              comment '是否关键物料',
  batch_bind_flag   varchar(64)                              comment '批次绑定要求',
  substitute_flag   varchar(64)                              comment '是否允许替代',
  substitute_material_code varchar(64)                              comment '替代料编码',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
) engine=innodb auto_increment=1 comment = 'BOM明细';

-- ----------------------------
-- 工艺路线
-- ----------------------------
drop table if exists md_route;
create table md_route (
  id                bigint(20)      not null auto_increment    comment '主键',
  route_code        varchar(64)                              comment '工艺编码',
  route_name        varchar(64)                              comment '工艺名称',
  product_id        bigint(20)                               comment '产品ID',
  product_code      varchar(64)                              comment '产品编码',
  version_no        varchar(64)                              comment '版本号',
  doc_status        varchar(64)                              comment '版本状态',
  status            varchar(64)                              comment '状态',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
  ,unique key uk_md_route (route_code)
) engine=innodb auto_increment=1 comment = '工艺路线';

-- ----------------------------
-- 工艺工序
-- ----------------------------
drop table if exists md_route_operation;
create table md_route_operation (
  id                bigint(20)      not null auto_increment    comment '主键',
  route_id          bigint(20)                               comment '工艺路线ID',
  operation_code    varchar(64)                              comment '工序编码',
  operation_name    varchar(64)                              comment '工序名称',
  sequence_no       int(11)                                  comment '工序顺序号',
  work_center_id    bigint(20)                               comment '工作中心',
  equipment_type    varchar(64)                              comment '设备类型要求',
  skill_requirement varchar(64)                              comment '技能要求',
  force_inspect     varchar(64)                              comment '是否强制检验',
  inspection_plan_id bigint(20)                               comment '检验方案',
  standard_hours    decimal(18,4)                            comment '标准工时',
  status            varchar(64)                              comment '状态',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
) engine=innodb auto_increment=1 comment = '工艺工序';

-- ----------------------------
-- 产线
-- ----------------------------
drop table if exists md_line;
create table md_line (
  id                bigint(20)      not null auto_increment    comment '主键',
  line_code         varchar(64)                              comment '产线编码',
  line_name         varchar(64)                              comment '产线名称',
  plant_code        varchar(64)                              comment '工厂编码',
  status            varchar(64)                              comment '状态',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
  ,unique key uk_md_line (line_code)
) engine=innodb auto_increment=1 comment = '产线';

-- ----------------------------
-- 工作中心
-- ----------------------------
drop table if exists md_work_center;
create table md_work_center (
  id                bigint(20)      not null auto_increment    comment '主键',
  work_center_code  varchar(64)                              comment '工作中心编码',
  work_center_name  varchar(64)                              comment '工作中心名称',
  line_id           bigint(20)                               comment '所属产线',
  capacity          decimal(18,4)                            comment '产能',
  status            varchar(64)                              comment '状态',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
  ,unique key uk_md_work_center (work_center_code)
) engine=innodb auto_increment=1 comment = '工作中心';

-- ----------------------------
-- 工位
-- ----------------------------
drop table if exists md_station;
create table md_station (
  id                bigint(20)      not null auto_increment    comment '主键',
  station_code      varchar(64)                              comment '工位编码',
  station_name      varchar(64)                              comment '工位名称',
  line_id           bigint(20)                               comment '所属产线',
  work_center_id    bigint(20)                               comment '所属工作中心',
  status            varchar(64)                              comment '状态',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
  ,unique key uk_md_station (station_code)
) engine=innodb auto_increment=1 comment = '工位';

-- ----------------------------
-- 设备档案
-- ----------------------------
drop table if exists md_equipment;
create table md_equipment (
  id                bigint(20)      not null auto_increment    comment '主键',
  equipment_code    varchar(64)                              comment '设备编码',
  equipment_name    varchar(64)                              comment '设备名称',
  equipment_type    varchar(64)                              comment '设备类型',
  work_center_id    bigint(20)                               comment '归属工作中心',
  collect_mode      varchar(64)                              comment '采集方式',
  status            varchar(64)                              comment '状态',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
  ,unique key uk_md_equipment (equipment_code)
) engine=innodb auto_increment=1 comment = '设备档案';

-- ----------------------------
-- 设备采集点位
-- ----------------------------
drop table if exists md_equipment_point;
create table md_equipment_point (
  id                bigint(20)      not null auto_increment    comment '主键',
  equipment_id      bigint(20)                               comment '设备ID',
  point_code        varchar(64)                              comment '点位编码',
  point_name        varchar(64)                              comment '点位名称',
  protocol          varchar(64)                              comment '通信协议',
  address           varchar(64)                              comment '点位地址',
  data_type         varchar(64)                              comment '数据类型',
  unit              varchar(64)                              comment '单位',
  change_reason     text                                     comment '变更原因',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
) engine=innodb auto_increment=1 comment = '设备采集点位';

-- ----------------------------
-- 班组
-- ----------------------------
drop table if exists md_team;
create table md_team (
  id                bigint(20)      not null auto_increment    comment '主键',
  team_code         varchar(64)                              comment '班组编码',
  team_name         varchar(64)                              comment '班组名称',
  leader_no         varchar(64)                              comment '班组长工号',
  status            varchar(64)                              comment '状态',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
  ,unique key uk_md_team (team_code)
) engine=innodb auto_increment=1 comment = '班组';

-- ----------------------------
-- 班次
-- ----------------------------
drop table if exists md_shift;
create table md_shift (
  id                bigint(20)      not null auto_increment    comment '主键',
  shift_code        varchar(64)                              comment '班次编码',
  shift_name        varchar(64)                              comment '班次名称',
  start_time        varchar(64)                              comment '开始时间',
  end_time          varchar(64)                              comment '结束时间',
  status            varchar(64)                              comment '状态',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
  ,unique key uk_md_shift (shift_code)
) engine=innodb auto_increment=1 comment = '班次';

-- ----------------------------
-- 员工
-- ----------------------------
drop table if exists md_employee;
create table md_employee (
  id                bigint(20)      not null auto_increment    comment '主键',
  emp_no            varchar(64)                              comment '员工工号',
  emp_name          varchar(64)                              comment '员工姓名',
  post_name         varchar(64)                              comment '岗位',
  skills            varchar(64)                              comment '技能',
  team_id           bigint(20)                               comment '所属班组',
  shift_id          bigint(20)                               comment '所属班次',
  user_id           bigint(20)                               comment '关联系统用户',
  status            varchar(64)                              comment '状态',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
  ,unique key uk_md_employee (emp_no)
) engine=innodb auto_increment=1 comment = '员工';

-- ----------------------------
-- 缺陷代码
-- ----------------------------
drop table if exists md_defect_code;
create table md_defect_code (
  id                bigint(20)      not null auto_increment    comment '主键',
  defect_code       varchar(64)                              comment '缺陷代码',
  defect_name       varchar(64)                              comment '缺陷名称',
  defect_category   varchar(64)                              comment '缺陷类别',
  resp_dept         varchar(64)                              comment '责任部门',
  default_disposition varchar(64)                              comment '默认处理方式',
  status            varchar(64)                              comment '状态',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
  ,unique key uk_md_defect_code (defect_code)
) engine=innodb auto_increment=1 comment = '缺陷代码';

-- ----------------------------
-- 检验项目
-- ----------------------------
drop table if exists md_inspection_item;
create table md_inspection_item (
  id                bigint(20)      not null auto_increment    comment '主键',
  item_code         varchar(64)                              comment '项目编码',
  item_name         varchar(64)                              comment '项目名称',
  unit              varchar(64)                              comment '单位',
  lower_limit       decimal(18,4)                            comment '下限',
  upper_limit       decimal(18,4)                            comment '上限',
  standard_value    decimal(18,4)                            comment '标准值',
  status            varchar(64)                              comment '状态',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
  ,unique key uk_md_inspection_item (item_code)
) engine=innodb auto_increment=1 comment = '检验项目';

-- ----------------------------
-- 检验方案
-- ----------------------------
drop table if exists md_inspection_plan;
create table md_inspection_plan (
  id                bigint(20)      not null auto_increment    comment '主键',
  plan_code         varchar(64)                              comment '方案编码',
  plan_name         varchar(64)                              comment '方案名称',
  inspection_type   varchar(64)                              comment '检验类型',
  product_id        bigint(20)                               comment '产品ID',
  operation_code    varchar(64)                              comment '工序编码',
  version_no        varchar(64)                              comment '版本号',
  doc_status        varchar(64)                              comment '版本状态',
  status            varchar(64)                              comment '状态',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
  ,unique key uk_md_inspection_plan (plan_code)
) engine=innodb auto_increment=1 comment = '检验方案';

-- ----------------------------
-- 检验方案明细
-- ----------------------------
drop table if exists md_inspection_plan_item;
create table md_inspection_plan_item (
  id                bigint(20)      not null auto_increment    comment '主键',
  plan_id           bigint(20)                               comment '方案ID',
  item_id           bigint(20)                               comment '检验项目ID',
  item_code         varchar(64)                              comment '项目编码',
  item_name         varchar(64)                              comment '项目名称',
  lower_limit       decimal(18,4)                            comment '下限',
  upper_limit       decimal(18,4)                            comment '上限',
  unit              varchar(64)                              comment '单位',
  sampling_rule     varchar(64)                              comment '抽样规则',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
) engine=innodb auto_increment=1 comment = '检验方案明细';

-- ----------------------------
-- 主数据版本审批
-- ----------------------------
drop table if exists md_version_approval;
create table md_version_approval (
  id                bigint(20)      not null auto_increment    comment '主键',
  biz_type          varchar(64)                              comment '业务类型',
  biz_id            bigint(20)                               comment '业务ID',
  version_no        varchar(64)                              comment '版本号',
  doc_status        varchar(64)                              comment '版本状态',
  change_reason     text                                     comment '变更原因',
  change_by         varchar(64)                              comment '变更人',
  change_time       datetime                                 comment '变更时间',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
) engine=innodb auto_increment=1 comment = '主数据版本审批';

-- ----------------------------
-- 生产需求
-- ----------------------------
drop table if exists om_demand;
create table om_demand (
  id                bigint(20)      not null auto_increment    comment '主键',
  demand_no         varchar(64)                              comment '需求单号',
  source_order_no   varchar(64)                              comment '来源订单号',
  source_order_line_no varchar(64)                              comment '来源订单行号',
  source_system     varchar(64)                              comment '来源系统',
  erp_demand_no     varchar(64)                              comment 'ERP需求号',
  plant_code        varchar(64)                              comment '工厂编码',
  product_id        bigint(20)                               comment '产品ID',
  product_code      varchar(64)                              comment '产品编码',
  product_version   varchar(64)                              comment '产品版本',
  required_qty      decimal(18,4)                            comment '需求数量',
  due_date          datetime                                 comment '交期',
  priority          varchar(64)                              comment '优先级',
  status            varchar(64)                              comment '状态',
  idempotency_key   varchar(64)                              comment '幂等键',
  change_reason     text                                     comment '变更原因',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
  ,unique key uk_om_demand (demand_no)
) engine=innodb auto_increment=1 comment = '生产需求';

-- ----------------------------
-- 生产计划
-- ----------------------------
drop table if exists ps_plan;
create table ps_plan (
  id                bigint(20)      not null auto_increment    comment '主键',
  plan_no           varchar(64)                              comment '计划单号',
  demand_id         bigint(20)                               comment '需求ID',
  demand_no         varchar(64)                              comment '需求单号',
  product_id        bigint(20)                               comment '产品ID',
  product_code      varchar(64)                              comment '产品编码',
  plan_qty          decimal(18,4)                            comment '计划数量',
  planned_start_time datetime                                 comment '计划开始',
  planned_end_time  datetime                                 comment '计划结束',
  line_id           bigint(20)                               comment '产线',
  priority          varchar(64)                              comment '优先级',
  status            varchar(64)                              comment '状态',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
  ,unique key uk_ps_plan (plan_no)
) engine=innodb auto_increment=1 comment = '生产计划';

-- ----------------------------
-- 排程任务
-- ----------------------------
drop table if exists ps_schedule_task;
create table ps_schedule_task (
  id                bigint(20)      not null auto_increment    comment '主键',
  plan_id           bigint(20)                               comment '计划ID',
  work_order_id     bigint(20)                               comment '工单ID',
  line_id           bigint(20)                               comment '产线',
  sequence_no       int(11)                                  comment '排序号',
  planned_start_time datetime                                 comment '计划开始',
  planned_end_time  datetime                                 comment '计划结束',
  schedule_type     varchar(64)                              comment '排程方式',
  conflict_flag     varchar(64)                              comment '是否冲突',
  conflict_reason   text                                     comment '冲突原因',
  adjust_reason     text                                     comment '调整原因',
  status            varchar(64)                              comment '状态',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
) engine=innodb auto_increment=1 comment = '排程任务';

-- ----------------------------
-- 生产工单
-- ----------------------------
drop table if exists wo_production_order;
create table wo_production_order (
  id                bigint(20)      not null auto_increment    comment '主键',
  order_no          varchar(64)                              comment '工单号',
  source_order_no   varchar(64)                              comment '来源订单号',
  erp_work_order_no varchar(64)                              comment 'ERP工单号',
  demand_id         bigint(20)                               comment '需求ID',
  parent_order_id   bigint(20)                               comment '父工单ID',
  split_reason      varchar(64)                              comment '拆分原因',
  product_id        bigint(20)                               comment '产品ID',
  product_code      varchar(64)                              comment '产品编码',
  product_version_id bigint(20)                               comment '固化产品版本',
  route_version_id  bigint(20)                               comment '固化工艺版本',
  bom_version_id    bigint(20)                               comment '固化BOM版本',
  inspection_plan_version_id bigint(20)                               comment '固化检验方案版本',
  line_id           bigint(20)                               comment '产线',
  priority          varchar(64)                              comment '优先级',
  planned_qty       decimal(18,4)                            comment '计划数量',
  completed_qty     decimal(18,4)                            comment '完成数量',
  qualified_qty     decimal(18,4)                            comment '合格数量',
  scrap_qty         decimal(18,4)                            comment '报废数量',
  kit_status        varchar(64)                              comment '齐套状态',
  planned_start_time datetime                                 comment '计划开始',
  planned_end_time  datetime                                 comment '计划结束',
  actual_start_time datetime                                 comment '实际开始',
  actual_end_time   datetime                                 comment '实际结束',
  pause_reason      text                                     comment '暂停原因',
  status            varchar(64)                              comment '工单状态',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
  ,unique key uk_wo_production_order (order_no)
) engine=innodb auto_increment=1 comment = '生产工单';

-- ----------------------------
-- 工序任务
-- ----------------------------
drop table if exists wo_operation_task;
create table wo_operation_task (
  id                bigint(20)      not null auto_increment    comment '主键',
  production_order_id bigint(20)                               comment '所属工单',
  order_no          varchar(64)                              comment '工单号',
  operation_code    varchar(64)                              comment '工序编码',
  operation_name    varchar(64)                              comment '工序名称',
  sequence_no       int(11)                                  comment '工序顺序号',
  work_center_id    bigint(20)                               comment '工作中心',
  required_equipment_type varchar(64)                              comment '设备类型要求',
  planned_qty       decimal(18,4)                            comment '计划数量',
  completed_qty     decimal(18,4)                            comment '完成数量',
  qualified_qty     decimal(18,4)                            comment '合格数量',
  unqualified_qty   decimal(18,4)                            comment '不合格数量',
  scrap_qty         decimal(18,4)                            comment '报废数量',
  force_inspect     varchar(64)                              comment '是否强制检验',
  dispatch_team_id  bigint(20)                               comment '派工班组',
  operator_no       varchar(64)                              comment '执行人',
  status            varchar(64)                              comment '工序状态',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
) engine=innodb auto_increment=1 comment = '工序任务';

-- ----------------------------
-- 返工工单
-- ----------------------------
drop table if exists wo_rework_order;
create table wo_rework_order (
  id                bigint(20)      not null auto_increment    comment '主键',
  rework_no         varchar(64)                              comment '返工单号',
  origin_order_id   bigint(20)                               comment '原工单ID',
  origin_order_no   varchar(64)                              comment '原工单号',
  nonconformity_id  bigint(20)                               comment '不合格记录ID',
  serial_no         varchar(64)                              comment '产品序列号',
  operation_code    varchar(64)                              comment '返工工序',
  rework_qty        decimal(18,4)                            comment '返工数量',
  reason            text                                     comment '返工原因',
  status            varchar(64)                              comment '状态',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
  ,unique key uk_wo_rework_order (rework_no)
) engine=innodb auto_increment=1 comment = '返工工单';

-- ----------------------------
-- 产品序列号
-- ----------------------------
drop table if exists ex_product_serial;
create table ex_product_serial (
  id                bigint(20)      not null auto_increment    comment '主键',
  serial_no         varchar(64)                              comment '序列号',
  product_id        bigint(20)                               comment '产品ID',
  product_code      varchar(64)                              comment '产品编码',
  production_order_id bigint(20)                               comment '所属工单',
  current_operation varchar(64)                              comment '当前工序',
  current_status    varchar(64)                              comment '当前状态',
  quality_status    varchar(64)                              comment '质量状态',
  warehouse_status  varchar(64)                              comment '库存状态',
  scrap_flag        varchar(64)                              comment '报废标识',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
  ,unique key uk_ex_product_serial (serial_no)
) engine=innodb auto_increment=1 comment = '产品序列号';

-- ----------------------------
-- 报工记录
-- ----------------------------
drop table if exists ex_production_report;
create table ex_production_report (
  id                bigint(20)      not null auto_increment    comment '主键',
  report_no         varchar(64)                              comment '报工单号',
  production_order_id bigint(20)                               comment '所属工单',
  order_no          varchar(64)                              comment '工单号',
  operation_task_id bigint(20)                               comment '工序任务ID',
  operation_code    varchar(64)                              comment '工序编码',
  serial_no         varchar(64)                              comment '产品序列号',
  qualified_qty     decimal(18,4)                            comment '合格数量',
  unqualified_qty   decimal(18,4)                            comment '不合格数量',
  scrap_qty         decimal(18,4)                            comment '报废数量',
  labor_minutes     decimal(18,4)                            comment '人工工时(分)',
  machine_minutes   decimal(18,4)                            comment '机器工时(分)',
  equipment_id      bigint(20)                               comment '设备ID',
  operator_no       varchar(64)                              comment '操作员',
  shift_code        varchar(64)                              comment '班次',
  reported_at       datetime                                 comment '报工时间',
  idempotency_key   varchar(64)                              comment '幂等键',
  sync_status       varchar(64)                              comment 'ERP同步状态',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
  ,unique key uk_ex_production_report (report_no)
) engine=innodb auto_increment=1 comment = '报工记录';

-- ----------------------------
-- 工艺参数采集
-- ----------------------------
drop table if exists ex_process_param;
create table ex_process_param (
  id                bigint(20)      not null auto_increment    comment '主键',
  production_order_id bigint(20)                               comment '工单ID',
  operation_task_id bigint(20)                               comment '工序任务ID',
  serial_no         varchar(64)                              comment '序列号',
  equipment_id      bigint(20)                               comment '设备ID',
  param_name        varchar(64)                              comment '参数名称',
  param_value       varchar(64)                              comment '参数值',
  param_unit        varchar(64)                              comment '单位',
  lower_limit       decimal(18,4)                            comment '下限',
  upper_limit       decimal(18,4)                            comment '上限',
  result            varchar(64)                              comment '判定结果',
  collected_at      datetime                                 comment '采集时间',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
) engine=innodb auto_increment=1 comment = '工艺参数采集';

-- ----------------------------
-- 现场异常上报
-- ----------------------------
drop table if exists ex_exception_report;
create table ex_exception_report (
  id                bigint(20)      not null auto_increment    comment '主键',
  exception_no      varchar(64)                              comment '异常单号',
  exception_type    varchar(64)                              comment '异常类型',
  production_order_id bigint(20)                               comment '工单ID',
  operation_task_id bigint(20)                               comment '工序任务ID',
  equipment_id      bigint(20)                               comment '设备ID',
  serial_no         varchar(64)                              comment '序列号',
  severity          varchar(64)                              comment '严重程度',
  description       text                                     comment '异常描述',
  reporter_no       varchar(64)                              comment '上报人',
  reported_at       datetime                                 comment '上报时间',
  handler_no        varchar(64)                              comment '处理人',
  handle_result     text                                     comment '处理结果',
  status            varchar(64)                              comment '状态',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
  ,unique key uk_ex_exception_report (exception_no)
) engine=innodb auto_increment=1 comment = '现场异常上报';

-- ----------------------------
-- 在制品
-- ----------------------------
drop table if exists ex_wip;
create table ex_wip (
  id                bigint(20)      not null auto_increment    comment '主键',
  production_order_id bigint(20)                               comment '工单ID',
  order_no          varchar(64)                              comment '工单号',
  operation_code    varchar(64)                              comment '工序编码',
  wip_qty           decimal(18,4)                            comment '在制数量',
  stay_minutes      decimal(18,4)                            comment '停留时长(分)',
  stat_time         datetime                                 comment '统计时间',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
) engine=innodb auto_increment=1 comment = '在制品';

-- ----------------------------
-- 工时记录
-- ----------------------------
drop table if exists ex_work_hour;
create table ex_work_hour (
  id                bigint(20)      not null auto_increment    comment '主键',
  production_order_id bigint(20)                               comment '工单ID',
  operation_task_id bigint(20)                               comment '工序任务ID',
  work_type         varchar(64)                              comment '工时类型',
  minutes           decimal(18,4)                            comment '工时(分)',
  operator_no       varchar(64)                              comment '操作员',
  record_time       datetime                                 comment '记录时间',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
) engine=innodb auto_increment=1 comment = '工时记录';

-- ----------------------------
-- 物料需求
-- ----------------------------
drop table if exists mt_material_requirement;
create table mt_material_requirement (
  id                bigint(20)      not null auto_increment    comment '主键',
  requirement_no    varchar(64)                              comment '需求单号',
  production_order_id bigint(20)                               comment '工单ID',
  order_no          varchar(64)                              comment '工单号',
  operation_code    varchar(64)                              comment '投料工序',
  material_id       bigint(20)                               comment '物料ID',
  material_code     varchar(64)                              comment '物料编码',
  material_name     varchar(64)                              comment '物料名称',
  unit              varchar(64)                              comment '单位',
  required_qty      decimal(18,4)                            comment '需求数量',
  allocated_qty     decimal(18,4)                            comment '分配数量',
  picked_qty        decimal(18,4)                            comment '拣料数量',
  consumed_qty      decimal(18,4)                            comment '消耗数量',
  lack_qty          decimal(18,4)                            comment '缺料数量',
  key_flag          varchar(64)                              comment '是否关键物料',
  kit_status        varchar(64)                              comment '齐套状态',
  status            varchar(64)                              comment '状态',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
  ,unique key uk_mt_material_requirement (requirement_no)
) engine=innodb auto_increment=1 comment = '物料需求';

-- ----------------------------
-- 物料批次消耗
-- ----------------------------
drop table if exists mt_material_consumption;
create table mt_material_consumption (
  id                bigint(20)      not null auto_increment    comment '主键',
  consume_no        varchar(64)                              comment '消耗单号',
  material_id       bigint(20)                               comment '物料ID',
  material_code     varchar(64)                              comment '物料编码',
  material_batch_no varchar(64)                              comment '物料批次号',
  production_order_id bigint(20)                               comment '工单ID',
  order_no          varchar(64)                              comment '工单号',
  serial_no         varchar(64)                              comment '产品序列号',
  operation_task_id bigint(20)                               comment '工序任务ID',
  operation_code    varchar(64)                              comment '工序编码',
  consumed_qty      decimal(18,4)                            comment '消耗数量',
  substitute_flag   varchar(64)                              comment '是否替代料',
  operator_no       varchar(64)                              comment '操作员',
  wms_transaction_no varchar(64)                              comment 'WMS库存事务号',
  sync_status       varchar(64)                              comment '同步状态',
  consumed_at       datetime                                 comment '消耗时间',
  idempotency_key   varchar(64)                              comment '幂等键',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
  ,unique key uk_mt_material_consumption (consume_no)
) engine=innodb auto_increment=1 comment = '物料批次消耗';

-- ----------------------------
-- 退料
-- ----------------------------
drop table if exists mt_return_material;
create table mt_return_material (
  id                bigint(20)      not null auto_increment    comment '主键',
  return_no         varchar(64)                              comment '退料单号',
  production_order_id bigint(20)                               comment '工单ID',
  order_no          varchar(64)                              comment '工单号',
  material_id       bigint(20)                               comment '物料ID',
  material_code     varchar(64)                              comment '物料编码',
  material_batch_no varchar(64)                              comment '物料批次号',
  origin_picking_no varchar(64)                              comment '原领料单',
  return_qty        decimal(18,4)                            comment '退料数量',
  reason            text                                     comment '退料原因',
  warehouse_confirm_status varchar(64)                              comment '仓库确认状态',
  status            varchar(64)                              comment '状态',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
  ,unique key uk_mt_return_material (return_no)
) engine=innodb auto_increment=1 comment = '退料';

-- ----------------------------
-- 补料
-- ----------------------------
drop table if exists mt_supplement_material;
create table mt_supplement_material (
  id                bigint(20)      not null auto_increment    comment '主键',
  supplement_no     varchar(64)                              comment '补料单号',
  production_order_id bigint(20)                               comment '工单ID',
  order_no          varchar(64)                              comment '工单号',
  material_id       bigint(20)                               comment '物料ID',
  material_code     varchar(64)                              comment '物料编码',
  material_batch_no varchar(64)                              comment '物料批次号',
  supplement_qty    decimal(18,4)                            comment '补料数量',
  reason            text                                     comment '补料原因',
  status            varchar(64)                              comment '状态',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
  ,unique key uk_mt_supplement_material (supplement_no)
) engine=innodb auto_increment=1 comment = '补料';

-- ----------------------------
-- 质量检验
-- ----------------------------
drop table if exists qc_inspection;
create table qc_inspection (
  id                bigint(20)      not null auto_increment    comment '主键',
  inspection_no     varchar(64)                              comment '检验单号',
  inspection_type   varchar(64)                              comment '检验类型',
  production_order_id bigint(20)                               comment '工单ID',
  order_no          varchar(64)                              comment '工单号',
  operation_task_id bigint(20)                               comment '工序任务ID',
  operation_code    varchar(64)                              comment '工序编码',
  serial_no         varchar(64)                              comment '产品序列号',
  material_id       bigint(20)                               comment '物料ID',
  material_batch_no varchar(64)                              comment '物料批次号',
  inspection_plan_id bigint(20)                               comment '检验方案',
  result            varchar(64)                              comment '检验结果',
  inspector_no      varchar(64)                              comment '检验员',
  inspected_at      datetime                                 comment '检验时间',
  release_flag      varchar(64)                              comment '是否已放行',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
  ,unique key uk_qc_inspection (inspection_no)
) engine=innodb auto_increment=1 comment = '质量检验';

-- ----------------------------
-- 检验项目值
-- ----------------------------
drop table if exists qc_inspection_value;
create table qc_inspection_value (
  id                bigint(20)      not null auto_increment    comment '主键',
  inspection_id     bigint(20)                               comment '检验单ID',
  item_code         varchar(64)                              comment '项目编码',
  item_name         varchar(64)                              comment '项目名称',
  item_value        varchar(64)                              comment '检验值',
  lower_limit       decimal(18,4)                            comment '下限',
  upper_limit       decimal(18,4)                            comment '上限',
  result            varchar(64)                              comment '判定结果',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
) engine=innodb auto_increment=1 comment = '检验项目值';

-- ----------------------------
-- 不合格品记录
-- ----------------------------
drop table if exists qc_nonconformity;
create table qc_nonconformity (
  id                bigint(20)      not null auto_increment    comment '主键',
  nonconformity_no  varchar(64)                              comment '不合格单号',
  production_order_id bigint(20)                               comment '工单ID',
  order_no          varchar(64)                              comment '工单号',
  operation_task_id bigint(20)                               comment '工序任务ID',
  operation_code    varchar(64)                              comment '工序编码',
  serial_no         varchar(64)                              comment '产品序列号',
  defect_code       varchar(64)                              comment '缺陷代码',
  defect_desc       text                                     comment '缺陷描述',
  discovery_source  varchar(64)                              comment '发现来源',
  discovery_no      varchar(64)                              comment '发现单单据',
  qty               decimal(18,4)                            comment '不合格数量',
  disposal_type     varchar(64)                              comment '处置方式',
  disposition_reason text                                     comment '判定理由',
  disposition_by    varchar(64)                              comment '判定人',
  disposition_time  datetime                                 comment '判定时间',
  authorized_by     varchar(64)                              comment '授权人',
  reinspect_result  varchar(64)                              comment '复检结果',
  status            varchar(64)                              comment '状态',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
  ,unique key uk_qc_nonconformity (nonconformity_no)
) engine=innodb auto_increment=1 comment = '不合格品记录';

-- ----------------------------
-- 返工返修
-- ----------------------------
drop table if exists qc_rework;
create table qc_rework (
  id                bigint(20)      not null auto_increment    comment '主键',
  rework_no         varchar(64)                              comment '返工单号',
  nonconformity_id  bigint(20)                               comment '不合格记录ID',
  production_order_id bigint(20)                               comment '工单ID',
  serial_no         varchar(64)                              comment '产品序列号',
  operation_code    varchar(64)                              comment '返工工序',
  rework_qty        decimal(18,4)                            comment '返工数量',
  rework_result     varchar(64)                              comment '复检结果',
  executor_no       varchar(64)                              comment '执行人',
  status            varchar(64)                              comment '状态',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
  ,unique key uk_qc_rework (rework_no)
) engine=innodb auto_increment=1 comment = '返工返修';

-- ----------------------------
-- 质量放行
-- ----------------------------
drop table if exists qc_release;
create table qc_release (
  id                bigint(20)      not null auto_increment    comment '主键',
  release_no        varchar(64)                              comment '放行单号',
  production_order_id bigint(20)                               comment '工单ID',
  order_no          varchar(64)                              comment '工单号',
  serial_no         varchar(64)                              comment '产品序列号',
  release_result    varchar(64)                              comment '放行结果',
  release_by        varchar(64)                              comment '放行人',
  release_time      datetime                                 comment '放行时间',
  special_flag      varchar(64)                              comment '是否特殊放行',
  block_reason      text                                     comment '阻塞项',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
  ,unique key uk_qc_release (release_no)
) engine=innodb auto_increment=1 comment = '质量放行';

-- ----------------------------
-- 设备状态
-- ----------------------------
drop table if exists eq_status;
create table eq_status (
  id                bigint(20)      not null auto_increment    comment '主键',
  equipment_id      bigint(20)                               comment '设备ID',
  equipment_code    varchar(64)                              comment '设备编码',
  status            varchar(64)                              comment '设备状态',
  changed_at        datetime                                 comment '变更时间',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
) engine=innodb auto_increment=1 comment = '设备状态';

-- ----------------------------
-- 设备报警
-- ----------------------------
drop table if exists eq_alarm;
create table eq_alarm (
  id                bigint(20)      not null auto_increment    comment '主键',
  equipment_id      bigint(20)                               comment '设备ID',
  equipment_code    varchar(64)                              comment '设备编码',
  alarm_code        varchar(64)                              comment '报警代码',
  alarm_level       varchar(64)                              comment '报警等级',
  alarm_desc        text                                     comment '报警描述',
  production_order_id bigint(20)                               comment '工单ID',
  operation_code    varchar(64)                              comment '工序编码',
  start_time        datetime                                 comment '开始时间',
  end_time          datetime                                 comment '恢复时间',
  duration          decimal(18,4)                            comment '持续时长(分)',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
) engine=innodb auto_increment=1 comment = '设备报警';

-- ----------------------------
-- 设备与工单绑定
-- ----------------------------
drop table if exists eq_bind;
create table eq_bind (
  id                bigint(20)      not null auto_increment    comment '主键',
  equipment_id      bigint(20)                               comment '设备ID',
  equipment_code    varchar(64)                              comment '设备编码',
  production_order_id bigint(20)                               comment '工单ID',
  operation_code    varchar(64)                              comment '工序编码',
  serial_no         varchar(64)                              comment '序列号',
  shift_code        varchar(64)                              comment '班次',
  operator_no       varchar(64)                              comment '操作员',
  bind_time         datetime                                 comment '绑定时间',
  unbind_time       datetime                                 comment '解绑时间',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
) engine=innodb auto_increment=1 comment = '设备与工单绑定';

-- ----------------------------
-- 设备命令
-- ----------------------------
drop table if exists eq_command;
create table eq_command (
  id                bigint(20)      not null auto_increment    comment '主键',
  command_no        varchar(64)                              comment '命令编号',
  equipment_id      bigint(20)                               comment '设备ID',
  equipment_code    varchar(64)                              comment '设备编码',
  command_type      varchar(64)                              comment '命令类型',
  production_order_id bigint(20)                               comment '工单ID',
  serial_no         varchar(64)                              comment '序列号',
  program_code      varchar(64)                              comment '程序/配方',
  params            text                                     comment '参数',
  send_by           varchar(64)                              comment '下发人',
  send_time         datetime                                 comment '下发时间',
  response_result   text                                     comment '响应结果',
  retry_count       int(11)                                  comment '重试次数',
  status            varchar(64)                              comment '状态',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
  ,unique key uk_eq_command (command_no)
) engine=innodb auto_increment=1 comment = '设备命令';

-- ----------------------------
-- 设备点检
-- ----------------------------
drop table if exists eq_spot_check;
create table eq_spot_check (
  id                bigint(20)      not null auto_increment    comment '主键',
  equipment_id      bigint(20)                               comment '设备ID',
  equipment_code    varchar(64)                              comment '设备编码',
  plan_time         datetime                                 comment '计划时间',
  check_result      varchar(64)                              comment '点检结果',
  checker_no        varchar(64)                              comment '点检人',
  problem_desc      text                                     comment '问题描述',
  repair_required   varchar(64)                              comment '是否需维修',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
) engine=innodb auto_increment=1 comment = '设备点检';

-- ----------------------------
-- 设备维护与停机
-- ----------------------------
drop table if exists eq_maintenance;
create table eq_maintenance (
  id                bigint(20)      not null auto_increment    comment '主键',
  equipment_id      bigint(20)                               comment '设备ID',
  equipment_code    varchar(64)                              comment '设备编码',
  maintenance_type  varchar(64)                              comment '维护类型',
  stop_reason       varchar(64)                              comment '停机原因分类',
  start_time        datetime                                 comment '开始时间',
  end_time          datetime                                 comment '结束时间',
  stop_minutes      decimal(18,4)                            comment '停机时长(分)',
  executor_no       varchar(64)                              comment '执行人',
  status            varchar(64)                              comment '状态',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
) engine=innodb auto_increment=1 comment = '设备维护与停机';

-- ----------------------------
-- OEE
-- ----------------------------
drop table if exists eq_oee;
create table eq_oee (
  id                bigint(20)      not null auto_increment    comment '主键',
  equipment_id      bigint(20)                               comment '设备ID',
  equipment_code    varchar(64)                              comment '设备编码',
  stat_date         datetime                                 comment '统计日期',
  availability      decimal(18,4)                            comment '开动率',
  performance       decimal(18,4)                            comment '性能效率',
  quality           decimal(18,4)                            comment '良品率',
  oee               decimal(18,4)                            comment 'OEE',
  run_minutes       decimal(18,4)                            comment '运行时长(分)',
  stop_minutes      decimal(18,4)                            comment '停机时长(分)',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
) engine=innodb auto_increment=1 comment = 'OEE';

-- ----------------------------
-- 追溯索引
-- ----------------------------
drop table if exists tr_trace_index;
create table tr_trace_index (
  id                bigint(20)      not null auto_increment    comment '主键',
  serial_no         varchar(64)                              comment '产品序列号',
  production_order_id bigint(20)                               comment '工单ID',
  order_no          varchar(64)                              comment '工单号',
  material_batch_no varchar(64)                              comment '物料批次号',
  equipment_code    varchar(64)                              comment '设备编码',
  operator_no       varchar(64)                              comment '操作员',
  operation_code    varchar(64)                              comment '工序编码',
  route_version     varchar(64)                              comment '工艺版本',
  ref_type          varchar(64)                              comment '业务类型',
  ref_id            bigint(20)                               comment '业务ID',
  biz_time          datetime                                 comment '业务时间',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
) engine=innodb auto_increment=1 comment = '追溯索引';

-- ----------------------------
-- 集成消息
-- ----------------------------
drop table if exists int_message;
create table int_message (
  id                bigint(20)      not null auto_increment    comment '主键',
  message_id        varchar(64)                              comment '消息编号',
  message_type      varchar(64)                              comment '消息类型',
  message_version   varchar(64)                              comment '消息版本',
  source_system     varchar(64)                              comment '来源系统',
  target_system     varchar(64)                              comment '目标系统',
  plant_code        varchar(64)                              comment '工厂编码',
  trace_id          varchar(64)                              comment '链路追踪编号',
  idempotency_key   varchar(64)                              comment '幂等键',
  direction         varchar(64)                              comment '方向',
  payload           text                                     comment '消息载荷',
  status            varchar(64)                              comment '消息状态',
  retry_count       int(11)                                  comment '重试次数',
  next_retry_time   datetime                                 comment '下次重试时间',
  error_msg         text                                     comment '错误信息',
  occurred_at       datetime                                 comment '发生时间',
  processed_at      datetime                                 comment '处理时间',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
  ,unique key uk_int_message (message_id)
) engine=innodb auto_increment=1 comment = '集成消息';

-- ----------------------------
-- 集成死信
-- ----------------------------
drop table if exists int_dead_letter;
create table int_dead_letter (
  id                bigint(20)      not null auto_increment    comment '主键',
  message_id        varchar(64)                              comment '消息编号',
  biz_type          varchar(64)                              comment '业务类型',
  payload           text                                     comment '消息载荷',
  error_msg         text                                     comment '错误信息',
  handler           varchar(64)                              comment '责任人',
  handle_status     varchar(64)                              comment '处理状态',
  failed_at         datetime                                 comment '失败时间',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
) engine=innodb auto_increment=1 comment = '集成死信';

-- ----------------------------
-- 对账结果
-- ----------------------------
drop table if exists int_reconcile_result;
create table int_reconcile_result (
  id                bigint(20)      not null auto_increment    comment '主键',
  task_id           bigint(20)                               comment '对账任务ID',
  target_system     varchar(64)                              comment '对账对象',
  biz_type          varchar(64)                              comment '业务类型',
  biz_key           varchar(64)                              comment '业务键',
  diff_type         varchar(64)                              comment '差异类型',
  mes_value         varchar(64)                              comment 'MES值',
  ext_value         varchar(64)                              comment '外部值',
  handle_status     varchar(64)                              comment '处理状态',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
) engine=innodb auto_increment=1 comment = '对账结果';

-- ----------------------------
-- 幂等记录
-- ----------------------------
drop table if exists int_idempotent_record;
create table int_idempotent_record (
  id                bigint(20)      not null auto_increment    comment '主键',
  idempotency_key   varchar(64)                              comment '幂等键',
  biz_type          varchar(64)                              comment '业务类型',
  biz_id            bigint(20)                               comment '业务ID',
  result_code       varchar(64)                              comment '结果码',
  result_body       text                                     comment '结果内容',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
  ,unique key uk_int_idempotent_record (idempotency_key)
) engine=innodb auto_increment=1 comment = '幂等记录';

-- ----------------------------
-- 领域事件
-- ----------------------------
drop table if exists int_domain_event;
create table int_domain_event (
  id                bigint(20)      not null auto_increment    comment '主键',
  event_id          varchar(64)                              comment '事件编号',
  event_type        varchar(64)                              comment '事件类型',
  aggregate_type    varchar(64)                              comment '聚合类型',
  aggregate_id      bigint(20)                               comment '聚合ID',
  payload           text                                     comment '事件载荷',
  trace_id          varchar(64)                              comment '链路追踪编号',
  status            varchar(64)                              comment '状态',
  retry_count       int(11)                                  comment '重试次数',
  next_retry_time   datetime                                 comment '下次重试时间',
  error_msg         text                                     comment '错误信息',
  occurred_at       datetime                                 comment '发生时间',
  processed_at      datetime                                 comment '处理时间',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
  ,unique key uk_int_domain_event (event_id)
) engine=innodb auto_increment=1 comment = '领域事件';

-- ----------------------------
-- 对账任务
-- ----------------------------
drop table if exists int_reconcile_task;
create table int_reconcile_task (
  id                bigint(20)      not null auto_increment    comment '主键',
  task_no           varchar(64)                              comment '任务编号',
  target_system     varchar(64)                              comment '对账对象',
  biz_date          datetime                                 comment '对账日期',
  status            varchar(64)                              comment '状态',
  diff_count        int(11)                                  comment '差异数量',
  start_time        datetime                                 comment '开始时间',
  end_time          datetime                                 comment '结束时间',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
  ,unique key uk_int_reconcile_task (task_no)
) engine=innodb auto_increment=1 comment = '对账任务';

-- ----------------------------
-- 对账结果
-- ----------------------------
drop table if exists int_reconcile_result;
create table int_reconcile_result (
  id                bigint(20)      not null auto_increment    comment '主键',
  task_id           bigint(20)                               comment '对账任务ID',
  target_system     varchar(64)                              comment '对账对象',
  biz_type          varchar(64)                              comment '业务类型',
  biz_key           varchar(64)                              comment '业务键',
  diff_type         varchar(64)                              comment '差异类型',
  mes_value         varchar(64)                              comment 'MES值',
  ext_value         varchar(64)                              comment '外部值',
  handle_status     varchar(64)                              comment '处理状态',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
) engine=innodb auto_increment=1 comment = '对账结果';
