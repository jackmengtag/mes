-- ----------------------------
-- 制造执行 MES 菜单权限初始化脚本
-- 说明：menu_id 从 3000 起始，与 ry_20260417.sql 不冲突
--       biz 与中文模块：md=主数据管理 om=订单管理 ps=计划排程 wo=工单管理
--       ex=生产执行 mt=物料管理 qc=质量管理 eq=设备管理 tr=追溯管理
--       bi=看板与分析 integration=集成管理
-- ----------------------------

-- 一级目录
insert into sys_menu values('3000', '制造执行 MES', '0',    '5', 'mes', null, '', '', 1, 0, 'M', '0', '0', '', 'tool', 'admin', sysdate(), '', null, '制造执行MES目录');
-- 二级目录
insert into sys_menu values('3001', '主数据管理', '3000', '1',  'md',          null, '', '', 1, 0, 'M', '0', '0', '', 'tree',       'admin', sysdate(), '', null, '主数据管理目录');
insert into sys_menu values('3002', '订单管理',   '3000', '2',  'om',          null, '', '', 1, 0, 'M', '0', '0', '', 'form',       'admin', sysdate(), '', null, '订单管理目录');
insert into sys_menu values('3003', '计划排程',   '3000', '3',  'ps',          null, '', '', 1, 0, 'M', '0', '0', '', 'job',        'admin', sysdate(), '', null, '计划排程目录');
insert into sys_menu values('3004', '工单管理',   '3000', '4',  'wo',          null, '', '', 1, 0, 'M', '0', '0', '', 'tree-table', 'admin', sysdate(), '', null, '工单管理目录');
insert into sys_menu values('3005', '生产执行',   '3000', '5',  'ex',          null, '', '', 1, 0, 'M', '0', '0', '', 'build',      'admin', sysdate(), '', null, '生产执行目录');
insert into sys_menu values('3006', '物料管理',   '3000', '6',  'mt',          null, '', '', 1, 0, 'M', '0', '0', '', 'shopping',   'admin', sysdate(), '', null, '物料管理目录');
insert into sys_menu values('3007', '质量管理',   '3000', '7',  'qc',          null, '', '', 1, 0, 'M', '0', '0', '', 'star',       'admin', sysdate(), '', null, '质量管理目录');
insert into sys_menu values('3008', '设备管理',   '3000', '8',  'eq',          null, '', '', 1, 0, 'M', '0', '0', '', 'server',     'admin', sysdate(), '', null, '设备管理目录');
insert into sys_menu values('3009', '追溯管理',   '3000', '9',  'tr',          null, '', '', 1, 0, 'M', '0', '0', '', 'search',     'admin', sysdate(), '', null, '追溯管理目录');
insert into sys_menu values('3010', '看板与分析', '3000', '10', 'bi',          null, '', '', 1, 0, 'M', '0', '0', '', 'chart',      'admin', sysdate(), '', null, '看板与分析目录');
insert into sys_menu values('3011', '集成管理',   '3000', '11', 'integration', null, '', '', 1, 0, 'M', '0', '0', '', 'link',       'admin', sysdate(), '', null, '集成管理目录');

