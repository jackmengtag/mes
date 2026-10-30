-- ============================================================
-- MES 制造与供应链协同平台 · 菜单与按钮权限
-- 前置：已执行 ry_20260417.sql；管理员 admin 拥有全部权限
-- ============================================================

DELETE FROM sys_menu WHERE menu_id BETWEEN 3000000 AND 3009999;

-- ---------- 一级目录 ----------
INSERT INTO sys_menu VALUES('3000000', 'MES 制造协同', '0', '4', 'mes', NULL, '', '', 1, 0, 'M', '0', '0', '', 'dashboard', 'admin', sysdate(), '', NULL, '制造与供应链协同平台');

-- ---------- 业务页面 ----------
INSERT INTO sys_menu VALUES('3000001', '生产总览', '3000000', '1', 'overview',      'mes/overview/index',      '', '', 1, 0, 'C', '0', '0', 'mes:overview:list',   'dashboard',     'admin', sysdate(), '', NULL, '生产总览');
INSERT INTO sys_menu VALUES('3000002', '供应链总览', '3000000', '2', 'scm',           'mes/scm/index',           '', '', 1, 0, 'C', '0', '0', 'mes:scm:list',        'chart',         'admin', sysdate(), '', NULL, '供应链总览');
INSERT INTO sys_menu VALUES('3000003', '车间大屏', '3000000', '3', 'board',         'mes/board/index',         '', '', 1, 0, 'C', '0', '0', 'mes:board:list',      'monitor',       'admin', sysdate(), '', NULL, '车间大屏');
INSERT INTO sys_menu VALUES('3000004', '计划管理', '3000000', '4', 'plan',          'mes/plan/index',          '', '', 1, 0, 'C', '0', '0', 'mes:plan:list',       'date',          'admin', sysdate(), '', NULL, '计划管理');
INSERT INTO sys_menu VALUES('3000005', '生产制造', '3000000', '5', 'manufacturing', 'mes/manufacturing/index', '', '', 1, 0, 'C', '0', '0', 'mes:workOrder:list',  'build',         'admin', sysdate(), '', NULL, '生产制造');
INSERT INTO sys_menu VALUES('3000006', '生产执行', '3000000', '6', 'production',    'mes/production/index',    '', '', 1, 0, 'C', '0', '0', 'mes:report:list',     'component',     'admin', sysdate(), '', NULL, '生产执行');
INSERT INTO sys_menu VALUES('3000007', '线边物料', '3000000', '7', 'material',      'mes/material/index',      '', '', 1, 0, 'C', '0', '0', 'mes:stock:list',      'guide',         'admin', sysdate(), '', NULL, '线边物料');
INSERT INTO sys_menu VALUES('3000008', '采购管理', '3000000', '8', 'purchase',      'mes/purchase/index',      '', '', 1, 0, 'C', '0', '0', 'mes:purchase:list',   'shopping',      'admin', sysdate(), '', NULL, '采购管理');
INSERT INTO sys_menu VALUES('3000009', '销售管理', '3000000', '9', 'sales',         'mes/sales/index',         '', '', 1, 0, 'C', '0', '0', 'mes:sales:list',      'money',         'admin', sysdate(), '', NULL, '销售管理');
INSERT INTO sys_menu VALUES('3000010', '库存管理', '3000000', '10', 'inventory',    'mes/inventory/index',     '', '', 1, 0, 'C', '0', '0', 'mes:stock:list',      'table',         'admin', sysdate(), '', NULL, '库存管理');
INSERT INTO sys_menu VALUES('3000011', '协同管理', '3000000', '11', 'collab',       'mes/collab/index',        '', '', 1, 0, 'C', '0', '0', 'mes:collab:list',     'peoples',       'admin', sysdate(), '', NULL, '供应链协同管理');
INSERT INTO sys_menu VALUES('3000012', '存货核算', '3000000', '12', 'costing',      'mes/costing/index',       '', '', 1, 0, 'C', '0', '0', 'mes:voucher:list',    'documentation', 'admin', sysdate(), '', NULL, '存货核算');
INSERT INTO sys_menu VALUES('3000013', '质量管理', '3000000', '13', 'quality',      'mes/quality/index',       '', '', 1, 0, 'C', '0', '0', 'mes:quality:list',    'star',          'admin', sysdate(), '', NULL, '质量管理');
INSERT INTO sys_menu VALUES('3000014', '质量追溯', '3000000', '14', 'trace',        'mes/trace/index',         '', '', 1, 0, 'C', '0', '0', 'mes:trace:list',      'search',        'admin', sysdate(), '', NULL, '质量追溯');
INSERT INTO sys_menu VALUES('3000015', '设备管理', '3000000', '15', 'equipment',    'mes/equipment/index',     '', '', 1, 0, 'C', '0', '0', 'mes:equipment:list',  'tool',          'admin', sysdate(), '', NULL, '设备管理');
INSERT INTO sys_menu VALUES('3000016', '统计分析', '3000000', '16', 'analysis',     'mes/analysis/index',      '', '', 1, 0, 'C', '0', '0', 'mes:analysis:list',   'education',     'admin', sysdate(), '', NULL, '统计分析');
INSERT INTO sys_menu VALUES('3000017', '基础数据', '3000000', '17', 'masterdata',   'mes/masterdata/index',    '', '', 1, 0, 'C', '0', '0', 'mes:material:list',   'tree',          'admin', sysdate(), '', NULL, '基础数据');
INSERT INTO sys_menu VALUES('3000018', '系统集成', '3000000', '18', 'system',       'mes/system/index',        '', '', 1, 0, 'C', '0', '0', 'mes:interface:list',  'system',        'admin', sysdate(), '', NULL, '系统集成');

