-- ----------------------------
-- 制造执行 MES 字典初始化脚本
-- 说明：dict_id / dict_code 从 3000 起始，与 ry_20260417.sql 不冲突
-- ----------------------------

-- 清理已有 MES 字典，保证脚本可重复执行
delete from sys_dict_type where dict_type in (
  'mes_work_order_status','mes_operation_status','mes_demand_status','mes_plan_status',
  'mes_inspection_type','mes_inspection_result','mes_nonconformity_status','mes_disposition_type',
  'mes_equipment_status','mes_kit_status','mes_serial_status','mes_message_status',
  'mes_sync_status','mes_document_status','mes_yes_no','mes_enable_status'
);
delete from sys_dict_data where dict_type in (
  'mes_work_order_status','mes_operation_status','mes_demand_status','mes_plan_status',
  'mes_inspection_type','mes_inspection_result','mes_nonconformity_status','mes_disposition_type',
  'mes_equipment_status','mes_kit_status','mes_serial_status','mes_message_status',
  'mes_sync_status','mes_document_status','mes_yes_no','mes_enable_status'
);

-- ----------------------------
-- 字典类型
-- ----------------------------
insert into sys_dict_type values(3000, '工单状态',       'mes_work_order_status',   '0', 'admin', sysdate(), '', null, '工单状态列表');
insert into sys_dict_type values(3001, '工序任务状态',   'mes_operation_status',    '0', 'admin', sysdate(), '', null, '工序任务状态列表');
insert into sys_dict_type values(3002, '生产需求状态',   'mes_demand_status',       '0', 'admin', sysdate(), '', null, '生产需求状态列表');
insert into sys_dict_type values(3003, '生产计划状态',   'mes_plan_status',         '0', 'admin', sysdate(), '', null, '生产计划状态列表');
insert into sys_dict_type values(3004, '检验类型',       'mes_inspection_type',     '0', 'admin', sysdate(), '', null, '检验类型列表');
insert into sys_dict_type values(3005, '检验结果',       'mes_inspection_result',   '0', 'admin', sysdate(), '', null, '检验结果列表');
insert into sys_dict_type values(3006, '不合格品状态',   'mes_nonconformity_status','0', 'admin', sysdate(), '', null, '不合格品处理状态列表');
insert into sys_dict_type values(3007, '不合格品处置方式','mes_disposition_type',   '0', 'admin', sysdate(), '', null, '不合格品处置方式列表');
insert into sys_dict_type values(3008, '设备状态',       'mes_equipment_status',    '0', 'admin', sysdate(), '', null, '设备状态列表');
insert into sys_dict_type values(3009, '物料齐套状态',   'mes_kit_status',          '0', 'admin', sysdate(), '', null, '物料齐套状态列表');
insert into sys_dict_type values(3010, '产品序列号状态', 'mes_serial_status',       '0', 'admin', sysdate(), '', null, '产品序列号状态列表');
insert into sys_dict_type values(3011, '集成消息状态',   'mes_message_status',      '0', 'admin', sysdate(), '', null, '集成消息状态列表');
insert into sys_dict_type values(3012, '外部同步状态',   'mes_sync_status',         '0', 'admin', sysdate(), '', null, '外部同步状态列表');
insert into sys_dict_type values(3013, '文档版本状态',   'mes_document_status',     '0', 'admin', sysdate(), '', null, '主数据文档版本状态列表');
insert into sys_dict_type values(3014, 'MES是否',        'mes_yes_no',              '0', 'admin', sysdate(), '', null, 'MES是否列表');
insert into sys_dict_type values(3015, 'MES启用状态',    'mes_enable_status',       '0', 'admin', sysdate(), '', null, 'MES启用状态列表');