-- ============================
-- 主数据管理（md）
-- ============================
-- 物料主数据
insert into sys_menu values('3100', '物料主数据', '3001', '1', 'material', 'mes/md/material/index', '', '', 1, 0, 'C', '0', '0', 'mes:md:material:list', '#', 'admin', sysdate(), '', null, '物料主数据菜单');
insert into sys_menu values('3101', '查询', '3100', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:material:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3102', '详情', '3100', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:material:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3103', '新增', '3100', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:material:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3104', '修改', '3100', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:material:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3105', '删除', '3100', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:material:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3106', '导出', '3100', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:material:export', '#', 'admin', sysdate(), '', null, '');
-- 产品主数据
insert into sys_menu values('3110', '产品主数据', '3001', '2', 'product', 'mes/md/product/index', '', '', 1, 0, 'C', '0', '0', 'mes:md:product:list', '#', 'admin', sysdate(), '', null, '产品主数据菜单');
insert into sys_menu values('3111', '查询', '3110', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:product:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3112', '详情', '3110', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:product:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3113', '新增', '3110', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:product:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3114', '修改', '3110', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:product:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3115', '删除', '3110', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:product:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3116', '导出', '3110', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:product:export', '#', 'admin', sysdate(), '', null, '');
-- 产品版本
insert into sys_menu values('3120', '产品版本', '3001', '3', 'productVersion', 'mes/md/productVersion/index', '', '', 1, 0, 'C', '0', '0', 'mes:md:productVersion:list', '#', 'admin', sysdate(), '', null, '产品版本菜单');
insert into sys_menu values('3121', '查询', '3120', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:productVersion:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3122', '详情', '3120', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:productVersion:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3123', '新增', '3120', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:productVersion:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3124', '修改', '3120', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:productVersion:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3125', '删除', '3120', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:productVersion:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3126', '导出', '3120', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:productVersion:export', '#', 'admin', sysdate(), '', null, '');
-- BOM主数据
insert into sys_menu values('3130', 'BOM主数据', '3001', '4', 'bom', 'mes/md/bom/index', '', '', 1, 0, 'C', '0', '0', 'mes:md:bom:list', '#', 'admin', sysdate(), '', null, 'BOM主数据菜单');
insert into sys_menu values('3131', '查询', '3130', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:bom:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3132', '详情', '3130', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:bom:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3133', '新增', '3130', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:bom:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3134', '修改', '3130', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:bom:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3135', '删除', '3130', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:bom:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3136', '导出', '3130', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:bom:export', '#', 'admin', sysdate(), '', null, '');
-- BOM明细
insert into sys_menu values('3140', 'BOM明细', '3001', '5', 'bomLine', 'mes/md/bomLine/index', '', '', 1, 0, 'C', '0', '0', 'mes:md:bomLine:list', '#', 'admin', sysdate(), '', null, 'BOM明细菜单');
insert into sys_menu values('3141', '查询', '3140', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:bomLine:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3142', '详情', '3140', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:bomLine:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3143', '新增', '3140', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:bomLine:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3144', '修改', '3140', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:bomLine:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3145', '删除', '3140', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:bomLine:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3146', '导出', '3140', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:bomLine:export', '#', 'admin', sysdate(), '', null, '');
-- 工艺路线
insert into sys_menu values('3150', '工艺路线', '3001', '6', 'route', 'mes/md/route/index', '', '', 1, 0, 'C', '0', '0', 'mes:md:route:list', '#', 'admin', sysdate(), '', null, '工艺路线菜单');
insert into sys_menu values('3151', '查询', '3150', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:route:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3152', '详情', '3150', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:route:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3153', '新增', '3150', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:route:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3154', '修改', '3150', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:route:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3155', '删除', '3150', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:route:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3156', '导出', '3150', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:route:export', '#', 'admin', sysdate(), '', null, '');
-- 工艺工序
insert into sys_menu values('3160', '工艺工序', '3001', '7', 'routeOperation', 'mes/md/routeOperation/index', '', '', 1, 0, 'C', '0', '0', 'mes:md:routeOperation:list', '#', 'admin', sysdate(), '', null, '工艺工序菜单');
insert into sys_menu values('3161', '查询', '3160', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:routeOperation:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3162', '详情', '3160', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:routeOperation:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3163', '新增', '3160', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:routeOperation:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3164', '修改', '3160', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:routeOperation:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3165', '删除', '3160', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:routeOperation:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3166', '导出', '3160', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:routeOperation:export', '#', 'admin', sysdate(), '', null, '');
-- 工作中心
insert into sys_menu values('3170', '工作中心', '3001', '8', 'workCenter', 'mes/md/workCenter/index', '', '', 1, 0, 'C', '0', '0', 'mes:md:workCenter:list', '#', 'admin', sysdate(), '', null, '工作中心菜单');
insert into sys_menu values('3171', '查询', '3170', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:workCenter:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3172', '详情', '3170', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:workCenter:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3173', '新增', '3170', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:workCenter:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3174', '修改', '3170', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:workCenter:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3175', '删除', '3170', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:workCenter:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3176', '导出', '3170', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:workCenter:export', '#', 'admin', sysdate(), '', null, '');
-- 产线
insert into sys_menu values('3180', '产线', '3001', '9', 'line', 'mes/md/line/index', '', '', 1, 0, 'C', '0', '0', 'mes:md:line:list', '#', 'admin', sysdate(), '', null, '产线菜单');
insert into sys_menu values('3181', '查询', '3180', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:line:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3182', '详情', '3180', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:line:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3183', '新增', '3180', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:line:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3184', '修改', '3180', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:line:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3185', '删除', '3180', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:line:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3186', '导出', '3180', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:line:export', '#', 'admin', sysdate(), '', null, '');
-- 工位
insert into sys_menu values('3190', '工位', '3001', '10', 'station', 'mes/md/station/index', '', '', 1, 0, 'C', '0', '0', 'mes:md:station:list', '#', 'admin', sysdate(), '', null, '工位菜单');
insert into sys_menu values('3191', '查询', '3190', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:station:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3192', '详情', '3190', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:station:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3193', '新增', '3190', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:station:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3194', '修改', '3190', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:station:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3195', '删除', '3190', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:station:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3196', '导出', '3190', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:station:export', '#', 'admin', sysdate(), '', null, '');
-- 设备档案
insert into sys_menu values('3200', '设备档案', '3001', '11', 'equipment', 'mes/md/equipment/index', '', '', 1, 0, 'C', '0', '0', 'mes:md:equipment:list', '#', 'admin', sysdate(), '', null, '设备档案菜单');
insert into sys_menu values('3201', '查询', '3200', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:equipment:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3202', '详情', '3200', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:equipment:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3203', '新增', '3200', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:equipment:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3204', '修改', '3200', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:equipment:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3205', '删除', '3200', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:equipment:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3206', '导出', '3200', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:equipment:export', '#', 'admin', sysdate(), '', null, '');
-- 设备采集点位
insert into sys_menu values('3210', '设备采集点位', '3001', '12', 'equipmentPoint', 'mes/md/equipmentPoint/index', '', '', 1, 0, 'C', '0', '0', 'mes:md:equipmentPoint:list', '#', 'admin', sysdate(), '', null, '设备采集点位菜单');
insert into sys_menu values('3211', '查询', '3210', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:equipmentPoint:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3212', '详情', '3210', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:equipmentPoint:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3213', '新增', '3210', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:equipmentPoint:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3214', '修改', '3210', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:equipmentPoint:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3215', '删除', '3210', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:equipmentPoint:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3216', '导出', '3210', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:equipmentPoint:export', '#', 'admin', sysdate(), '', null, '');
-- 员工
insert into sys_menu values('3220', '员工', '3001', '13', 'employee', 'mes/md/employee/index', '', '', 1, 0, 'C', '0', '0', 'mes:md:employee:list', '#', 'admin', sysdate(), '', null, '员工菜单');
insert into sys_menu values('3221', '查询', '3220', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:employee:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3222', '详情', '3220', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:employee:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3223', '新增', '3220', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:employee:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3224', '修改', '3220', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:employee:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3225', '删除', '3220', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:employee:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3226', '导出', '3220', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:employee:export', '#', 'admin', sysdate(), '', null, '');
-- 班组
insert into sys_menu values('3230', '班组', '3001', '14', 'team', 'mes/md/team/index', '', '', 1, 0, 'C', '0', '0', 'mes:md:team:list', '#', 'admin', sysdate(), '', null, '班组菜单');
insert into sys_menu values('3231', '查询', '3230', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:team:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3232', '详情', '3230', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:team:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3233', '新增', '3230', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:team:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3234', '修改', '3230', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:team:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3235', '删除', '3230', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:team:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3236', '导出', '3230', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:team:export', '#', 'admin', sysdate(), '', null, '');
-- 班次
insert into sys_menu values('3240', '班次', '3001', '15', 'shift', 'mes/md/shift/index', '', '', 1, 0, 'C', '0', '0', 'mes:md:shift:list', '#', 'admin', sysdate(), '', null, '班次菜单');
insert into sys_menu values('3241', '查询', '3240', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:shift:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3242', '详情', '3240', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:shift:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3243', '新增', '3240', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:shift:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3244', '修改', '3240', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:shift:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3245', '删除', '3240', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:shift:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3246', '导出', '3240', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:shift:export', '#', 'admin', sysdate(), '', null, '');
-- 缺陷代码
insert into sys_menu values('3250', '缺陷代码', '3001', '16', 'defectCode', 'mes/md/defectCode/index', '', '', 1, 0, 'C', '0', '0', 'mes:md:defectCode:list', '#', 'admin', sysdate(), '', null, '缺陷代码菜单');
insert into sys_menu values('3251', '查询', '3250', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:defectCode:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3252', '详情', '3250', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:defectCode:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3253', '新增', '3250', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:defectCode:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3254', '修改', '3250', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:defectCode:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3255', '删除', '3250', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:defectCode:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3256', '导出', '3250', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:defectCode:export', '#', 'admin', sysdate(), '', null, '');
-- 检验项目
insert into sys_menu values('3260', '检验项目', '3001', '17', 'inspectionItem', 'mes/md/inspectionItem/index', '', '', 1, 0, 'C', '0', '0', 'mes:md:inspectionItem:list', '#', 'admin', sysdate(), '', null, '检验项目菜单');
insert into sys_menu values('3261', '查询', '3260', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:inspectionItem:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3262', '详情', '3260', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:inspectionItem:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3263', '新增', '3260', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:inspectionItem:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3264', '修改', '3260', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:inspectionItem:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3265', '删除', '3260', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:inspectionItem:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3266', '导出', '3260', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:inspectionItem:export', '#', 'admin', sysdate(), '', null, '');
-- 检验方案
insert into sys_menu values('3270', '检验方案', '3001', '18', 'inspectionPlan', 'mes/md/inspectionPlan/index', '', '', 1, 0, 'C', '0', '0', 'mes:md:inspectionPlan:list', '#', 'admin', sysdate(), '', null, '检验方案菜单');
insert into sys_menu values('3271', '查询', '3270', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:inspectionPlan:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3272', '详情', '3270', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:inspectionPlan:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3273', '新增', '3270', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:inspectionPlan:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3274', '修改', '3270', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:inspectionPlan:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3275', '删除', '3270', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:inspectionPlan:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3276', '导出', '3270', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:inspectionPlan:export', '#', 'admin', sysdate(), '', null, '');
-- 检验方案明细
insert into sys_menu values('3280', '检验方案明细', '3001', '19', 'inspectionPlanItem', 'mes/md/inspectionPlanItem/index', '', '', 1, 0, 'C', '0', '0', 'mes:md:inspectionPlanItem:list', '#', 'admin', sysdate(), '', null, '检验方案明细菜单');
insert into sys_menu values('3281', '查询', '3280', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:inspectionPlanItem:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3282', '详情', '3280', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:inspectionPlanItem:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3283', '新增', '3280', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:inspectionPlanItem:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3284', '修改', '3280', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:inspectionPlanItem:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3285', '删除', '3280', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:inspectionPlanItem:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3286', '导出', '3280', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:inspectionPlanItem:export', '#', 'admin', sysdate(), '', null, '');
-- 主数据版本审批
insert into sys_menu values('3290', '主数据版本审批', '3001', '20', 'versionApproval', 'mes/md/versionApproval/index', '', '', 1, 0, 'C', '0', '0', 'mes:md:versionApproval:list', '#', 'admin', sysdate(), '', null, '主数据版本审批菜单');
insert into sys_menu values('3291', '查询', '3290', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:versionApproval:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3292', '详情', '3290', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:versionApproval:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3293', '新增', '3290', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:versionApproval:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3294', '修改', '3290', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:versionApproval:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3295', '删除', '3290', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:versionApproval:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3296', '导出', '3290', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:md:versionApproval:export', '#', 'admin', sysdate(), '', null, '');

