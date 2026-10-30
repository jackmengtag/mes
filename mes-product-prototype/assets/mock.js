/* ============================================================
   MES 制造与供应链协同平台 · 通用领域演示数据（单一数据源）
   抽象自 CVMES：plant / plan / production / wip / quality /
   equipment / material / trace / integration
   说明：所有页面只读本文件；替换为真实接口时保持同名字段即可。
   ============================================================ */

var DB = {};

/* ---------- 基地 / 用户 / 通知 ---------- */
DB.plant = {
  code: 'HD-MES',
  name: '华东智造基地',
  short: '华东基地',
  workDate: '2026-10-25',
  currentShift: '白班 · 08:00-20:00',
  shifts: ['白班 · 08:00-20:00', '夜班 · 20:00-08:00'],
  workshops: ['机加车间', '装配车间', '测试包装车间'],
  lines: [
    { code: 'MC-01', name: '机加一线', ws: '机加车间' },
    { code: 'MC-02', name: '机加二线', ws: '机加车间' },
    { code: 'AS-01', name: '装配一线', ws: '装配车间' },
    { code: 'AS-02', name: '装配二线', ws: '装配车间' },
    { code: 'TS-01', name: '测试线', ws: '测试包装车间' },
    { code: 'PK-01', name: '包装线', ws: '测试包装车间' }
  ],
  products: ['SD-300 伺服驱动器', 'SD-500 伺服驱动器', 'SM-80 伺服电机', 'SM-130 伺服电机', 'CM-10 运动控制器'],
  jph: { 'MC-01': 130, 'MC-02': 120, 'AS-01': 150, 'AS-02': 150, 'TS-01': 150, 'PK-01': 150 }
};

DB.user = { id: 'U1027', name: '张明', role: '制造运营经理', dept: '制造运营部', avatarText: '张明' };

DB.notifications = [
  { title: 'AS-02 拧紧机扭矩连续 3 件超差，已触发停线', time: '09:42', from: '设备监控', href: 'equipment.html?tab=alarm' },
  { title: '定子铁芯库存低于安全库存，缺口 1,240 件', time: '10:15', from: '物料拉动', href: 'inventory.html?tab=alert&q=M-ST-0801' },
  { title: '测试线绝缘不良率升至 4.2%，需质量工程师确认', time: '11:08', from: '质量管理', href: 'quality.html?mount=%23issueTable&q=QI-261025-04' },
  { title: 'D+3 计划已锁定，10-28 前变更需走审批', time: '08:30', from: '计划管理', href: 'plan.html?tab=d3' }
];

DB.audit = [
  { time: '11:58:12', page: '系统集成', msg: 'U8C 凭证 CU-261025-021 同步成功' },
  { time: '11:36:20', page: '生产执行', msg: 'WO-261025-003 报工 18 台，合格 17 台' },
  { time: '10:42:05', page: '采购管理', msg: '到货单 DH-261025-003 已登记并生成入库单' }
];

/* ---------- 生产总览 ---------- */
DB.overview = {
  kpis: [
    { key: 'output', label: '当班产出', value: '1,284', unit: '台', delta: '+3.2%', trend: 'up', tone: 'ok', foot: '计划 1,400 · 缺口 116 台', icon: 'box' },
    { key: 'plan', label: '计划达成率', value: '91.7', unit: '%', delta: '+1.4%', trend: 'up', tone: 'ok', foot: 'D+3 锁定计划 · 白班', icon: 'target' },
    { key: 'ftt', label: '一次合格率 FTT', value: '97.4', unit: '%', delta: '-0.3%', trend: 'down', tone: 'warn', foot: '目标 ≥ 97.5%', icon: 'shield' },
    { key: 'oee', label: '综合效率 OEE', value: '82.6', unit: '%', delta: '+2.1%', trend: 'up', tone: 'ok', foot: '目标 ≥ 80%', icon: 'gauge' },
    { key: 'wip', label: '在制品 WIP', value: '386', unit: '台', delta: '+18', trend: 'up', tone: 'warn', foot: '安全水位 ≤ 420 台', icon: 'layers' },
    { key: 'cycle', label: '平均节拍', value: '42.5', unit: 's', delta: '-1.2s', trend: 'up', tone: 'ok', foot: '标准节拍 45s', icon: 'clock' },
    { key: 'util', label: '设备稼动率', value: '88.1', unit: '%', delta: '+0.8%', trend: 'up', tone: 'ok', foot: '在线设备 28 / 30', icon: 'cpu' },
    { key: 'shortage', label: '缺料工单', value: '2', unit: '单', delta: '+1', trend: 'down', tone: 'danger', foot: '定子铁芯 / 编码器', icon: 'alert' }
  ],
  flow: [
    { name: '机加', meta: 'MC-01 / MC-02 · 节拍 41s', tone: 'ok', icon: 'gitBranch', out: 474, wip: 132 },
    { name: '装配', meta: 'AS-01 / AS-02 · 6 工位', tone: 'warn', icon: 'tool', out: 540, wip: 186 },
    { name: '测试', meta: 'TS-01 · 4 个测试台', tone: 'ok', icon: 'activity', out: 142, wip: 68 },
    { name: '包装', meta: 'PK-01 · 2 条打包线', tone: 'warn', icon: 'box', out: 128, wip: 42 }
  ],
  lines: [
    { line: 'MC-01', ws: '机加车间', status: '生产中', plan: 260, done: 248, ftt: 98.2, oee: 86.4, cycle: 41 },
    { line: 'MC-02', ws: '机加车间', status: '生产中', plan: 240, done: 226, ftt: 97.8, oee: 83.1, cycle: 43 },
    { line: 'AS-01', ws: '装配车间', status: '生产中', plan: 300, done: 282, ftt: 97.1, oee: 84.6, cycle: 42 },
    { line: 'AS-02', ws: '装配车间', status: '待料', plan: 300, done: 258, ftt: 96.4, oee: 79.2, cycle: 46 },
    { line: 'TS-01', ws: '测试包装车间', status: '生产中', plan: 150, done: 142, ftt: 98.6, oee: 88.0, cycle: 39 },
    { line: 'PK-01', ws: '测试包装车间', status: '预警', plan: 150, done: 128, ftt: 99.1, oee: 90.2, cycle: 40 }
  ],
  hourly: [
    { h: '08', v: 78 }, { h: '09', v: 96 }, { h: '10', v: 112 }, { h: '11', v: 108 },
    { h: '12', v: 120 }, { h: '13', v: 105 }, { h: '14', v: 118 }, { h: '15', v: 124 },
    { h: '16', v: 110 }, { h: '17', v: 126 }, { h: '18', v: 116 }, { h: '19', v: 71 }
  ],
  outputTrend: [
    { d: '10-19', v: 1180 }, { d: '10-20', v: 1226 }, { d: '10-21', v: 1304 },
    { d: '10-22', v: 1272 }, { d: '10-23', v: 1350 }, { d: '10-24', v: 1312 }, { d: '10-25', v: 1284 }
  ],
  alerts: [
    { tone: 'danger', title: 'AS-02 拧紧机扭矩超差', time: '09:42', body: '连续 3 件扭矩低于下限 18N·m，已自动停线并通知设备工程师。' },
    { tone: 'warn', title: '定子铁芯缺料', time: '10:15', body: '线边库存 1,240 件低于安全库存，预计 14:30 到货，影响 2 张工单。' },
    { tone: 'warn', title: '测试线绝缘不良率上升', time: '11:08', body: '近 200 台绝缘不良 8 台（4.0%），已隔离批次 B261025-03。' },
    { tone: 'info', title: 'SD-500 插单已确认', time: '08:30', body: '客户加急 200 台，D+3 计划已调整，装配二线优先级提升。' }
  ],
  kitting: [
    { name: '定子铁芯', v: 82 }, { name: '转子组件', v: 94 }, { name: '端盖', v: 98 },
    { name: '编码器', v: 76 }, { name: '线束', v: 91 }, { name: '包装材料', v: 99 }
  ],
  orders: [
    { seq: 1, serial: 'SN-SD3-261025-0091', model: 'SD-300', order: 'SO-20261018-042', stage: '机加', station: 'MC-01 / OP30', status: '加工中', eta: '12:40' },
    { seq: 2, serial: 'SN-SD3-261025-0090', model: 'SD-300', order: 'SO-20261018-042', stage: '装配', station: 'AS-01 / OP40', status: '生产中', eta: '12:10' },
    { seq: 3, serial: 'SN-SM8-261025-0038', model: 'SM-80', order: 'SO-20261020-011', stage: '装配', station: 'AS-02 / OP20', status: '待料', eta: '14:30' },
    { seq: 4, serial: 'SN-SD5-261025-0012', model: 'SD-500', order: 'SO-20261022-008', stage: '测试', station: 'TS-01 / T2', status: '待检验', eta: '12:25' },
    { seq: 5, serial: 'SN-SM8-261025-0037', model: 'SM-80', order: 'SO-20261020-011', stage: '测试', station: 'TS-01 / T4', status: '检验中', eta: '12:05' },
    { seq: 6, serial: 'SN-SD3-261025-0089', model: 'SD-300', order: 'SO-20261018-042', stage: '包装', station: 'PK-01 / P1', status: '已完工', eta: '11:50' }
  ]
};

/* ---------- 计划管理 ---------- */
DB.plans = {
  kpis: [
    { label: '本月计划', value: '32,000', unit: '台', tone: '', foot: '10 月 · 20 个工作日', icon: 'calendar' },
    { label: '本月完成', value: '27,860', unit: '台', tone: 'ok', delta: '+4.1%', trend: 'up', foot: '达成率 87.1%', icon: 'box' },
    { label: 'D+3 锁定', value: '4,200', unit: '台', tone: 'accent', foot: '10-26 ~ 10-28 · 变更需审批', icon: 'target' },
    { label: '计划变更', value: '3', unit: '次', tone: 'warn', foot: '插单 1 · 缺料调整 2', icon: 'refresh' }
  ],
  monthly: [
    { line: 'MC-01', plan: 5200, done: 4680, rate: 90.0, status: '正常' },
    { line: 'MC-02', plan: 4800, done: 4260, rate: 88.8, status: '正常' },
    { line: 'AS-01', plan: 6000, done: 5320, rate: 88.7, status: '正常' },
    { line: 'AS-02', plan: 6000, done: 5010, rate: 83.5, status: '预警' },
    { line: 'TS-01', plan: 5000, done: 4450, rate: 89.0, status: '正常' },
    { line: 'PK-01', plan: 5000, done: 4140, rate: 82.8, status: '预警' }
  ],
  weekly: [
    { week: 'W43 (10-19 ~ 10-25)', plan: 7800, done: 7010, rate: 89.9, status: '正常' },
    { week: 'W44 (10-26 ~ 11-01)', plan: 7600, done: 0, rate: 0, status: '已排程' },
    { week: 'W45 (11-02 ~ 11-08)', plan: 7400, done: 0, rate: 0, status: '草稿' },
    { week: 'W46 (11-09 ~ 11-15)', plan: 7200, done: 0, rate: 0, status: '草稿' }
  ],
  d3: [
    { date: '2026-10-26', product: 'SD-300 伺服驱动器', qty: 800, line: 'AS-01', shift: '白班', status: '已锁定' },
    { date: '2026-10-26', product: 'SM-80 伺服电机', qty: 600, line: 'AS-02', shift: '白班', status: '已锁定' },
    { date: '2026-10-27', product: 'SD-500 伺服驱动器', qty: 700, line: 'AS-01', shift: '白班', status: '已锁定' },
    { date: '2026-10-27', product: 'SM-130 伺服电机', qty: 500, line: 'AS-02', shift: '夜班', status: '已锁定' },
    { date: '2026-10-28', product: 'SD-300 伺服驱动器', qty: 900, line: 'AS-01', shift: '白班', status: '已锁定' },
    { date: '2026-10-28', product: 'CM-10 运动控制器', qty: 700, line: 'AS-02', shift: '白班', status: '已锁定' }
  ],
  schedules: [
    { no: 'WO-261025-001', product: 'SD-300 伺服驱动器', qty: 260, line: 'MC-01', shift: '白班', start: '08:00', end: '14:00', progress: 95, status: '生产中' },
    { no: 'WO-261025-002', product: 'SM-80 伺服电机', qty: 240, line: 'MC-02', shift: '白班', start: '08:00', end: '15:00', progress: 94, status: '生产中' },
    { no: 'WO-261025-003', product: 'SD-300 伺服驱动器', qty: 300, line: 'AS-01', shift: '白班', start: '08:00', end: '16:00', progress: 94, status: '生产中' },
    { no: 'WO-261025-004', product: 'SM-80 伺服电机', qty: 300, line: 'AS-02', shift: '白班', start: '08:00', end: '17:00', progress: 86, status: '待料' },
    { no: 'WO-261025-005', product: 'SD-500 伺服驱动器', qty: 150, line: 'TS-01', shift: '白班', start: '09:00', end: '17:00', progress: 95, status: '生产中' },
    { no: 'WO-261025-006', product: 'CM-10 运动控制器', qty: 150, line: 'PK-01', shift: '白班', start: '09:00', end: '18:00', progress: 85, status: '预警' },
    { no: 'WO-261025-007', product: 'SD-500 伺服驱动器', qty: 200, line: 'AS-01', shift: '夜班', start: '20:00', end: '02:00', progress: 0, status: '已排程' },
    { no: 'WO-261025-008', product: 'SM-130 伺服电机', qty: 180, line: 'AS-02', shift: '夜班', start: '20:00', end: '03:00', progress: 0, status: '已排程' }
  ],
  gantt: [
    { label: 'MC-01', start: 0, width: 38, tone: 'ok', text: 'SD-300 × 260', value: '95%' },
    { label: 'MC-02', start: 2, width: 36, tone: '', text: 'SM-80 × 240', value: '94%' },
    { label: 'AS-01', start: 0, width: 46, tone: '', text: 'SD-300 × 300', value: '94%' },
    { label: 'AS-02', start: 0, width: 42, tone: 'warn', text: 'SM-80 × 300', value: '86%' },
    { label: 'TS-01', start: 6, width: 34, tone: '', text: 'SD-500 × 150', value: '95%' },
    { label: 'PK-01', start: 6, width: 32, tone: 'warn', text: 'CM-10 × 150', value: '85%' }
  ],
  warnings: [
    { tone: 'danger', title: 'AS-02 待料影响 D+1 计划', time: '10:20', body: '定子铁芯缺口 1,240 件，若 14:30 未到货，将影响 300 台产出。' },
    { tone: 'warn', title: 'PK-01 产能不足', time: '09:10', body: '按当前节拍，今日计划缺口 22 台，建议夜班增加 1 个打包工位。' },
    { tone: 'info', title: 'D+3 计划已锁定', time: '08:30', body: '10-26 ~ 10-28 计划锁版，变更需生产经理审批。' }
  ],
  rules: [
    ['排产粒度', '日计划 → 班次计划 → 工单'],
    ['锁定策略', 'D+3 锁定，D+7 滚动'],
    ['优先级', '客户等级 > 交期 > 换型次数'],
    ['换型约束', '同产品族连续排产，换型 ≥ 30min'],
    ['产能约束', '设备能力 / 人员出勤 / 物料齐套'],
    ['发布时点', '每日 15:00 前发布次日计划']
  ]
};