-- ---------- 计划管理 ----------
INSERT INTO sys_menu VALUES('3000401', '计划查询', '3000004', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:plan:query',  '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3000402', '计划新增', '3000004', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:plan:add',    '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3000403', '计划修改', '3000004', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:plan:edit',   '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3000404', '计划删除', '3000004', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:plan:remove', '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3000405', '计划导出', '3000004', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:plan:export', '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3000406', '计划发布', '3000004', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:plan:publish','#', 'admin', sysdate(), '', NULL, '');

-- ---------- 生产制造（工单） ----------
INSERT INTO sys_menu VALUES('3000501', '工单查询', '3000005', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:workOrder:query',  '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3000502', '工单新增', '3000005', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:workOrder:add',    '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3000503', '工单修改', '3000005', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:workOrder:edit',   '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3000504', '工单删除', '3000005', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:workOrder:remove', '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3000505', '工单导出', '3000005', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:workOrder:export', '#', 'admin', sysdate(), '', NULL, '');

-- ---------- 生产执行（报工） ----------
INSERT INTO sys_menu VALUES('3000601', '报工查询', '3000006', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:report:query',  '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3000602', '生产报工', '3000006', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:report:add',    '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3000603', '报工删除', '3000006', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:report:remove', '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3000604', '报工导出', '3000006', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:report:export', '#', 'admin', sysdate(), '', NULL, '');

-- ---------- 线边物料 ----------
INSERT INTO sys_menu VALUES('3000701', '库存编辑', '3000007', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:stock:edit', '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3000702', '拉动申请', '3000007', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:collab:add', '#', 'admin', sysdate(), '', NULL, '');

-- ---------- 采购管理 ----------
INSERT INTO sys_menu VALUES('3000801', '采购查询', '3000008', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:purchase:query',  '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3000802', '采购新增', '3000008', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:purchase:add',    '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3000803', '采购修改', '3000008', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:purchase:edit',   '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3000804', '采购删除', '3000008', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:purchase:remove', '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3000805', '采购导出', '3000008', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:purchase:export', '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3000806', '到货登记', '3000008', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:arrival:add',     '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3000807', '供应商新增', '3000008', '7', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:supplier:add',  '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3000808', '供应商审核', '3000008', '8', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:supplier:audit','#', 'admin', sysdate(), '', NULL, '');

-- ---------- 销售管理 ----------
INSERT INTO sys_menu VALUES('3000901', '销售查询', '3000009', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:sales:query',   '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3000902', '销售新增', '3000009', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:sales:add',     '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3000903', '销售修改', '3000009', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:sales:edit',    '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3000904', '销售删除', '3000009', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:sales:remove',  '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3000905', '销售导出', '3000009', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:sales:export',  '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3000906', '销售出库', '3000009', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:shipment:add',  '#', 'admin', sysdate(), '', NULL, '');

-- ---------- 库存管理 ----------
INSERT INTO sys_menu VALUES('3001001', '库存查询', '3000010', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:stock:query',  '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3001002', '库存调整', '3000010', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:stock:adjust', '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3001003', '库存调拨', '3000010', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:stock:transfer','#','admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3001004', '库存导出', '3000010', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:stock:export', '#', 'admin', sysdate(), '', NULL, '');

-- ---------- 协同管理 ----------
INSERT INTO sys_menu VALUES('3001101', '协同查询', '3000011', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:collab:query',  '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3001102', '协同新增', '3000011', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:collab:add',    '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3001103', '协同修改', '3000011', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:collab:edit',   '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3001104', '协同删除', '3000011', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:collab:remove', '#', 'admin', sysdate(), '', NULL, '');

-- ---------- 存货核算 ----------
INSERT INTO sys_menu VALUES('3001201', '凭证查询', '3000012', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:voucher:query',  '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3001202', '凭证新增', '3000012', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:voucher:add',    '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3001203', '凭证记账', '3000012', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:voucher:post',   '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3001204', 'U8C 推送', '3000012', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:voucher:sync',   '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3001205', '凭证导出', '3000012', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:voucher:export', '#', 'admin', sysdate(), '', NULL, '');

-- ---------- 质量管理 ----------
INSERT INTO sys_menu VALUES('3001301', '质量查询', '3000013', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:quality:query',  '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3001302', '质量新增', '3000013', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:quality:add',    '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3001303', '质量修改', '3000013', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:quality:edit',   '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3001304', '质量删除', '3000013', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:quality:remove', '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3001305', '不合格处置', '3000013', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:quality:dispose','#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3001306', '检验判定', '3000013', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:inspection:edit','#', 'admin', sysdate(), '', NULL, '');

-- ---------- 质量追溯 ----------
INSERT INTO sys_menu VALUES('3001401', '追溯查询', '3000014', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:trace:query',  '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3001402', '追溯导出', '3000014', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:trace:export', '#', 'admin', sysdate(), '', NULL, '');

-- ---------- 设备管理 ----------
INSERT INTO sys_menu VALUES('3001501', '设备查询', '3000015', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:equipment:query',  '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3001502', '设备新增', '3000015', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:equipment:add',    '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3001503', '设备修改', '3000015', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:equipment:edit',   '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3001504', '设备删除', '3000015', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:equipment:remove', '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3001505', '设备指令', '3000015', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:equipment:command','#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3001506', '报警处理', '3000015', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:alarm:handle',     '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3001507', '设备导出', '3000015', '7', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:equipment:export', '#', 'admin', sysdate(), '', NULL, '');

-- ---------- 基础数据 ----------
INSERT INTO sys_menu VALUES('3001701', '物料查询', '3000017', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:material:query',  '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3001702', '物料新增', '3000017', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:material:add',    '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3001703', '物料修改', '3000017', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:material:edit',   '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3001704', '物料删除', '3000017', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:material:remove', '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3001705', '客户维护', '3000017', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:customer:edit',   '#', 'admin', sysdate(), '', NULL, '');

-- ---------- 系统集成 ----------
INSERT INTO sys_menu VALUES('3001801', '接口查询', '3000018', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:interface:query', '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3001802', '接口修改', '3000018', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:interface:edit',  '#', 'admin', sysdate(), '', NULL, '');
INSERT INTO sys_menu VALUES('3001803', '测试连接', '3000018', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'mes:interface:test',  '#', 'admin', sysdate(), '', NULL, '');