-- ============================
-- 订单管理（om）
-- ============================
-- 生产需求
insert into sys_menu values('3300', '生产需求', '3002', '1', 'demand', 'mes/om/demand/index', '', '', 1, 0, 'C', '0', '0', 'mes:om:demand:list', '#', 'admin', sysdate(), '', null, '生产需求菜单');
insert into sys_menu values('3301', '查询', '3300', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:om:demand:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3302', '详情', '3300', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:om:demand:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3303', '新增', '3300', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:om:demand:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3304', '修改', '3300', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:om:demand:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3305', '删除', '3300', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:om:demand:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3306', '导出', '3300', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:om:demand:export', '#', 'admin', sysdate(), '', null, '');

-- ============================
-- 计划排程（ps）
-- ============================
-- 生产计划
insert into sys_menu values('3310', '生产计划', '3003', '1', 'plan', 'mes/ps/plan/index', '', '', 1, 0, 'C', '0', '0', 'mes:ps:plan:list', '#', 'admin', sysdate(), '', null, '生产计划菜单');
insert into sys_menu values('3311', '查询', '3310', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ps:plan:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3312', '详情', '3310', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ps:plan:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3313', '新增', '3310', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ps:plan:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3314', '修改', '3310', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ps:plan:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3315', '删除', '3310', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ps:plan:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3316', '导出', '3310', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ps:plan:export', '#', 'admin', sysdate(), '', null, '');
-- 排程任务
insert into sys_menu values('3320', '排程任务', '3003', '2', 'scheduleTask', 'mes/ps/scheduleTask/index', '', '', 1, 0, 'C', '0', '0', 'mes:ps:scheduleTask:list', '#', 'admin', sysdate(), '', null, '排程任务菜单');
insert into sys_menu values('3321', '查询', '3320', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ps:scheduleTask:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3322', '详情', '3320', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ps:scheduleTask:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3323', '新增', '3320', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ps:scheduleTask:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3324', '修改', '3320', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ps:scheduleTask:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3325', '删除', '3320', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ps:scheduleTask:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3326', '导出', '3320', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ps:scheduleTask:export', '#', 'admin', sysdate(), '', null, '');