-- ----------------------------
-- 字典数据
-- ----------------------------
-- 工单状态（WorkOrderStatus）
insert into sys_dict_data values(3000, 1,  '新建',       'CREATED',           'mes_work_order_status',    '', '', 'N', '0', 'admin', sysdate(), '', null, '工单状态-新建');
insert into sys_dict_data values(3001, 2,  '已审核',     'AUDITED',           'mes_work_order_status',    '', '', 'N', '0', 'admin', sysdate(), '', null, '工单状态-已审核');
insert into sys_dict_data values(3002, 3,  '已排产',     'SCHEDULED',         'mes_work_order_status',    '', '', 'N', '0', 'admin', sysdate(), '', null, '工单状态-已排产');
insert into sys_dict_data values(3003, 4,  '已下达',     'RELEASED',          'mes_work_order_status',    '', '', 'N', '0', 'admin', sysdate(), '', null, '工单状态-已下达');
insert into sys_dict_data values(3004, 5,  '待料',       'WAIT_MATERIAL',     'mes_work_order_status',    '', '', 'N', '0', 'admin', sysdate(), '', null, '工单状态-待料');
insert into sys_dict_data values(3005, 6,  '执行中',     'EXECUTING',         'mes_work_order_status',    '', '', 'N', '0', 'admin', sysdate(), '', null, '工单状态-执行中');
insert into sys_dict_data values(3006, 7,  '暂停',       'PAUSED',            'mes_work_order_status',    '', '', 'N', '0', 'admin', sysdate(), '', null, '工单状态-暂停');
insert into sys_dict_data values(3007, 8,  '异常',       'ABNORMAL',          'mes_work_order_status',    '', '', 'N', '0', 'admin', sysdate(), '', null, '工单状态-异常');
insert into sys_dict_data values(3008, 9,  '部分完成',   'PARTIAL',           'mes_work_order_status',    '', '', 'N', '0', 'admin', sysdate(), '', null, '工单状态-部分完成');
insert into sys_dict_data values(3009, 10, '已完成',     'COMPLETED',         'mes_work_order_status',    '', '', 'N', '0', 'admin', sysdate(), '', null, '工单状态-已完成');
insert into sys_dict_data values(3010, 11, '已关闭',     'CLOSED',            'mes_work_order_status',    '', '', 'N', '0', 'admin', sysdate(), '', null, '工单状态-已关闭');
-- 工序任务状态（OperationStatus）
insert into sys_dict_data values(3011, 1,  '未开始',     'NOT_STARTED',       'mes_operation_status',     '', '', 'N', '0', 'admin', sysdate(), '', null, '工序任务状态-未开始');
insert into sys_dict_data values(3012, 2,  '已派工',     'DISPATCHED',        'mes_operation_status',     '', '', 'N', '0', 'admin', sysdate(), '', null, '工序任务状态-已派工');
insert into sys_dict_data values(3013, 3,  '执行中',     'EXECUTING',         'mes_operation_status',     '', '', 'N', '0', 'admin', sysdate(), '', null, '工序任务状态-执行中');
insert into sys_dict_data values(3014, 4,  '待检验',     'WAIT_INSPECT',      'mes_operation_status',     '', '', 'N', '0', 'admin', sysdate(), '', null, '工序任务状态-待检验');
insert into sys_dict_data values(3015, 5,  '已完成',     'COMPLETED',         'mes_operation_status',     '', '', 'N', '0', 'admin', sysdate(), '', null, '工序任务状态-已完成');
insert into sys_dict_data values(3016, 6,  '暂停',       'PAUSED',            'mes_operation_status',     '', '', 'N', '0', 'admin', sysdate(), '', null, '工序任务状态-暂停');
insert into sys_dict_data values(3017, 7,  '异常',       'ABNORMAL',          'mes_operation_status',     '', '', 'N', '0', 'admin', sysdate(), '', null, '工序任务状态-异常');
insert into sys_dict_data values(3018, 8,  '已取消',     'CANCELLED',         'mes_operation_status',     '', '', 'N', '0', 'admin', sysdate(), '', null, '工序任务状态-已取消');
-- 生产需求状态（DemandStatus）
insert into sys_dict_data values(3019, 1,  '已确认',     'CONFIRMED',         'mes_demand_status',        '', '', 'N', '0', 'admin', sysdate(), '', null, '生产需求状态-已确认');
insert into sys_dict_data values(3020, 2,  '已分解',     'DECOMPOSED',        'mes_demand_status',        '', '', 'N', '0', 'admin', sysdate(), '', null, '生产需求状态-已分解');
insert into sys_dict_data values(3021, 3,  '生产中',     'PRODUCING',         'mes_demand_status',        '', '', 'N', '0', 'admin', sysdate(), '', null, '生产需求状态-生产中');
insert into sys_dict_data values(3022, 4,  '部分完成',   'PARTIAL',           'mes_demand_status',        '', '', 'N', '0', 'admin', sysdate(), '', null, '生产需求状态-部分完成');
insert into sys_dict_data values(3023, 5,  '已完成',     'COMPLETED',         'mes_demand_status',        '', '', 'N', '0', 'admin', sysdate(), '', null, '生产需求状态-已完成');
insert into sys_dict_data values(3024, 6,  '已关闭',     'CLOSED',            'mes_demand_status',        '', '', 'N', '0', 'admin', sysdate(), '', null, '生产需求状态-已关闭');
insert into sys_dict_data values(3025, 7,  '校验失败',   'VALIDATION_FAILED', 'mes_demand_status',        '', '', 'N', '0', 'admin', sysdate(), '', null, '生产需求状态-校验失败');
-- 生产计划状态（PlanStatus）
insert into sys_dict_data values(3026, 1,  '新建',       'CREATED',           'mes_plan_status',          '', '', 'N', '0', 'admin', sysdate(), '', null, '生产计划状态-新建');
insert into sys_dict_data values(3027, 2,  '已排程',     'SCHEDULED',         'mes_plan_status',          '', '', 'N', '0', 'admin', sysdate(), '', null, '生产计划状态-已排程');
insert into sys_dict_data values(3028, 3,  '已下达',     'RELEASED',          'mes_plan_status',          '', '', 'N', '0', 'admin', sysdate(), '', null, '生产计划状态-已下达');
insert into sys_dict_data values(3029, 4,  '已关闭',     'CLOSED',            'mes_plan_status',          '', '', 'N', '0', 'admin', sysdate(), '', null, '生产计划状态-已关闭');
-- 检验类型（InspectionType）
insert into sys_dict_data values(3030, 1,  '来料检验',   'IQC',               'mes_inspection_type',      '', '', 'N', '0', 'admin', sysdate(), '', null, '检验类型-来料检验');
insert into sys_dict_data values(3031, 2,  '首件检验',   'FIRST',             'mes_inspection_type',      '', '', 'N', '0', 'admin', sysdate(), '', null, '检验类型-首件检验');
insert into sys_dict_data values(3032, 3,  '巡检',       'PATROL',            'mes_inspection_type',      '', '', 'N', '0', 'admin', sysdate(), '', null, '检验类型-巡检');
insert into sys_dict_data values(3033, 4,  '完工检验',   'FQC',               'mes_inspection_type',      '', '', 'N', '0', 'admin', sysdate(), '', null, '检验类型-完工检验');
-- 检验结果（InspectionResult）
insert into sys_dict_data values(3034, 1,  '合格',       'PASS',              'mes_inspection_result',    '', '', 'N', '0', 'admin', sysdate(), '', null, '检验结果-合格');
insert into sys_dict_data values(3035, 2,  '不合格',     'FAIL',              'mes_inspection_result',    '', '', 'N', '0', 'admin', sysdate(), '', null, '检验结果-不合格');
insert into sys_dict_data values(3036, 3,  '待判定',     'PENDING',           'mes_inspection_result',    '', '', 'N', '0', 'admin', sysdate(), '', null, '检验结果-待判定');
-- 不合格品处理状态（NonconformityStatus）
insert into sys_dict_data values(3037, 1,  '已登记',     'REGISTERED',        'mes_nonconformity_status','', '', 'N', '0', 'admin', sysdate(), '', null, '不合格品状态-已登记');
insert into sys_dict_data values(3038, 2,  '已隔离',     'ISOLATED',          'mes_nonconformity_status','', '', 'N', '0', 'admin', sysdate(), '', null, '不合格品状态-已隔离');
insert into sys_dict_data values(3039, 3,  '待判定',     'WAIT_DISPOSITION',  'mes_nonconformity_status','', '', 'N', '0', 'admin', sysdate(), '', null, '不合格品状态-待判定');
insert into sys_dict_data values(3040, 4,  '返工中',     'REWORKING',         'mes_nonconformity_status','', '', 'N', '0', 'admin', sysdate(), '', null, '不合格品状态-返工中');
insert into sys_dict_data values(3041, 5,  '返修中',     'REPAIRING',         'mes_nonconformity_status','', '', 'N', '0', 'admin', sysdate(), '', null, '不合格品状态-返修中');
insert into sys_dict_data values(3042, 6,  '已报废',     'SCRAPPED',          'mes_nonconformity_status','', '', 'N', '0', 'admin', sysdate(), '', null, '不合格品状态-已报废');
insert into sys_dict_data values(3043, 7,  '让步接收',   'CONCESSION',        'mes_nonconformity_status','', '', 'N', '0', 'admin', sysdate(), '', null, '不合格品状态-让步接收');
insert into sys_dict_data values(3044, 8,  '待复检',     'WAIT_REINSPECT',    'mes_nonconformity_status','', '', 'N', '0', 'admin', sysdate(), '', null, '不合格品状态-待复检');
insert into sys_dict_data values(3045, 9,  '已关闭',     'CLOSED',            'mes_nonconformity_status','', '', 'N', '0', 'admin', sysdate(), '', null, '不合格品状态-已关闭');
-- 不合格品处置方式（DispositionType）
insert into sys_dict_data values(3046, 1,  '返工',       'REWORK',            'mes_disposition_type',     '', '', 'N', '0', 'admin', sysdate(), '', null, '处置方式-返工');
insert into sys_dict_data values(3047, 2,  '返修',       'REPAIR',            'mes_disposition_type',     '', '', 'N', '0', 'admin', sysdate(), '', null, '处置方式-返修');
insert into sys_dict_data values(3048, 3,  '报废',       'SCRAP',             'mes_disposition_type',     '', '', 'N', '0', 'admin', sysdate(), '', null, '处置方式-报废');
insert into sys_dict_data values(3049, 4,  '让步接收',   'CONCESSION',        'mes_disposition_type',     '', '', 'N', '0', 'admin', sysdate(), '', null, '处置方式-让步接收');
insert into sys_dict_data values(3050, 5,  '退供应商',   'RETURN_SUPPLIER',   'mes_disposition_type',     '', '', 'N', '0', 'admin', sysdate(), '', null, '处置方式-退供应商');
-- 设备状态（EquipmentStatus）
insert into sys_dict_data values(3051, 1,  '运行',       'RUNNING',           'mes_equipment_status',     '', '', 'N', '0', 'admin', sysdate(), '', null, '设备状态-运行');
insert into sys_dict_data values(3052, 2,  '待机',       'STANDBY',           'mes_equipment_status',     '', '', 'N', '0', 'admin', sysdate(), '', null, '设备状态-待机');
insert into sys_dict_data values(3053, 3,  '停机',       'STOPPED',           'mes_equipment_status',     '', '', 'N', '0', 'admin', sysdate(), '', null, '设备状态-停机');
insert into sys_dict_data values(3054, 4,  '故障',       'FAULT',             'mes_equipment_status',     '', '', 'N', '0', 'admin', sysdate(), '', null, '设备状态-故障');
insert into sys_dict_data values(3055, 5,  '维护',       'MAINTENANCE',       'mes_equipment_status',     '', '', 'N', '0', 'admin', sysdate(), '', null, '设备状态-维护');
insert into sys_dict_data values(3056, 6,  '离线',       'OFFLINE',           'mes_equipment_status',     '', '', 'N', '0', 'admin', sysdate(), '', null, '设备状态-离线');
-- 物料齐套状态（KitStatus）
insert into sys_dict_data values(3057, 1,  '未计算',     'NOT_CALCULATED',    'mes_kit_status',           '', '', 'N', '0', 'admin', sysdate(), '', null, '物料齐套状态-未计算');
insert into sys_dict_data values(3058, 2,  '缺料',       'LACK',              'mes_kit_status',           '', '', 'N', '0', 'admin', sysdate(), '', null, '物料齐套状态-缺料');
insert into sys_dict_data values(3059, 3,  '部分齐套',   'PARTIAL',           'mes_kit_status',           '', '', 'N', '0', 'admin', sysdate(), '', null, '物料齐套状态-部分齐套');
insert into sys_dict_data values(3060, 4,  '已齐套',     'KITTED',            'mes_kit_status',           '', '', 'N', '0', 'admin', sysdate(), '', null, '物料齐套状态-已齐套');
insert into sys_dict_data values(3061, 5,  '已配送',     'DELIVERED',         'mes_kit_status',           '', '', 'N', '0', 'admin', sysdate(), '', null, '物料齐套状态-已配送');
insert into sys_dict_data values(3062, 6,  '部分消耗',   'PARTIAL_CONSUMED',  'mes_kit_status',           '', '', 'N', '0', 'admin', sysdate(), '', null, '物料齐套状态-部分消耗');
insert into sys_dict_data values(3063, 7,  '已消耗',     'CONSUMED',          'mes_kit_status',           '', '', 'N', '0', 'admin', sysdate(), '', null, '物料齐套状态-已消耗');
-- 产品序列号状态（SerialStatus）
insert into sys_dict_data values(3064, 1,  '已创建',     'CREATED',           'mes_serial_status',        '', '', 'N', '0', 'admin', sysdate(), '', null, '序列号状态-已创建');
insert into sys_dict_data values(3065, 2,  '在制',       'IN_PROCESS',        'mes_serial_status',        '', '', 'N', '0', 'admin', sysdate(), '', null, '序列号状态-在制');
insert into sys_dict_data values(3066, 3,  '待检验',     'WAIT_INSPECT',      'mes_serial_status',        '', '', 'N', '0', 'admin', sysdate(), '', null, '序列号状态-待检验');
insert into sys_dict_data values(3067, 4,  '合格',       'QUALIFIED',         'mes_serial_status',        '', '', 'N', '0', 'admin', sysdate(), '', null, '序列号状态-合格');
insert into sys_dict_data values(3068, 5,  '不合格',     'UNQUALIFIED',       'mes_serial_status',        '', '', 'N', '0', 'admin', sysdate(), '', null, '序列号状态-不合格');
insert into sys_dict_data values(3069, 6,  '已报废',     'SCRAPPED',          'mes_serial_status',        '', '', 'N', '0', 'admin', sysdate(), '', null, '序列号状态-已报废');
insert into sys_dict_data values(3070, 7,  '已入库',     'IN_STOCK',          'mes_serial_status',        '', '', 'N', '0', 'admin', sysdate(), '', null, '序列号状态-已入库');
insert into sys_dict_data values(3071, 8,  '已发货',     'SHIPPED',           'mes_serial_status',        '', '', 'N', '0', 'admin', sysdate(), '', null, '序列号状态-已发货');
-- 集成消息状态（MessageStatus）
insert into sys_dict_data values(3072, 1,  '已创建',     'CREATED',           'mes_message_status',       '', '', 'N', '0', 'admin', sysdate(), '', null, '集成消息状态-已创建');
insert into sys_dict_data values(3073, 2,  '已校验',     'VALIDATED',         'mes_message_status',       '', '', 'N', '0', 'admin', sysdate(), '', null, '集成消息状态-已校验');
insert into sys_dict_data values(3074, 3,  '已发送',     'SENT',              'mes_message_status',       '', '', 'N', '0', 'admin', sysdate(), '', null, '集成消息状态-已发送');
insert into sys_dict_data values(3075, 4,  '已受理',     'ACCEPTED',          'mes_message_status',       '', '', 'N', '0', 'admin', sysdate(), '', null, '集成消息状态-已受理');
insert into sys_dict_data values(3076, 5,  '已处理',     'PROCESSED',         'mes_message_status',       '', '', 'N', '0', 'admin', sysdate(), '', null, '集成消息状态-已处理');
insert into sys_dict_data values(3077, 6,  '已确认',     'CONFIRMED',         'mes_message_status',       '', '', 'N', '0', 'admin', sysdate(), '', null, '集成消息状态-已确认');
insert into sys_dict_data values(3078, 7,  '校验失败',   'VALIDATION_FAILED', 'mes_message_status',       '', '', 'N', '0', 'admin', sysdate(), '', null, '集成消息状态-校验失败');
insert into sys_dict_data values(3079, 8,  '重试中',     'RETRYING',          'mes_message_status',       '', '', 'N', '0', 'admin', sysdate(), '', null, '集成消息状态-重试中');
insert into sys_dict_data values(3080, 9,  '死信',       'DEAD_LETTER',       'mes_message_status',       '', '', 'N', '0', 'admin', sysdate(), '', null, '集成消息状态-死信');
insert into sys_dict_data values(3081, 10, '人工处理中', 'MANUAL_PROCESSING', 'mes_message_status',       '', '', 'N', '0', 'admin', sysdate(), '', null, '集成消息状态-人工处理中');
insert into sys_dict_data values(3082, 11, '已取消',     'CANCELLED',         'mes_message_status',       '', '', 'N', '0', 'admin', sysdate(), '', null, '集成消息状态-已取消');
-- 外部同步状态（SyncStatus）
insert into sys_dict_data values(3083, 1,  '待同步',     'PENDING',           'mes_sync_status',          '', '', 'N', '0', 'admin', sysdate(), '', null, '同步状态-待同步');
insert into sys_dict_data values(3084, 2,  '成功',       'SUCCESS',           'mes_sync_status',          '', '', 'N', '0', 'admin', sysdate(), '', null, '同步状态-成功');
insert into sys_dict_data values(3085, 3,  '失败',       'FAILED',            'mes_sync_status',          '', '', 'N', '0', 'admin', sysdate(), '', null, '同步状态-失败');
insert into sys_dict_data values(3086, 4,  '死信',       'DEAD_LETTER',       'mes_sync_status',          '', '', 'N', '0', 'admin', sysdate(), '', null, '同步状态-死信');
insert into sys_dict_data values(3087, 5,  '人工处理',   'MANUAL',            'mes_sync_status',          '', '', 'N', '0', 'admin', sysdate(), '', null, '同步状态-人工处理');
-- 文档版本状态（DocumentStatus）
insert into sys_dict_data values(3088, 1,  '草稿',       'DRAFT',             'mes_document_status',      '', '', 'N', '0', 'admin', sysdate(), '', null, '文档版本状态-草稿');
insert into sys_dict_data values(3089, 2,  '待审批',     'PENDING_APPROVAL',  'mes_document_status',      '', '', 'N', '0', 'admin', sysdate(), '', null, '文档版本状态-待审批');
insert into sys_dict_data values(3090, 3,  '已发布',     'PUBLISHED',         'mes_document_status',      '', '', 'N', '0', 'admin', sysdate(), '', null, '文档版本状态-已发布');
insert into sys_dict_data values(3091, 4,  '已失效',     'INVALID',           'mes_document_status',      '', '', 'N', '0', 'admin', sysdate(), '', null, '文档版本状态-已失效');
-- MES是否
insert into sys_dict_data values(3092, 1,  '是',         'Y',                 'mes_yes_no',               '', '', 'Y', '0', 'admin', sysdate(), '', null, 'MES是否-是');
insert into sys_dict_data values(3093, 2,  '否',         'N',                 'mes_yes_no',               '', '', 'N', '0', 'admin', sysdate(), '', null, 'MES是否-否');
-- MES启用状态
insert into sys_dict_data values(3094, 1,  '正常',       '0',                 'mes_enable_status',        '', '', 'Y', '0', 'admin', sysdate(), '', null, '启用状态-正常');
insert into sys_dict_data values(3095, 2,  '停用',       '1',                 'mes_enable_status',        '', '', 'N', '0', 'admin', sysdate(), '', null, '启用状态-停用');