/* ---------- 生产执行 ---------- */
DB.production = {
  kpis: [
    { label: '当班工单', value: '18', unit: '单', tone: '', foot: '已完成 11 · 在制 7', icon: 'clipboard' },
    { label: '报工数量', value: '1,284', unit: '台', tone: 'ok', delta: '+3.2%', trend: 'up', foot: '合格 1,251 · 不良 33', icon: 'box' },
    { label: '报工及时率', value: '96.8', unit: '%', tone: 'ok', foot: '超时 2 笔待补录', icon: 'clock' },
    { label: '停机时长', value: '46.5', unit: 'min', tone: 'warn', foot: '设备 28min · 待料 18.5min', icon: 'alert' }
  ],
  workOrders: [
    { no: 'WO-261025-001', product: 'SD-300 伺服驱动器', plan: 260, done: 248, line: 'MC-01', priority: '高', status: '生产中', due: '14:00', owner: '李强' },
    { no: 'WO-261025-002', product: 'SM-80 伺服电机', plan: 240, done: 226, line: 'MC-02', priority: '中', status: '生产中', due: '15:00', owner: '王磊' },
    { no: 'WO-261025-003', product: 'SD-300 伺服驱动器', plan: 300, done: 282, line: 'AS-01', priority: '高', status: '生产中', due: '16:00', owner: '陈刚' },
    { no: 'WO-261025-004', product: 'SM-80 伺服电机', plan: 300, done: 258, line: 'AS-02', priority: '高', status: '待料', due: '17:00', owner: '赵敏' },
    { no: 'WO-261025-005', product: 'SD-500 伺服驱动器', plan: 150, done: 142, line: 'TS-01', priority: '中', status: '生产中', due: '17:00', owner: '孙悦' },
    { no: 'WO-261025-006', product: 'CM-10 运动控制器', plan: 150, done: 128, line: 'PK-01', priority: '低', status: '预警', due: '18:00', owner: '周涛' },
    { no: 'WO-261025-007', product: 'SD-500 伺服驱动器', plan: 200, done: 0, line: 'AS-01', priority: '中', status: '已排程', due: '02:00', owner: '陈刚' },
    { no: 'WO-261025-008', product: 'SM-130 伺服电机', plan: 180, done: 0, line: 'AS-02', priority: '中', status: '已排程', due: '03:00', owner: '赵敏' },
    { no: 'WO-261025-009', product: 'SD-300 伺服驱动器', plan: 120, done: 120, line: 'AS-01', priority: '高', status: '已完成', due: '11:30', owner: '陈刚' },
    { no: 'WO-261025-010', product: 'SM-80 伺服电机', plan: 80, done: 80, line: 'AS-02', priority: '低', status: '已完成', due: '10:50', owner: '赵敏' }
  ],
  stations: [
    { title: 'OP10 预装', meta: '节拍 38s / 标准 40s', value: 96, unit: '%', tone: 'ok', valueTone: 'ok' },
    { title: 'OP20 定子装配', meta: '节拍 41s / 标准 40s', value: 98, unit: '%', tone: '', valueTone: '' },
    { title: 'OP30 转子压装', meta: '节拍 39s / 标准 40s', value: 102, unit: '%', tone: 'ok', valueTone: 'ok' },
    { title: 'OP40 端盖装配', meta: '节拍 44s / 标准 40s', value: 88, unit: '%', tone: 'warn', valueTone: 'low' },
    { title: 'OP50 拧紧', meta: '扭矩 18~22N·m', value: 86, unit: '%', tone: 'warn', valueTone: 'low' },
    { title: 'OP60 性能测试', meta: '测试台 T1-T4', value: 94, unit: '%', tone: '', valueTone: '' },
    { title: 'OP70 外观检验', meta: '全检 · 目视', value: 97, unit: '%', tone: 'ok', valueTone: 'ok' },
    { title: 'OP80 包装', meta: '打包 + 贴标', value: 91, unit: '%', tone: '', valueTone: '' }
  ],
  reports: [
    { time: '11:42', order: 'WO-261025-001', op: 'OP30 转子压装', qty: 24, ok: 24, ng: 0, worker: '李强' },
    { time: '11:36', order: 'WO-261025-003', op: 'OP50 拧紧', qty: 18, ok: 17, ng: 1, worker: '陈刚' },
    { time: '11:28', order: 'WO-261025-005', op: 'OP60 性能测试', qty: 12, ok: 11, ng: 1, worker: '孙悦' },
    { time: '11:15', order: 'WO-261025-002', op: 'OP20 定子装配', qty: 20, ok: 20, ng: 0, worker: '王磊' },
    { time: '11:02', order: 'WO-261025-004', op: 'OP40 端盖装配', qty: 16, ok: 15, ng: 1, worker: '赵敏' },
    { time: '10:55', order: 'WO-261025-006', op: 'OP80 包装', qty: 30, ok: 30, ng: 0, worker: '周涛' },
    { time: '10:48', order: 'WO-261025-001', op: 'OP30 转子压装', qty: 22, ok: 22, ng: 0, worker: '李强' },
    { time: '10:32', order: 'WO-261025-003', op: 'OP40 端盖装配', qty: 19, ok: 18, ng: 1, worker: '陈刚' }
  ],
  downtime: [
    { eq: 'AS-02 / 拧紧机', start: '09:42', duration: 28, reason: '扭矩传感器故障', type: '设备故障', status: '已恢复' },
    { eq: 'AS-02 / 上料工位', start: '10:18', duration: 18.5, reason: '定子铁芯待料', type: '物料等待', status: '进行中' },
    { eq: 'PK-01 / 打包机', start: '09:05', duration: 6, reason: '标签纸卡纸', type: '设备故障', status: '已恢复' },
    { eq: 'MC-02 / CNC-04', start: '08:47', duration: 9, reason: '刀具更换', type: '计划换刀', status: '已恢复' },
    { eq: 'TS-01 / 测试台 T3', start: '10:40', duration: 4.5, reason: '工装复位', type: '换型调整', status: '已恢复' },
    { eq: 'AS-01 / OP50', start: '11:12', duration: 3.2, reason: '操作员短时离岗', type: '人员', status: '已恢复' }
  ],
  hourly: [
    { h: '08', v: 78 }, { h: '09', v: 96 }, { h: '10', v: 112 }, { h: '11', v: 108 },
    { h: '12', v: 120 }, { h: '13', v: 105 }, { h: '14', v: 118 }, { h: '15', v: 124 },
    { h: '16', v: 110 }, { h: '17', v: 126 }, { h: '18', v: 116 }, { h: '19', v: 71 }
  ]
};

/* ---------- 质量管理 ---------- */
DB.quality = {
  kpis: [
    { label: '一次合格率 FTT', value: '97.4', unit: '%', tone: 'warn', delta: '-0.3%', trend: 'down', foot: '目标 ≥ 97.5%', icon: 'shield' },
    { label: 'DPU 下线缺陷率', value: '0.026', unit: '件/台', tone: 'ok', delta: '-8.2%', trend: 'up', foot: '目标 ≤ 0.03', icon: 'activity' },
    { label: '待处理问题', value: '4', unit: '项', tone: 'danger', foot: '严重 1 · 一般 3', icon: 'alert' },
    { label: '客诉', value: '0', unit: '起', tone: 'ok', foot: '近 30 天', icon: 'flag' }
  ],
  inspections: [
    { no: 'QC-261025-031', type: '首检', object: 'SD-300 / B261025-01', sample: 5, ok: 5, ng: 0, inspector: '刘洋', time: '08:25', status: '合格' },
    { no: 'QC-261025-032', type: '巡检', object: 'AS-01 / OP50', sample: 20, ok: 19, ng: 1, inspector: '刘洋', time: '10:10', status: '不合格' },
    { no: 'QC-261025-033', type: '巡检', object: 'AS-02 / OP40', sample: 20, ok: 19, ng: 1, inspector: '何静', time: '10:35', status: '不合格' },
    { no: 'QC-261025-034', type: '终检', object: 'SM-80 / B261025-02', sample: 50, ok: 49, ng: 1, inspector: '何静', time: '11:00', status: '让步接收' },
    { no: 'QC-261025-035', type: '终检', object: 'SD-500 / B261025-03', sample: 50, ok: 48, ng: 2, inspector: '刘洋', time: '11:20', status: '不合格' },
    { no: 'QC-261025-036', type: '首检', object: 'CM-10 / B261025-04', sample: 5, ok: 5, ng: 0, inspector: '何静', time: '11:35', status: '合格' },
    { no: 'QC-261025-037', type: '巡检', object: 'TS-01 / 绝缘测试', sample: 30, ok: 29, ng: 1, inspector: '王芳', time: '11:48', status: '不合格' },
    { no: 'QC-261025-038', type: '终检', object: 'SD-300 / B261025-05', sample: 50, ok: 50, ng: 0, inspector: '王芳', time: '12:02', status: '合格' }
  ],
  issues: [
    { no: 'QI-261025-01', time: '09:45', source: '设备报警', product: 'SM-80', defect: '拧紧扭矩超差', level: '严重', owner: '设备组', status: '处理中' },
    { no: 'QI-261025-02', time: '10:12', source: '巡检', product: 'SD-300', defect: '端盖装配间隙 > 0.2mm', level: '一般', owner: '装配一组', status: '待处理' },
    { no: 'QI-261025-03', time: '10:40', source: '巡检', product: 'SM-80', defect: '转子压装高度偏差', level: '一般', owner: '装配二组', status: '待处理' },
    { no: 'QI-261025-04', time: '11:20', source: '终检', product: 'SD-500', defect: '绝缘电阻不合格', level: '严重', owner: '质量部', status: '处理中' },
    { no: 'QI-261025-05', time: '11:48', source: '终检', product: 'SD-500', defect: '外观划伤', level: '一般', owner: '包装组', status: '待处理' },
    { no: 'QI-261025-06', time: '11:55', source: '终检', product: 'SD-300', defect: '标签信息错误', level: '轻微', owner: '包装组', status: '已关闭' }
  ],
  defects: [
    { name: '装配间隙', v: 38 }, { name: '扭矩超差', v: 27 }, { name: '绝缘不良', v: 19 },
    { name: '外观划伤', v: 14 }, { name: '标签错误', v: 8 }, { name: '其他', v: 6 }
  ],
  dpu: [
    { name: 'MC-01', v: 0.018 }, { name: 'MC-02', v: 0.021 }, { name: 'AS-01', v: 0.029 },
    { name: 'AS-02', v: 0.036 }, { name: 'TS-01', v: 0.014 }, { name: 'PK-01', v: 0.009 }
  ],
  ncFlow: [
    { no: 1, name: '发现', meta: '检验 / 报工', state: 'done' },
    { no: 2, name: '隔离', meta: '批次冻结', state: 'done' },
    { no: 3, name: '评审', meta: '质量工程师', state: 'active' },
    { no: 4, name: '处置', meta: '返工 / 让步 / 报废', state: 'todo' },
    { no: 5, name: '放行', meta: '复检确认', state: 'todo' }
  ],
  ncList: [
    { no: 'NC-261025-01', batch: 'B261025-03', product: 'SD-500', qty: 8, defect: '绝缘电阻不合格', disposition: '返工', owner: '质量部', status: '待评审' },
    { no: 'NC-261025-02', batch: 'B261025-02', product: 'SM-80', qty: 3, defect: '扭矩超差', disposition: '返工', owner: '装配二组', status: '处理中' },
    { no: 'NC-261025-03', batch: 'B261025-01', product: 'SD-300', qty: 2, defect: '装配间隙', disposition: '让步接收', owner: '装配一组', status: '待放行' },
    { no: 'NC-261025-04', batch: 'B261025-04', product: 'CM-10', qty: 1, defect: '标签错误', disposition: '返工', owner: '包装组', status: '已关闭' },
    { no: 'NC-261025-05', batch: 'B261025-05', product: 'SD-300', qty: 1, defect: '外观划伤', disposition: '报废', owner: '包装组', status: '已关闭' }
  ]
};

/* ---------- 设备管理 ---------- */
DB.equipment = {
  kpis: [
    { label: '综合效率 OEE', value: '82.6', unit: '%', tone: 'ok', delta: '+2.1%', trend: 'up', foot: '目标 ≥ 80%', icon: 'gauge' },
    { label: '设备稼动率', value: '88.1', unit: '%', tone: 'ok', delta: '+0.8%', trend: 'up', foot: '在线 28 / 30', icon: 'activity' },
    { label: '当前报警', value: '3', unit: '条', tone: 'danger', foot: '严重 1 · 一般 2', icon: 'alert' },
    { label: '点检完成率', value: '96.0', unit: '%', tone: 'warn', foot: '未完成 2 台', icon: 'clipboard' }
  ],
  list: [
    { code: 'CNC-04', name: '立式加工中心', line: 'MC-02', status: '运行', oee: 86.2, util: 91.0, temp: 42.5, hours: 6120, maintain: '2026-11-08' },
    { code: 'CNC-07', name: '卧式加工中心', line: 'MC-01', status: '运行', oee: 88.4, util: 93.2, temp: 41.8, hours: 5480, maintain: '2026-11-12' },
    { code: 'PRS-02', name: '转子压装机', line: 'AS-01', status: '运行', oee: 84.1, util: 90.5, temp: 39.2, hours: 3260, maintain: '2026-10-30' },
    { code: 'SCR-05', name: '自动拧紧机', line: 'AS-02', status: '报警', oee: 71.2, util: 76.8, temp: 48.6, hours: 2890, maintain: '2026-10-26' },
    { code: 'TST-01', name: '绝缘测试台', line: 'TS-01', status: '运行', oee: 90.1, util: 94.0, temp: 36.4, hours: 4120, maintain: '2026-11-18' },
    { code: 'TST-03', name: '性能测试台', line: 'TS-01', status: '待机', oee: 82.6, util: 85.0, temp: 35.1, hours: 3870, maintain: '2026-11-05' },
    { code: 'PKG-01', name: '自动打包机', line: 'PK-01', status: '运行', oee: 92.3, util: 95.1, temp: 34.7, hours: 2240, maintain: '2026-11-20' },
    { code: 'LBL-02', name: '贴标机', line: 'PK-01', status: '待机', oee: 89.0, util: 90.2, temp: 33.8, hours: 1980, maintain: '2026-11-22' },
    { code: 'AGV-03', name: '潜伏式 AGV', line: '物流', status: '运行', oee: 78.4, util: 82.0, temp: 31.2, hours: 1640, maintain: '2026-12-02' },
    { code: 'CNC-11', name: '数控车床', line: 'MC-01', status: '停机', oee: 0, util: 0, temp: 28.0, hours: 7210, maintain: '2026-10-25' }
  ],
  alarms: [
    { time: '09:42', eq: 'SCR-05', code: 'E-1021', msg: '扭矩传感器信号异常', level: '严重', status: '处理中' },
    { time: '10:05', eq: 'CNC-04', code: 'W-2208', msg: '主轴温度偏高（42.5℃）', level: '一般', status: '已确认' },
    { time: '10:40', eq: 'PRS-02', code: 'W-1103', msg: '液压油位低于下限', level: '一般', status: '已处理' },
    { time: '11:12', eq: 'AGV-03', code: 'I-3301', msg: '路径避障等待 2 分钟', level: '提示', status: '已恢复' },
    { time: '11:36', eq: 'TST-03', code: 'W-4102', msg: '测试台校准即将到期', level: '提示', status: '待处理' }
  ],
  inspections: [
    { item: '主轴油温 / 油压', eq: 'CNC-04', period: '每班', last: '08:05', worker: '王磊', status: '正常' },
    { item: '拧紧枪扭矩校准', eq: 'SCR-05', period: '每日', last: '08:10', worker: '设备组', status: '异常' },
    { item: '液压系统油位', eq: 'PRS-02', period: '每日', last: '08:15', worker: '设备组', status: '正常' },
    { item: '测试台接地电阻', eq: 'TST-01', period: '每周', last: '10-23', worker: '王芳', status: '正常' },
    { item: 'AGV 电池电量', eq: 'AGV-03', period: '每班', last: '11:20', worker: '物流组', status: '未完成' }
  ],
  oeeTrend: [
    { d: '10-19', v: 79.2 }, { d: '10-20', v: 80.6 }, { d: '10-21', v: 81.4 },
    { d: '10-22', v: 80.2 }, { d: '10-23', v: 82.0 }, { d: '10-24', v: 81.5 }, { d: '10-25', v: 82.6 }
  ]
};