-- ============================
-- 工单管理（wo）
-- ============================
-- 生产工单
insert into sys_menu values('3330', '生产工单', '3004', '1', 'order', 'mes/wo/order/index', '', '', 1, 0, 'C', '0', '0', 'mes:wo:order:list', '#', 'admin', sysdate(), '', null, '生产工单菜单');
insert into sys_menu values('3331', '查询', '3330', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:wo:order:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3332', '详情', '3330', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:wo:order:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3333', '新增', '3330', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:wo:order:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3334', '修改', '3330', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:wo:order:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3335', '删除', '3330', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:wo:order:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3336', '导出', '3330', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:wo:order:export', '#', 'admin', sysdate(), '', null, '');
-- 工序任务
insert into sys_menu values('3340', '工序任务', '3004', '2', 'operationTask', 'mes/wo/operationTask/index', '', '', 1, 0, 'C', '0', '0', 'mes:wo:operationTask:list', '#', 'admin', sysdate(), '', null, '工序任务菜单');
insert into sys_menu values('3341', '查询', '3340', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:wo:operationTask:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3342', '详情', '3340', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:wo:operationTask:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3343', '新增', '3340', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:wo:operationTask:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3344', '修改', '3340', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:wo:operationTask:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3345', '删除', '3340', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:wo:operationTask:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3346', '导出', '3340', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:wo:operationTask:export', '#', 'admin', sysdate(), '', null, '');
-- 返工工单
insert into sys_menu values('3350', '返工工单', '3004', '3', 'reworkOrder', 'mes/wo/reworkOrder/index', '', '', 1, 0, 'C', '0', '0', 'mes:wo:reworkOrder:list', '#', 'admin', sysdate(), '', null, '返工工单菜单');
insert into sys_menu values('3351', '查询', '3350', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:wo:reworkOrder:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3352', '详情', '3350', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:wo:reworkOrder:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3353', '新增', '3350', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:wo:reworkOrder:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3354', '修改', '3350', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:wo:reworkOrder:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3355', '删除', '3350', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:wo:reworkOrder:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3356', '导出', '3350', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:wo:reworkOrder:export', '#', 'admin', sysdate(), '', null, '');

