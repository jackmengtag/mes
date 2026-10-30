-- ============================================================
-- MES 制造与供应链协同平台 · 数据库结构（MySQL 8）
-- 依据：mes-product-prototype/docs/01-architecture.md、02-requirements.md
-- 说明：所有表使用 mes_ 前缀，字段与若依 BaseEntity 的审计字段保持一致
-- ============================================================

SET NAMES utf8mb4;

DROP TABLE IF EXISTS mes_material;
CREATE TABLE mes_material (
  material_id     BIGINT       NOT NULL AUTO_INCREMENT COMMENT '物料ID',
  material_code   VARCHAR(64)  NOT NULL COMMENT '物料/产品编码',
  material_name   VARCHAR(128) NOT NULL COMMENT '名称',
  spec            VARCHAR(128) DEFAULT '' COMMENT '规格',
  unit            VARCHAR(16)  DEFAULT '件' COMMENT '单位',
  category        VARCHAR(32)  DEFAULT '' COMMENT '分类',
  product_flag    CHAR(1)      DEFAULT '0' COMMENT '0物料 1产品',
  safety_stock    DECIMAL(14,2) DEFAULT 0 COMMENT '安全库存',
  max_stock       DECIMAL(14,2) DEFAULT 0 COMMENT '最高库存',
  price           DECIMAL(14,2) DEFAULT 0 COMMENT '单价',
  status          CHAR(1)      DEFAULT '0' COMMENT '0正常 1停用',
  remark          VARCHAR(500) DEFAULT NULL,
  create_by       VARCHAR(64)  DEFAULT '',
  create_time     DATETIME     DEFAULT NULL,
  update_by       VARCHAR(64)  DEFAULT '',
  update_time     DATETIME     DEFAULT NULL,
  PRIMARY KEY (material_id),
  UNIQUE KEY uk_material_code (material_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='物料与产品主数据';

DROP TABLE IF EXISTS mes_supplier;
CREATE TABLE mes_supplier (
  supplier_id     BIGINT       NOT NULL AUTO_INCREMENT COMMENT '供应商ID',
  supplier_code   VARCHAR(32)  NOT NULL COMMENT '供应商编码（分类2位+流水3位）',
  supplier_name   VARCHAR(128) NOT NULL COMMENT '供应商名称',
  category        VARCHAR(32)  DEFAULT '物料' COMMENT '分类：整车/物料/外协/服务',
  level           CHAR(1)      DEFAULT 'B' COMMENT '等级 A/B/C',
  contact         VARCHAR(32)  DEFAULT '' COMMENT '联系人',
  phone           VARCHAR(32)  DEFAULT '' COMMENT '联系电话',
  address         VARCHAR(255) DEFAULT '' COMMENT '地址',
  score           DECIMAL(5,1) DEFAULT 0 COMMENT '综合评分',
  on_time_rate    DECIMAL(5,1) DEFAULT 0 COMMENT '准时交付率%',
  status          CHAR(1)      DEFAULT '0' COMMENT '0待审核 1合作中 2停用',
  audit_time      DATETIME     DEFAULT NULL COMMENT '审核时间',
  remark          VARCHAR(500) DEFAULT NULL,
  create_by       VARCHAR(64)  DEFAULT '',
  create_time     DATETIME     DEFAULT NULL,
  update_by       VARCHAR(64)  DEFAULT '',
  update_time     DATETIME     DEFAULT NULL,
  PRIMARY KEY (supplier_id),
  UNIQUE KEY uk_supplier_code (supplier_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='供应商档案';

DROP TABLE IF EXISTS mes_customer;
CREATE TABLE mes_customer (
  customer_id     BIGINT       NOT NULL AUTO_INCREMENT COMMENT '客户ID',
  customer_code   VARCHAR(32)  NOT NULL COMMENT '客户编码',
  customer_name   VARCHAR(128) NOT NULL COMMENT '客户名称',
  contact         VARCHAR(32)  DEFAULT '' COMMENT '联系人',
  phone           VARCHAR(32)  DEFAULT '' COMMENT '电话',
  credit_limit    DECIMAL(14,2) DEFAULT 0 COMMENT '信用额度',
  status          CHAR(1)      DEFAULT '0' COMMENT '0正常 1停用',
  remark          VARCHAR(500) DEFAULT NULL,
  create_by       VARCHAR(64)  DEFAULT '',
  create_time     DATETIME     DEFAULT NULL,
  update_by       VARCHAR(64)  DEFAULT '',
  update_time     DATETIME     DEFAULT NULL,
  PRIMARY KEY (customer_id),
  UNIQUE KEY uk_customer_code (customer_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户档案';

DROP TABLE IF EXISTS mes_plan;
CREATE TABLE mes_plan (
  plan_id         BIGINT       NOT NULL AUTO_INCREMENT COMMENT '计划ID',
  plan_code       VARCHAR(64)  NOT NULL COMMENT '计划单号',
  plan_type       VARCHAR(32)  NOT NULL COMMENT 'group总计划/instruction指令/schedule排产/daily日计划/feeding投料',
  plan_month      VARCHAR(7)   DEFAULT '' COMMENT '计划月份 yyyy-MM',
  product_code    VARCHAR(64)  DEFAULT '' COMMENT '产品编码',
  product_name    VARCHAR(128) DEFAULT '' COMMENT '产品名称',
  qty             DECIMAL(14,2) DEFAULT 0 COMMENT '计划数量',
  unit            VARCHAR(16)  DEFAULT '台' COMMENT '单位',
  line_code       VARCHAR(32)  DEFAULT '' COMMENT '产线',
  status          VARCHAR(16)  DEFAULT 'draft' COMMENT 'draft/released/running/finished/locked',
  start_date      DATE         DEFAULT NULL,
  end_date        DATE         DEFAULT NULL,
  source_code     VARCHAR(64)  DEFAULT '' COMMENT '来源单号',
  remark          VARCHAR(500) DEFAULT NULL,
  create_by       VARCHAR(64)  DEFAULT '',
  create_time     DATETIME     DEFAULT NULL,
  update_by       VARCHAR(64)  DEFAULT '',
  update_time     DATETIME     DEFAULT NULL,
  PRIMARY KEY (plan_id),
  UNIQUE KEY uk_plan_code (plan_code),
  KEY idx_plan_type (plan_type),
  KEY idx_plan_month (plan_month)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='生产计划（总计划/指令/排产/日计划/投料）';

DROP TABLE IF EXISTS mes_work_order;
CREATE TABLE mes_work_order (
  work_order_id   BIGINT       NOT NULL AUTO_INCREMENT COMMENT '工单ID',
  order_code      VARCHAR(64)  NOT NULL COMMENT '工单号',
  plan_code       VARCHAR(64)  DEFAULT '' COMMENT '来源计划号',
  product_code    VARCHAR(64)  DEFAULT '' COMMENT '产品编码',
  product_name    VARCHAR(128) DEFAULT '' COMMENT '产品名称',
  qty             DECIMAL(14,2) DEFAULT 0 COMMENT '计划数量',
  completed_qty   DECIMAL(14,2) DEFAULT 0 COMMENT '完成数量',
  ng_qty          DECIMAL(14,2) DEFAULT 0 COMMENT '不良数量',
  unit            VARCHAR(16)  DEFAULT '台' COMMENT '单位',
  line_code       VARCHAR(32)  DEFAULT '' COMMENT '产线',
  station         VARCHAR(64)  DEFAULT '' COMMENT '当前工位',
  status          VARCHAR(16)  DEFAULT 'scheduled' COMMENT 'scheduled/running/waiting/warning/finished',
  priority        VARCHAR(8)   DEFAULT 'normal' COMMENT 'normal/urgent',
  plan_start      DATE         DEFAULT NULL,
  plan_end        DATE         DEFAULT NULL,
  source_order    VARCHAR(64)  DEFAULT '' COMMENT '关联销售订单',
  remark          VARCHAR(500) DEFAULT NULL,
  create_by       VARCHAR(64)  DEFAULT '',
  create_time     DATETIME     DEFAULT NULL,
  update_by       VARCHAR(64)  DEFAULT '',
  update_time     DATETIME     DEFAULT NULL,
  PRIMARY KEY (work_order_id),
  UNIQUE KEY uk_work_order_code (order_code),
  KEY idx_wo_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='生产工单';

DROP TABLE IF EXISTS mes_report;
CREATE TABLE mes_report (
  report_id       BIGINT       NOT NULL AUTO_INCREMENT COMMENT '报工ID',
  report_code     VARCHAR(64)  NOT NULL COMMENT '报工单号',
  order_code      VARCHAR(64)  NOT NULL COMMENT '工单号',
  station         VARCHAR(64)  DEFAULT '' COMMENT '工位',
  operator        VARCHAR(32)  DEFAULT '' COMMENT '报工人',
  qty_ok          DECIMAL(14,2) DEFAULT 0 COMMENT '合格数量',
  qty_ng          DECIMAL(14,2) DEFAULT 0 COMMENT '不良数量',
  shift           VARCHAR(32)  DEFAULT '' COMMENT '班次',
  report_time     DATETIME     DEFAULT NULL COMMENT '报工时间',
  remark          VARCHAR(500) DEFAULT NULL,
  create_by       VARCHAR(64)  DEFAULT '',
  create_time     DATETIME     DEFAULT NULL,
  update_by       VARCHAR(64)  DEFAULT '',
  update_time     DATETIME     DEFAULT NULL,
  PRIMARY KEY (report_id),
  KEY idx_report_order (order_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='生产报工记录';

DROP TABLE IF EXISTS mes_quality_issue;
CREATE TABLE mes_quality_issue (
  issue_id        BIGINT       NOT NULL AUTO_INCREMENT COMMENT '质量问题ID',
  issue_code      VARCHAR(64)  NOT NULL COMMENT '问题单号',
  source_type     VARCHAR(32)  DEFAULT 'process' COMMENT 'report报工/incoming来料/process过程',
  source_code     VARCHAR(64)  DEFAULT '' COMMENT '来源单号',
  product_code    VARCHAR(64)  DEFAULT '' COMMENT '产品编码',
  batch_no        VARCHAR(64)  DEFAULT '' COMMENT '批次号',
  serial_no       VARCHAR(64)  DEFAULT '' COMMENT '序列号',
  issue_type      VARCHAR(32)  DEFAULT '' COMMENT '问题类型',
  severity        VARCHAR(16)  DEFAULT 'normal' COMMENT 'serious/normal/info',
  qty             DECIMAL(14,2) DEFAULT 0 COMMENT '不良数量',
  description     VARCHAR(500) DEFAULT '' COMMENT '问题描述',
  dispose         VARCHAR(16)  DEFAULT '' COMMENT 'rework/concession/scrap',
  status          VARCHAR(16)  DEFAULT 'open' COMMENT 'open/isolated/reviewing/disposed/released',
  handler         VARCHAR(32)  DEFAULT '' COMMENT '处理人',
  found_time      DATETIME     DEFAULT NULL COMMENT '发现时间',
  close_time      DATETIME     DEFAULT NULL COMMENT '关闭时间',
  remark          VARCHAR(500) DEFAULT NULL,
  create_by       VARCHAR(64)  DEFAULT '',
  create_time     DATETIME     DEFAULT NULL,
  update_by       VARCHAR(64)  DEFAULT '',
  update_time     DATETIME     DEFAULT NULL,
  PRIMARY KEY (issue_id),
  UNIQUE KEY uk_issue_code (issue_code),
  KEY idx_issue_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='质量问题与不合格品';

DROP TABLE IF EXISTS mes_inspection;
CREATE TABLE mes_inspection (
  inspection_id   BIGINT       NOT NULL AUTO_INCREMENT COMMENT '检验ID',
  inspection_code VARCHAR(64)  NOT NULL COMMENT '检验单号',
  inspection_type VARCHAR(16)  DEFAULT 'patrol' COMMENT 'first首检/patrol巡检/final终检',
  order_code      VARCHAR(64)  DEFAULT '' COMMENT '工单号',
  product_code    VARCHAR(64)  DEFAULT '' COMMENT '产品编码',
  batch_no        VARCHAR(64)  DEFAULT '' COMMENT '批次号',
  sample_qty      DECIMAL(14,2) DEFAULT 0 COMMENT '抽样数',
  ng_qty          DECIMAL(14,2) DEFAULT 0 COMMENT '不良数',
  result          VARCHAR(16)  DEFAULT 'pass' COMMENT 'pass/fail/concession',
  inspector       VARCHAR(32)  DEFAULT '' COMMENT '检验员',
  inspect_time    DATETIME     DEFAULT NULL COMMENT '检验时间',
  remark          VARCHAR(500) DEFAULT NULL,
  create_by       VARCHAR(64)  DEFAULT '',
  create_time     DATETIME     DEFAULT NULL,
  update_by       VARCHAR(64)  DEFAULT '',
  update_time     DATETIME     DEFAULT NULL,
  PRIMARY KEY (inspection_id),
  UNIQUE KEY uk_inspection_code (inspection_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='质量检验任务';

DROP TABLE IF EXISTS mes_purchase_order;
CREATE TABLE mes_purchase_order (
  purchase_id     BIGINT       NOT NULL AUTO_INCREMENT COMMENT '采购订单ID',
  order_code      VARCHAR(64)  NOT NULL COMMENT '采购订单号',
  supplier_code   VARCHAR(32)  DEFAULT '' COMMENT '供应商编码',
  supplier_name   VARCHAR(128) DEFAULT '' COMMENT '供应商名称',
  material_code   VARCHAR(64)  DEFAULT '' COMMENT '物料编码',
  material_name   VARCHAR(128) DEFAULT '' COMMENT '物料名称',
  qty             DECIMAL(14,2) DEFAULT 0 COMMENT '订购数量',
  received_qty    DECIMAL(14,2) DEFAULT 0 COMMENT '已到货数量',
  price           DECIMAL(14,2) DEFAULT 0 COMMENT '单价',
  amount          DECIMAL(14,2) DEFAULT 0 COMMENT '金额',
  status          VARCHAR(16)  DEFAULT 'ordered' COMMENT 'ordered/partial/received/overdue/closed',
  order_date      DATE         DEFAULT NULL COMMENT '下单日期',
  expect_date     DATE         DEFAULT NULL COMMENT '期望到货日期',
  remark          VARCHAR(500) DEFAULT NULL,
  create_by       VARCHAR(64)  DEFAULT '',
  create_time     DATETIME     DEFAULT NULL,
  update_by       VARCHAR(64)  DEFAULT '',
  update_time     DATETIME     DEFAULT NULL,
  PRIMARY KEY (purchase_id),
  UNIQUE KEY uk_purchase_code (order_code),
  KEY idx_purchase_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='采购订单';

DROP TABLE IF EXISTS mes_arrival;
CREATE TABLE mes_arrival (
  arrival_id      BIGINT       NOT NULL AUTO_INCREMENT COMMENT '到货单ID',
  arrival_code    VARCHAR(64)  NOT NULL COMMENT '到货单号',
  order_code      VARCHAR(64)  DEFAULT '' COMMENT '采购订单号',
  supplier_name   VARCHAR(128) DEFAULT '' COMMENT '供应商',
  material_code   VARCHAR(64)  DEFAULT '' COMMENT '物料编码',
  material_name   VARCHAR(128) DEFAULT '' COMMENT '物料名称',
  qty             DECIMAL(14,2) DEFAULT 0 COMMENT '到货数量',
  qualified_qty   DECIMAL(14,2) DEFAULT 0 COMMENT '合格数量',
  rejected_qty    DECIMAL(14,2) DEFAULT 0 COMMENT '拒收数量',
  warehouse_code  VARCHAR(32)  DEFAULT 'WH-RM' COMMENT '入库仓库',
  arrival_date    DATE         DEFAULT NULL COMMENT '到货日期',
  status          VARCHAR(16)  DEFAULT 'pending' COMMENT 'pending待检/qualified合格/rejected拒收/stocked已入库',
  remark          VARCHAR(500) DEFAULT NULL,
  create_by       VARCHAR(64)  DEFAULT '',
  create_time     DATETIME     DEFAULT NULL,
  update_by       VARCHAR(64)  DEFAULT '',
  update_time     DATETIME     DEFAULT NULL,
  PRIMARY KEY (arrival_id),
  UNIQUE KEY uk_arrival_code (arrival_code),
  KEY idx_arrival_order (order_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='采购到货单';

DROP TABLE IF EXISTS mes_sales_order;
CREATE TABLE mes_sales_order (
  sales_id        BIGINT       NOT NULL AUTO_INCREMENT COMMENT '销售订单ID',
  order_code      VARCHAR(64)  NOT NULL COMMENT '销售订单号',
  customer_code   VARCHAR(32)  DEFAULT '' COMMENT '客户编码',
  customer_name   VARCHAR(128) DEFAULT '' COMMENT '客户名称',
  product_code    VARCHAR(64)  DEFAULT '' COMMENT '产品编码',
  product_name    VARCHAR(128) DEFAULT '' COMMENT '产品名称',
  qty             DECIMAL(14,2) DEFAULT 0 COMMENT '订购数量',
  shipped_qty     DECIMAL(14,2) DEFAULT 0 COMMENT '已发货数量',
  price           DECIMAL(14,2) DEFAULT 0 COMMENT '单价',
  amount          DECIMAL(14,2) DEFAULT 0 COMMENT '金额',
  status          VARCHAR(16)  DEFAULT 'pending' COMMENT 'pending/producing/partial/shipped/invoiced/received',
  order_date      DATE         DEFAULT NULL,
  delivery_date   DATE         DEFAULT NULL,
  remark          VARCHAR(500) DEFAULT NULL,
  create_by       VARCHAR(64)  DEFAULT '',
  create_time     DATETIME     DEFAULT NULL,
  update_by       VARCHAR(64)  DEFAULT '',
  update_time     DATETIME     DEFAULT NULL,
  PRIMARY KEY (sales_id),
  UNIQUE KEY uk_sales_code (order_code),
  KEY idx_sales_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='销售订单';

DROP TABLE IF EXISTS mes_shipment;
CREATE TABLE mes_shipment (
  shipment_id     BIGINT       NOT NULL AUTO_INCREMENT COMMENT '出库单ID',
  shipment_code   VARCHAR(64)  NOT NULL COMMENT '出库单号',
  order_code      VARCHAR(64)  DEFAULT '' COMMENT '销售订单号',
  customer_name   VARCHAR(128) DEFAULT '' COMMENT '客户',
  product_code    VARCHAR(64)  DEFAULT '' COMMENT '产品编码',
  product_name    VARCHAR(128) DEFAULT '' COMMENT '产品名称',
  qty             DECIMAL(14,2) DEFAULT 0 COMMENT '出库数量',
  price           DECIMAL(14,2) DEFAULT 0 COMMENT '单价',
  amount          DECIMAL(14,2) DEFAULT 0 COMMENT '金额',
  warehouse_code  VARCHAR(32)  DEFAULT 'WH-FG' COMMENT '出库仓库',
  ship_date       DATE         DEFAULT NULL COMMENT '出库日期',
  status          VARCHAR(16)  DEFAULT 'shipped' COMMENT 'shipped/invoiced/confirmed',
  remark          VARCHAR(500) DEFAULT NULL,
  create_by       VARCHAR(64)  DEFAULT '',
  create_time     DATETIME     DEFAULT NULL,
  update_by       VARCHAR(64)  DEFAULT '',
  update_time     DATETIME     DEFAULT NULL,
  PRIMARY KEY (shipment_id),
  UNIQUE KEY uk_shipment_code (shipment_code),
  KEY idx_shipment_order (order_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='销售出库单';

DROP TABLE IF EXISTS mes_stock;
CREATE TABLE mes_stock (
  stock_id        BIGINT       NOT NULL AUTO_INCREMENT COMMENT '库存ID',
  material_code   VARCHAR(64)  NOT NULL COMMENT '物料编码',
  material_name   VARCHAR(128) DEFAULT '' COMMENT '物料名称',
  spec            VARCHAR(128) DEFAULT '' COMMENT '规格',
  unit            VARCHAR(16)  DEFAULT '件' COMMENT '单位',
  warehouse_code  VARCHAR(32)  NOT NULL COMMENT '仓库编码',
  warehouse_name  VARCHAR(64)  DEFAULT '' COMMENT '仓库名称',
  batch_no        VARCHAR(64)  DEFAULT '' COMMENT '批次号',
  qty             DECIMAL(14,2) DEFAULT 0 COMMENT '库存数量',
  locked_qty      DECIMAL(14,2) DEFAULT 0 COMMENT '锁定数量',
  safety_qty      DECIMAL(14,2) DEFAULT 0 COMMENT '安全库存',
  price           DECIMAL(14,2) DEFAULT 0 COMMENT '单价',
  amount          DECIMAL(14,2) DEFAULT 0 COMMENT '金额',
  location        VARCHAR(32)  DEFAULT '' COMMENT '库位',
  status          VARCHAR(16)  DEFAULT 'normal' COMMENT 'normal/warning/shortage/idle',
  update_time     DATETIME     DEFAULT NULL,
  PRIMARY KEY (stock_id),
  UNIQUE KEY uk_stock (material_code, warehouse_code, batch_no),
  KEY idx_stock_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='实时库存';

DROP TABLE IF EXISTS mes_stock_txn;
CREATE TABLE mes_stock_txn (
  txn_id          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '流水ID',
  txn_code        VARCHAR(64)  NOT NULL COMMENT '流水号',
  txn_type        VARCHAR(16)  NOT NULL COMMENT 'inbound/outbound/transfer_in/transfer_out/check_in/check_out',
  source_type     VARCHAR(16)  DEFAULT '' COMMENT 'purchase/sales/production/other/transfer/check',
  source_code     VARCHAR(64)  DEFAULT '' COMMENT '来源单号',
  material_code   VARCHAR(64)  DEFAULT '' COMMENT '物料编码',
  material_name   VARCHAR(128) DEFAULT '' COMMENT '物料名称',
  warehouse_code  VARCHAR(32)  DEFAULT '' COMMENT '仓库编码',
  batch_no        VARCHAR(64)  DEFAULT '' COMMENT '批次号',
  qty             DECIMAL(14,2) DEFAULT 0 COMMENT '数量（正入负出）',
  unit            VARCHAR(16)  DEFAULT '件' COMMENT '单位',
  price           DECIMAL(14,2) DEFAULT 0 COMMENT '单价',
  amount          DECIMAL(14,2) DEFAULT 0 COMMENT '金额',
  txn_time        DATETIME     DEFAULT NULL COMMENT '发生时间',
  operator        VARCHAR(32)  DEFAULT '' COMMENT '操作人',
  remark          VARCHAR(500) DEFAULT NULL,
  PRIMARY KEY (txn_id),
  KEY idx_txn_material (material_code),
  KEY idx_txn_time (txn_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='库存出入库流水';

DROP TABLE IF EXISTS mes_equipment;
CREATE TABLE mes_equipment (
  equipment_id    BIGINT       NOT NULL AUTO_INCREMENT COMMENT '设备ID',
  equipment_code  VARCHAR(32)  NOT NULL COMMENT '设备编码',
  equipment_name  VARCHAR(128) NOT NULL COMMENT '设备名称',
  line_code       VARCHAR(32)  DEFAULT '' COMMENT '产线',
  equipment_type  VARCHAR(32)  DEFAULT '' COMMENT '类型',
  status          VARCHAR(16)  DEFAULT 'run' COMMENT 'run/idle/alarm/stop',
  oee             DECIMAL(5,1) DEFAULT 0 COMMENT 'OEE%',
  run_hours       DECIMAL(10,1) DEFAULT 0 COMMENT '累计运行小时',
  location        VARCHAR(64)  DEFAULT '' COMMENT '位置',
  last_check_time DATETIME     DEFAULT NULL COMMENT '最近点检时间',
  remark          VARCHAR(500) DEFAULT NULL,
  create_by       VARCHAR(64)  DEFAULT '',
  create_time     DATETIME     DEFAULT NULL,
  update_by       VARCHAR(64)  DEFAULT '',
  update_time     DATETIME     DEFAULT NULL,
  PRIMARY KEY (equipment_id),
  UNIQUE KEY uk_equipment_code (equipment_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='设备台账';

DROP TABLE IF EXISTS mes_alarm;
CREATE TABLE mes_alarm (
  alarm_id        BIGINT       NOT NULL AUTO_INCREMENT COMMENT '报警ID',
  alarm_code      VARCHAR(64)  NOT NULL COMMENT '报警单号',
  equipment_code  VARCHAR(32)  DEFAULT '' COMMENT '设备编码',
  equipment_name  VARCHAR(128) DEFAULT '' COMMENT '设备名称',
  level           VARCHAR(16)  DEFAULT 'normal' COMMENT 'serious/normal/info',
  content         VARCHAR(500) DEFAULT '' COMMENT '报警内容',
  alarm_time      DATETIME     DEFAULT NULL COMMENT '报警时间',
  status          VARCHAR(16)  DEFAULT 'active' COMMENT 'active/handled',
  handler         VARCHAR(32)  DEFAULT '' COMMENT '处理人',
  remark          VARCHAR(500) DEFAULT NULL,
  create_by       VARCHAR(64)  DEFAULT '',
  create_time     DATETIME     DEFAULT NULL,
  update_by       VARCHAR(64)  DEFAULT '',
  update_time     DATETIME     DEFAULT NULL,
  PRIMARY KEY (alarm_id),
  UNIQUE KEY uk_alarm_code (alarm_code),
  KEY idx_alarm_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='设备报警';

DROP TABLE IF EXISTS mes_trace;
CREATE TABLE mes_trace (
  trace_id        BIGINT       NOT NULL AUTO_INCREMENT COMMENT '追溯ID',
  serial_no       VARCHAR(64)  NOT NULL COMMENT '序列号',
  batch_no        VARCHAR(64)  DEFAULT '' COMMENT '批次号',
  product_code    VARCHAR(64)  DEFAULT '' COMMENT '产品编码',
  product_name    VARCHAR(128) DEFAULT '' COMMENT '产品名称',
  order_code      VARCHAR(64)  DEFAULT '' COMMENT '工单号',
  station         VARCHAR(64)  DEFAULT '' COMMENT '当前/最后工位',
  key_material    VARCHAR(128) DEFAULT '' COMMENT '关键件',
  supplier_batch  VARCHAR(64)  DEFAULT '' COMMENT '供应商批次',
  inspection_result VARCHAR(16) DEFAULT 'pass' COMMENT 'pass/fail/concession',
  produced_time   DATETIME     DEFAULT NULL COMMENT '生产时间',
  status          VARCHAR(16)  DEFAULT 'released' COMMENT 'released/frozen',
  remark          VARCHAR(500) DEFAULT NULL,
  create_by       VARCHAR(64)  DEFAULT '',
  create_time     DATETIME     DEFAULT NULL,
  update_by       VARCHAR(64)  DEFAULT '',
  update_time     DATETIME     DEFAULT NULL,
  PRIMARY KEY (trace_id),
  UNIQUE KEY uk_trace_serial (serial_no),
  KEY idx_trace_batch (batch_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='质量追溯档案';

DROP TABLE IF EXISTS mes_collab;
CREATE TABLE mes_collab (
  collab_id       BIGINT       NOT NULL AUTO_INCREMENT COMMENT '协同单ID',
  collab_code     VARCHAR(64)  NOT NULL COMMENT '协同单号',
  collab_type     VARCHAR(16)  NOT NULL COMMENT 'delivery交付计划/shipping送货/sample样品/exception异常/material车间物料',
  supplier_code   VARCHAR(32)  DEFAULT '' COMMENT '供应商编码',
  supplier_name   VARCHAR(128) DEFAULT '' COMMENT '供应商',
  material_code   VARCHAR(64)  DEFAULT '' COMMENT '物料编码',
  material_name   VARCHAR(128) DEFAULT '' COMMENT '物料名称',
  qty             DECIMAL(14,2) DEFAULT 0 COMMENT '数量',
  plan_date       DATE         DEFAULT NULL COMMENT '计划日期',
  actual_date     DATE         DEFAULT NULL COMMENT '实际日期',
  status          VARCHAR(16)  DEFAULT 'pending' COMMENT 'pending/running/finished/overdue/abnormal',
  content         VARCHAR(500) DEFAULT '' COMMENT '内容/说明',
  handler         VARCHAR(32)  DEFAULT '' COMMENT '处理人',
  remark          VARCHAR(500) DEFAULT NULL,
  create_by       VARCHAR(64)  DEFAULT '',
  create_time     DATETIME     DEFAULT NULL,
  update_by       VARCHAR(64)  DEFAULT '',
  update_time     DATETIME     DEFAULT NULL,
  PRIMARY KEY (collab_id),
  UNIQUE KEY uk_collab_code (collab_code),
  KEY idx_collab_type (collab_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='供应链协同单（交付计划/送货/样品/异常/车间物料）';

DROP TABLE IF EXISTS mes_voucher;
CREATE TABLE mes_voucher (
  voucher_id      BIGINT       NOT NULL AUTO_INCREMENT COMMENT '凭证ID',
  voucher_code    VARCHAR(64)  NOT NULL COMMENT '凭证号',
  biz_type        VARCHAR(32)  NOT NULL COMMENT 'purchase_in/product_in/sales_out/production_pick/other/check_adjust',
  biz_code        VARCHAR(64)  DEFAULT '' COMMENT '来源单号',
  qty             DECIMAL(14,2) DEFAULT 0 COMMENT '数量',
  amount          DECIMAL(14,2) DEFAULT 0 COMMENT '金额',
  account_period  VARCHAR(7)   DEFAULT '' COMMENT '会计期间 yyyy-MM',
  status          VARCHAR(16)  DEFAULT 'pending' COMMENT 'pending待记账/posted已记账',
  u8c_status      VARCHAR(16)  DEFAULT 'pending' COMMENT 'pending/success/failed',
  sync_time       DATETIME     DEFAULT NULL COMMENT '同步时间',
  message         VARCHAR(500) DEFAULT '' COMMENT '同步结果信息',
  remark          VARCHAR(500) DEFAULT NULL,
  create_by       VARCHAR(64)  DEFAULT '',
  create_time     DATETIME     DEFAULT NULL,
  update_by       VARCHAR(64)  DEFAULT '',
  update_time     DATETIME     DEFAULT NULL,
  PRIMARY KEY (voucher_id),
  UNIQUE KEY uk_voucher_code (voucher_code),
  KEY idx_voucher_status (status),
  KEY idx_voucher_u8c (u8c_status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='存货核算凭证与U8C同步';

DROP TABLE IF EXISTS mes_interface;
CREATE TABLE mes_interface (
  interface_id    BIGINT       NOT NULL AUTO_INCREMENT COMMENT '接口ID',
  interface_code  VARCHAR(64)  NOT NULL COMMENT '接口编码',
  interface_name  VARCHAR(128) NOT NULL COMMENT '接口名称',
  target_system   VARCHAR(32)  DEFAULT '' COMMENT '目标系统 U8C/ERP/WMS/SCADA/QMS',
  status          VARCHAR(16)  DEFAULT 'running' COMMENT 'running/alarm/stopped',
  last_sync       DATETIME     DEFAULT NULL COMMENT '最近同步时间',
  success_rate    DECIMAL(5,1) DEFAULT 100 COMMENT '成功率%',
  remark          VARCHAR(500) DEFAULT NULL,
  create_by       VARCHAR(64)  DEFAULT '',
  create_time     DATETIME     DEFAULT NULL,
  update_by       VARCHAR(64)  DEFAULT '',
  update_time     DATETIME     DEFAULT NULL,
  PRIMARY KEY (interface_id),
  UNIQUE KEY uk_interface_code (interface_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统集成接口';