/* ---------- 线边物料 ---------- */
DB.material = {
  kpis: [
    { label: '整体齐套率', value: '90.4', unit: '%', tone: 'warn', foot: '目标 ≥ 95%', icon: 'package' },
    { label: '缺料工单', value: '2', unit: '单', tone: 'danger', foot: '定子铁芯 / 编码器', icon: 'alert' },
    { label: '线边库存', value: '128.6', unit: '万元', tone: '', delta: '-4.2%', trend: 'up', foot: '较昨日下降', icon: 'warehouse' },
    { label: '库存周转', value: '6.8', unit: '次/月', tone: 'ok', foot: '目标 ≥ 6 次', icon: 'sync' }
  ],
  warehouses: [
    { name: '原料库 A', meta: 'A 区 · 立库', locations: 320, used: 274, value: '86.2 万', tone: 'ok', alert: 0 },
    { name: '线边库 AS', meta: '装配车间 · 线边', locations: 96, used: 88, value: '32.4 万', tone: 'warn', alert: 2 },
    { name: '成品库 F', meta: 'F 区 · 发货暂存', locations: 180, used: 121, value: '10.0 万', tone: '', alert: 0 }
  ],
  inventory: [
    { code: 'M-ST-0801', name: '定子铁芯', loc: 'AS-A-03', stock: 1240, safe: 2000, incoming: 4000, status: '缺料', updated: '11:35' },
    { code: 'M-RT-0302', name: '转子组件', loc: 'AS-A-05', stock: 2860, safe: 1500, incoming: 0, status: '正常', updated: '11:30' },
    { code: 'M-EC-0101', name: '编码器', loc: 'AS-B-02', stock: 820, safe: 900, incoming: 2000, status: '预警', updated: '11:28' },
    { code: 'M-CP-0401', name: '端盖', loc: 'AS-A-08', stock: 3480, safe: 1200, incoming: 0, status: '正常', updated: '11:20' },
    { code: 'M-WH-0203', name: '线束', loc: 'AS-B-04', stock: 1620, safe: 800, incoming: 0, status: '正常', updated: '11:18' },
    { code: 'M-BR-0501', name: '轴承', loc: 'A-C-01', stock: 4200, safe: 1500, incoming: 0, status: '正常', updated: '11:10' },
    { code: 'M-OR-0602', name: 'O 型密封圈', loc: 'A-C-06', stock: 6800, safe: 2000, incoming: 0, status: '正常', updated: '11:05' },
    { code: 'M-PK-0701', name: '包装泡沫', loc: 'F-A-01', stock: 960, safe: 500, incoming: 0, status: '正常', updated: '10:58' },
    { code: 'M-LB-0801', name: '产品标签纸', loc: 'F-A-03', stock: 240, safe: 300, incoming: 1000, status: '预警', updated: '10:52' },
    { code: 'M-SC-0901', name: '紧固螺钉 M4', loc: 'A-B-02', stock: 12000, safe: 3000, incoming: 0, status: '正常', updated: '10:45' }
  ],
  pullings: [
    { no: 'PL-261025-012', material: '定子铁芯', qty: 4000, route: '原料库 A → AS 线边', status: '配送中', time: '10:20' },
    { no: 'PL-261025-011', material: '编码器', qty: 2000, route: '原料库 A → AS 线边', status: '待拣货', time: '10:05' },
    { no: 'PL-261025-010', material: '端盖', qty: 1500, route: '原料库 A → AS 线边', status: '已入库', time: '09:42' },
    { no: 'PL-261025-009', material: '包装泡沫', qty: 600, route: '原料库 A → F 成品库', status: '已入库', time: '09:20' },
    { no: 'PL-261025-008', material: '线束', qty: 800, route: '原料库 A → AS 线边', status: '已入库', time: '08:55' },
    { no: 'PL-261025-007', material: '产品标签纸', qty: 1000, route: '原料库 A → F 成品库', status: '异常', time: '08:30' }
  ],
  kitting: [
    { name: 'SD-300 套件', v: 86 }, { name: 'SD-500 套件', v: 78 }, { name: 'SM-80 套件', v: 94 },
    { name: 'SM-130 套件', v: 97 }, { name: 'CM-10 套件', v: 99 }
  ]
};

/* ---------- 质量追溯 ---------- */
DB.trace = {
  index: [
    { serial: 'SN-SD3-261025-0091', product: 'SD-300 伺服驱动器', batch: 'B261025-01', offline: '2026-10-25 11:40', status: '在制', order: 'SO-20261018-042' },
    { serial: 'SN-SD3-261025-0090', product: 'SD-300 伺服驱动器', batch: 'B261025-01', offline: '2026-10-25 11:32', status: '在制', order: 'SO-20261018-042' },
    { serial: 'SN-SM8-261025-0038', product: 'SM-80 伺服电机', batch: 'B261025-02', offline: '2026-10-25 11:20', status: '待检', order: 'SO-20261020-011' },
    { serial: 'SN-SD5-261025-0012', product: 'SD-500 伺服驱动器', batch: 'B261025-03', offline: '2026-10-25 11:02', status: '待检', order: 'SO-20261022-008' },
    { serial: 'SN-SM8-261025-0037', product: 'SM-80 伺服电机', batch: 'B261025-02', offline: '2026-10-25 10:48', status: '合格', order: 'SO-20261020-011' },
    { serial: 'SN-SD3-261025-0089', product: 'SD-300 伺服驱动器', batch: 'B261025-01', offline: '2026-10-25 10:30', status: '已入库', order: 'SO-20261018-042' },
    { serial: 'SN-CM1-261025-0006', product: 'CM-10 运动控制器', batch: 'B261025-04', offline: '2026-10-25 10:12', status: '合格', order: 'SO-20261021-017' },
    { serial: 'SN-SD5-261025-0011', product: 'SD-500 伺服驱动器', batch: 'B261025-03', offline: '2026-10-25 09:55', status: '不合格', order: 'SO-20261022-008' }
  ],
  bySerial: {
    serial: 'SN-SD3-261025-0091',
    product: 'SD-300 伺服驱动器',
    batch: 'B261025-01',
    order: 'SO-20261018-042',
    customer: '恒力自动化（苏州）',
    status: '在制',
    steps: [
      { tone: 'ok', title: '物料上线 · 定子铁芯 / 转子组件', time: '08:12', body: '线边库 AS-A-03 / AS-A-05 · 批次 M-ST-0801 / M-RT-0302' },
      { tone: 'ok', title: '机加 · OP30 转子压装', time: '08:56', body: '设备 CNC-07 · 压装高度 42.06mm（标准 42.0±0.05）' },
      { tone: 'ok', title: '装配 · OP50 拧紧', time: '10:24', body: '扭矩 20.4N·m（标准 18~22）· 设备 SCR-05' },
      { tone: 'warn', title: '装配 · OP40 端盖装配', time: '10:52', body: '间隙 0.21mm 接近上限 0.2mm，已标记重点检查' },
      { tone: 'active', title: '测试 · 待性能测试', time: '11:40', body: '排队 T2 测试台 · 预计 12:10 开始' }
    ],
    materials: [
      { code: 'M-ST-0801', name: '定子铁芯', batch: 'ST-261018-02', supplier: '苏州精密冲压', qty: 1, status: '合格' },
      { code: 'M-RT-0302', name: '转子组件', batch: 'RT-261020-05', supplier: '本厂机加', qty: 1, status: '合格' },
      { code: 'M-EC-0101', name: '编码器', batch: 'EC-261016-11', supplier: '多摩川', qty: 1, status: '合格' },
      { code: 'M-CP-0401', name: '端盖', batch: 'CP-261022-03', supplier: '无锡压铸', qty: 2, status: '合格' }
    ],
    params: [
      { name: '压装高度', value: '42.06 mm', std: '42.00 ± 0.05', result: '合格' },
      { name: '拧紧扭矩', value: '20.4 N·m', std: '18 ~ 22', result: '合格' },
      { name: '端盖间隙', value: '0.21 mm', std: '≤ 0.20', result: '预警' },
      { name: '绝缘电阻', value: '—', std: '≥ 100 MΩ', result: '待测' }
    ],
    inspections: [
      { no: 'QC-261025-031', type: '首检', time: '08:25', result: '合格' },
      { no: 'QC-261025-032', type: '巡检', time: '10:10', result: '合格' }
    ]
  },
  byBatch: {
    batch: 'B261025-03',
    product: 'SD-500 伺服驱动器',
    qty: 150,
    passRate: 94.7,
    issues: 8,
    serials: ['SN-SD5-261025-0012', 'SN-SD5-261025-0011', 'SN-SD5-261025-0010', 'SN-SD5-261025-0009']
  }
};

/* ---------- 车间大屏 ---------- */
DB.board = {
  led: [
    { k: '当班产出', v: '1,284' },
    { k: '计划达成', v: '91.7%', tone: 'amber' },
    { k: '在制 WIP', v: '386' },
    { k: '异常', v: '3', tone: 'red' }
  ],
  kanban: [
    {
      title: '待生产', tone: 'idle', cards: [
        { no: 'WO-007', name: 'SD-500 × 200', meta: ['AS-01', '夜班 20:00'] },
        { no: 'WO-008', name: 'SM-130 × 180', meta: ['AS-02', '夜班 20:00'] }
      ]
    },
    {
      title: '生产中', tone: 'ok', cards: [
        { no: 'WO-001', name: 'SD-300 × 260', meta: ['MC-01', '95%'] },
        { no: 'WO-003', name: 'SD-300 × 300', meta: ['AS-01', '94%'] },
        { no: 'WO-004', name: 'SM-80 × 300', meta: ['AS-02', '86%'], cls: 'p2' },
        { no: 'WO-005', name: 'SD-500 × 150', meta: ['TS-01', '95%'] }
      ]
    },
    {
      title: '待检验', tone: 'warn', cards: [
        { no: 'WO-002', name: 'SM-80 × 240', meta: ['MC-02', '待终检'] },
        { no: 'WO-006', name: 'CM-10 × 150', meta: ['PK-01', '待抽检'], cls: 'p2' }
      ]
    },
    {
      title: '已完工', tone: 'ok', cards: [
        { no: 'WO-009', name: 'SD-300 × 120', meta: ['AS-01', '11:30'] },
        { no: 'WO-010', name: 'SM-80 × 80', meta: ['AS-02', '10:50'] }
      ]
    }
  ],
  alerts: [
    { tone: 'danger', title: 'AS-02 拧紧机停机', time: '09:42', body: '已恢复，累计停机 28 分钟' },
    { tone: 'warn', title: '定子铁芯待料', time: '10:18', body: '预计 14:30 到货' },
    { tone: 'warn', title: 'PK-01 计划缺口 22 台', time: '11:10', body: '建议夜班增开 1 个工位' },
    { tone: 'info', title: 'SD-500 插单 200 台', time: '08:30', body: '已并入 D+3 计划' }
  ],
  downtime: [
    { eq: 'SCR-05', start: '09:42', duration: 28, reason: '扭矩传感器故障' },
    { eq: 'AS-02 上料', start: '10:18', duration: 18.5, reason: '定子铁芯待料' },
    { eq: 'PKG-01', start: '09:05', duration: 6, reason: '标签纸卡纸' },
    { eq: 'CNC-04', start: '08:47', duration: 9, reason: '计划换刀' }
  ]
};

/* ---------- 统计分析 ---------- */
DB.analysis = {
  kpis: [
    { label: '本月产出', value: '27,860', unit: '台', tone: 'ok', delta: '+4.1%', trend: 'up', foot: '同比 +6.8%', icon: 'box' },
    { label: '计划达成率', value: '87.1', unit: '%', tone: 'warn', delta: '+1.2%', trend: 'up', foot: '目标 ≥ 92%', icon: 'target' },
    { label: '平均 OEE', value: '82.6', unit: '%', tone: 'ok', delta: '+2.1%', trend: 'up', foot: '环比 +1.6%', icon: 'gauge' },
    { label: '不良率', value: '2.6', unit: '%', tone: 'ok', delta: '-0.4%', trend: 'up', foot: '目标 ≤ 3%', icon: 'shield' }
  ],
  outputTrend: [
    { d: '10-12', v: 1090 }, { d: '10-13', v: 1120 }, { d: '10-14', v: 1240 }, { d: '10-15', v: 1210 },
    { d: '10-16', v: 1280 }, { d: '10-17', v: 1190 }, { d: '10-18', v: 980 }, { d: '10-19', v: 1180 },
    { d: '10-20', v: 1226 }, { d: '10-21', v: 1304 }, { d: '10-22', v: 1272 }, { d: '10-23', v: 1350 },
    { d: '10-24', v: 1312 }, { d: '10-25', v: 1284 }
  ],
  oeeByLine: [
    { name: 'MC-01', v: 86.4 }, { name: 'MC-02', v: 83.1 }, { name: 'AS-01', v: 84.6 },
    { name: 'AS-02', v: 79.2 }, { name: 'TS-01', v: 88.0 }, { name: 'PK-01', v: 90.2 }
  ],
  defectPareto: [
    { name: '装配间隙', v: 38 }, { name: '扭矩超差', v: 27 }, { name: '绝缘不良', v: 19 },
    { name: '外观划伤', v: 14 }, { name: '标签错误', v: 8 }, { name: '其他', v: 6 }
  ],
  monthly: [
    { h: '3月', v: 24800 }, { h: '4月', v: 25600 }, { h: '5月', v: 26100 }, { h: '6月', v: 25400 },
    { h: '7月', v: 26900 }, { h: '8月', v: 27300 }, { h: '9月', v: 26800 }, { h: '10月', v: 27860 }
  ],
  lineCompare: [
    { line: 'MC-01', out: 4680, plan: 5200, rate: 90.0, ftt: 98.2, oee: 86.4, down: 186, pp: 42 },
    { line: 'MC-02', out: 4260, plan: 4800, rate: 88.8, ftt: 97.8, oee: 83.1, down: 214, pp: 39 },
    { line: 'AS-01', out: 5320, plan: 6000, rate: 88.7, ftt: 97.1, oee: 84.6, down: 240, pp: 36 },
    { line: 'AS-02', out: 5010, plan: 6000, rate: 83.5, ftt: 96.4, oee: 79.2, down: 318, pp: 34 },
    { line: 'TS-01', out: 4450, plan: 5000, rate: 89.0, ftt: 98.6, oee: 88.0, down: 142, pp: 48 },
    { line: 'PK-01', out: 4140, plan: 5000, rate: 82.8, ftt: 99.1, oee: 90.2, down: 128, pp: 52 }
  ]
};