-- ============================
-- 生产执行（ex）
-- ============================
-- 报工记录
insert into sys_menu values('3360', '报工记录', '3005', '1', 'report', 'mes/ex/report/index', '', '', 1, 0, 'C', '0', '0', 'mes:ex:report:list', '#', 'admin', sysdate(), '', null, '报工记录菜单');
insert into sys_menu values('3361', '查询', '3360', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:report:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3362', '详情', '3360', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:report:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3363', '新增', '3360', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:report:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3364', '修改', '3360', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:report:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3365', '删除', '3360', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:report:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3366', '导出', '3360', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:report:export', '#', 'admin', sysdate(), '', null, '');
-- 在制品
insert into sys_menu values('3370', '在制品', '3005', '2', 'wip', 'mes/ex/wip/index', '', '', 1, 0, 'C', '0', '0', 'mes:ex:wip:list', '#', 'admin', sysdate(), '', null, '在制品菜单');
insert into sys_menu values('3371', '查询', '3370', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:wip:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3372', '详情', '3370', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:wip:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3373', '新增', '3370', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:wip:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3374', '修改', '3370', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:wip:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3375', '删除', '3370', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:wip:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3376', '导出', '3370', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:wip:export', '#', 'admin', sysdate(), '', null, '');
-- 产品序列号
insert into sys_menu values('3380', '产品序列号', '3005', '3', 'serial', 'mes/ex/serial/index', '', '', 1, 0, 'C', '0', '0', 'mes:ex:serial:list', '#', 'admin', sysdate(), '', null, '产品序列号菜单');
insert into sys_menu values('3381', '查询', '3380', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:serial:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3382', '详情', '3380', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:serial:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3383', '新增', '3380', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:serial:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3384', '修改', '3380', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:serial:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3385', '删除', '3380', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:serial:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3386', '导出', '3380', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:serial:export', '#', 'admin', sysdate(), '', null, '');
-- 工艺参数采集
insert into sys_menu values('3390', '工艺参数采集', '3005', '4', 'processParam', 'mes/ex/processParam/index', '', '', 1, 0, 'C', '0', '0', 'mes:ex:processParam:list', '#', 'admin', sysdate(), '', null, '工艺参数采集菜单');
insert into sys_menu values('3391', '查询', '3390', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:processParam:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3392', '详情', '3390', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:processParam:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3393', '新增', '3390', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:processParam:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3394', '修改', '3390', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:processParam:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3395', '删除', '3390', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:processParam:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3396', '导出', '3390', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:processParam:export', '#', 'admin', sysdate(), '', null, '');
-- 工时记录
insert into sys_menu values('3400', '工时记录', '3005', '5', 'workHour', 'mes/ex/workHour/index', '', '', 1, 0, 'C', '0', '0', 'mes:ex:workHour:list', '#', 'admin', sysdate(), '', null, '工时记录菜单');
insert into sys_menu values('3401', '查询', '3400', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:workHour:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3402', '详情', '3400', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:workHour:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3403', '新增', '3400', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:workHour:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3404', '修改', '3400', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:workHour:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3405', '删除', '3400', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:workHour:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3406', '导出', '3400', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:workHour:export', '#', 'admin', sysdate(), '', null, '');
-- 现场异常上报
insert into sys_menu values('3410', '现场异常上报', '3005', '6', 'exceptionReport', 'mes/ex/exceptionReport/index', '', '', 1, 0, 'C', '0', '0', 'mes:ex:exceptionReport:list', '#', 'admin', sysdate(), '', null, '现场异常上报菜单');
insert into sys_menu values('3411', '查询', '3410', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:exceptionReport:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3412', '详情', '3410', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:exceptionReport:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3413', '新增', '3410', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:exceptionReport:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3414', '修改', '3410', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:exceptionReport:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3415', '删除', '3410', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:exceptionReport:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3416', '导出', '3410', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:ex:exceptionReport:export', '#', 'admin', sysdate(), '', null, '');

-- ============================
-- 物料管理（mt）
-- ============================
-- 物料需求
insert into sys_menu values('3420', '物料需求', '3006', '1', 'requirement', 'mes/mt/requirement/index', '', '', 1, 0, 'C', '0', '0', 'mes:mt:requirement:list', '#', 'admin', sysdate(), '', null, '物料需求菜单');
insert into sys_menu values('3421', '查询', '3420', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:mt:requirement:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3422', '详情', '3420', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:mt:requirement:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3423', '新增', '3420', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:mt:requirement:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3424', '修改', '3420', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:mt:requirement:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3425', '删除', '3420', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:mt:requirement:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3426', '导出', '3420', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:mt:requirement:export', '#', 'admin', sysdate(), '', null, '');
-- 物料批次消耗
insert into sys_menu values('3430', '物料批次消耗', '3006', '2', 'consumption', 'mes/mt/consumption/index', '', '', 1, 0, 'C', '0', '0', 'mes:mt:consumption:list', '#', 'admin', sysdate(), '', null, '物料批次消耗菜单');
insert into sys_menu values('3431', '查询', '3430', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:mt:consumption:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3432', '详情', '3430', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:mt:consumption:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3433', '新增', '3430', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:mt:consumption:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3434', '修改', '3430', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:mt:consumption:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3435', '删除', '3430', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:mt:consumption:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3436', '导出', '3430', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:mt:consumption:export', '#', 'admin', sysdate(), '', null, '');
-- 补料
insert into sys_menu values('3440', '补料', '3006', '3', 'supplementMaterial', 'mes/mt/supplementMaterial/index', '', '', 1, 0, 'C', '0', '0', 'mes:mt:supplementMaterial:list', '#', 'admin', sysdate(), '', null, '补料菜单');
insert into sys_menu values('3441', '查询', '3440', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:mt:supplementMaterial:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3442', '详情', '3440', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:mt:supplementMaterial:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3443', '新增', '3440', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:mt:supplementMaterial:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3444', '修改', '3440', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:mt:supplementMaterial:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3445', '删除', '3440', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:mt:supplementMaterial:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3446', '导出', '3440', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:mt:supplementMaterial:export', '#', 'admin', sysdate(), '', null, '');
-- 退料
insert into sys_menu values('3450', '退料', '3006', '4', 'returnMaterial', 'mes/mt/returnMaterial/index', '', '', 1, 0, 'C', '0', '0', 'mes:mt:returnMaterial:list', '#', 'admin', sysdate(), '', null, '退料菜单');
insert into sys_menu values('3451', '查询', '3450', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:mt:returnMaterial:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3452', '详情', '3450', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:mt:returnMaterial:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3453', '新增', '3450', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:mt:returnMaterial:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3454', '修改', '3450', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:mt:returnMaterial:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3455', '删除', '3450', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:mt:returnMaterial:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3456', '导出', '3450', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:mt:returnMaterial:export', '#', 'admin', sysdate(), '', null, '');

-- ============================
-- 质量管理（qc）
-- ============================
-- 质量检验
insert into sys_menu values('3460', '质量检验', '3007', '1', 'inspection', 'mes/qc/inspection/index', '', '', 1, 0, 'C', '0', '0', 'mes:qc:inspection:list', '#', 'admin', sysdate(), '', null, '质量检验菜单');
insert into sys_menu values('3461', '查询', '3460', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:qc:inspection:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3462', '详情', '3460', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:qc:inspection:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3463', '新增', '3460', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:qc:inspection:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3464', '修改', '3460', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:qc:inspection:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3465', '删除', '3460', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:qc:inspection:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3466', '导出', '3460', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:qc:inspection:export', '#', 'admin', sysdate(), '', null, '');
-- 检验项目值
insert into sys_menu values('3470', '检验项目值', '3007', '2', 'inspectionValue', 'mes/qc/inspectionValue/index', '', '', 1, 0, 'C', '0', '0', 'mes:qc:inspectionValue:list', '#', 'admin', sysdate(), '', null, '检验项目值菜单');
insert into sys_menu values('3471', '查询', '3470', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:qc:inspectionValue:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3472', '详情', '3470', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:qc:inspectionValue:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3473', '新增', '3470', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:qc:inspectionValue:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3474', '修改', '3470', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:qc:inspectionValue:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3475', '删除', '3470', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:qc:inspectionValue:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3476', '导出', '3470', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:qc:inspectionValue:export', '#', 'admin', sysdate(), '', null, '');
-- 不合格品记录
insert into sys_menu values('3480', '不合格品记录', '3007', '3', 'nonconformity', 'mes/qc/nonconformity/index', '', '', 1, 0, 'C', '0', '0', 'mes:qc:nonconformity:list', '#', 'admin', sysdate(), '', null, '不合格品记录菜单');
insert into sys_menu values('3481', '查询', '3480', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:qc:nonconformity:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3482', '详情', '3480', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:qc:nonconformity:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3483', '新增', '3480', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:qc:nonconformity:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3484', '修改', '3480', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:qc:nonconformity:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3485', '删除', '3480', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:qc:nonconformity:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3486', '导出', '3480', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:qc:nonconformity:export', '#', 'admin', sysdate(), '', null, '');
-- 返工返修
insert into sys_menu values('3490', '返工返修', '3007', '4', 'rework', 'mes/qc/rework/index', '', '', 1, 0, 'C', '0', '0', 'mes:qc:rework:list', '#', 'admin', sysdate(), '', null, '返工返修菜单');
insert into sys_menu values('3491', '查询', '3490', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:qc:rework:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3492', '详情', '3490', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:qc:rework:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3493', '新增', '3490', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:qc:rework:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3494', '修改', '3490', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:qc:rework:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3495', '删除', '3490', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:qc:rework:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3496', '导出', '3490', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:qc:rework:export', '#', 'admin', sysdate(), '', null, '');
-- 质量放行
insert into sys_menu values('3500', '质量放行', '3007', '5', 'release', 'mes/qc/release/index', '', '', 1, 0, 'C', '0', '0', 'mes:qc:release:list', '#', 'admin', sysdate(), '', null, '质量放行菜单');
insert into sys_menu values('3501', '查询', '3500', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:qc:release:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3502', '详情', '3500', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:qc:release:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3503', '新增', '3500', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:qc:release:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3504', '修改', '3500', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:qc:release:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3505', '删除', '3500', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:qc:release:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3506', '导出', '3500', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:qc:release:export', '#', 'admin', sysdate(), '', null, '');