/* ---------- 基础数据 / 建模 ---------- */
DB.masterdata = {
  hierarchy: [
    { level: 0, name: '华东智造基地', type: '工厂', code: 'HD-MES', meta: '3 车间 · 6 产线 · 48 工位' },
    { level: 1, name: '机加车间', type: '车间', code: 'WS-MC', meta: '2 条产线 · 18 台设备' },
    { level: 2, name: '机加一线', type: '产线', code: 'MC-01', meta: '9 工位 · 节拍 41s' },
    { level: 3, name: 'OP30 转子压装', type: '工位', code: 'MC-01-OP30', meta: '设备 CNC-07' },
    { level: 2, name: '机加二线', type: '产线', code: 'MC-02', meta: '8 工位 · 节拍 43s' },
    { level: 1, name: '装配车间', type: '车间', code: 'WS-AS', meta: '2 条产线 · 24 工位' },
    { level: 2, name: '装配一线', type: '产线', code: 'AS-01', meta: '8 工位 · 节拍 42s' },
    { level: 3, name: 'OP50 拧紧', type: '工位', code: 'AS-01-OP50', meta: '设备 SCR-01' },
    { level: 1, name: '测试包装车间', type: '车间', code: 'WS-TP', meta: '2 条产线 · 6 工位' },
    { level: 2, name: '测试线', type: '产线', code: 'TS-01', meta: '4 个测试台' }
  ],
  products: [
    { code: 'P-SD300', name: 'SD-300 伺服驱动器', family: 'SD 系列', unit: '台', lifecycle: '量产', version: 'V3.2' },
    { code: 'P-SD500', name: 'SD-500 伺服驱动器', family: 'SD 系列', unit: '台', lifecycle: '量产', version: 'V2.8' },
    { code: 'P-SM80', name: 'SM-80 伺服电机', family: 'SM 系列', unit: '台', lifecycle: '量产', version: 'V1.9' },
    { code: 'P-SM130', name: 'SM-130 伺服电机', family: 'SM 系列', unit: '台', lifecycle: '量产', version: 'V1.6' },
    { code: 'P-CM10', name: 'CM-10 运动控制器', family: 'CM 系列', unit: '台', lifecycle: '试产', version: 'V0.9' }
  ],
  boms: [
    { product: 'SD-300', version: 'BOM-V3.2', items: 42, key: '定子铁芯 / 转子 / 编码器', status: '生效' },
    { product: 'SD-500', version: 'BOM-V2.8', items: 48, key: '定子铁芯 / 转子 / 编码器', status: '生效' },
    { product: 'SM-80', version: 'BOM-V1.9', items: 26, key: '定子 / 转子 / 端盖', status: '生效' },
    { product: 'SM-130', version: 'BOM-V1.6', items: 31, key: '定子 / 转子 / 端盖', status: '生效' },
    { product: 'CM-10', version: 'BOM-V0.9', items: 55, key: '主板 / 功率板 / 外壳', status: '试产' }
  ],
  routes: [
    { product: 'SD-300', route: 'RT-SD300-V3', ops: 8, cycle: '42s', status: '生效' },
    { product: 'SD-500', route: 'RT-SD500-V2', ops: 8, cycle: '45s', status: '生效' },
    { product: 'SM-80', route: 'RT-SM80-V1', ops: 7, cycle: '40s', status: '生效' },
    { product: 'SM-130', route: 'RT-SM130-V1', ops: 7, cycle: '44s', status: '生效' },
    { product: 'CM-10', route: 'RT-CM10-V0', ops: 9, cycle: '52s', status: '试产' }
  ],
  materials: [
    { code: 'M-ST-0801', name: '定子铁芯', type: '关键件', unit: '件', supplier: '苏州精密冲压', lead: '7 天' },
    { code: 'M-RT-0302', name: '转子组件', type: '自制件', unit: '件', supplier: '本厂机加', lead: '1 天' },
    { code: 'M-EC-0101', name: '编码器', type: '关键件', unit: '件', supplier: '多摩川', lead: '21 天' },
    { code: 'M-CP-0401', name: '端盖', type: '标准件', unit: '件', supplier: '无锡压铸', lead: '5 天' },
    { code: 'M-WH-0203', name: '线束', type: '标准件', unit: '套', supplier: '昆山线束', lead: '3 天' },
    { code: 'M-BR-0501', name: '轴承', type: '标准件', unit: '件', supplier: 'NSK', lead: '14 天' }
  ],
  shifts: [
    { name: '白班', time: '08:00-20:00', break: '12:00-12:30', capacity: '100%', status: '启用' },
    { name: '夜班', time: '20:00-08:00', break: '00:00-00:30', capacity: '85%', status: '启用' },
    { name: '常白班', time: '08:30-17:30', break: '12:00-13:00', capacity: '100%', status: '启用' },
    { name: '维修班', time: '按需', break: '—', capacity: '—', status: '停用' }
  ],
  teams: [
    { name: '装配一组', leader: '陈刚', members: 12, line: 'AS-01', shift: '白班' },
    { name: '装配二组', leader: '赵敏', members: 11, line: 'AS-02', shift: '白班' },
    { name: '机加组', leader: '李强', members: 9, line: 'MC-01/02', shift: '白班' },
    { name: '测试包装组', leader: '孙悦', members: 10, line: 'TS-01/PK-01', shift: '白班' }
  ]
};

/* ---------- 系统集成 ---------- */
DB.system = {
  kpis: [
    { label: '接口总数', value: '15', unit: '个', tone: '', foot: '运行 12 · 异常 2 · 停用 1', icon: 'plug' },
    { label: '今日消息', value: '18,426', unit: '条', tone: 'ok', delta: '+6.4%', trend: 'up', foot: '成功率 99.87%', icon: 'sync' },
    { label: '失败消息', value: '24', unit: '条', tone: 'danger', foot: '待重试 6 条', icon: 'alert' },
    { label: '平均延迟', value: '186', unit: 'ms', tone: 'ok', foot: 'P95 420ms', icon: 'activity' }
  ],
  interfaces: [
    { name: 'ERP 生产订单同步', code: 'INT-ERP-001', direction: 'ERP → MES', protocol: 'REST', status: '运行', success: 99.98, last: '11:58:20', latency: '120ms' },
    { name: 'ERP 物料主数据', code: 'INT-ERP-002', direction: 'ERP → MES', protocol: 'REST', status: '运行', success: 100, last: '11:50:02', latency: '98ms' },
    { name: 'WMS 线边拉动', code: 'INT-WMS-001', direction: 'MES → WMS', protocol: 'MQ', status: '运行', success: 99.92, last: '11:57:44', latency: '210ms' },
    { name: 'QMS 检验结果', code: 'INT-QMS-001', direction: 'QMS ↔ MES', protocol: 'REST', status: '运行', success: 99.85, last: '11:56:10', latency: '260ms' },
    { name: '设备数据采集', code: 'INT-SCADA-001', direction: 'SCADA → MES', protocol: 'OPC UA', status: '报警', success: 97.40, last: '11:42:05', latency: '640ms' },
    { name: 'PLC 拧紧扭矩', code: 'INT-PLC-002', direction: 'PLC → MES', protocol: 'OPC UA', status: '报警', success: 95.10, last: '10:05:33', latency: '880ms' },
    { name: '标签打印服务', code: 'INT-PRT-001', direction: 'MES → 打印', protocol: 'HTTP', status: '运行', success: 99.99, last: '11:58:01', latency: '60ms' },
    { name: '客户追溯平台', code: 'INT-CUS-001', direction: 'MES → 客户', protocol: 'REST', status: '停用', success: 0, last: '10-20 18:00', latency: '—' }
  ],
  logs: [
    { time: '11:58:20', code: 'INT-ERP-001', msg: '生产订单 SO-20261022-008 同步成功', level: '成功', duration: '116ms' },
    { time: '11:57:44', code: 'INT-WMS-001', msg: '拉动单 PL-261025-012 已推送', level: '成功', duration: '204ms' },
    { time: '11:56:10', code: 'INT-QMS-001', msg: '检验单 QC-261025-038 回传成功', level: '成功', duration: '252ms' },
    { time: '11:42:05', code: 'INT-SCADA-001', msg: 'SCR-05 采集超时，已重试 3 次', level: '失败', duration: '3000ms' },
    { time: '11:36:12', code: 'INT-PLC-002', msg: '扭矩数据包解析异常：字段缺失', level: '失败', duration: '—' },
    { time: '11:20:44', code: 'INT-WMS-001', msg: '物料编码 M-LB-0801 库存校验通过', level: '成功', duration: '188ms' },
    { time: '11:05:30', code: 'INT-ERP-001', msg: '工单 WO-261025-008 状态回传成功', level: '成功', duration: '132ms' },
    { time: '10:58:02', code: 'INT-PRT-001', msg: '目视卡打印任务完成 30 张', level: '成功', duration: '48ms' }
  ],
  params: [
    { key: 'plan.lock.days', name: '计划锁定天数', value: '3', unit: '天', group: '计划' },
    { key: 'plan.publish.time', name: '次日计划发布时间', value: '15:00', unit: '', group: '计划' },
    { key: 'prod.report.interval', name: '报工采集间隔', value: '30', unit: '秒', group: '执行' },
    { key: 'quality.ftt.target', name: 'FTT 目标值', value: '97.5', unit: '%', group: '质量' },
    { key: 'eq.oee.target', name: 'OEE 目标值', value: '80', unit: '%', group: '设备' },
    { key: 'trace.retention', name: '追溯数据保留', value: '10', unit: '年', group: '追溯' }
  ],
  roles: [
    { name: '制造运营经理', users: 3, scope: '全基地', menus: '全部', status: '启用' },
    { name: '车间主管', users: 8, scope: '本车间', menus: '计划 / 执行 / 质量', status: '启用' },
    { name: '质量工程师', users: 6, scope: '全基地', menus: '质量 / 追溯 / 报表', status: '启用' },
    { name: '设备工程师', users: 5, scope: '全基地', menus: '设备 / 报警 / 点检', status: '启用' }
  ]
};

/* ============================================================
   制造 + 供应链协同（来源：《爱达兴高铂尔供应链协同系统业务方案 V1.0》）
   覆盖：组织与编码、采购、销售、库存、存货核算、供应链协同、
        生产制造、U8C 财务接口
   ============================================================ */

DB.orgs = [
  { code: 'YY-ADX', name: '岳阳爱达兴智能科技有限公司', short: '岳阳爱达兴', role: '制造基地' },
  { code: 'JS-GBE', name: '江苏高铂尔智能科技有限公司', short: '江苏高铂尔', role: '供应链中心' }
];