-- ============================
-- 设备管理（eq）
-- ============================
-- 设备状态
insert into sys_menu values('3510', '设备状态', '3008', '1', 'status', 'mes/eq/status/index', '', '', 1, 0, 'C', '0', '0', 'mes:eq:status:list', '#', 'admin', sysdate(), '', null, '设备状态菜单');
insert into sys_menu values('3511', '查询', '3510', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:status:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3512', '详情', '3510', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:status:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3513', '新增', '3510', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:status:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3514', '修改', '3510', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:status:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3515', '删除', '3510', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:status:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3516', '导出', '3510', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:status:export', '#', 'admin', sysdate(), '', null, '');
-- 设备报警
insert into sys_menu values('3520', '设备报警', '3008', '2', 'alarm', 'mes/eq/alarm/index', '', '', 1, 0, 'C', '0', '0', 'mes:eq:alarm:list', '#', 'admin', sysdate(), '', null, '设备报警菜单');
insert into sys_menu values('3521', '查询', '3520', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:alarm:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3522', '详情', '3520', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:alarm:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3523', '新增', '3520', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:alarm:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3524', '修改', '3520', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:alarm:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3525', '删除', '3520', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:alarm:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3526', '导出', '3520', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:alarm:export', '#', 'admin', sysdate(), '', null, '');
-- 设备命令
insert into sys_menu values('3530', '设备命令', '3008', '3', 'command', 'mes/eq/command/index', '', '', 1, 0, 'C', '0', '0', 'mes:eq:command:list', '#', 'admin', sysdate(), '', null, '设备命令菜单');
insert into sys_menu values('3531', '查询', '3530', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:command:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3532', '详情', '3530', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:command:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3533', '新增', '3530', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:command:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3534', '修改', '3530', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:command:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3535', '删除', '3530', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:command:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3536', '导出', '3530', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:command:export', '#', 'admin', sysdate(), '', null, '');
-- 设备维护与停机
insert into sys_menu values('3540', '设备维护与停机', '3008', '4', 'maintenance', 'mes/eq/maintenance/index', '', '', 1, 0, 'C', '0', '0', 'mes:eq:maintenance:list', '#', 'admin', sysdate(), '', null, '设备维护与停机菜单');
insert into sys_menu values('3541', '查询', '3540', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:maintenance:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3542', '详情', '3540', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:maintenance:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3543', '新增', '3540', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:maintenance:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3544', '修改', '3540', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:maintenance:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3545', '删除', '3540', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:maintenance:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3546', '导出', '3540', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:maintenance:export', '#', 'admin', sysdate(), '', null, '');
-- 设备点检
insert into sys_menu values('3550', '设备点检', '3008', '5', 'spotCheck', 'mes/eq/spotCheck/index', '', '', 1, 0, 'C', '0', '0', 'mes:eq:spotCheck:list', '#', 'admin', sysdate(), '', null, '设备点检菜单');
insert into sys_menu values('3551', '查询', '3550', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:spotCheck:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3552', '详情', '3550', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:spotCheck:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3553', '新增', '3550', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:spotCheck:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3554', '修改', '3550', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:spotCheck:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3555', '删除', '3550', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:spotCheck:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3556', '导出', '3550', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:spotCheck:export', '#', 'admin', sysdate(), '', null, '');
-- OEE
insert into sys_menu values('3560', 'OEE', '3008', '6', 'oee', 'mes/eq/oee/index', '', '', 1, 0, 'C', '0', '0', 'mes:eq:oee:list', '#', 'admin', sysdate(), '', null, 'OEE菜单');
insert into sys_menu values('3561', '查询', '3560', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:oee:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3562', '详情', '3560', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:oee:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3563', '新增', '3560', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:oee:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3564', '修改', '3560', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:oee:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3565', '删除', '3560', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:oee:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3566', '导出', '3560', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:oee:export', '#', 'admin', sysdate(), '', null, '');
-- 设备与工单绑定
insert into sys_menu values('3570', '设备与工单绑定', '3008', '7', 'bind', 'mes/eq/bind/index', '', '', 1, 0, 'C', '0', '0', 'mes:eq:bind:list', '#', 'admin', sysdate(), '', null, '设备与工单绑定菜单');
insert into sys_menu values('3571', '查询', '3570', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:bind:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3572', '详情', '3570', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:bind:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3573', '新增', '3570', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:bind:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3574', '修改', '3570', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:bind:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3575', '删除', '3570', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:bind:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3576', '导出', '3570', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:eq:bind:export', '#', 'admin', sysdate(), '', null, '');

-- ============================
-- 追溯管理（tr）
-- ============================
-- 追溯索引
insert into sys_menu values('3580', '追溯索引', '3009', '1', 'index', 'mes/tr/index/index', '', '', 1, 0, 'C', '0', '0', 'mes:tr:index:list', '#', 'admin', sysdate(), '', null, '追溯索引菜单');
insert into sys_menu values('3581', '查询', '3580', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:tr:index:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3582', '详情', '3580', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:tr:index:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3583', '新增', '3580', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:tr:index:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3584', '修改', '3580', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:tr:index:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3585', '删除', '3580', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:tr:index:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3586', '导出', '3580', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:tr:index:export', '#', 'admin', sysdate(), '', null, '');