DB.scm = {
  kpis: [
    { label: '采购在途', value: '2,860', unit: '万元', delta: '+6.8%', trend: 'up', tone: '', foot: '在途订单 18 单', icon: 'truck' },
    { label: '待交付订单', value: '24', unit: '单', delta: '逾期 3', trend: 'down', tone: 'warn', foot: '供应商交付计划', icon: 'clipboard' },
    { label: '库存金额', value: '1,286', unit: '万元', delta: '+2.4%', trend: 'up', tone: '', foot: '4 仓 · 386 个 SKU', icon: 'warehouse' },
    { label: '交付准时率', value: '92.6', unit: '%', delta: '-1.2%', trend: 'down', tone: 'warn', foot: '目标 ≥ 95%', icon: 'target' },
    { label: '本月采购额', value: '3,420', unit: '万元', delta: '+6.8%', trend: 'up', tone: 'ok', foot: '订单 32 张', icon: 'box' },
    { label: '本月销售额', value: '4,860', unit: '万元', delta: '+9.2%', trend: 'up', tone: 'ok', foot: '订单 41 张', icon: 'trend' },
    { label: '应付余额', value: '1,860', unit: '万元', delta: '待结算 4', trend: 'flat', tone: 'accent', foot: 'U8C 应付单据', icon: 'fileText' },
    { label: 'U8C 凭证同步', value: '99.9', unit: '%', delta: '待推送 2', trend: 'flat', tone: 'ok', foot: '最近同步 11:58', icon: 'link' }
  ],
  bizMap: [
    { name: '供应商协同', meta: '8 家合格供应商 · 2 组织', tone: 'ok', icon: 'users', href: 'collab.html', cells: [{ k: '在途', v: '2,860 万' }, { k: '准时率', v: '92.6%' }] },
    { name: '采购管理', meta: '询价 → 订单 → 到货 → 入库', tone: '', icon: 'clipboard', href: 'purchase.html', cells: [{ k: '在途订单', v: '18' }, { k: '待入库', v: '6' }] },
    { name: '库存管理', meta: '4 仓 · 386 个 SKU', tone: 'warn', icon: 'warehouse', href: 'inventory.html', cells: [{ k: '库存金额', v: '1,286 万' }, { k: '预警', v: '5' }] },
    { name: '生产制造', meta: '集团计划 → 指令 → 投料', tone: '', icon: 'cpu', href: 'manufacturing.html', cells: [{ k: '生产指令', v: '18' }, { k: '齐套率', v: '94%' }] },
    { name: '销售管理', meta: '订单 → 出库 → 开票 → 收入', tone: '', icon: 'truck', href: 'sales.html', cells: [{ k: '待发货', v: '8' }, { k: '应收', v: '2,140 万' }] },
    { name: 'U8C 财务', meta: '存货凭证 · 应收应付', tone: 'ok', icon: 'link', href: 'system.html', cells: [{ k: '同步率', v: '99.9%' }, { k: '待推送', v: '2' }] }
  ],
  todos: [
    { tone: 'danger', title: '3 张采购订单交付逾期', time: '11:20', body: 'PO-261021-006 定子铁芯逾期 2 天，已通知采购员与供应商，交付计划待重排。' },
    { tone: 'warn', title: '5 个物料低于安全库存', time: '10:48', body: '定子铁芯、编码器、标签纸等低于安全库存，建议立即生成采购申请。' },
    { tone: 'warn', title: '2 张 U8C 凭证推送失败', time: '10:05', body: 'CU-261024-018 存货凭证科目映射缺失，已进入重试队列。' },
    { tone: 'info', title: '供应商资质到期提醒', time: '09:30', body: '宁波轴承营业执照 30 天后到期，请供应商在协同门户更新资质文件。' },
    { tone: 'ok', title: '10 月采购结算完成 86%', time: '08:50', body: '已结算 28 张发票，剩余 4 张待财务勾对。' }
  ],
  deliveryTrend: [
    { d: '10-12', v: 88.2 }, { d: '10-13', v: 90.1 }, { d: '10-14', v: 89.4 }, { d: '10-15', v: 91.0 },
    { d: '10-16', v: 92.4 }, { d: '10-17', v: 93.1 }, { d: '10-18', v: 90.8 }, { d: '10-19', v: 91.6 },
    { d: '10-20', v: 92.2 }, { d: '10-21', v: 93.4 }, { d: '10-22', v: 94.0 }, { d: '10-23', v: 93.2 },
    { d: '10-24', v: 93.8 }, { d: '10-25', v: 92.6 }
  ],
  monthly: [
    { h: '3月', purchase: 2860, sales: 3980 }, { h: '4月', purchase: 2980, sales: 4120 },
    { h: '5月', purchase: 3120, sales: 4260 }, { h: '6月', purchase: 3040, sales: 4180 },
    { h: '7月', purchase: 3260, sales: 4420 }, { h: '8月', purchase: 3350, sales: 4560 },
    { h: '9月', purchase: 3200, sales: 4450 }, { h: '10月', purchase: 3420, sales: 4860 }
  ],
  exceptions: [
    { supplier: '苏州精密冲压', po: 'PO-261021-006', material: '定子铁芯', qty: 2000, due: '2026-10-23', delay: 2, reason: '原材料涨价，供应商产能不足', owner: '李强', status: '处理中' },
    { supplier: '多摩川（上海）', po: 'PO-261018-003', material: '编码器', qty: 800, due: '2026-10-24', delay: 1, reason: '清关延误', owner: '王磊', status: '待确认' },
    { supplier: '昆山线束', po: 'PO-261022-011', material: '线束', qty: 1200, due: '2026-10-25', delay: 0, reason: '送货单据缺失', owner: '赵敏', status: '已解决' },
    { supplier: '无锡压铸', po: 'PO-261019-008', material: '端盖', qty: 1500, due: '2026-10-22', delay: 3, reason: '模具维修', owner: '陈刚', status: '处理中' },
    { supplier: '常州电镀', po: 'PO-261020-009', material: '表面处理件', qty: 600, due: '2026-10-24', delay: 1, reason: '环保限产', owner: '周涛', status: '待确认' }
  ],
  stockAlerts: [
    { material: '定子铁芯', code: 'M-ST-0801', warehouse: 'AS 线边库', current: 1240, safe: 2000, type: '低于安全库存', owner: '物料组' },
    { material: '编码器', code: 'M-EC-0101', warehouse: '原料库 A', current: 820, safe: 900, type: '低于安全库存', owner: '采购部' },
    { material: '产品标签纸', code: 'M-LB-0801', warehouse: '成品库 F', current: 240, safe: 300, type: '低于安全库存', owner: '包装组' },
    { material: '包装泡沫', code: 'M-PK-0701', warehouse: '成品库 F', current: 960, safe: 500, type: '高于最高库存', owner: '仓储部' },
    { material: 'O 型密封圈', code: 'M-OR-0602', warehouse: '原料库 A', current: 6800, safe: 2000, type: '呆滞 90 天', owner: '仓储部' }
  ],
  u8c: {
    lastSync: '2026-10-25 11:58:12',
    period: '2026-10',
    vouchers: [
      { no: 'CU-261025-021', type: '存货凭证', source: '采购入库 RK-261025-012', amount: 86.4, direction: 'MES → U8C', status: '已同步', time: '11:58' },
      { no: 'CU-261025-020', type: '存货凭证', source: '销售出库 CK-261025-018', amount: 128.6, direction: 'MES → U8C', status: '已同步', time: '11:42' },
      { no: 'AP-261025-009', type: '应付单', source: '采购发票 FP-261025-006', amount: 96.2, direction: 'MES → U8C', status: '已同步', time: '11:20' },
      { no: 'AR-261025-007', type: '应收单', source: '销售发票 FP-261025-004', amount: 142.8, direction: 'MES → U8C', status: '已同步', time: '10:58' },
      { no: 'CU-261024-018', type: '存货凭证', source: '其他入库 RK-261024-009', amount: 12.6, direction: 'MES → U8C', status: '失败', time: '昨日 18:20' },
      { no: 'AP-261024-006', type: '应付单', source: '采购发票 FP-261024-003', amount: 68.4, direction: 'MES → U8C', status: '已同步', time: '昨日 17:40' }
    ]
  }
};

/* ---------- 采购管理 ---------- */
DB.purchase = {
  kpis: [
    { label: '本月采购额', value: '3,420', unit: '万元', delta: '+6.8%', trend: 'up', tone: '', foot: '订单 32 张 · 供应商 8 家', icon: 'box' },
    { label: '在途订单', value: '18', unit: '单', tone: 'warn', foot: '逾期 3 单', icon: 'truck' },
    { label: '待入库', value: '6', unit: '单', tone: '', foot: '今日到货 3 单', icon: 'warehouse' },
    { label: '待结算', value: '4', unit: '单', tone: 'accent', foot: '发票金额 286.4 万元', icon: 'fileText' }
  ],
  suppliers: [
    { code: 'S02-001', name: '苏州精密冲压有限公司', category: '物料 02', level: '战略', contact: '陈志远', phone: '138****6621', qualified: '已审核', status: '合作中' },
    { code: 'S02-002', name: '无锡压铸科技有限公司', category: '物料 02', level: '核心', contact: '刘海峰', phone: '139****3188', qualified: '已审核', status: '合作中' },
    { code: 'S02-003', name: '多摩川（上海）贸易', category: '物料 02', level: '战略', contact: '山田', phone: '021****8801', qualified: '已审核', status: '合作中' },
    { code: 'S02-004', name: '昆山线束制造', category: '物料 02', level: '一般', contact: '张敏', phone: '137****4402', qualified: '已审核', status: '合作中' },
    { code: 'S02-005', name: '宁波轴承工业', category: '物料 02', level: '一般', contact: '周伟', phone: '135****9021', qualified: '待审核', status: '考察中' },
    { code: 'S01-001', name: '江苏某整车装备厂', category: '整车 01', level: '战略', contact: '王军', phone: '136****7788', qualified: '已审核', status: '合作中' },
    { code: 'S03-001', name: '常州电镀加工', category: '外协 03', level: '一般', contact: '李娜', phone: '138****2210', qualified: '已审核', status: '合作中' },
    { code: 'S04-001', name: '顺丰供应链物流', category: '服务 04', level: '一般', contact: '赵磊', phone: '400****5566', qualified: '已审核', status: '合作中' }
  ],
  orders: [
    { no: 'PO-261025-004', supplier: '苏州精密冲压', material: '定子铁芯', qty: 4000, amount: 86.4, orderDate: '2026-10-25', deliveryDate: '2026-10-28', received: 0, status: '已下单' },
    { no: 'PO-261024-010', supplier: '无锡压铸', material: '端盖', qty: 3000, amount: 42.6, orderDate: '2026-10-24', deliveryDate: '2026-10-27', received: 1500, status: '部分到货' },
    { no: 'PO-261023-007', supplier: '多摩川（上海）', material: '编码器', qty: 2000, amount: 168.0, orderDate: '2026-10-23', deliveryDate: '2026-10-30', received: 0, status: '已下单' },
    { no: 'PO-261022-011', supplier: '昆山线束', material: '线束', qty: 1200, amount: 18.6, orderDate: '2026-10-22', deliveryDate: '2026-10-25', received: 1200, status: '已到货' },
    { no: 'PO-261021-006', supplier: '苏州精密冲压', material: '定子铁芯', qty: 2000, amount: 43.2, orderDate: '2026-10-21', deliveryDate: '2026-10-23', received: 0, status: '交付逾期' },
    { no: 'PO-261020-009', supplier: '常州电镀', material: '表面处理件', qty: 600, amount: 9.8, orderDate: '2026-10-20', deliveryDate: '2026-10-24', received: 300, status: '部分到货' },
    { no: 'PO-261019-008', supplier: '无锡压铸', material: '端盖', qty: 1500, amount: 21.3, orderDate: '2026-10-19', deliveryDate: '2026-10-22', received: 1500, status: '已到货' },
    { no: 'PO-261018-003', supplier: '多摩川（上海）', material: '编码器', qty: 800, amount: 67.2, orderDate: '2026-10-18', deliveryDate: '2026-10-24', received: 0, status: '交付逾期' }
  ],
  arrivals: [
    { no: 'DH-261025-003', po: 'PO-261022-011', supplier: '昆山线束', date: '2026-10-25', qty: 1200, passed: 1200, rejected: 0, status: '已入库' },
    { no: 'DH-261025-002', po: 'PO-261024-010', supplier: '无锡压铸', date: '2026-10-25', qty: 1500, passed: 1460, rejected: 40, status: '部分入库' },
    { no: 'DH-261025-001', po: 'PO-261020-009', supplier: '常州电镀', date: '2026-10-25', qty: 300, passed: 300, rejected: 0, status: '已入库' },
    { no: 'DH-261024-006', po: 'PO-261019-008', supplier: '无锡压铸', date: '2026-10-24', qty: 1500, passed: 1500, rejected: 0, status: '已入库' },
    { no: 'DH-261024-005', po: 'PO-261023-007', supplier: '多摩川（上海）', date: '2026-10-24', qty: 0, passed: 0, rejected: 0, status: '未到货' },
    { no: 'DH-261023-004', po: 'PO-261021-006', supplier: '苏州精密冲压', date: '2026-10-23', qty: 0, passed: 0, rejected: 0, status: '未到货' }
  ],
  receipts: [
    { no: 'RK-261025-012', po: 'PO-261022-011', supplier: '昆山线束', warehouse: '原料库 A', qty: 1200, amount: 18.6, date: '2026-10-25', status: '已审核' },
    { no: 'RK-261025-011', po: 'PO-261024-010', supplier: '无锡压铸', warehouse: '原料库 A', qty: 1460, amount: 20.7, date: '2026-10-25', status: '已审核' },
    { no: 'RK-261025-010', po: 'PO-261020-009', supplier: '常州电镀', warehouse: '原料库 A', qty: 300, amount: 4.9, date: '2026-10-25', status: '待审核' },
    { no: 'RK-261024-009', po: 'PO-261019-008', supplier: '无锡压铸', warehouse: '原料库 A', qty: 1500, amount: 21.3, date: '2026-10-24', status: '已审核' },
    { no: 'RK-261023-008', po: 'PO-261018-003', supplier: '多摩川（上海）', warehouse: '原料库 A', qty: 0, amount: 0, date: '2026-10-23', status: '已作废' },
    { no: 'RK-261022-007', po: 'PO-261017-002', supplier: '昆山线束', warehouse: '原料库 A', qty: 800, amount: 12.4, date: '2026-10-22', status: '已审核' }
  ],
  returns: [
    { no: 'CT-261025-002', po: 'PO-261024-010', supplier: '无锡压铸', material: '端盖', qty: 40, reason: '尺寸超差', amount: 0.57, status: '待退货' },
    { no: 'CT-261024-001', po: 'PO-261019-008', supplier: '无锡压铸', material: '端盖', qty: 20, reason: '外观划伤', amount: 0.28, status: '已退货' },
    { no: 'CT-261022-003', po: 'PO-261017-002', supplier: '昆山线束', material: '线束', qty: 15, reason: '端子压接不良', amount: 0.23, status: '已退货' },
    { no: 'CT-261020-002', po: 'PO-261015-001', supplier: '常州电镀', material: '表面处理件', qty: 30, reason: '镀层厚度不足', amount: 0.49, status: '已退款' }
  ],
  invoices: [
    { no: 'FP-261025-006', po: 'PO-261022-011', supplier: '昆山线束', amount: 18.6, tax: 2.42, type: '专票', date: '2026-10-25', status: '待结算' },
    { no: 'FP-261025-005', po: 'PO-261024-010', supplier: '无锡压铸', amount: 20.7, tax: 2.69, type: '专票', date: '2026-10-25', status: '待结算' },
    { no: 'FP-261024-004', po: 'PO-261019-008', supplier: '无锡压铸', amount: 21.3, tax: 2.77, type: '专票', date: '2026-10-24', status: '已结算' },
    { no: 'FP-261024-003', po: 'PO-261018-003', supplier: '多摩川（上海）', amount: 67.2, tax: 8.74, type: '专票', date: '2026-10-24', status: '已结算' },
    { no: 'FP-261023-002', po: 'PO-261020-009', supplier: '常州电镀', amount: 9.8, tax: 1.27, type: '普票', date: '2026-10-23', status: '待结算' }
  ],
  settlements: [
    { no: 'JS-261025-004', supplier: '昆山线束', invoice: 'FP-261025-006', amount: 18.6, date: '2026-10-25', status: '待审核' },
    { no: 'JS-261025-003', supplier: '无锡压铸', invoice: 'FP-261025-005', amount: 20.7, date: '2026-10-25', status: '待审核' },
    { no: 'JS-261024-002', supplier: '无锡压铸', invoice: 'FP-261024-004', amount: 21.3, date: '2026-10-24', status: '已结算' },
    { no: 'JS-261024-001', supplier: '多摩川（上海）', invoice: 'FP-261024-003', amount: 67.2, date: '2026-10-24', status: '已结算' },
    { no: 'JS-261023-006', supplier: '常州电镀', invoice: 'FP-261023-002', amount: 9.8, date: '2026-10-23', status: '已结算' }
  ],
  estimates: [
    { no: 'ZG-2610-004', po: 'PO-261025-004', supplier: '苏州精密冲压', material: '定子铁芯', qty: 4000, amount: 86.4, period: '2026-10', status: '暂估中' },
    { no: 'ZG-2610-003', po: 'PO-261023-007', supplier: '多摩川（上海）', material: '编码器', qty: 2000, amount: 168.0, period: '2026-10', status: '暂估中' },
    { no: 'ZG-2610-002', po: 'PO-261021-006', supplier: '苏州精密冲压', material: '定子铁芯', qty: 2000, amount: 43.2, period: '2026-10', status: '待回冲' },
    { no: 'ZG-2610-001', po: 'PO-261019-008', supplier: '无锡压铸', material: '端盖', qty: 1500, amount: 21.3, period: '2026-10', status: '已回冲' }
  ]
};