-- ============================
-- 看板与分析（bi）
-- ============================
insert into sys_menu values('3600', '生产看板', '3010', '1', 'production', 'mes/dashboard/index', '', '', 1, 0, 'C', '0', '0', 'mes:bi:production:list', '#', 'admin', sysdate(), '', null, '生产看板菜单');
insert into sys_menu values('3610', '质量看板', '3010', '2', 'quality',    'mes/dashboard/index', '', '', 1, 0, 'C', '0', '0', 'mes:bi:quality:list',    '#', 'admin', sysdate(), '', null, '质量看板菜单');
insert into sys_menu values('3620', '设备看板', '3010', '3', 'equipment',  'mes/dashboard/index', '', '', 1, 0, 'C', '0', '0', 'mes:bi:equipment:list',  '#', 'admin', sysdate(), '', null, '设备看板菜单');
insert into sys_menu values('3630', '物料看板', '3010', '4', 'material',   'mes/dashboard/index', '', '', 1, 0, 'C', '0', '0', 'mes:bi:material:list',   '#', 'admin', sysdate(), '', null, '物料看板菜单');

-- ============================
-- 集成管理（integration）
-- ============================
-- 集成消息
insert into sys_menu values('3640', '集成消息', '3011', '1', 'message', 'mes/integration/message/index', '', '', 1, 0, 'C', '0', '0', 'mes:integration:message:list', '#', 'admin', sysdate(), '', null, '集成消息菜单');
insert into sys_menu values('3641', '查询', '3640', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:message:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3642', '详情', '3640', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:message:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3643', '新增', '3640', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:message:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3644', '修改', '3640', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:message:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3645', '删除', '3640', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:message:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3646', '导出', '3640', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:message:export', '#', 'admin', sysdate(), '', null, '');
-- 领域事件
insert into sys_menu values('3650', '领域事件', '3011', '2', 'event', 'mes/integration/event/index', '', '', 1, 0, 'C', '0', '0', 'mes:integration:event:list', '#', 'admin', sysdate(), '', null, '领域事件菜单');
insert into sys_menu values('3651', '查询', '3650', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:event:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3652', '详情', '3650', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:event:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3653', '新增', '3650', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:event:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3654', '修改', '3650', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:event:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3655', '删除', '3650', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:event:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3656', '导出', '3650', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:event:export', '#', 'admin', sysdate(), '', null, '');
-- 幂等记录
insert into sys_menu values('3660', '幂等记录', '3011', '3', 'idempotent', 'mes/integration/idempotent/index', '', '', 1, 0, 'C', '0', '0', 'mes:integration:idempotent:list', '#', 'admin', sysdate(), '', null, '幂等记录菜单');
insert into sys_menu values('3661', '查询', '3660', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:idempotent:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3662', '详情', '3660', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:idempotent:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3663', '新增', '3660', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:idempotent:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3664', '修改', '3660', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:idempotent:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3665', '删除', '3660', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:idempotent:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3666', '导出', '3660', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:idempotent:export', '#', 'admin', sysdate(), '', null, '');
-- 集成死信
insert into sys_menu values('3670', '集成死信', '3011', '4', 'deadLetter', 'mes/integration/deadLetter/index', '', '', 1, 0, 'C', '0', '0', 'mes:integration:deadLetter:list', '#', 'admin', sysdate(), '', null, '集成死信菜单');
insert into sys_menu values('3671', '查询', '3670', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:deadLetter:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3672', '详情', '3670', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:deadLetter:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3673', '新增', '3670', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:deadLetter:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3674', '修改', '3670', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:deadLetter:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3675', '删除', '3670', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:deadLetter:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3676', '导出', '3670', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:deadLetter:export', '#', 'admin', sysdate(), '', null, '');
-- 对账任务
insert into sys_menu values('3680', '对账任务', '3011', '5', 'reconcileTask', 'mes/integration/reconcileTask/index', '', '', 1, 0, 'C', '0', '0', 'mes:integration:reconcileTask:list', '#', 'admin', sysdate(), '', null, '对账任务菜单');
insert into sys_menu values('3681', '查询', '3680', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:reconcileTask:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3682', '详情', '3680', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:reconcileTask:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3683', '新增', '3680', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:reconcileTask:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3684', '修改', '3680', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:reconcileTask:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3685', '删除', '3680', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:reconcileTask:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3686', '导出', '3680', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:reconcileTask:export', '#', 'admin', sysdate(), '', null, '');
-- 对账结果
insert into sys_menu values('3690', '对账结果', '3011', '6', 'reconcileResult', 'mes/integration/reconcileResult/index', '', '', 1, 0, 'C', '0', '0', 'mes:integration:reconcileResult:list', '#', 'admin', sysdate(), '', null, '对账结果菜单');
insert into sys_menu values('3691', '查询', '3690', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:reconcileResult:list',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3692', '详情', '3690', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:reconcileResult:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3693', '新增', '3690', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:reconcileResult:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3694', '修改', '3690', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:reconcileResult:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3695', '删除', '3690', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:reconcileResult:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('3696', '导出', '3690', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:integration:reconcileResult:export', '#', 'admin', sysdate(), '', null, '');