/* ---------- 销售管理 ---------- */
DB.sales = {
  kpis: [
    { label: '本月销售额', value: '4,860', unit: '万元', delta: '+9.2%', trend: 'up', tone: 'ok', foot: '订单 41 张', icon: 'trend' },
    { label: '待发货', value: '8', unit: '单', tone: 'warn', foot: '其中加急 2 单', icon: 'truck' },
    { label: '待开票', value: '6', unit: '单', tone: '', foot: '金额 286.4 万元', icon: 'fileText' },
    { label: '应收余额', value: '2,140', unit: '万元', tone: 'accent', foot: '逾期 120 万元', icon: 'target' }
  ],
  customers: [
    { code: 'C-001', name: '恒力自动化（苏州）', level: 'A', contact: '孙经理', phone: '139****8801', credit: '500 万', status: '合作中' },
    { code: 'C-002', name: '宁波海天精工', level: 'A', contact: '李经理', phone: '137****6602', credit: '420 万', status: '合作中' },
    { code: 'C-003', name: '广州数控设备', level: 'B', contact: '陈经理', phone: '136****4433', credit: '260 万', status: '合作中' },
    { code: 'C-004', name: '山东临工机械', level: 'B', contact: '王经理', phone: '135****7788', credit: '300 万', status: '合作中' },
    { code: 'C-005', name: '江苏亚威股份', level: 'A', contact: '周经理', phone: '138****1122', credit: '450 万', status: '合作中' },
    { code: 'C-006', name: '上海电气自动化', level: 'C', contact: '赵经理', phone: '021****9900', credit: '150 万', status: '暂停' }
  ],
  orders: [
    { no: 'SO-261025-008', customer: '恒力自动化（苏州）', product: 'SD-300 伺服驱动器', qty: 260, amount: 182.0, orderDate: '2026-10-25', deliveryDate: '2026-10-28', status: '待发货' },
    { no: 'SO-261025-007', customer: '宁波海天精工', product: 'SM-80 伺服电机', qty: 300, amount: 156.0, orderDate: '2026-10-25', deliveryDate: '2026-10-30', status: '生产中' },
    { no: 'SO-261024-006', customer: '广州数控设备', product: 'SD-500 伺服驱动器', qty: 150, amount: 168.0, orderDate: '2026-10-24', deliveryDate: '2026-10-27', status: '待发货' },
    { no: 'SO-261024-005', customer: '山东临工机械', product: 'CM-10 运动控制器', qty: 150, amount: 96.0, orderDate: '2026-10-24', deliveryDate: '2026-11-02', status: '生产中' },
    { no: 'SO-261023-004', customer: '江苏亚威股份', product: 'SM-130 伺服电机', qty: 180, amount: 118.8, orderDate: '2026-10-23', deliveryDate: '2026-10-26', status: '待发货' },
    { no: 'SO-261022-003', customer: '恒力自动化（苏州）', product: 'SD-300 伺服驱动器', qty: 120, amount: 84.0, orderDate: '2026-10-22', deliveryDate: '2026-10-24', status: '已发货' },
    { no: 'SO-261021-002', customer: '宁波海天精工', product: 'SM-80 伺服电机', qty: 200, amount: 104.0, orderDate: '2026-10-21', deliveryDate: '2026-10-23', status: '已开票' },
    { no: 'SO-261020-001', customer: '江苏亚威股份', product: 'SD-500 伺服驱动器', qty: 100, amount: 112.0, orderDate: '2026-10-20', deliveryDate: '2026-10-22', status: '已收款' }
  ],
  outbounds: [
    { no: 'CK-261025-018', order: 'SO-261022-003', customer: '恒力自动化（苏州）', product: 'SD-300 伺服驱动器', qty: 120, warehouse: '成品库 F', date: '2026-10-25', status: '已出库' },
    { no: 'CK-261025-017', order: 'SO-261021-002', customer: '宁波海天精工', product: 'SM-80 伺服电机', qty: 200, warehouse: '成品库 F', date: '2026-10-25', status: '已出库' },
    { no: 'CK-261024-016', order: 'SO-261020-001', customer: '江苏亚威股份', product: 'SD-500 伺服驱动器', qty: 100, warehouse: '成品库 F', date: '2026-10-24', status: '已出库' },
    { no: 'CK-261024-015', order: 'SO-261019-009', customer: '广州数控设备', product: 'CM-10 运动控制器', qty: 80, warehouse: '成品库 F', date: '2026-10-24', status: '已签收' },
    { no: 'CK-261023-014', order: 'SO-261018-008', customer: '山东临工机械', product: 'SM-130 伺服电机', qty: 150, warehouse: '成品库 F', date: '2026-10-23', status: '已签收' },
    { no: 'CK-261022-013', order: 'SO-261017-007', customer: '恒力自动化（苏州）', product: 'SD-300 伺服驱动器', qty: 90, warehouse: '成品库 F', date: '2026-10-22', status: '已签收' }
  ],
  invoices: [
    { no: 'FP-261025-004', order: 'SO-261021-002', customer: '宁波海天精工', amount: 104.0, tax: 13.52, type: '专票', date: '2026-10-25', status: '待开票' },
    { no: 'FP-261024-003', order: 'SO-261020-001', customer: '江苏亚威股份', amount: 112.0, tax: 14.56, type: '专票', date: '2026-10-24', status: '已开票' },
    { no: 'FP-261024-002', order: 'SO-261019-009', customer: '广州数控设备', amount: 96.0, tax: 12.48, type: '专票', date: '2026-10-24', status: '已开票' },
    { no: 'FP-261023-001', order: 'SO-261018-008', customer: '山东临工机械', amount: 118.8, tax: 15.44, type: '专票', date: '2026-10-23', status: '已开票' },
    { no: 'FP-261022-005', order: 'SO-261017-007', customer: '恒力自动化（苏州）', amount: 84.0, tax: 10.92, type: '普票', date: '2026-10-22', status: '已开票' }
  ],
  revenues: [
    { no: 'SR-2610-005', customer: '恒力自动化（苏州）', order: 'SO-261017-007', amount: 84.0, period: '2026-10', confirmDate: '2026-10-22', type: '已开票确认', status: '已确认' },
    { no: 'SR-2610-004', customer: '江苏亚威股份', order: 'SO-261020-001', amount: 112.0, period: '2026-10', confirmDate: '2026-10-24', type: '已开票确认', status: '已确认' },
    { no: 'SR-2610-003', customer: '广州数控设备', order: 'SO-261019-009', amount: 96.0, period: '2026-10', confirmDate: '2026-10-24', type: '已开票确认', status: '已确认' },
    { no: 'SR-2610-002', customer: '宁波海天精工', order: 'SO-261021-002', amount: 104.0, period: '2026-10', confirmDate: '—', type: '已出库未开票', status: '待确认' },
    { no: 'SR-2610-001', customer: '山东临工机械', order: 'SO-261018-008', amount: 118.8, period: '2026-10', confirmDate: '2026-10-23', type: '已开票确认', status: '已确认' }
  ]
};

/* ---------- 库存管理 ---------- */
DB.inventory = {
  kpis: [
    { label: '库存金额', value: '1,286', unit: '万元', delta: '+2.4%', trend: 'up', tone: '', foot: '4 个仓库 · 386 SKU', icon: 'warehouse' },
    { label: '库存预警', value: '5', unit: '项', tone: 'danger', foot: '低于安全 3 · 呆滞 1', icon: 'alert' },
    { label: '待入库', value: '6', unit: '单', tone: 'warn', foot: '采购到货 3 单', icon: 'box' },
    { label: '待出库', value: '8', unit: '单', tone: '', foot: '销售订单 8 单', icon: 'truck' }
  ],
  warehouses: [
    { name: '原料库 A', meta: 'A 区 · 立库', locations: 320, used: 274, value: '862.4 万', tone: 'ok', alert: 1 },
    { name: '线边库 AS', meta: '装配车间 · 线边', locations: 96, used: 88, value: '324.0 万', tone: 'warn', alert: 3 },
    { name: '成品库 F', meta: 'F 区 · 发货暂存', locations: 180, used: 121, value: '286.8 万', tone: '', alert: 1 },
    { name: '备件库 S', meta: 'S 区 · 设备备件', locations: 120, used: 64, value: '86.2 万', tone: 'ok', alert: 0 }
  ],
  stock: [
    { code: 'M-ST-0801', name: '定子铁芯', category: '物料 02', warehouse: 'AS 线边库', qty: 1240, unit: '件', amount: 26.8, safe: 2000, min: 1200, max: 6000, status: '缺料' },
    { code: 'M-EC-0101', name: '编码器', category: '物料 02', warehouse: '原料库 A', qty: 820, unit: '件', amount: 68.8, safe: 900, min: 600, max: 3000, status: '预警' },
    { code: 'M-CP-0401', name: '端盖', category: '物料 02', warehouse: '原料库 A', qty: 3480, unit: '件', amount: 49.6, safe: 1200, min: 800, max: 5000, status: '正常' },
    { code: 'M-RT-0302', name: '转子组件', category: '物料 02', warehouse: 'AS 线边库', qty: 2860, unit: '件', amount: 57.2, safe: 1500, min: 1000, max: 5000, status: '正常' },
    { code: 'M-WH-0203', name: '线束', category: '物料 02', warehouse: '原料库 A', qty: 1620, unit: '套', amount: 12.4, safe: 800, min: 600, max: 3000, status: '正常' },
    { code: 'M-BR-0501', name: '轴承', category: '物料 02', warehouse: '原料库 A', qty: 4200, unit: '件', amount: 18.6, safe: 1500, min: 1000, max: 8000, status: '正常' },
    { code: 'P-SD300', name: 'SD-300 伺服驱动器', category: '成品 03', warehouse: '成品库 F', qty: 386, unit: '台', amount: 270.2, safe: 100, min: 50, max: 800, status: '正常' },
    { code: 'P-SM80', name: 'SM-80 伺服电机', category: '成品 03', warehouse: '成品库 F', qty: 268, unit: '台', amount: 139.4, safe: 100, min: 50, max: 600, status: '正常' },
    { code: 'M-LB-0801', name: '产品标签纸', category: '包材 04', warehouse: '成品库 F', qty: 240, unit: '卷', amount: 1.2, safe: 300, min: 200, max: 1000, status: '预警' },
    { code: 'M-OR-0602', name: 'O 型密封圈', category: '物料 02', warehouse: '原料库 A', qty: 6800, unit: '件', amount: 3.4, safe: 2000, min: 1500, max: 5000, status: '呆滞' }
  ],
  inbound: [
    { no: 'RK-261025-012', type: '采购入库', source: 'DH-261025-003 / 昆山线束', warehouse: '原料库 A', qty: 1200, amount: 18.6, date: '2026-10-25', status: '已审核' },
    { no: 'RK-261025-011', type: '采购入库', source: 'DH-261025-002 / 无锡压铸', warehouse: '原料库 A', qty: 1460, amount: 20.7, date: '2026-10-25', status: '已审核' },
    { no: 'RK-261025-009', type: '成品入库', source: 'WO-261025-009 / SD-300', warehouse: '成品库 F', qty: 120, amount: 84.0, date: '2026-10-25', status: '已审核' },
    { no: 'RK-261025-008', type: '其他入库', source: '盘盈调整', warehouse: '原料库 A', qty: 30, amount: 0.6, date: '2026-10-25', status: '待审核' },
    { no: 'RK-261024-007', type: '成品入库', source: 'WO-261024-006 / SM-80', warehouse: '成品库 F', qty: 200, amount: 104.0, date: '2026-10-24', status: '已审核' },
    { no: 'RK-261024-006', type: '采购入库', source: 'DH-261024-006 / 无锡压铸', warehouse: '原料库 A', qty: 1500, amount: 21.3, date: '2026-10-24', status: '已审核' }
  ],
  outbound: [
    { no: 'CK-261025-018', type: '销售出库', target: 'SO-261022-003 / 恒力自动化', warehouse: '成品库 F', qty: 120, amount: 84.0, date: '2026-10-25', status: '已审核' },
    { no: 'CK-261025-017', type: '销售出库', target: 'SO-261021-002 / 宁波海天', warehouse: '成品库 F', qty: 200, amount: 104.0, date: '2026-10-25', status: '已审核' },
    { no: 'LL-261025-006', type: '生产领料', target: 'WO-261025-003 / AS-01', warehouse: 'AS 线边库', qty: 320, amount: 6.8, date: '2026-10-25', status: '已审核' },
    { no: 'LL-261025-005', type: '生产领料', target: 'WO-261025-001 / MC-01', warehouse: 'AS 线边库', qty: 260, amount: 5.6, date: '2026-10-25', status: '已审核' },
    { no: 'CK-261024-016', type: '其他出库', target: '研发领用', warehouse: '备件库 S', qty: 4, amount: 0.8, date: '2026-10-24', status: '待审核' },
    { no: 'CK-261024-015', type: '销售出库', target: 'SO-261020-001 / 江苏亚威', warehouse: '成品库 F', qty: 100, amount: 112.0, date: '2026-10-24', status: '已审核' }
  ],
  transfers: [
    { no: 'DB-261025-004', from: '原料库 A', to: 'AS 线边库', material: '定子铁芯', qty: 800, date: '2026-10-25', status: '已调拨' },
    { no: 'DB-261025-003', from: '原料库 A', to: 'AS 线边库', material: '编码器', qty: 400, date: '2026-10-25', status: '配送中' },
    { no: 'DB-261024-002', from: '成品库 F', to: '客户现场', material: 'SD-300 伺服驱动器', qty: 60, date: '2026-10-24', status: '已调拨' },
    { no: 'DB-261023-001', from: '备件库 S', to: '装配车间', material: '拧紧枪备件', qty: 2, date: '2026-10-23', status: '已调拨' }
  ],
  stocktakes: [
    { no: 'PD-2610-004', warehouse: '原料库 A', planDate: '2026-10-31', counted: 0, diff: 0, status: '待盘点' },
    { no: 'PD-2610-003', warehouse: 'AS 线边库', planDate: '2026-10-31', counted: 0, diff: 0, status: '待盘点' },
    { no: 'PD-2610-002', warehouse: '成品库 F', planDate: '2026-10-25', counted: 386, diff: -2, status: '盘点中' },
    { no: 'PD-2610-001', warehouse: '备件库 S', planDate: '2026-10-20', counted: 64, diff: 0, status: '已完成' }
  ],
  alerts: [
    { material: '定子铁芯', code: 'M-ST-0801', warehouse: 'AS 线边库', current: 1240, safe: 2000, type: '低于安全库存', owner: '物料组', status: '处理中' },
    { material: '编码器', code: 'M-EC-0101', warehouse: '原料库 A', current: 820, safe: 900, type: '低于安全库存', owner: '采购部', status: '待处理' },
    { material: '产品标签纸', code: 'M-LB-0801', warehouse: '成品库 F', current: 240, safe: 300, type: '低于安全库存', owner: '包装组', status: '待处理' },
    { material: '包装泡沫', code: 'M-PK-0701', warehouse: '成品库 F', current: 960, safe: 500, type: '高于最高库存', owner: '仓储部', status: '已确认' },
    { material: 'O 型密封圈', code: 'M-OR-0602', warehouse: '原料库 A', current: 6800, safe: 2000, type: '呆滞 90 天', owner: '仓储部', status: '待处理' }
  ],
  aging: [
    { range: '0-30 天', sku: 286, amount: 986.4 },
    { range: '31-60 天', sku: 58, amount: 198.2 },
    { range: '61-90 天', sku: 30, amount: 68.6 },
    { range: '90 天以上', sku: 12, amount: 32.8 }
  ]
};

/* ---------- 供应链协同管理 ---------- */
DB.collab = {
  kpis: [
    { label: '交付准时率', value: '92.6', unit: '%', delta: '-1.2%', trend: 'down', tone: 'warn', foot: '目标 ≥ 95%', icon: 'target' },
    { label: '在途送货', value: '6', unit: '单', tone: '', foot: '今日预计到货 3 单', icon: 'truck' },
    { label: '待送检样品', value: '3', unit: '件', tone: 'warn', foot: '技术部 2 · 工程部 1', icon: 'shield' },
    { label: '交付异常', value: '5', unit: '项', tone: 'danger', foot: '逾期 3 · 质量 2', icon: 'alert' }
  ],
  deliveryPlans: [
    { no: 'JH-261025-004', supplier: '苏州精密冲压', material: '定子铁芯', qty: 4000, planDate: '2026-10-28', confirmed: '已确认', status: '执行中' },
    { no: 'JH-261025-003', supplier: '多摩川（上海）', material: '编码器', qty: 2000, planDate: '2026-10-30', confirmed: '已确认', status: '执行中' },
    { no: 'JH-261024-006', supplier: '无锡压铸', material: '端盖', qty: 1500, planDate: '2026-10-27', confirmed: '已确认', status: '执行中' },
    { no: 'JH-261021-005', supplier: '苏州精密冲压', material: '定子铁芯', qty: 2000, planDate: '2026-10-23', confirmed: '已确认', status: '已逾期' },
    { no: 'JH-261018-002', supplier: '多摩川（上海）', material: '编码器', qty: 800, planDate: '2026-10-24', confirmed: '已确认', status: '已逾期' },
    { no: 'JH-261020-007', supplier: '常州电镀', material: '表面处理件', qty: 600, planDate: '2026-10-24', confirmed: '待确认', status: '待确认' },
    { no: 'JH-261019-008', supplier: '昆山线束', material: '线束', qty: 1200, planDate: '2026-10-25', confirmed: '已确认', status: '已完成' },
    { no: 'JH-261022-009', supplier: '无锡压铸', material: '端盖', qty: 1500, planDate: '2026-10-22', confirmed: '已确认', status: '已完成' }
  ],
  shipments: [
    { no: 'SH-261025-006', supplier: '昆山线束', waybill: 'SF-8826102512', material: '线束', qty: 1200, eta: '2026-10-25 14:30', status: '已到货' },
    { no: 'SH-261025-005', supplier: '无锡压铸', waybill: 'JD-6620251088', material: '端盖', qty: 1500, eta: '2026-10-25 15:10', status: '运输中' },
    { no: 'SH-261025-004', supplier: '常州电镀', waybill: 'DB-3320251177', material: '表面处理件', qty: 300, eta: '2026-10-25 16:00', status: '运输中' },
    { no: 'SH-261024-003', supplier: '多摩川（上海）', waybill: 'GJ-1120254402', material: '编码器', qty: 800, eta: '2026-10-26 10:00', status: '已发货' },
    { no: 'SH-261024-002', supplier: '苏州精密冲压', waybill: 'SF-8826102488', material: '定子铁芯', qty: 2000, eta: '2026-10-25 09:30', status: '已到货' },
    { no: 'SH-261023-001', supplier: '无锡压铸', waybill: 'JD-6620251066', material: '端盖', qty: 1500, eta: '2026-10-24 15:00', status: '已到货' }
  ],
  samples: [
    { no: 'YP-261025-003', supplier: '宁波轴承工业', sample: '轴承 6204-ZZ', submitDate: '2026-10-25', dept: '技术部', result: '待检', status: '待审核' },
    { no: 'YP-261024-002', supplier: '常州电镀', sample: '镀锌端盖', submitDate: '2026-10-24', dept: '工程部', result: '合格', status: '已通过' },
    { no: 'YP-261023-001', supplier: '苏州精密冲压', sample: '定子铁芯样件', submitDate: '2026-10-23', dept: '技术部', result: '待复检', status: '待审核' },
    { no: 'YP-261021-004', supplier: '昆山线束', sample: '高压线束', submitDate: '2026-10-21', dept: '工程部', result: '不合格', status: '已驳回' },
    { no: 'YP-261020-003', supplier: '无锡压铸', sample: '端盖样件', submitDate: '2026-10-20', dept: '技术部', result: '合格', status: '已通过' }
  ],
  orderExec: [
    { po: 'PO-261025-004', supplier: '苏州精密冲压', ordered: 4000, arrived: 0, inTransit: 4000, exception: 0, rate: 0 },
    { po: 'PO-261024-010', supplier: '无锡压铸', ordered: 3000, arrived: 1500, inTransit: 1500, exception: 40, rate: 50 },
    { po: 'PO-261023-007', supplier: '多摩川（上海）', ordered: 2000, arrived: 0, inTransit: 2000, exception: 0, rate: 0 },
    { po: 'PO-261022-011', supplier: '昆山线束', ordered: 1200, arrived: 1200, inTransit: 0, exception: 0, rate: 100 },
    { po: 'PO-261021-006', supplier: '苏州精密冲压', ordered: 2000, arrived: 0, inTransit: 0, exception: 2000, rate: 0 },
    { po: 'PO-261019-008', supplier: '无锡压铸', ordered: 1500, arrived: 1500, inTransit: 0, exception: 20, rate: 100 }
  ],
  exceptions: [
    { no: 'YC-261025-005', time: '11:20', type: '交付逾期', material: '定子铁芯', supplier: '苏州精密冲压', qty: 2000, reason: '供应商产能不足，交付延期 2 天', owner: '李强', status: '处理中' },
    { no: 'YC-261025-004', time: '10:48', type: '交付逾期', material: '编码器', supplier: '多摩川（上海）', qty: 800, reason: '进口清关延误', owner: '王磊', status: '待确认' },
    { no: 'YC-261025-003', time: '10:12', type: '质量异常', material: '端盖', supplier: '无锡压铸', qty: 40, reason: '尺寸超差，已判定退货', owner: '刘洋', status: '处理中' },
    { no: 'YC-261025-002', time: '09:40', type: '单据异常', material: '线束', supplier: '昆山线束', qty: 1200, reason: '送货单缺失，已补录', owner: '赵敏', status: '已解决' },
    { no: 'YC-261025-001', time: '09:05', type: '质量异常', material: '表面处理件', supplier: '常州电镀', qty: 30, reason: '镀层厚度不足', owner: '刘洋', status: '待确认' },
    { no: 'YC-261024-006', time: '17:30', type: '交付逾期', material: '端盖', supplier: '无锡压铸', qty: 1500, reason: '模具维修导致延期', owner: '陈刚', status: '已解决' }
  ],
  supplierSummary: [
    { supplier: '苏州精密冲压', orders: 8, ontime: 87.5, quality: 98.2, score: 91.2, level: 'A' },
    { supplier: '无锡压铸', orders: 6, ontime: 83.3, quality: 97.1, score: 88.6, level: 'B' },
    { supplier: '多摩川（上海）', orders: 4, ontime: 75.0, quality: 99.4, score: 86.4, level: 'B' },
    { supplier: '昆山线束', orders: 5, ontime: 100, quality: 98.8, score: 96.2, level: 'A' },
    { supplier: '常州电镀', orders: 3, ontime: 66.7, quality: 95.2, score: 78.4, level: 'C' },
    { supplier: '宁波轴承工业', orders: 2, ontime: 100, quality: 99.0, score: 94.0, level: 'A' }
  ],
  workshopMaterials: [
    { material: '定子铁芯', line: 'AS-01', opening: 1600, received: 800, used: 1160, remain: 1240, status: '预警' },
    { material: '转子组件', line: 'AS-01', opening: 3200, received: 0, used: 340, remain: 2860, status: '正常' },
    { material: '端盖', line: 'AS-02', opening: 3600, received: 1500, used: 1620, remain: 3480, status: '正常' },
    { material: '编码器', line: 'AS-02', opening: 1100, received: 0, used: 280, remain: 820, status: '预警' },
    { material: '线束', line: 'AS-01', opening: 1800, received: 1200, used: 1380, remain: 1620, status: '正常' },
    { material: '轴承', line: 'MC-01', opening: 4600, received: 0, used: 400, remain: 4200, status: '正常' }
  ],
  wipInspection: [
    { product: 'SD-300 伺服驱动器', line: 'AS-01', qty: 68, waitTime: '2.5h', status: '待检验' },
    { product: 'SM-80 伺服电机', line: 'AS-02', qty: 42, waitTime: '1.8h', status: '检验中' },
    { product: 'SD-500 伺服驱动器', line: 'TS-01', qty: 26, waitTime: '0.6h', status: '待检验' },
    { product: 'CM-10 运动控制器', line: 'PK-01', qty: 18, waitTime: '3.2h', status: '待检验' },
    { product: 'SM-130 伺服电机', line: 'AS-02', qty: 32, waitTime: '1.2h', status: '正常' }
  ],
  picking: [
    { no: 'LL-261025-006', line: 'AS-01', material: '定子铁芯', qty: 320, date: '2026-10-25', status: '已领料' },
    { no: 'LL-261025-005', line: 'MC-01', material: '轴承', qty: 260, date: '2026-10-25', status: '已领料' },
    { no: 'LL-261025-004', line: 'AS-02', material: '端盖', qty: 300, date: '2026-10-25', status: '待领料' },
    { no: 'LL-261025-003', line: 'TS-01', material: '编码器', qty: 120, date: '2026-10-25', status: '已领料' },
    { no: 'LL-261025-002', line: 'AS-01', material: '线束', qty: 280, date: '2026-10-25', status: '已领料' }
  ],
  prodExceptions: [
    { no: 'SC-261025-004', time: '11:40', type: '异常物料', product: 'SD-300', qty: 8, reason: '端盖装配间隙超差，物料隔离', status: '处理中' },
    { no: 'SC-261025-003', time: '10:52', type: '异常成品', product: 'SD-500', qty: 2, reason: '绝缘测试不合格', status: '待评审' },
    { no: 'SC-261025-002', time: '09:48', type: '异常物料', product: 'SM-80', qty: 15, reason: '轴承外观锈蚀', status: '已隔离' },
    { no: 'SC-261025-001', time: '08:55', type: '异常成品', product: 'CM-10', qty: 1, reason: '标签信息错误', status: '已关闭' },
    { no: 'SC-261024-006', time: '16:20', type: '异常物料', product: 'SM-130', qty: 6, reason: '编码器通信异常', status: '已关闭' }
  ]
};

/* ---------- 存货核算 ---------- */
DB.costing = {
  kpis: [
    { label: '本月入库金额', value: '3,120', unit: '万元', delta: '+5.6%', trend: 'up', tone: '', foot: '采购 / 成品 / 其他入库', icon: 'download' },
    { label: '本月出库金额', value: '3,480', unit: '万元', delta: '+8.4%', trend: 'up', tone: '', foot: '销售 / 领料 / 其他出库', icon: 'upload' },
    { label: '期末结存', value: '1,286', unit: '万元', tone: 'ok', foot: '先进先出法核算', icon: 'warehouse' },
    { label: '待记账单据', value: '14', unit: '单', tone: 'warn', foot: '今日新增 5 单', icon: 'clipboard' }
  ],
  vouchers: [
    { no: 'JZ-261025-008', type: '采购入库', source: 'RK-261025-012', amount: 18.6, period: '2026-10', status: '已记账', time: '11:40' },
    { no: 'JZ-261025-007', type: '销售出库', source: 'CK-261025-018', amount: 84.0, period: '2026-10', status: '已记账', time: '11:20' },
    { no: 'JZ-261025-006', type: '生产领料', source: 'LL-261025-006', amount: 6.8, period: '2026-10', status: '已记账', time: '10:58' },
    { no: 'JZ-261025-005', type: '成品入库', source: 'RK-261025-009', amount: 84.0, period: '2026-10', status: '待记账', time: '10:30' },
    { no: 'JZ-261025-004', type: '其他入库', source: 'RK-261025-008', amount: 0.6, period: '2026-10', status: '待记账', time: '10:10' },
    { no: 'JZ-261024-003', type: '销售出库', source: 'CK-261024-015', amount: 112.0, period: '2026-10', status: '已记账', time: '昨日 17:20' },
    { no: 'JZ-261024-002', type: '采购入库', source: 'RK-261024-006', amount: 21.3, period: '2026-10', status: '已记账', time: '昨日 16:40' },
    { no: 'JZ-261024-001', type: '其他出库', source: 'CK-261024-016', amount: 0.8, period: '2026-10', status: '待记账', time: '昨日 15:10' }
  ],
  adjustments: [
    { no: 'TZ-261025-002', material: 'O 型密封圈', warehouse: '原料库 A', qty: 0, amount: -1.2, reason: '结存数量为 0，金额调整', status: '待审核' },
    { no: 'TZ-261025-001', material: '包装泡沫', warehouse: '成品库 F', qty: 0, amount: 0.6, reason: '盘点差异金额调整', status: '待审核' },
    { no: 'TZ-261024-003', material: '产品标签纸', warehouse: '成品库 F', qty: 0, amount: -0.3, reason: '尾差调整', status: '已记账' },
    { no: 'TZ-261023-002', material: '轴承', warehouse: '原料库 A', qty: 0, amount: 0.8, reason: '暂估回冲差异', status: '已记账' }
  ],
  reports: [
    { name: '入库汇总表', period: '2026-10', qty: 12680, amount: 3120, status: '可查询' },
    { name: '出库汇总表', period: '2026-10', qty: 13240, amount: 3480, status: '可查询' },
    { name: '存货收发存汇总表', period: '2026-10', qty: 386, amount: 1286, status: '可查询' },
    { name: '存货明细账', period: '2026-10', qty: 142, amount: 386.4, status: '可查询' }
  ],
  monthly: [
    { h: '5月', in: 2960, out: 3180 }, { h: '6月', in: 2880, out: 3060 },
    { h: '7月', in: 3140, out: 3320 }, { h: '8月', in: 3220, out: 3480 },
    { h: '9月', in: 3060, out: 3380 }, { h: '10月', in: 3120, out: 3480 }
  ],
  accounts: [
    { account: '原材料', opening: 682.4, in: 1860, out: 2140, balance: 402.4 },
    { account: '库存商品', opening: 486.2, in: 1120, out: 1180, balance: 426.2 },
    { account: '周转材料', opening: 86.4, in: 120, out: 140, balance: 66.4 },
    { account: '委托加工物资', opening: 42.8, in: 20, out: 20, balance: 42.8 },
    { account: '合计', opening: 1297.8, in: 3120, out: 3480, balance: 937.8 }
  ],
  pending: [
    { no: 'JZ-261025-005', type: '成品入库', date: '2026-10-25', amount: 84.0, reason: '等待成品检验结果' },
    { no: 'JZ-261025-004', type: '其他入库', date: '2026-10-25', amount: 0.6, reason: '等待仓储主管审核' },
    { no: 'JZ-261024-001', type: '其他出库', date: '2026-10-24', amount: 0.8, reason: '研发领用单待审批' },
    { no: 'JZ-261024-009', type: '采购入库', date: '2026-10-24', amount: 12.6, reason: '发票未到，转暂估处理' }
  ]
};

/* ---------- 生产制造管理 ---------- */
DB.manufacturing = {
  kpis: [
    { label: '集团总计划', value: '12', unit: '单', tone: '', foot: '本月已评审 10 单', icon: 'clipboard' },
    { label: '生产指令单', value: '18', unit: '单', tone: '', delta: '+2', trend: 'up', foot: '待排产 4 单', icon: 'fileText' },
    { label: '投料齐套率', value: '94.0', unit: '%', tone: 'warn', foot: '缺料工单 2 单', icon: 'package' },
    { label: '生产异常', value: '5', unit: '项', tone: 'danger', foot: '物料 3 · 成品 2', icon: 'alert' }
  ],
  groupPlan: [
    { no: 'GP-2610-012', customer: '恒力自动化（苏州）', product: 'SD-300 伺服驱动器', qty: 2600, reviewDate: '2026-10-24', planMonth: '2026-10', status: '已评审' },
    { no: 'GP-2610-011', customer: '宁波海天精工', product: 'SM-80 伺服电机', qty: 2200, reviewDate: '2026-10-23', planMonth: '2026-10', status: '已评审' },
    { no: 'GP-2610-010', customer: '江苏亚威股份', product: 'SD-500 伺服驱动器', qty: 1800, reviewDate: '2026-10-22', planMonth: '2026-10', status: '已评审' },
    { no: 'GP-2610-009', customer: '广州数控设备', product: 'CM-10 运动控制器', qty: 1200, reviewDate: '2026-10-21', planMonth: '2026-10', status: '执行中' },
    { no: 'GP-2610-008', customer: '山东临工机械', product: 'SM-130 伺服电机', qty: 900, reviewDate: '2026-10-25', planMonth: '2026-10', status: '待评审' },
    { no: 'GP-2610-007', customer: '上海电气自动化', product: 'SD-300 伺服驱动器', qty: 600, reviewDate: '2026-10-25', planMonth: '2026-10', status: '待评审' }
  ],
  instructions: [
    { no: 'SC-261025-018', groupPlan: 'GP-2610-012', product: 'SD-300 伺服驱动器', qty: 260, orderDate: '2026-10-25', planDate: '2026-10-26', status: '已排产' },
    { no: 'SC-261025-017', groupPlan: 'GP-2610-011', product: 'SM-80 伺服电机', qty: 300, orderDate: '2026-10-25', planDate: '2026-10-26', status: '已排产' },
    { no: 'SC-261025-016', groupPlan: 'GP-2610-010', product: 'SD-500 伺服驱动器', qty: 200, orderDate: '2026-10-25', planDate: '2026-10-27', status: '待排产' },
    { no: 'SC-261025-015', groupPlan: 'GP-2610-009', product: 'CM-10 运动控制器', qty: 150, orderDate: '2026-10-25', planDate: '2026-10-27', status: '待排产' },
    { no: 'SC-261024-014', groupPlan: 'GP-2610-012', product: 'SD-300 伺服驱动器', qty: 240, orderDate: '2026-10-24', planDate: '2026-10-25', status: '生产中' },
    { no: 'SC-261024-013', groupPlan: 'GP-2610-011', product: 'SM-80 伺服电机', qty: 180, orderDate: '2026-10-24', planDate: '2026-10-25', status: '生产中' }
  ],
  boms: [
    { product: 'SD-300 伺服驱动器', version: 'V3.2', items: 42, confirmed: 40, fuzzy: 2, status: '生效' },
    { product: 'SD-500 伺服驱动器', version: 'V2.8', items: 48, confirmed: 48, fuzzy: 0, status: '生效' },
    { product: 'SM-80 伺服电机', version: 'V1.9', items: 26, confirmed: 25, fuzzy: 1, status: '生效' },
    { product: 'SM-130 伺服电机', version: 'V1.6', items: 31, confirmed: 31, fuzzy: 0, status: '生效' },
    { product: 'CM-10 运动控制器', version: 'V0.9', items: 55, confirmed: 48, fuzzy: 7, status: '试产' },
    { product: 'SD-300 伺服驱动器', version: 'V3.3', items: 43, confirmed: 43, fuzzy: 0, status: '待生效' }
  ],
  schedules: [
    { line: 'MC-01', product: 'SD-300 伺服驱动器', qty: 260, start: '2026-10-25 08:00', end: '2026-10-25 20:00', status: '执行中' },
    { line: 'MC-02', product: 'SM-80 伺服电机', qty: 240, start: '2026-10-25 08:00', end: '2026-10-25 20:00', status: '执行中' },
    { line: 'AS-01', product: 'SD-300 伺服驱动器', qty: 300, start: '2026-10-25 08:00', end: '2026-10-25 20:00', status: '执行中' },
    { line: 'AS-02', product: 'SM-80 伺服电机', qty: 300, start: '2026-10-25 08:00', end: '2026-10-25 20:00', status: '执行中' },
    { line: 'AS-01', product: 'SD-500 伺服驱动器', qty: 200, start: '2026-10-26 20:00', end: '2026-10-27 02:00', status: '待执行' },
    { line: 'AS-02', product: 'CM-10 运动控制器', qty: 150, start: '2026-10-27 08:00', end: '2026-10-27 18:00', status: '待执行' }
  ],
  dailyPlans: [
    { date: '2026-10-25', product: 'SD-300 伺服驱动器', line: 'AS-01', qty: 300, feedingNo: 'TL-261025-003', status: '执行中' },
    { date: '2026-10-25', product: 'SM-80 伺服电机', line: 'AS-02', qty: 300, feedingNo: 'TL-261025-002', status: '执行中' },
    { date: '2026-10-26', product: 'SD-300 伺服驱动器', line: 'AS-01', qty: 260, feedingNo: 'TL-261026-001', status: '已排程' },
    { date: '2026-10-26', product: 'SM-80 伺服电机', line: 'AS-02', qty: 300, feedingNo: 'TL-261026-002', status: '已排程' },
    { date: '2026-10-27', product: 'SD-500 伺服驱动器', line: 'AS-01', qty: 200, feedingNo: 'TL-261027-001', status: '已排程' },
    { date: '2026-10-27', product: 'CM-10 运动控制器', line: 'AS-02', qty: 150, feedingNo: 'TL-261027-002', status: '已排程' }
  ],
  weeklyPlans: [
    { week: 'W43 (10-19 ~ 10-25)', plan: 7800, done: 7010, rate: 89.9, status: '执行中' },
    { week: 'W44 (10-26 ~ 11-01)', plan: 7600, done: 0, rate: 0, status: '已排程' },
    { week: 'W45 (11-02 ~ 11-08)', plan: 7400, done: 0, rate: 0, status: '草稿' },
    { week: 'W46 (11-09 ~ 11-15)', plan: 7200, done: 0, rate: 0, status: '草稿' }
  ],
  feeding: [
    { no: 'TL-261025-003', date: '2026-10-25', product: 'SD-300 伺服驱动器', material: '定子铁芯', qty: 320, status: '已领用' },
    { no: 'TL-261025-002', date: '2026-10-25', product: 'SM-80 伺服电机', material: '轴承', qty: 260, status: '已领用' },
    { no: 'TL-261025-001', date: '2026-10-25', product: 'SD-300 伺服驱动器', material: '端盖', qty: 300, status: '部分领用' },
    { no: 'TL-261026-001', date: '2026-10-26', product: 'SD-300 伺服驱动器', material: '定子铁芯', qty: 280, status: '待领用' },
    { no: 'TL-261026-002', date: '2026-10-26', product: 'SM-80 伺服电机', material: '编码器', qty: 300, status: '待领用' },
    { no: 'TL-261027-001', date: '2026-10-27', product: 'SD-500 伺服驱动器', material: '线束', qty: 200, status: '待领用' }
  ],
  picking: [
    { no: 'LL-261025-006', line: 'AS-01', material: '定子铁芯', qty: 320, date: '2026-10-25', status: '已领料' },
    { no: 'LL-261025-005', line: 'MC-01', material: '轴承', qty: 260, date: '2026-10-25', status: '已领料' },
    { no: 'LL-261025-004', line: 'AS-02', material: '端盖', qty: 300, date: '2026-10-25', status: '待领料' },
    { no: 'LL-261025-003', line: 'TS-01', material: '编码器', qty: 120, date: '2026-10-25', status: '已领料' },
    { no: 'LL-261025-002', line: 'AS-01', material: '线束', qty: 280, date: '2026-10-25', status: '已领料' }
  ],
  finished: [
    { no: 'RK-261025-009', product: 'SD-300 伺服驱动器', qty: 120, warehouse: '成品库 F', date: '2026-10-25', status: '已入库' },
    { no: 'RK-261025-008', product: 'SM-80 伺服电机', qty: 80, warehouse: '成品库 F', date: '2026-10-25', status: '待检验' },
    { no: 'RK-261024-007', product: 'SM-80 伺服电机', qty: 200, warehouse: '成品库 F', date: '2026-10-24', status: '已入库' },
    { no: 'RK-261024-006', product: 'SD-500 伺服驱动器', qty: 60, warehouse: '成品库 F', date: '2026-10-24', status: '已入库' },
    { no: 'RK-261023-005', product: 'CM-10 运动控制器', qty: 150, warehouse: '成品库 F', date: '2026-10-23', status: '已入库' }
  ],
  progress: [
    { order: 'SC-261025-018', product: 'SD-300 伺服驱动器', plan: 260, done: 248, stage: '机加', percent: 95, status: '正常' },
    { order: 'SC-261025-017', product: 'SM-80 伺服电机', plan: 300, done: 282, stage: '装配', percent: 94, status: '正常' },
    { order: 'SC-261025-016', product: 'SD-500 伺服驱动器', plan: 200, done: 0, stage: '待排产', percent: 0, status: '待料' },
    { order: 'SC-261025-015', product: 'CM-10 运动控制器', plan: 150, done: 0, stage: '待排产', percent: 0, status: '正常' },
    { order: 'SC-261024-014', product: 'SD-300 伺服驱动器', plan: 240, done: 226, stage: '测试', percent: 94, status: '正常' },
    { order: 'SC-261024-013', product: 'SM-80 伺服电机', plan: 180, done: 142, stage: '包装', percent: 79, status: '预警' }
  ],
  exceptions: [
    { no: 'SC-261025-004', time: '11:40', type: '异常物料', product: 'SD-300', qty: 8, reason: '端盖装配间隙超差，物料隔离', status: '处理中' },
    { no: 'SC-261025-003', time: '10:52', type: '异常成品', product: 'SD-500', qty: 2, reason: '绝缘测试不合格', status: '待评审' },
    { no: 'SC-261025-002', time: '09:48', type: '异常物料', product: 'SM-80', qty: 15, reason: '轴承外观锈蚀', status: '已隔离' },
    { no: 'SC-261025-001', time: '08:55', type: '异常成品', product: 'CM-10', qty: 1, reason: '标签信息错误', status: '已关闭' },
    { no: 'SC-261024-006', time: '16:20', type: '异常物料', product: 'SM-130', qty: 6, reason: '编码器通信异常', status: '已关闭' }
  ],
  bomChanges: [
    { no: 'EC-261025-002', product: 'SD-300 伺服驱动器', version: 'V3.2 → V3.3', change: '编码器供应商由多摩川切换为国产替代', date: '2026-10-25', status: '待评审' },
    { no: 'EC-261024-001', product: 'SM-80 伺服电机', version: 'V1.9 → V2.0', change: '端盖材质由压铸铝改为铸铁', date: '2026-10-24', status: '已生效' },
    { no: 'EC-261022-003', product: 'CM-10 运动控制器', version: 'V0.9 → V1.0', change: '主板元器件国产化', date: '2026-10-22', status: '评审中' },
    { no: 'EC-261020-002', product: 'SD-500 伺服驱动器', version: 'V2.8 → V2.9', change: '散热片结构优化', date: '2026-10-20', status: '已驳回' }
  ]
};

/* U8C 接口补充到系统集成数据 */
DB.system.interfaces = DB.system.interfaces.concat([
  { name: 'U8C 存货凭证', code: 'INT-U8C-001', direction: 'MES → U8C', protocol: 'REST', status: '运行', success: 99.96, last: '11:58:12', latency: '140ms' },
  { name: 'U8C 应付单据', code: 'INT-U8C-002', direction: 'MES → U8C', protocol: 'REST', status: '运行', success: 99.90, last: '11:20:05', latency: '186ms' },
  { name: 'U8C 应收单据', code: 'INT-U8C-003', direction: 'MES → U8C', protocol: 'REST', status: '运行', success: 99.82, last: '10:58:33', latency: '204ms' },
  { name: 'HR 组织人员同步', code: 'INT-HR-001', direction: 'HR → MES', protocol: 'REST', status: '运行', success: 99.99, last: '11:30:00', latency: '80ms' },
  { name: 'OA 审批流', code: 'INT-OA-001', direction: 'MES ↔ OA', protocol: 'REST', status: '运行', success: 99.50, last: '11:12:40', latency: '180ms' },
  { name: '供应商协同门户', code: 'INT-SUP-001', direction: '供应商 → MES', protocol: 'REST', status: '运行', success: 99.70, last: '11:48:10', latency: '220ms' },
  { name: '客户订单门户', code: 'INT-CUS-002', direction: '客户 → MES', protocol: 'REST', status: '运行', success: 99.60, last: '11:05:22', latency: '240ms' }
]);

DB.plant.code = 'ADX-GBE';
DB.plant.name = '爱达兴 · 高铂尔制造基地';
DB.plant.short = '爱达兴高铂尔';
DB.masterdata.hierarchy[0].name = '爱达兴 · 高铂尔制造基地';
DB.masterdata.hierarchy[0].meta = '2 个组织 · 3 车间 · 6 产线 · 48 工位';
DB.u8c = DB.scm.u8c;

window.DB = DB;
